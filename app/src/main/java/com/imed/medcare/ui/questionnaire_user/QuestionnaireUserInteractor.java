package com.imed.medcare.ui.questionnaire_user;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.User;
import com.imed.medcare.model.UserPoll;
import com.imed.medcare.model.UserPollQuestion;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.request.MasiveAnswerUserPollRequest;
import com.imed.medcare.network.response.AnswerUserPollResponse;
import com.imed.medcare.network.response.ProfileResponse;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.ping;

public class QuestionnaireUserInteractor implements QuestionnaireUserContract.Interactor {

    @Override
    public void verifyLocalData(UserPollQuestion userPollQuestion, onVerifyLocalDataListener listener) {
        boolean found = false;
        if(userPollQuestion !=null) {
            for (AnswerPoll answerPoll : userPollQuestion.getAnswerPolls()) {
                if (answerPoll.isLocal()) {
                    found = true;
                }
            }
        }else {
            listener.onVerifyLocalDataNotFound();
        }
        if(!found) {
            listener.onVerifyLocalDataNotFound();
        }else {
            listener.onVerifyLocalDataFound();

        }
    }

    @Override
    public void deleteLocalValues(UserPollQuestion userPollQuestion) {

        if(userPollQuestion.getAnswerPolls() != null && userPollQuestion.getAnswerPolls().size()>0) {
            for (int i=0; userPollQuestion.getAnswerPolls().size()>i;i++) {
                if (userPollQuestion.getAnswerPolls().get(i).isLocal()) {
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    userPollQuestion.getAnswerPolls().get(i).deleteFromRealm();
                    realm.commitTransaction();
                    realm.close();
                }
            }
        }

    }

    @Override
    public void getValues(int id, onGetValuesListener listener) {

        GenericRepositoryRealm<UserPoll> userPollGenericRepositoryRealm = new GenericRepositoryRealm<>(UserPoll.class);
        userPollGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        UserPoll UserPoll = userPollGenericRepositoryRealm.getFirst();
        if(UserPoll!=null){
            initAnswersList(UserPoll,listener);

        }else {
            listener.onError(App.getContext().getResources().getString(R.string.generic_error_message));
        }
    }

    private void initAnswersList(UserPoll UserPoll, onGetValuesListener listener) {
        RealmList<AnswerPoll> answerPollRealmList = new RealmList<>();
        RealmList<UserPollQuestion> UserPollQuestionRealmList = UserPoll.getuserPollQuestionsRealmList();
        for(UserPollQuestion UserPollQuestion : UserPollQuestionRealmList){
            if(UserPollQuestion.getAnswerPolls()!=null && UserPollQuestion.getAnswerPolls().size() != 0){
                answerPollRealmList.addAll(UserPollQuestion.getAnswerPolls());
            }
        }
        listener.onSuccess(UserPoll,answerPollRealmList);
    }

    @Override
    public void setNewAnswer(RealmList<AnswerPoll>answerPollRealmList,UserPollQuestion UserPollQuestion,Integer choiceId, String value,String date, onSetNewAnswerListener listener) {

        if(UserPollQuestion.getAnswerPolls() !=null && UserPollQuestion.getAnswerPolls().size()>0){

            if(UserPollQuestion.getType() == 4 ){

                boolean newAnswer = true;
                for(AnswerPoll answerPoll : UserPollQuestion.getAnswerPolls()){
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
                    createNewAnswer(UserPollQuestion,choiceId,value);
                }
            }else {

                updateAnswer(UserPollQuestion.getAnswerPolls().get(0), choiceId,value,date);
            }
        }else{
            createNewAnswer(UserPollQuestion,choiceId,value);
        }
        if(UserPollQuestion.getType() == 4 || UserPollQuestion.getType() == 5){
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

    private void createNewAnswer(UserPollQuestion UserPollQuestion, Integer choiceId, String value){
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
        UserPollQuestion.getAnswerPolls().add(new AnswerPoll(newId,choiceId,value,100,"",true) );
        realm.commitTransaction();
    }

    @Override
    public void postPollResponse(UserPoll userPoll, final onPostPollResponseListener listener) {
        MasiveAnswerUserPollRequest masiveAnswerUserPollRequest = new MasiveAnswerUserPollRequest(userPoll.getuserPollQuestionsRealmList());
        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        boolean allResponded = true;
        if(userPoll.getuserPollQuestionsRealmList() != null) {
            for (UserPollQuestion userPollQuestion : userPoll.getuserPollQuestionsRealmList()) {
                if (userPollQuestion == null) {
                    allResponded = false;
                }else {
                    if(userPollQuestion.getAnswerPolls() == null){
                        allResponded = false;
                    }else {
                        if(userPollQuestion.getAnswerPolls().size() == 0){
                            allResponded = false;
                        }
                    }
                }
            }
        }else {
            allResponded = false;
        }



        Call<AnswerUserPollResponse> postLoginUser = RestClient.get().postAnswerUserPollResponse(userPoll.getPollPeriodId(),App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken(),masiveAnswerUserPollRequest);
        final boolean finalAllResponded = allResponded;
        postLoginUser.enqueue(new Callback<AnswerUserPollResponse>() {
            @Override
            public void onResponse(Call<AnswerUserPollResponse> call, Response<AnswerUserPollResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok") ) {


                        getProfile(listener, finalAllResponded);
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
            public void onFailure(Call<AnswerUserPollResponse> call, Throwable t) {
                Log.e("POST POLLTREATMENT FAIL", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.onErrorPostPollResponse(ping());

            }
        });
    }

    void getProfile(final onPostPollResponseListener listener, final boolean allResponded){
        final GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());

        Call<ProfileResponse> postProfile = RestClient.get().getProfile(App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken());
        postProfile.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {

                        User.saveToRealm(response.body(),userGenericRepositoryRealm.getFirst().getAccessToken(),userGenericRepositoryRealm.getFirst().getRefreshToken(), userGenericRepositoryRealm.getFirst().getMessageDate(),userGenericRepositoryRealm.getFirst().isMessageReaded(), Realm.getDefaultInstance());
                        listener.onSuccessPostPollResponse(allResponded);
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