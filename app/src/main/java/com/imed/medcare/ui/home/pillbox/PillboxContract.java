package com.imed.medcare.ui.home.pillbox;


import com.imed.medcare.model.Pillbox;
import com.imed.medcare.model.PillboxHeader;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ramiro on 15-05-2018.
 */

public interface PillboxContract {

    interface View{
        void closeBottomSheet();
        void showBottomSheet(int bottomSheetType, Pillbox pillbox);
        void showMedicines(ArrayList<PillboxHeader> medicines);
        void showTimePickerDialog(int idPrescription,String date, String time, String dose, String responseDate, String responseDose, Integer taken);
        void showUpdatedDate(String dateUpdated);
        void setPrescriptionAttachment(int idPrescription,String date, String time, String dose, String responseDate, String responseTime, String responseDose,Integer reason , Integer taken);
        void showManagePrescription();
        void manageLoader();
        void showResult(String message);
    }

    interface Presenter{
        void getMedicinesByDay(Calendar date);
        void showMedicines(ArrayList<PillboxHeader> results);
        void updateDate(Calendar instance);
        void showUpdatedDate(String dateUpdated);
        void setPrescriptionAttachment(int idPrescription,String date, String time, String dose, String responseDate, String responseTime, String responseDose, Integer reason, Integer taken);
    }

    interface Interactor{
        void getMedicinesByDay(Calendar date);
        void updateDate(Calendar calendar);
        void setPrescriptionAttachment(int idPrescription,String date, String time, String dose, String responseDate, String responseTime, String responseDose, Integer reason, Integer taken,onPrescriptionAttachment listeneer);
        interface onPrescriptionAttachment {
            void showResult(String message);
        }
    }
}
