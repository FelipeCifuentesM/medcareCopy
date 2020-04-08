package com.imed.medcare.ui.questionnaire_user.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.ChoicesPoll;
import com.imed.medcare.model.MeasurementPoll;

import io.realm.RealmList;

import static com.imed.medcare.utils.MedcareUtils.reverseDateFormatDocument;

public class QuestionnaireUserAdapter extends RecyclerView.Adapter<QuestionnaireUserAdapter.ViewHolder>{

    private Context context;
    private int buttonItem;
    private int inputItem;
    private RealmList<ChoicesPoll> choicesPolls;
    private RealmList<AnswerPoll> answerPolls;
    private MeasurementPoll measurementPoll;
    private onItemListener listener;
    int inputItemDate;
    private int type;

    public QuestionnaireUserAdapter(RealmList<ChoicesPoll> choicesPolls, RealmList<AnswerPoll> answerPolls,MeasurementPoll measurementPoll, int type, int buttonItem, int inputItem, int inputItemDate, onItemListener listener){
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
    public QuestionnaireUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;


        if (1 == type || 2 == type ||type == 6) {
            view = LayoutInflater.from(parent.getContext()).inflate(inputItem,parent,false);

        }else if(type == 3){
            view = LayoutInflater.from(parent.getContext()).inflate(inputItemDate, parent, false);

        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(buttonItem, parent, false);
        }

        QuestionnaireUserAdapter.ViewHolder viewHolder = new QuestionnaireUserAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(getItemViewType(position) == 4 || getItemViewType(position) == 5) {
            holder.bind(null,choicesPolls.get(position),getItemViewType(position), listener,answerPolls,choicesPolls);
        }else if (getItemViewType(position) == 3){
            holder.bind(measurementPoll, null, getItemViewType(position), listener, answerPolls,choicesPolls);
        }else {
            if(measurementPoll.getUnit()!=null) {
                holder.bind(measurementPoll, null, getItemViewType(position), listener, answerPolls,choicesPolls);
            }else {
                holder.bind(null, null, getItemViewType(position), listener, answerPolls,choicesPolls);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        if(choicesPolls.size()!=0) {
            return choicesPolls.size();
        }else {
            return 1;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        void bind(final MeasurementPoll measurementPoll,final ChoicesPoll choicesPoll, int type, final onItemListener listener, final RealmList<AnswerPoll> answerPolls, final RealmList<ChoicesPoll> choicesPolls){

            if(4 == type || 5 == type) {
                CardView button = itemView.findViewById(R.id.button);
                TextView textView = itemView.findViewById(R.id.text);
                textView.setText(choicesPoll.getValue());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onButtonListener(choicesPoll.getId(),choicesPoll.getValue());
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

            }else if(type == 3){
                TextView measure = itemView.findViewById(R.id.weigth);
                final TextInputEditText input = itemView.findViewById(R.id.input);
                final TextInputEditText input2 = itemView.findViewById(R.id.input2);
                final TextInputEditText inputDate = itemView.findViewById(R.id.input_date);
                final boolean[] fieldDate = {false};
                final boolean[] field1 = {false};
                final boolean[] field2= {false};


                if (measurementPoll != null) {
                    measure.setText(measurementPoll.getUnit());
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
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }

                    @Override
                    public void afterTextChanged(Editable s) {
                        field1[0] = !s.toString().isEmpty();
                        if(fieldDate[0])
                            listenerType3(input,input2,inputDate,field1, field2);
                    }
                });

                input2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }

                    @Override
                    public void afterTextChanged(Editable s) {
                        field2[0] = !s.toString().isEmpty();
                        if(fieldDate[0]) listenerType3(input,input2,inputDate,field1, field2);
                    }
                });

                inputDate.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }

                    @Override
                    public void afterTextChanged(Editable s) {
                        fieldDate[0] = true;
                        listenerType3(input,input2,inputDate,field1, field2);
                    }
                });

            }else{
                TextView measure = itemView.findViewById(R.id.weigth);

                final TextInputEditText input = itemView.findViewById(R.id.input);

                if(type==1){
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }else {
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                }

                if (measurementPoll != null) {
                    measure.setText(measurementPoll.getUnit());
                } else {
                    measure.setText("");
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
                        listener.onTextChangeListener(input.getText().toString());
                    }
                });
            }
        }

        void listenerType3(TextInputEditText input,TextInputEditText input2,TextInputEditText inputDate, boolean[] field1, boolean[] field2){
            if(field1[0] && field2[0]){
                listener.onDateAndTextChangeListener(input.getText().toString()+","+input2.getText().toString(),reverseDateFormatDocument(inputDate.getText().toString()));
            }else if(field1[0]){
                listener.onDateAndTextChangeListener(input.getText().toString()+",0",reverseDateFormatDocument(inputDate.getText().toString()));

            }else {
                listener.onDateAndTextChangeListener("0,"+input2.getText().toString(),reverseDateFormatDocument(inputDate.getText().toString()));
            }
        }
    }

    public interface onItemListener{
        void onButtonListener(Integer choiceId,String value);
        void onTextChangeListener(String value);
        void onDateAndTextChangeListener(String value, String date);
        void setCalendar(TextInputEditText inputDate);
    }
}