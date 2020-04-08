package com.imed.medcare.ui.questionnaire_treatment;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.request.MasiveAnswerTreatmentPollRequest;
import com.imed.medcare.network.response.AnswerTreatmentPollResponse;
import com.imed.medcare.network.response.ProfileResponse;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.ping;

public class QuestionnaireInteractor implements QuestionnaireContract.Interactor {

    @Override
    public void verifyLocalData(TreatmentPollQuestion treatmentPollQuestion, onVerifyLocalDataListener listener) {
        boolean found = false;
        for(AnswerPoll answerPoll: treatmentPollQuestion.getAnswerPolls()){
            if(answerPoll.isLocal()){
                found = true;
            }
        }
        if(!found) {
            listener.onVerifyLocalDataNotFound();
        }else {
            listener.onVerifyLocalDataFound();

        }
    }

    @Override
    public void deleteLocalValues(TreatmentPollQuestion treatmentPollQuestion, onDeleteLocalValuesListener listener) {

        if(treatmentPollQuestion.getAnswerPolls() != null && treatmentPollQuestion.getAnswerPolls().size()>0) {
            for (int i=0; treatmentPollQuestion.getAnswerPolls().size()>i;i++) {
                if (treatmentPollQuestion.getAnswerPolls().get(i).isLocal()) {
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    treatmentPollQuestion.getAnswerPolls().get(i).deleteFromRealm();
                    realm.commitTransaction();
                    realm.close();
                }
            }
        }

        listener.onSuccessDeleteLocalValues();
    }

    @Override
    public void getValues(int id, onGetValuesListener listener) {
        GenericRepositoryRealm<TreatmentPoll> treatmentPollGenericRepositoryRealm = new GenericRepositoryRealm<>(TreatmentPoll.class);
        treatmentPollGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        TreatmentPoll treatmentPoll = treatmentPollGenericRepositoryRealm.get(id,"id");
        if(treatmentPoll!=null){
            initAnswersList(treatmentPoll,listener);

        }else {
            listener.onError(App.getContext().getResources().getString(R.string.generic_error_message));
        }
    }

    private void initAnswersList(TreatmentPoll treatmentPoll, onGetValuesListener listener) {
        RealmList<AnswerPoll> answerPollRealmList = new RealmList<>();
        RealmList<TreatmentPollQuestion> treatmentPollQuestionRealmList = new RealmList<>() ;
        for(TreatmentPollQuestion treatmentPollQuestion :treatmentPoll.getTreatmentPollQuestionsRealmList()){
            if(treatmentPollQuestion.isShow()) {
                treatmentPollQuestionRealmList.add(treatmentPollQuestion);
            }
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        treatmentPoll.setTreatmentPollQuestionsRealmList(treatmentPollQuestionRealmList);
        realm.commitTransaction();
        realm.close();
        for(TreatmentPollQuestion treatmentPollQuestion : treatmentPollQuestionRealmList){
            if(treatmentPollQuestion.getAnswerPolls()!=null && treatmentPollQuestion.getAnswerPolls().size() != 0){
                answerPollRealmList.addAll(treatmentPollQuestion.getAnswerPolls());
            }
        }
        listener.onSuccess(treatmentPoll,answerPollRealmList);
    }

    @Override
    public void setNewAnswer(RealmList<AnswerPoll>answerPollRealmList,TreatmentPollQuestion treatmentPollQuestion,Integer choiceId, String value,String date, onSetNewAnswerListener listener) {

        if(treatmentPollQuestion.getAnswerPolls() !=null && treatmentPollQuestion.getAnswerPolls().size()>0){

            if(treatmentPollQuestion.getType() == 4 ){

                boolean newAnswer = true;
                for(AnswerPoll answerPoll : treatmentPollQuestion.getAnswerPolls()){
                    if(answerPoll.getChoiseId() == choiceId && answerPoll.getValue().equalsIgnoreCase(value)){
                        newAnswer = false;
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        answerPoll.deleteFromRealm();
                        realm.commitTransaction();
                        realm.close();
                        break;
                    }
                }
                if(newAnswer){

                    createNewAnswer(treatmentPollQuestion,choiceId,value,date);
                }
            }else {

                updateAnswer(treatmentPollQuestion.getAnswerPolls().get(0), choiceId,value,date);
            }
        }else{
            createNewAnswer(treatmentPollQuestion,choiceId,value,date);
        }
        if(treatmentPollQuestion.getType() == 4 || treatmentPollQuestion.getType() == 5 ){
            listener.notifyDataSetChangedAdapter();
        }


    }

    private void updateAnswer(AnswerPoll answerPoll, Integer choiceId, String value,String date){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        answerPoll.setChoiseId(choiceId);
        answerPoll.setValue(value);
        answerPoll.setScore(100);
        answerPoll.setDate(date);
        answerPoll.setLocal(true);
        realm.commitTransaction();
    }

    private void createNewAnswer(TreatmentPollQuestion treatmentPollQuestion, Integer choiceId, String value,String date){
        GenericRepositoryRealm<AnswerPoll> answerPollGenericRepositoryRealm = new GenericRepositoryRealm<>(AnswerPoll.class);
        answerPollGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        RealmResults<AnswerPoll> answerPollRealmResults = answerPollGenericRepositoryRealm.getAll();
        int newId = 0;
        for(AnswerPoll answerPoll:answerPollRealmResults){
            if(answerPoll.getId()>newId){
                newId = answerPoll.getId();
            }
        }
        newId++;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        treatmentPollQuestion.getAnswerPolls().add(new AnswerPoll(newId,choiceId,value,100,date,true) );
        realm.commitTransaction();
    }

    @Override
    public void postPollResponse(TreatmentPoll treatmentPoll, final onPostPollResponseListener listener) {

        MasiveAnswerTreatmentPollRequest masiveAnswerTreatmentPollRequest = new MasiveAnswerTreatmentPollRequest(treatmentPoll.getTreatmentPollQuestionsRealmList());
        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());

        Call<AnswerTreatmentPollResponse> postLoginUser = RestClient.get().postAnswerTreatmentPollResponse(treatmentPoll.getIdTreatment(),treatmentPoll.getPollPeriodId(),App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken(),masiveAnswerTreatmentPollRequest);
        postLoginUser.enqueue(new Callback<AnswerTreatmentPollResponse>() {
            @Override
            public void onResponse(Call<AnswerTreatmentPollResponse> call, Response<AnswerTreatmentPollResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok") ) {
                        getProfile(listener);
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.onErrorPostPollResponse(response.body().getMessage());
                        } else {
                            listener.onErrorPostPollResponse(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.onErrorPostPollResponse(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<AnswerTreatmentPollResponse> call, Throwable t) {
                Log.e("POST POLLTREATMENT FAIL", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.onErrorPostPollResponse(ping());

            }
        });
    }



    void getProfile(final onPostPollResponseListener listener){
        final GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());

        Call<ProfileResponse> postProfile = RestClient.get().getProfile(App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken());
        postProfile.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {

                            User.saveToRealm(response.body(),userGenericRepositoryRealm.getFirst().getAccessToken(),userGenericRepositoryRealm.getFirst().getRefreshToken(),userGenericRepositoryRealm.getFirst().getMessageDate(),userGenericRepositoryRealm.getFirst().isMessageReaded(), Realm.getDefaultInstance());
                            listener.onSuccessPostPollResponse();
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.onErrorPostPollResponse(response.body().getMessage());
                        } else {
                            listener.onErrorPostPollResponse(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.onErrorPostPollResponse(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                listener.onErrorPostPollResponse(App.getContext().getString(R.string.generic_error_message));
            }
        });
    }

}
