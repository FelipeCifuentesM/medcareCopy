package com.imed.medcare.ui.home.treatmentList;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Professional;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.response.InvitationResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.ping;

/**
 * Created by Ramiro on 30-05-2018.
 */

public class TreatmentListInteractor implements TreatmentListContract.Interactor {

    private TreatmentListContract.Presenter treatmentListPresenter;

    public TreatmentListInteractor(TreatmentListContract.Presenter treatmentListPresenter) {
        this.treatmentListPresenter = treatmentListPresenter;
    }



    @Override
    public void acceptInvitation(Professional professional, final AcceptInvitationListener listener) {
        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());

        Call<InvitationResponse> postLoginUser = RestClient.get().postInvitation(professional.getId(),App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken());
        postLoginUser.enqueue(new Callback<InvitationResponse>() {
            @Override
            public void onResponse(Call<InvitationResponse> call, Response<InvitationResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null && response.body().getStatus().equals("ok")) {
                        listener.sendStatus("Invitación aceptada con exito");
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.sendStatus(response.body().getMessage());
                        } else {
                            listener.sendStatus(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.sendStatus(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<InvitationResponse> call, Throwable t) {
                Log.e("SEND INVITATION FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.sendStatus(ping());

            }
        });

    }
}
