package com.imed.medcare.ui.menu_questionnaire_medic;

import com.imed.medcare.model.MedicPollQuestion;
import com.imed.medcare.model.Treatment;

import io.realm.RealmResults;

public interface MenuQuestionnaireMedicContract {

    interface View{
        void showLoader();
        void showError(String message);
        void showQuestionnaireMedic(RealmResults<MedicPollQuestion> questionnaireMedicRealmList, Treatment treatment);
    }

    interface Presenter{
        void getQuestionnaireMedic(int idTreatment);
    }

    interface Interactor{
        void getQuestionnaireMedic(int idTreatment, MenuQuestionnaireMedicListener listener);
        interface MenuQuestionnaireMedicListener{
            void success(RealmResults<MedicPollQuestion> medicPollQuestionRealmList, Treatment treatment);
            void error(String message);
        }
    }

}
