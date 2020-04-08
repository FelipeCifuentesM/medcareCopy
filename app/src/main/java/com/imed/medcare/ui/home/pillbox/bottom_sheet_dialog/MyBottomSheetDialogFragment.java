package com.imed.medcare.ui.home.pillbox.bottom_sheet_dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.imed.medcare.R;
import com.imed.medcare.model.Prescription;
import com.imed.medcare.model.Schedule;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.ui.home.pillbox.PillboxContract;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;
import io.realm.Realm;
import static com.imed.medcare.utils.Constants.BOTTOM_SHEET_SKIP_MEDICINE;
import static com.imed.medcare.utils.Constants.BOTTOM_SHEET_TAKE_MEDICINE;

/**
 * Created by Ramiro on 17-05-2018.
 */

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @Nullable
    @BindView(R.id.on_time)
    TextView onTime;
    @Nullable
    @BindView(R.id.now)
    TextView nowDate;
    @Nullable
    @BindView(R.id.skip_item_1)
    TextView skipItem1;
    @Nullable
    @BindView(R.id.skip_item_2)
    TextView skipItem2;
    @Nullable
    @BindView(R.id.skip_item_3)
    TextView skipItem3;
    @Nullable
    @BindView(R.id.skip_item_4)
    TextView skipItem4;
    @Nullable
    @BindView(R.id.skip_item_5)
    TextView skipItem5;

    private Unbinder unbinder;
    private Prescription prescription;
    private Schedule schedule;
    public static int type;
    private String time;
    private String date;
    private String responseDate;
    private String responseTime;
    public static PillboxContract.View view;
    private String actualNowDate;

    public static MyBottomSheetDialogFragment newInstance(int sheetType, PillboxContract.View pillboxView) {
        view = pillboxView;
        type = sheetType;
        return new MyBottomSheetDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;

        switch (type) {
            case BOTTOM_SHEET_TAKE_MEDICINE:
                view = inflater.inflate(R.layout.bottom_sheet_take_medicine, container, false);
                break;
            case BOTTOM_SHEET_SKIP_MEDICINE:
                view = inflater.inflate(R.layout.bottom_sheet_skip_medicine, container, false);
                break;
        }


        int idPrescription = getArguments().getInt("idPrescription");
        int idSchedule = getArguments().getInt("idSchedule");
        time = getArguments().getString("time");
        date = getArguments().getString("date");
        responseDate = getArguments().getString("responseDate");
        responseTime = getArguments().getString("responseTime");

        Realm realm = Realm.getDefaultInstance();
        GenericRepositoryRealm<Prescription> prescriptionGenericRepositoryRealm = new GenericRepositoryRealm<>(Prescription.class);
        prescriptionGenericRepositoryRealm.setRealm(realm);
        prescription = prescriptionGenericRepositoryRealm.get(idPrescription,"id");
        GenericRepositoryRealm<Schedule>  scheduleGenericRepositoryRealm = new GenericRepositoryRealm<>(Schedule.class);
        scheduleGenericRepositoryRealm.setRealm(realm);
        schedule = scheduleGenericRepositoryRealm.get(idSchedule,"id");


        if(view != null){
            unbinder = ButterKnife.bind(this,view);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(type == BOTTOM_SHEET_TAKE_MEDICINE)
            setTakeMedicineData();
        if(type == BOTTOM_SHEET_SKIP_MEDICINE)
           setSkipMedicineData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setSkipMedicineData() {
    }

    private void setTakeMedicineData() {

        Calendar c = Calendar.getInstance();
        int hrs = c.get(Calendar.HOUR_OF_DAY);//24
        int min = c.get(Calendar.MINUTE);//59

        String onTimeAux = time.split(":")[0]+":"+time.split(":")[1];
        onTimeAux = "A tiempo ("+onTimeAux+")";
        onTime.setText(onTimeAux);

        String newMin = String.valueOf(min);
        if(newMin.length() == 1)
            newMin = "0"+newMin;
        actualNowDate = hrs+":"+newMin+":00";
        String nowDateAux = "Ahora ("+hrs+":"+newMin+" "+")";
        nowDate.setText(nowDateAux);
    }

    @Optional
    @OnClick({R.id.on_time, R.id.now, R.id.set_time})
    void onTimeClicked(View v){

        switch (v.getId()){
            case R.id.on_time:

                view.setPrescriptionAttachment(prescription.getId(),date, time, schedule.getDose(), date,time, schedule.getDose(), null,1);
                view.closeBottomSheet();
                break;
            case R.id.now:

                view.setPrescriptionAttachment(prescription.getId(),date, time, schedule.getDose(), responseDate, actualNowDate, schedule.getDose(), null,1);
                view.closeBottomSheet();
                break;
            case R.id.set_time:

                view.showTimePickerDialog(prescription.getId(),date, time, schedule.getDose(), responseDate, schedule.getDose(),1);
                view.closeBottomSheet();
                break;
        }
    }

    @Optional
    @OnClick({ R.id.skip_item_1, R.id.skip_item_2,  R.id.skip_item_3,  R.id.skip_item_4,  R.id.skip_item_5 })
    public void pickOption(View v) {

        switch (v.getId()){
            case R.id.skip_item_1:
                view.setPrescriptionAttachment(prescription.getId(),date, time, schedule.getDose(), responseDate, responseTime, schedule.getDose(), 1,2);
                view.closeBottomSheet();
                break;
            case R.id.skip_item_2:
                view.setPrescriptionAttachment(prescription.getId(),date, time, schedule.getDose(), responseDate, responseTime, schedule.getDose(), 2,2);
                view.closeBottomSheet();
                break;
            case R.id.skip_item_3:
                view.setPrescriptionAttachment(prescription.getId(),date, time, schedule.getDose(), responseDate, responseTime, schedule.getDose(), 3,2);
                view.closeBottomSheet();
                break;

            case R.id.skip_item_4:
                view.setPrescriptionAttachment(prescription.getId(),date, time, schedule.getDose(), responseDate, responseTime, schedule.getDose(), 4,2);
                view.closeBottomSheet();
                break;

            case R.id.skip_item_5:
                view.setPrescriptionAttachment(prescription.getId(),date, time, schedule.getDose(), responseDate, responseTime, schedule.getDose(), 5,2);
                view.closeBottomSheet();
                break;
        }
    }

}
