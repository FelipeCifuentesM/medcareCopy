package com.imed.medcare.ui.home;

import com.imed.medcare.model.Treatment;

/**
 * Created by Ramiro on 26-02-2018.
 */

public interface HomeContract {

    interface View{

        void showTreatment(Treatment treatment);

        void showProfile();

        void showPersonalHabit();

        void showPersonalMedic();

        void showPersonalProfile();
    }

    interface Presenter{


    }

    interface Interactor{

    }
}
