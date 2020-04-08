package com.imed.medcare.ui.response_personal_questions;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.PersonalOptions;
import com.imed.medcare.model.PersonalProfile;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClientLogin;
import com.imed.medcare.network.request.PersonalProfileRequest;
import com.imed.medcare.network.response.PersonalProfileSetAnswerResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.ping;

public class ResponsePersonalQuestionsInteractorImpl implements ResponsePersonalQuestionsContract.Interactor{

    @Override
    public PersonalProfile getData(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(PersonalProfile.class).equalTo("id",id).findFirst();
    }

    @Override
    public void setData(final PersonalProfile personalProfile, int typeView ,final onSetDataListener listener) {
        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        PersonalProfileRequest personalProfileRequest = new PersonalProfileRequest(personalProfile);
        Call<PersonalProfileSetAnswerResponse> postLoginUser;
        if(typeView == 1){
            postLoginUser = RestClientLogin.get().postPersonalProfileSetAnswerResponse(App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken(),personalProfileRequest);
        }else if(typeView == 3){
            postLoginUser = RestClientLogin.get().postPersonalHabitsSetAnswerResponse(App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken(),personalProfileRequest);
        }else {
            postLoginUser = RestClientLogin.get().postPersonalMedicSetAnswerResponse(App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken(),personalProfileRequest);
        }
        postLoginUser.enqueue(new Callback<PersonalProfileSetAnswerResponse>() {
            @Override
            public void onResponse(Call<PersonalProfileSetAnswerResponse> call, Response<PersonalProfileSetAnswerResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("success") ) {
                        Realm realm = Realm.getDefaultInstance();
                        PersonalProfile personalProfileLocal = realm.where(PersonalProfile.class).equalTo("id", personalProfile.getId()).findFirst();
                        realm.beginTransaction();
                        personalProfileLocal.setCurrenValue(personalProfile.getCurrenValue());
                        personalProfileLocal.setCurrentName(personalProfile.getCurrentName());
                        realm.commitTransaction();
                        realm.close();
                        listener.showResult("Datos cargados exitosamente");
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.showResult("Error al cargar los datos");
                        } else {
                            listener.showResult("Error al cargar los datos");                        }
                    }
                }else{
                    listener.showResult("Error al cargar los datos");
                }
            }

            @Override
            public void onFailure(Call<PersonalProfileSetAnswerResponse> call, Throwable t) {
                listener.showResult(ping());
            }
        });
    }

    @Override
    public void setAnswer(String value, String choiceId, PersonalProfile personalProfile, onSetAnswerListener listener) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for(PersonalOptions personalOptions :personalProfile.getPersonalOptionsRealmList()){

            if(personalProfile.getType() == 2){
                personalOptions.setSelected(choiceId.equalsIgnoreCase(String.valueOf(personalOptions.getId())));
            } else if(personalProfile.getType() == 3){
                if (choiceId.equalsIgnoreCase(String.valueOf(personalOptions.getId()))) {
                    boolean isSelected= !personalOptions.isSelected();
                    personalOptions.setSelected(isSelected);
                }
            }else {
                personalProfile.setCurrenValue(null);
            }
        }
        personalProfile.setCurrentName(value);

        realm.commitTransaction();
        realm.close();
        listener.notifyDataSetChangedAdapter();
    }
}
