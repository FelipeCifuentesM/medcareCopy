package com.imed.medcare.ui.user_poll_menu;

import com.imed.medcare.model.UserPoll;
import com.imed.medcare.network.response.UserPollResponse;

public interface UserPollMenuContract {

    interface UserPollMenuView{
        void setData(UserPoll userPoll);
        void showError(String message);
        void showLoading();
    }

    interface userPollMenuPresenter{
        void getData();
    }

    interface userPollMenuInteractor{
        void getData(onUserPollMenuListener listener);

        interface onUserPollMenuListener{
            void onUserPollMenuSuccess(UserPollResponse.DataBean dataBean);
            void onUserPollMenuError(String message);
        }
    }
}
