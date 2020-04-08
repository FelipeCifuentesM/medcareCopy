package com.imed.medcare.ui.treatment_poll_menu;

import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;
import com.imed.medcare.model.repository.GenericRepositoryRealm;

import io.realm.Realm;
import io.realm.RealmResults;

public class PollMenuInteractor implements  PollMenuContract.PollMenuInteractor {

    @Override
    public void getData(int id, PollMenuListener listener) {

        Realm realm = Realm.getDefaultInstance();
        GenericRepositoryRealm<TreatmentPoll> treatmentPollGenericRepositoryRealm = new GenericRepositoryRealm<>(TreatmentPoll.class);
        treatmentPollGenericRepositoryRealm.setRealm(realm);
        TreatmentPoll treatmentPoll = treatmentPollGenericRepositoryRealm.get(id,"id");


        RealmResults <TreatmentPollQuestion> treatmentPollQuestionRealmResults = realm.where(TreatmentPollQuestion.class)
                                                                                      .equalTo("show", true)
                                                                                      .findAll()
                                                                                      .sort("questionOrder");

        listener.onSuccess(treatmentPoll,treatmentPollQuestionRealmResults);
    }
}
