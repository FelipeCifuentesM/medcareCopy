package com.imed.medcare.ui.answer_questionnaire.adapter.holder;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.MeasurementPoll;
import com.imed.medcare.ui.answer_questionnaire.adapter.TypeAnswerAdapter;

import butterknife.ButterKnife;

import static com.imed.medcare.utils.MedcareUtils.reverseDateFormatDocument;

public class InputDateViewHolder extends RecyclerView.ViewHolder {

    public InputDateViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(MeasurementPoll measurementPoll, final TypeAnswerAdapter.onItemListener listener) {

        TextView measure = itemView.findViewById(R.id.weigth);
        TextInputLayout textinputLayoutDate = itemView.findViewById(R.id.text_input_layout_date);
        final TextInputEditText input = itemView.findViewById(R.id.input);
        final TextInputEditText input2 = itemView.findViewById(R.id.input2);
        final TextInputEditText inputDate = itemView.findViewById(R.id.input_date);


        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        input2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

        final boolean[] fieldDate = {false};
        final boolean[] field1 = {false};
        final boolean[] field2 = {false};

        if (measurementPoll != null) {
            measure.setText(measurementPoll.getUnit());

            if (measurementPoll.getRequestDate()) {
                textinputLayoutDate.setVisibility(View.VISIBLE);
            } else {
                textinputLayoutDate.setVisibility(View.GONE);
            }
        } else {
            measure.setText("");
        }

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setCalendar(inputDate);
            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                field1[0] = !s.toString().isEmpty();
                if (fieldDate[0])
                    listenerType3(input, input2, inputDate, field1, field2, listener);
            }
        });

        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                field2[0] = !s.toString().isEmpty();
                if (fieldDate[0]) listenerType3(input, input2, inputDate, field1, field2, listener);
            }
        });

        inputDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                fieldDate[0] = true;
                listenerType3(input, input2, inputDate, field1, field2, listener);
            }
        });
    }

    void listenerType3(TextInputEditText input, TextInputEditText input2, TextInputEditText inputDate, boolean[] field1, boolean[] field2, TypeAnswerAdapter.onItemListener listener) {
        if (field1[0] && field2[0]) {
            listener.onDateAndTextChangeListener(input.getText().toString() + "," + input2.getText().toString(), reverseDateFormatDocument(inputDate.getText().toString()));
        } else if (field1[0]) {
            listener.onDateAndTextChangeListener(input.getText().toString() + ",0", reverseDateFormatDocument(inputDate.getText().toString()));

        } else {
            listener.onDateAndTextChangeListener("0," + input2.getText().toString(), reverseDateFormatDocument(inputDate.getText().toString()));
        }
    }
}
