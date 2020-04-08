package com.imed.medcare.ui.responded_polls;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.response.RespondedPollResponse;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespondendPollsInteractor implements RespondedPollsContract.Interactor {

    @Override
    public void getPoll(int id, final RespondedPollsContract.OutputInteractor outputs) {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        realm.close();

        Call<RespondedPollResponse> postLoginUser = RestClient.get().getHistoryResponse(id,App.getContext().getString(R.string.API_KEY), user.getAccessToken());
        postLoginUser.enqueue(new Callback<RespondedPollResponse>() {
            @Override
            public void onResponse(Call<RespondedPollResponse> call, Response<RespondedPollResponse> response) {
                if (response.isSuccessful()) {

                    assert response.body() != null;
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        outputs.removeHiddenPoll(response.body().getData().get(0));
                    } else {
                        if (response.body().getMessage() != null) {
                            outputs.showMessage(response.body().getMessage());
                        } else {
                            outputs.showMessage(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    outputs.showMessage(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<RespondedPollResponse> call, Throwable t) {
                Log.e("HISTORY FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                outputs.showMessage(App.getContext().getString(R.string.generic_error_message));


            }
        });

    }

    @Override
    public void removeHiddenPoll(List<RespondedPollResponse.DataBean> listRespondedPolls, RespondedPollsContract.OutputInteractor output) {

        List<RespondedPollResponse.DataBean> listRespondedPollsCopy = new ArrayList<>();

        for(int i = 0;  i<listRespondedPolls.size() ; i++){
            if(listRespondedPolls.get(i).getShow() != null && listRespondedPolls.get(i).getShow()){
                listRespondedPollsCopy.add(listRespondedPolls.get(i));
            }
        }
        output.ShowPoll(listRespondedPollsCopy);
    }

}
