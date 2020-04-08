package com.imed.medcare.ui.answer_questionnaire.adapter.holder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.MeasurementPoll;
import com.imed.medcare.ui.answer_questionnaire.adapter.TypeAnswerAdapter;

import java.util.Calendar;

import butterknife.ButterKnife;
import io.realm.RealmList;

import static com.imed.medcare.utils.MedcareUtils.YearMonthDayString;
import static com.imed.medcare.utils.MedcareUtils.reverseDateFormatDocument;

public class InputViewHolder extends RecyclerView.ViewHolder {

    public InputViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(MeasurementPoll measurementPoll, final int itemViewType, final TypeAnswerAdapter.onItemListener listener, RealmList<AnswerPoll> answerPolls, Activity activity) {

        TextView measure = itemView.findViewById(R.id.weigth);
        TextInputLayout textInputLayout = itemView.findViewById(R.id.text_input_layout);
        final TextInputEditText input = itemView.findViewById(R.id.input);
        TextInputLayout textInputLayoutDate = itemView.findViewById(R.id.text_input_layout_date);
        final TextInputEditText textInputEditDate = itemView.findViewById(R.id.input_date);
        final AppCompatCheckBox switchButton = itemView.findViewById(R.id.btn_switch);


        textInputEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setCalendar(textInputEditDate);
            }
        });

        if (itemViewType == 1) {
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            switchButton.setVisibility(View.GONE);
        } else if (itemViewType == 6) {
            switchButton.setVisibility(View.VISIBLE);
            if (answerPolls != null && answerPolls.size() > 0 && answerPolls.get(0) != null && answerPolls.get(0).getValue() != null) {
                if (answerPolls.get(0).getValue().equals("1")) {
                    switchButton.setChecked(true);
                    textInputEditDate.setEnabled(false);
                    textInputEditDate.setTextColor(activity.getResources().getColor(R.color.button_disable_color));
                } else {
                    textInputEditDate.setTextColor(activity.getResources().getColor(R.color.white));
                    switchButton.setChecked(false);
                    textInputEditDate.setEnabled(true);
                }
            } else {
                textInputEditDate.setTextColor(activity.getResources().getColor(R.color.white));
                switchButton.setChecked(false);
                textInputEditDate.setEnabled(true);
            }
            textInputLayout.setVisibility(View.GONE);
        } else {
            switchButton.setVisibility(View.GONE);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        }

        if (measurementPoll != null && measurementPoll.getUnit() != null) {

            if (!measurementPoll.getUnit().equals("-")) {

                measure.setText(measurementPoll.getUnit());
            } else {
                measure.setText("");
            }

            if (measurementPoll.getRequestDate() != null && measurementPoll.getRequestDate()) {
                textInputLayoutDate.setVisibility(View.VISIBLE);
            } else {
                textInputLayoutDate.setVisibility(View.GONE);
            }
        }

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    if (!textInputEditDate.getText().toString().isEmpty()) {
                        listener.onTextChangeListener(input.getText().toString(), reverseDateFormatDocument(textInputEditDate.getText().toString()));
                    }
                    input.setError(null);
                } else {
                    input.setError("Debes ingresar un dato");
                }
            }
        });

        textInputEditDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (itemViewType == 6) {
                    listener.onTextChangeListener("1", reverseDateFormatDocument(textInputEditDate.getText().toString()));
                }else {
                    listener.onTextChangeListener(input.getText().toString(), reverseDateFormatDocument(textInputEditDate.getText().toString()));
                }
            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    listener.onTextChangeListener("1", YearMonthDayString(Calendar.getInstance().getTime()) + " 00:00:00");
                } else {
                    if (textInputEditDate.getText().toString().isEmpty()) {
                        listener.onTextChangeListener("0", YearMonthDayString(Calendar.getInstance().getTime()) + " 00:00:00");
                    } else {
                        listener.onTextChangeListener("0", reverseDateFormatDocument(textInputEditDate.getText().toString()));
                    }
                }
            }
        });


    }
}
