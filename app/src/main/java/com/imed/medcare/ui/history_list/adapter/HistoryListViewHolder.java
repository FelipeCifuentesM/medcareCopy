package com.imed.medcare.ui.history_list.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.network.response.HistoryResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.imed.medcare.utils.MedcareUtils.dateFormatHistory;

public class HistoryListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textView12)
    TextView tvDate;
    @BindView(R.id.textView13)
    TextView tvAnswerScore;
    @BindView(R.id.textView14)
    TextView tvAttachmentScore;
    @BindView(R.id.cv_history)
    ConstraintLayout cvHistory;

    public HistoryListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final HistoryResponse.DataBean hisoryData, boolean isFromAttachment, final HistoryListAdapter.onItemListener listener) {

        tvDate.setText(dateFormatHistory(hisoryData.getPoll_period().getStart()));

        if (hisoryData.getCurrent_questions() == hisoryData.getTotal_questions()) {
            tvAnswerScore.setBackgroundResource(R.drawable.shape_attachment_history_green);
            tvAnswerScore.setText("Completada " + hisoryData.getCurrent_questions() + "/" + hisoryData.getTotal_questions());

        } else if (hisoryData.getCurrent_questions() == 0) {
            tvAnswerScore.setBackgroundResource(R.drawable.shape_attachment_history_red);
            tvAnswerScore.setText("No contestada " + hisoryData.getCurrent_questions() + "/" + hisoryData.getTotal_questions());

        } else {
            tvAnswerScore.setBackgroundResource(R.drawable.shape_attachment_history_gray);
            tvAnswerScore.setText("Incompleto " + hisoryData.getCurrent_questions() + "/" + hisoryData.getTotal_questions());
        }

        if (isFromAttachment) {
            tvAttachmentScore.setVisibility(View.VISIBLE);
            tvAttachmentScore.setText("Apego " + hisoryData.getAttachment() + "%");
        } else {
            tvAttachmentScore.setVisibility(View.GONE);
        }

        cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showHistory(hisoryData.getId(),dateFormatHistory(hisoryData.getPoll_period().getStart()));
            }
        });
    }

}
