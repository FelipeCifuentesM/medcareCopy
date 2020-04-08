package com.imed.medcare.ui.home.pillbox;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.HistoryPrescription;
import com.imed.medcare.model.Pillbox;
import com.imed.medcare.model.PillboxHeader;
import com.imed.medcare.model.Prescription;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.User;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.request.AttachmentRequest;
import com.imed.medcare.network.response.AttachmentResponse;
import com.imed.medcare.network.response.HistoryPrescriptionResponse;
import com.imed.medcare.utils.MedcareUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.YearMonthDayDate;
import static com.imed.medcare.utils.MedcareUtils.YearMonthDayString;
import static com.imed.medcare.utils.MedcareUtils.getCosetsTime;
import static com.imed.medcare.utils.MedcareUtils.getPillboxSortedbyTime;
import static com.imed.medcare.utils.MedcareUtils.ping;
import static com.imed.medcare.utils.MedcareUtils.setDifferenceTime;

/**
 * Created by Ramiro on 15-05-2018.
 */

public class PillboxInteractor implements PillboxContract.Interactor{
    private PillboxContract.Presenter pillboxPresenter;

    public PillboxInteractor(PillboxContract.Presenter pillboxPresenter) {
        this.pillboxPresenter = pillboxPresenter;
    }


    @Override
    public void getMedicinesByDay(Calendar calendar) {

        final ArrayList<Pillbox> pillboxList = getPillboxSortedbyTime(calendar, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dateSelected = sdf.format(calendar.getTime());
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        Date dateInput = YearMonthDayDate(YearMonthDayString(calendar.getTime()));
        Date twentyFourOclock = new Date();
        Date sixOclock = new Date();
        Date nineteentyOclock = new Date();
        Date twelveOclock = new Date();
        Date rightNownowOclock = new Date();
        Date twentyThreeFiftyNineOclock = new Date();
        boolean haveMoring = false;
        boolean haveEarlyMoring = false;
        boolean haveAfternoon = false;
        boolean haveNight = false;

        Calendar rightNowCalendar = Calendar.getInstance();
        Date dateNow = YearMonthDayDate(YearMonthDayString(rightNowCalendar.getTime()));
        int hour = rightNowCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = rightNowCalendar.get(Calendar.MINUTE);
        int sec = rightNowCalendar.get(Calendar.SECOND);

        try{
            rightNownowOclock = df.parse(hour+":"+minute+":"+sec);
            twentyFourOclock = df.parse("00:00:00");
            twentyThreeFiftyNineOclock = df.parse("23:59:59");
            sixOclock = df.parse("6:00:00");
            nineteentyOclock = df.parse("19:00:00");
            twelveOclock = df.parse("12:00:00");
        }catch (Exception e){

        }
        final ArrayList<PillboxHeader> pillboxHeaderArrayList = new ArrayList<>();

        for (int i = 0 ; i<pillboxList.size();i++) {
            Date d = new Date();
            try {
                d = df.parse(pillboxList.get(i).getTime());
            }catch (Exception e){
                Log.e("Error", "date parcer");
            }

            if(dateInput.getTime() < dateNow.getTime() ||(rightNownowOclock.getTime() > d.getTime() && dateInput.getTime() == dateNow.getTime())){
                pillboxList.get(i).setOk(false);
                String datePrescriptionString  = dateSelected +" "+pillboxList.get(i).getTime();
                SimpleDateFormat sdfPrescription = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                Date datePrescription = null;
                try {
                    datePrescription = sdfPrescription.parse(datePrescriptionString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setDifferenceTime(pillboxList.get(i),rightNowCalendar.getTime().getTime(), datePrescription.getTime());
            }

            if(d.getTime()>= sixOclock.getTime() && d.getTime()<twelveOclock.getTime()){
                if(!haveMoring){
                    pillboxHeaderArrayList.add(new PillboxHeader("Mañana", rightNowCalendar.getTime().getTime() > rightNownowOclock.getTime()|| twelveOclock.getTime()>=rightNownowOclock.getTime()));
                    haveMoring = true;
                }
                for(int j=0; pillboxHeaderArrayList.size() > j;j++){
                    if(pillboxHeaderArrayList.get(j).getName().equalsIgnoreCase("Mañana")){
                        pillboxHeaderArrayList.get(j).getPillboxArrayList().add(pillboxList.get(i));
                    }
                }
            }

            if(d.getTime()>=twelveOclock.getTime() && d.getTime() < nineteentyOclock.getTime()){
                if(!haveAfternoon){
                    pillboxHeaderArrayList.add(new PillboxHeader("Tarde",rightNowCalendar.getTime().getTime() > rightNownowOclock.getTime() || nineteentyOclock.getTime()>=rightNownowOclock.getTime()));
                    haveAfternoon = true;
                }
                for(int j=0; pillboxHeaderArrayList.size() > j;j++){
                    if(pillboxHeaderArrayList.get(j).getName().equalsIgnoreCase("Tarde")){
                        pillboxHeaderArrayList.get(j).getPillboxArrayList().add(pillboxList.get(i));
                    }
                }

            }

            if(d.getTime()>=nineteentyOclock.getTime() && d.getTime() <= twentyThreeFiftyNineOclock.getTime()){
                if(!haveNight){
                    pillboxHeaderArrayList.add(new PillboxHeader("Noche",rightNowCalendar.getTime().getTime() > rightNownowOclock.getTime() || twentyFourOclock.getTime()>=rightNownowOclock.getTime()));
                    haveNight = true;
                }
                for(int j=0; pillboxHeaderArrayList.size() > j;j++){
                    if(pillboxHeaderArrayList.get(j).getName().equalsIgnoreCase("Noche")){
                        pillboxHeaderArrayList.get(j).getPillboxArrayList().add(pillboxList.get(i));
                    }
                }
            }

            if(d.getTime()>=twentyFourOclock.getTime() && d.getTime()< sixOclock.getTime()){
                if(!haveEarlyMoring){

                    pillboxHeaderArrayList.add(new PillboxHeader("Madrugada",rightNowCalendar.getTime().getTime() > rightNownowOclock.getTime() || sixOclock.getTime()>=rightNownowOclock.getTime()));
                    haveEarlyMoring = true;
                }
                for(int j=0; pillboxHeaderArrayList.size() > j;j++){
                    if(pillboxHeaderArrayList.get(j).getName().equalsIgnoreCase("Madrugada")){
                        pillboxHeaderArrayList.get(j).getPillboxArrayList().add(pillboxList.get(i));
                    }
                }
            }
        }

        Realm realm = Realm.getDefaultInstance();
        for(PillboxHeader pillboxHeader : pillboxHeaderArrayList){
            for(Pillbox pillbox: pillboxHeader.getPillboxArrayList()){
                HistoryPrescription historyPrescription = realm.where(HistoryPrescription.class)
                        .equalTo("prescriptionId",pillbox.getIdPrescription())
                        .equalTo("time",pillbox.getTime())
                        .equalTo("date",dateSelected)
                        .findFirst();
                if(historyPrescription != null){
                    pillbox.setTaken(historyPrescription.getTaken());
                    pillbox.setOk(true);

                    String datePrescriptionString  = dateSelected +" "+pillbox.getTime();
                    String dateHistoryString  = historyPrescription.getResponseDate() +" "+historyPrescription.getResponseTime();

                    SimpleDateFormat sdfPrescription = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    Date datePrescription = null;
                    Date dateHistory = null;
                    try {
                        datePrescription = sdfPrescription.parse(datePrescriptionString);
                        dateHistory = sdfPrescription.parse(dateHistoryString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    setDifferenceTime(pillbox,dateHistory.getTime(),datePrescription.getTime());
                }
            }
        }


        pillboxPresenter.showMedicines(pillboxHeaderArrayList);


    }

    @Override
    public void updateDate(Calendar calendar) {

        if(calendar.getTime().getTime() == Calendar.getInstance().getTime().getTime()){
            pillboxPresenter.showUpdatedDate("Hoy, "+MedcareUtils.getDatePillbox(calendar.getTime()));
        }else {
            pillboxPresenter.showUpdatedDate(MedcareUtils.getDatePillbox(calendar.getTime()));
        }

    }

    @Override
    public void setPrescriptionAttachment(int prescriptionId, String date, String time, String dose, String responseDate, String responseTime, String responseDose, Integer reason, final Integer taken, final onPrescriptionAttachment listener) {
        Realm realm = Realm.getDefaultInstance();
        final User user = realm.where(User.class).findFirst();
        final Prescription prescription = realm.where(Prescription.class).equalTo("id",prescriptionId).findFirst();
        AttachmentRequest attachmentRequest = new AttachmentRequest(date,time,dose,responseDate,responseTime,responseDose,reason,taken);
        Call<AttachmentResponse> getTreatment = RestClient.get().postAttachment(prescriptionId,App.getContext().getString(R.string.API_KEY),user.getAccessToken(),attachmentRequest);
        getTreatment.enqueue(new Callback<AttachmentResponse>() {
            @Override
            public void onResponse(Call<AttachmentResponse> call, Response<AttachmentResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        String result;
                        if(taken ==1) {
                            result = "Tomado exitosamente "+prescription.getName();
                        }else {
                            result = "Omitido exitosamente "+prescription.getName();
                        }
                        getHistoryPrescription(result, listener, user.getAccessToken());
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
            public void onFailure(Call<AttachmentResponse> call, Throwable t) {
                listener.showResult(ping());

            }
        });
    }


    private void getHistoryPrescription(final String result , final onPrescriptionAttachment listener, final String accessToken){

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
