package com.imed.medcare.ui.history_list;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.User;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.response.HistoryResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryListInteractor implements HistoryListContract.Interactor {

    @Override
    public void getListHistory(boolean isFromAttachment,final HistoryListContract.InteractorOutputs outputs) {

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        Treatment treatment = realm.where(Treatment.class).findFirst();
        realm.close();
        Call<HistoryResponse> postLoginUser;
        if(!isFromAttachment) {
            postLoginUser = RestClient.get().getHistoryTreatmentListResponse(treatment.getId(), App.getContext().getString(R.string.API_KEY), user.getAccessToken());
        }else {
            postLoginUser = RestClient.get().getHistoryListResponse(App.getContext().getString(R.string.API_KEY), user.getAccessToken());
        }
        postLoginUser.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if (response.isSuccessful()) {

                    assert response.body() != null;
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        outputs.showList(response.body().getData().get(0));
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
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                Log.e("HISTORY FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                outputs.showMessage(App.getContext().getString(R.string.generic_error_message));


            }
        });

    }


}
