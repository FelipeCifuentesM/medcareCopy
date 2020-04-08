package com.imed.medcare.ui.treatment.treatmentDetail;

import com.imed.medcare.model.Treatment;

/**
 * Created by Ramiro on 06-06-2018.
 */

public interface TreatmentDetailContract {

    interface View{
        void setTreatment(Treatment treatment);
    }

    interface Presenter{
        void getTreatment(int id);
    }
}
