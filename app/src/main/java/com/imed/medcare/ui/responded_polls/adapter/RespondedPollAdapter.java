package com.imed.medcare.ui.responded_polls.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.network.response.RespondedPollResponse;
import com.imed.medcare.utils.MedcareUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RespondedPollAdapter extends RecyclerView.Adapter<RespondedPollAdapter.ViewHolder> {

    private int layout;
    private List<RespondedPollResponse.DataBean> list;
    private boolean isFromAttachment;

    public RespondedPollAdapter(int layout, List<RespondedPollResponse.DataBean> list, boolean isFromAttachment) {
        this.layout = layout;
        this.list = list;
        this.isFromAttachment = isFromAttachment;
    }

    @NonNull
    @Override
    public RespondedPollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RespondedPollAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView4)
        TextView tvTitle;
        @BindView(R.id.textView5)
        TextView tvQuestion;
        @BindView(R.id.textView6)
        TextView tvAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(RespondedPollResponse.DataBean dataBean) {
            if (!isFromAttachment) {
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(dataBean.getTitle());
            } else {
                tvTitle.setVisibility(View.GONE);
            }
            tvQuestion.setText(dataBean.getDescription());

            if (dataBean.getAnswers() != null && dataBean.getAnswers().size() > 0) {

                if(dataBean.getType() == 6){
                    if(dataBean.getAnswers().get(0).getValue() == null || !dataBean.getAnswers().get(0).getValue().equals("1")) {
                        String date;
                        if (dataBean.getAnswers().get(0).getDate().contains(" ")) {
                            date = dataBean.getAnswers().get(0).getDate();
                        } else {
                            date = dataBean.getAnswers().get(0).getDate() + " 00:00:00";
                        }
                        tvAnswer.setText(MedcareUtils.dateInfoTreatment(date));

                    }else {
                        tvAnswer.setText("No he tenido");
                    }
                }else {

                    tvAnswer.setText(dataBean.getAnswers().get(0).getValue());

                }
                tvAnswer.setTextColor(App.getContext().getResources().getColor(R.color.primary_text_color));
            } else {
                tvAnswer.setText("Sin contestar");
                tvAnswer.setTextColor(App.getContext().getResources().getColor(R.color.black__38));
            }

        }
    }
}
