package com.imed.medcare.ui.treatment_poll_menu.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.TreatmentPollQuestion;
import com.imed.medcare.utils.MedcareUtils;

import io.realm.RealmResults;

public class AdapterPollMenu extends RecyclerView.Adapter<AdapterPollMenu.ViewHolder>{

    private RealmResults<TreatmentPollQuestion> pollMenuArrayList;
    private int layout;
    private onItemListener listener;
    private Context context;
    public AdapterPollMenu(Context context,RealmResults<TreatmentPollQuestion> pollMenuArrayList, int layout, onItemListener listener){
        this.layout = layout;
        this.pollMenuArrayList = pollMenuArrayList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public AdapterPollMenu.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterPollMenu.ViewHolder holder, int position) {
        holder.bind(context,pollMenuArrayList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return pollMenuArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Context context,final TreatmentPollQuestion pollMenu, final int position) {

            ConstraintLayout contentItem = itemView.findViewById(R.id.content_item);

            TextView body = itemView.findViewById(R.id.textView5);
            TextView status = itemView.findViewById(R.id.textView6);
            TextView title = itemView.findViewById(R.id.textView4);

            title.setVisibility(View.VISIBLE);
            title.setText(pollMenu.getQuestionOrder()+"._ "+pollMenu.getTitle());

            body.setText(pollMenu.getDescription());


            if(pollMenu.getAnswerPolls() != null && pollMenu.getAnswerPolls().size()>0) {
                status.setTextColor(context.getResources().getColor(R.color.primary_text_color));
                if(pollMenu.getAnswerPolls().get(0).getValue() != null && !pollMenu.getAnswerPolls().get(0).getValue().isEmpty()) {
                    if(pollMenu.getType()==6) {
                        if(pollMenu.getAnswerPolls().get(0).getValue().equals("1")) {
                            status.setText("No he tenido");
                        }else {
                            String date;
                            if(pollMenu.getAnswerPolls().get(0).getDate().contains(" ")){
                                date = pollMenu.getAnswerPolls().get(0).getDate();
                            }else {
                                date = pollMenu.getAnswerPolls().get(0).getDate()+" 00:00:00";
                            }
                            status.setText(MedcareUtils.dateInfoTreatment(date));
                        }
                    }else {
                        if(pollMenu.getMeasuremtPoll()!=null && pollMenu.getMeasuremtPoll().getUnit() !=null) {
                            status.setText(pollMenu.getAnswerPolls().get(0).getValue()+ " "+ pollMenu.getMeasuremtPoll().getUnit());
                        }else {
                            status.setText(pollMenu.getAnswerPolls().get(0).getValue());
                        }
                    }
                }else {
                    if(pollMenu.getAnswerPolls().get(0).getDate() != null && !pollMenu.getAnswerPolls().get(0).getDate().isEmpty()) {

                        String date;
                        if(pollMenu.getAnswerPolls().get(0).getDate().contains(" ")){
                            date = pollMenu.getAnswerPolls().get(0).getDate();
                        }else {
                            date = pollMenu.getAnswerPolls().get(0).getDate()+" 00:00:00";
                        }
                        status.setText(MedcareUtils.dateInfoTreatment(date));

                    }else {
                        status.setText("Sin contestar");
                        status.setTextColor(context.getResources().getColor(R.color.black__38));
                    }
                }

            }else {
                status.setText("Sin contestar");
                status.setTextColor(context.getResources().getColor(R.color.black__38));
            }
            contentItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemSelected(position);
                }
            });


        }
    }


    public interface onItemListener{
        void itemSelected(int position);
    }
}
