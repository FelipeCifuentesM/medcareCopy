package com.imed.medcare.ui.answer_questionnaire.adapter.holder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.ChoicesPoll;
import com.imed.medcare.ui.answer_questionnaire.adapter.TypeAnswerAdapter;

import butterknife.ButterKnife;
import io.realm.RealmList;

public class InputButtonViewHolder extends RecyclerView.ViewHolder {

    public InputButtonViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Activity activity, final ChoicesPoll choicesPoll, final TypeAnswerAdapter.onItemListener listener, RealmList<AnswerPoll> answerPolls, final int position) {


        CardView button = itemView.findViewById(R.id.button);
        TextView textView = itemView.findViewById(R.id.text);
        textView.setText(choicesPoll.getValue());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonListener(choicesPoll.getId(), choicesPoll.getValue(),position);
            }
        });


        button.setCardBackgroundColor(activity.getResources().getColor(R.color.white));
        textView.setTextColor(activity.getResources().getColor(R.color.marine_blue));

        if (answerPolls != null && answerPolls.size() != 0) {

            for (AnswerPoll answerPoll : answerPolls) {
                if (choicesPoll.getValue().equalsIgnoreCase(answerPoll.getValue())) {
                    button.setCardBackgroundColor(activity.getResources().getColor(R.color.primary_text_color));
                    textView.setTextColor(activity.getResources().getColor(R.color.white));
                }
            }
        }



    }
}
