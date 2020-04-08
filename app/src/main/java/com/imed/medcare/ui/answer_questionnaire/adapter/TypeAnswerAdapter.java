package com.imed.medcare.ui.answer_questionnaire.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.ChoicesPoll;
import com.imed.medcare.model.MeasurementPoll;
import com.imed.medcare.model.TreatmentPollQuestion;
import com.imed.medcare.ui.answer_questionnaire.adapter.holder.InputButtonViewHolder;
import com.imed.medcare.ui.answer_questionnaire.adapter.holder.InputDateViewHolder;
import com.imed.medcare.ui.answer_questionnaire.adapter.holder.InputViewHolder;

import io.realm.RealmList;

public class TypeAnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int buttonItem;
    private int inputItem;
    private RealmList<ChoicesPoll> choicesPolls;
    private RealmList<AnswerPoll> answerPolls;
    private MeasurementPoll measurementPoll;
    private onItemListener listener;
    private int inputItemDate;
    private int type;
    private Activity activity;


    public TypeAnswerAdapter(Activity activity, RealmList<ChoicesPoll> choicesPolls,RealmList<AnswerPoll> answerPolls , MeasurementPoll measurementPoll, int type, int buttonItem, int inputItem, int inputItemDate, onItemListener listener) {

        this.inputItem = inputItem;
        this.buttonItem = buttonItem;
        this.choicesPolls = choicesPolls;
        this.measurementPoll = measurementPoll;
        this.listener = listener;
        this.type = type;
        this.answerPolls = answerPolls;
        this.inputItemDate = inputItemDate;
        this.activity = activity;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (1 == type || 2 == type || type == 6) {
            return new InputViewHolder(LayoutInflater.from(activity).inflate(inputItem, parent, false));
        } else if (type == 3) {
            return new InputDateViewHolder(LayoutInflater.from(activity).inflate(inputItemDate, parent, false));

        } else {
            return new InputButtonViewHolder(LayoutInflater.from(activity).inflate(buttonItem, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 4 || getItemViewType(position) == 5) {
            ((InputButtonViewHolder) holder).bind(activity, choicesPolls.get(position), listener, answerPolls, position);
        } else if (getItemViewType(position) == 3) {
            ((InputDateViewHolder) holder).bind(measurementPoll, listener);
        } else {
            if (measurementPoll != null) {
                ((InputViewHolder) holder).bind(measurementPoll, getItemViewType(position), listener, answerPolls, activity);
            } else {
                ((InputViewHolder) holder).bind(null, getItemViewType(position), listener, answerPolls, activity);
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



    public interface onItemListener {
        void onButtonListener(Integer choiceId, String value, int position);

        void onTextChangeListener(String value, String date);

        void onDateAndTextChangeListener(String value, String date);

        void setCalendar(TextInputEditText inputDate);
    }
}
