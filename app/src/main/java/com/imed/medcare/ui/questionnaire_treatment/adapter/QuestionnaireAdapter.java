package com.imed.medcare.ui.questionnaire_treatment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.ChoicesPoll;
import com.imed.medcare.model.MeasurementPoll;

import java.util.Calendar;

import io.realm.RealmList;

import static com.imed.medcare.utils.MedcareUtils.YearMonthDayString;
import static com.imed.medcare.utils.MedcareUtils.dateFormatDocument;
import static com.imed.medcare.utils.MedcareUtils.reverseDateFormatDocument;

public class QuestionnaireAdapter extends RecyclerView.Adapter<QuestionnaireAdapter.ProfileHolder> {

    private Context context;
    private int buttonItem;
    private int inputItem;
    private RealmList<ChoicesPoll> choicesPolls;
    private RealmList<AnswerPoll> answerPolls;
    private MeasurementPoll measurementPoll;
    private onItemListener listener;
    private int inputItemDate;
    private int type;

    public QuestionnaireAdapter(RealmList<ChoicesPoll> choicesPolls, RealmList<AnswerPoll> answerPolls, MeasurementPoll measurementPoll, int type, int buttonItem, int inputItem, int inputItemDate, onItemListener listener) {
        this.inputItem = inputItem;
        this.buttonItem = buttonItem;
        this.choicesPolls = choicesPolls;
        this.measurementPoll = measurementPoll;
        this.listener = listener;
        this.type = type;
        this.answerPolls = answerPolls;
        this.inputItemDate = inputItemDate;
    }


    @Override
    public QuestionnaireAdapter.ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;

        if (1 == type || 2 == type || type == 6) {
            view = LayoutInflater.from(parent.getContext()).inflate(inputItem, parent, false);

        } else if (type == 3) {
            view = LayoutInflater.from(parent.getContext()).inflate(inputItemDate, parent, false);

        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(buttonItem, parent, false);
        }

        QuestionnaireAdapter.ProfileHolder viewHolder = new QuestionnaireAdapter.ProfileHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        if (getItemViewType(position) == 4 || getItemViewType(position) == 5) {
            holder.bind(null, choicesPolls.get(position), getItemViewType(position), listener, answerPolls, choicesPolls);
        } else if (getItemViewType(position) == 3) {
            holder.bind(measurementPoll, null, getItemViewType(position), listener, answerPolls, choicesPolls);
        } else {
            if (measurementPoll != null) {
                holder.bind(measurementPoll, null, getItemViewType(position), listener, answerPolls, choicesPolls);
            } else {
                holder.bind(null, null, getItemViewType(position), listener, answerPolls, choicesPolls);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        if (choicesPolls.size() != 0) {
            return choicesPolls.size();
        } else {
            return 1;
        }
    }


    public class ProfileHolder extends RecyclerView.ViewHolder {
        public ProfileHolder(View itemView) {
            super(itemView);
        }

        void bind(final MeasurementPoll measurementPoll, final ChoicesPoll choicesPoll, final int type, final onItemListener listener, final RealmList<AnswerPoll> answerPolls, final RealmList<ChoicesPoll> choicesPolls) {

            if (4 == type || 5 == type) {
                CardView button = itemView.findViewById(R.id.button);
                TextView textView = itemView.findViewById(R.id.text);
                textView.setText(choicesPoll.getValue());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onButtonListener(choicesPoll.getId(), choicesPoll.getValue());
                    }
                });

                button.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                textView.setTextColor(context.getResources().getColor(R.color.marine_blue));

                if (answerPolls != null && answerPolls.size() != 0) {

                    for (AnswerPoll answerPoll : answerPolls) {
                        if (choicesPoll.getValue().equalsIgnoreCase(answerPoll.getValue())) {
                            button.setCardBackgroundColor(context.getResources().getColor(R.color.primary_text_color));
                            textView.setTextColor(context.getResources().getColor(R.color.white));
                        }
                    }
                }

            } else if (type == 3) {
                TextView measure = itemView.findViewById(R.id.weigth);
                TextInputLayout textinputLayoutDate = itemView.findViewById(R.id.text_input_layout_date);
                final TextInputEditText input = itemView.findViewById(R.id.input);
                final TextInputEditText input2 = itemView.findViewById(R.id.input2);
                final TextInputEditText inputDate = itemView.findViewById(R.id.input_date);


                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                input2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);


                if(answerPolls != null && answerPolls.size()>0 &&answerPolls.get(0).getValue() != null){
                    input.setText(answerPolls.get(0).getValue().split("\\.")[0]);
                    input2.setText(answerPolls.get(0).getValue().split("\\.")[1]);
                    inputDate.setText(dateFormatDocument(answerPolls.get(0).getDate()+" 00:00:00"));
                }
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
                            listenerType3(input, input2, inputDate, field1, field2);
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
                        if (fieldDate[0]) listenerType3(input, input2, inputDate, field1, field2);
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
                        listenerType3(input, input2, inputDate, field1, field2);
                    }
                });

            } else {
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

                if (type == 1) {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    switchButton.setVisibility(View.GONE);
                } else if (type == 6) {
                    switchButton.setVisibility(View.VISIBLE);
                    if (answerPolls != null && answerPolls.size() > 0 && answerPolls.get(0) != null && answerPolls.get(0).getValue() != null) {
                        if (answerPolls.get(0).getValue().equals("1")) {
                            switchButton.setChecked(true);
                            textInputEditDate.setEnabled(false);
                            textInputEditDate.setTextColor(context.getResources().getColor(R.color.button_disable_color));

                        } else {
                            textInputEditDate.setTextColor(context.getResources().getColor(R.color.white));
                            switchButton.setChecked(false);
                            textInputEditDate.setEnabled(true);
                            textInputEditDate.setText(dateFormatDocument(answerPolls.get(0).getDate()+" 00:00:00"));
                        }
                    } else {
                        textInputEditDate.setTextColor(context.getResources().getColor(R.color.white));
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

                        if (type == 6) {
                            Log.i("afterTextChanged",reverseDateFormatDocument(textInputEditDate.getText().toString()));
                            listener.onTextChangeListener("0", reverseDateFormatDocument(textInputEditDate.getText().toString()));
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

                            switchButton.setChecked(true);
                            textInputEditDate.setEnabled(false);
                            textInputEditDate.setTextColor(context.getResources().getColor(R.color.button_disable_color));
                        } else {

                            textInputEditDate.setTextColor(context.getResources().getColor(R.color.white));
                            switchButton.setChecked(false);
                            textInputEditDate.setEnabled(true);
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

        void listenerType3(TextInputEditText input, TextInputEditText input2, TextInputEditText inputDate, boolean[] field1, boolean[] field2) {
            if (field1[0] && field2[0]) {
                listener.onDateAndTextChangeListener(input.getText().toString() + "." + input2.getText().toString(), reverseDateFormatDocument(inputDate.getText().toString()));
            } else if (field1[0]) {
                listener.onDateAndTextChangeListener(input.getText().toString() + ".0", reverseDateFormatDocument(inputDate.getText().toString()));

            } else {
                listener.onDateAndTextChangeListener("0." + input2.getText().toString(), reverseDateFormatDocument(inputDate.getText().toString()));
            }
        }
    }

    public interface onItemListener {
        void onButtonListener(Integer choiceId, String value);

        void onTextChangeListener(String value, String date);

        void onDateAndTextChangeListener(String value, String date);

        void setCalendar(TextInputEditText inputDate);
    }
}
