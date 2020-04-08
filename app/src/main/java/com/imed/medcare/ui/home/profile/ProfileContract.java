package com.imed.medcare.ui.home.profile;

import com.imed.medcare.model.User;

/**
 * Created by Ramiro on 09-05-2018.
 */

public interface ProfileContract {

    interface View {
    }

    interface Presenter {
        User getProfile();
    }


}
