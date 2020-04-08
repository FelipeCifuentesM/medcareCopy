package com.imed.medcare.ui.home.pillbox.dialog_fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.imed.medcare.R;
import com.imed.medcare.ui.home.pillbox.PillboxContract;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by Ramiro on 31-05-2018.
 */

public class TimePickerDialogFragment extends DialogFragment{
    @BindView(R.id.dialog_text)
    TextView dialogText;
    @BindView(R.id.timePicker)
    TimePicker timePicker;
    Integer taken;
    Integer idPrescription;
    String time;
    String date;
    String dose;
    String responseDate;
    String responseDose;
    String responseTime;

    private static PillboxContract.View view;
    private Unbinder unbinder;

    public TimePickerDialogFragment(){}

    public static TimePickerDialogFragment newInstance(PillboxContract.View pillboxView){
        view = pillboxView;
        return new TimePickerDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.custom_dialog_time, container);
        unbinder = ButterKnife.bind(this,view);

        taken = getArguments().getInt("taken");
        idPrescription = getArguments().getInt("idPrescription");
        time = getArguments().getString("time");
        date = getArguments().getString("date");
        dose = getArguments().getString("dose");
        responseDate = getArguments().getString("responseDate");
        responseDose = getArguments().getString("responseDose");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setElements();
    }

    private void setElements() {
        timePicker.setIs24HourView(true);

        Calendar date = Calendar.getInstance();
        int hour = date.get(Calendar.HOUR_OF_DAY);
        int minute = date.get(Calendar.MINUTE);
        responseTime = hour+":"+minute+":00";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(hour);
            timePicker.setMinute(minute);
        }
    }

    @OnClick(R.id.cancel)
    void onButtonClicked(){
        this.dismiss();
    }

    @OnClick(R.id.done)
    void onDoneClicked(){
        view.setPrescriptionAttachment(idPrescription, date,time,dose,responseDate,responseTime,responseDose,null,taken);
        this.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
