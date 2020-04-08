package com.imed.medcare.ui.manage_prescription;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.HistoryPrescription;
import com.imed.medcare.model.Pillbox;
import com.imed.medcare.model.Prescription;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.User;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.request.MasiveAttachmentRequest;
import com.imed.medcare.network.response.HistoryPrescriptionResponse;
import com.imed.medcare.network.response.MasiveAttachmentResponse;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.YearMonthDayString;
import static com.imed.medcare.utils.MedcareUtils.getCosetsTime;

public class ManagePrescriptionInteractorImpl implements ManagePrescriptionContract.Interactor{
    @Override
    public RealmList<Pillbox> getListPillbox() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Pillbox> pillboxRealmResults = realm.where(Pillbox.class).findAll();
        RealmList<Pillbox> pillboxRealmList = new RealmList<>();
        pillboxRealmList.addAll(pillboxRealmResults);
        return pillboxRealmList;
    }

    @Override
    public void managePrescription(RealmList<Pillbox> pillboxRealmList, String date, final String taken, String reason, final onResultManagePrescription listener) {

        Realm realm = Realm.getDefaultInstance();
        final User user = realm.where(User.class).findFirst();
        Prescription prescription = null;
        if(pillboxRealmList.size() == 1) {
            prescription = realm.where(Prescription.class).equalTo("id", pillboxRealmList.get(0).getIdPrescription()).findFirst();
        }
        MasiveAttachmentRequest attachmentRequest = new MasiveAttachmentRequest(pillboxRealmList,date,taken,reason);
        Call<MasiveAttachmentResponse> getTreatment = RestClient.get().postMassiveAttachment(App.getContext().getString(R.string.API_KEY),user.getAccessToken(),attachmentRequest);
        final Prescription finalPrescription = prescription;
        getTreatment.enqueue(new Callback<MasiveAttachmentResponse>() {
            @Override
            public void onResponse(Call<MasiveAttachmentResponse> call, Response<MasiveAttachmentResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        String result;
                        if(finalPrescription != null) {
                            if (taken.equalsIgnoreCase("1")) {
                                result = "Tomado exitosamente " + finalPrescription.getName();
                            } else {
                                result = "Omitido exitosamente " + finalPrescription.getName();
                            }
                            getHistoryPrescription(result, listener, user.getAccessToken());
                        }else {
                            getHistoryPrescription("Éxito al cargar los datos", listener, user.getAccessToken());
                        }
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.showResult(response.body().getMessage());
                        } else {
                            listener.showResult(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.showResult(App.getContext().getString(R.string.generic_error_message));
                }
            }
            @Override
            public void onFailure(Call<MasiveAttachmentResponse> call, Throwable t) {
                listener.showResult(App.getContext().getString(R.string.generic_error_message));

            }
        });
    }

    private void getHistoryPrescription(final String result , final onResultManagePrescription listener, final String accessToken){

        Realm realm = Realm.getDefaultInstance();
        int idTreatment = realm.where(Treatment.class).findFirst().getId();
        Calendar dateEnd = Calendar.getInstance();
        Calendar dateStart = Calendar.getInstance();
        dateStart.add(Calendar.DAY_OF_MONTH, -30);
        String dateEndString = YearMonthDayString(dateEnd.getTime());
        String dateStartString = YearMonthDayString(dateStart.getTime());
        Call<HistoryPrescriptionResponse> postProfile = RestClient.get().getHistoryPrescription(App.getContext().getString(R.string.API_KEY),accessToken,idTreatment,dateStartString,dateEndString);
        postProfile.enqueue(new Callback<HistoryPrescriptionResponse>() {
            @Override
            public void onResponse(Call<HistoryPrescriptionResponse> call, Response<HistoryPrescriptionResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null) {
                        if (response.body().getStatus().equalsIgnoreCase("ok")) {
                            HistoryPrescription.saveToRealm(response.body(), Realm.getDefaultInstance());
                            listener.showResult(result);
                            getCosetsTime();
                        }else {
                            listener.showResult(response.body().getMessage());
                        }

                    }else {
                        listener.showResult(response.body().getMessage());
                    }
                }else{
                    listener.showResult(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<HistoryPrescriptionResponse> re, Throwable t) {
                listener.showResult(App.getContext().getString(R.string.generic_error_message));
            }
        });
    }
}
