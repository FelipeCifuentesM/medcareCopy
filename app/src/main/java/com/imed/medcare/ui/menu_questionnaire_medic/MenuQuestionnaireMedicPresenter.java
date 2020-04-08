package com.imed.medcare.ui.menu_questionnaire_medic;

import com.imed.medcare.model.MedicPollQuestion;
import com.imed.medcare.model.Treatment;

import io.realm.RealmResults;

public class MenuQuestionnaireMedicPresenter implements  MenuQuestionnaireMedicContract.Presenter, MenuQuestionnaireMedicContract.Interactor.MenuQuestionnaireMedicListener{

    MenuQuestionnaireMedicContract.View view;
    MenuQuestionnaireMedicContract.Interactor interactor;

    public MenuQuestionnaireMedicPresenter(MenuQuestionnaireMedicContract.View view){
        this.view = view;
        interactor = new MenuQuestionnaireMedicInteractor();
    }

    @Override
    public void getQuestionnaireMedic(int idTreatment) {
        view.showLoader();
        interactor.getQuestionnaireMedic(idTreatment,this);
    }

    @Override
    public void success(RealmResults<MedicPollQuestion> medicPollQuestionRealmList, Treatment treatment) {
        view.showLoader();
        view.showQuestionnaireMedic(medicPollQuestionRealmList, treatment);
    }

    @Override
    public void error(String message) {
        view.showLoader();
        view.showError(message);
    }
}
