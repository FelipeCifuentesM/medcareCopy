package com.imed.medcare.ui.home.pillbox;

import com.imed.medcare.model.PillboxHeader;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ramiro on 15-05-2018.
 */

public class PillboxPresenter implements PillboxContract.Presenter,PillboxContract.Interactor.onPrescriptionAttachment {

    private PillboxContract.View pillboxView;
    private PillboxContract.Interactor pillboxInteractor;

    public PillboxPresenter(PillboxContract.View pillboxFragment) {
        this.pillboxView = pillboxFragment;
        pillboxInteractor = new PillboxInteractor(this);
    }

    @Override
    public void getMedicinesByDay(Calendar date) {
        pillboxInteractor.getMedicinesByDay(date);
    }

    @Override
    public void showMedicines(ArrayList<PillboxHeader> results) {
        pillboxView.showMedicines(results);
    }

    @Override
    public void updateDate(Calendar calendar) {
        pillboxInteractor.updateDate(calendar);
    }

    @Override
    public void showUpdatedDate(String dateUpdated) {
        pillboxView.showUpdatedDate(dateUpdated);
    }

    @Override
    public void setPrescriptionAttachment(int idPrescription,String date, String time, String dose, String responseDate, String responseTime, String responseDose, Integer reason, Integer taken) {
        pillboxView.manageLoader();
        pillboxInteractor.setPrescriptionAttachment(idPrescription,date,time,dose,responseDate,responseTime,responseDose,reason,taken,this);
    }

    @Override
    public void showResult(String message) {
        pillboxView.showResult(message);
    }


}
