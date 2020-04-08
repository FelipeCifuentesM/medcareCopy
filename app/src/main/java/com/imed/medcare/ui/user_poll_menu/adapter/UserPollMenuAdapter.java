package com.imed.medcare.ui.user_poll_menu.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.UserPollQuestion;

import io.realm.RealmResults;

public class UserPollMenuAdapter extends RecyclerView.Adapter<UserPollMenuAdapter.ViewHolder>{

    private RealmResults<UserPollQuestion> userPollQuestionRealmList;
    private int layout;
    private onItemListener listener;
    private Context context;

    public UserPollMenuAdapter(RealmResults<UserPollQuestion> userPollQuestionRealmList, int layout, onItemListener listener){
        this.layout = layout;
        this.userPollQuestionRealmList = userPollQuestionRealmList;
        this.listener = listener;
    }

    @Override
    public UserPollMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = App.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserPollMenuAdapter.ViewHolder holder, int position) {
        holder.bind(userPollQuestionRealmList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return userPollQuestionRealmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(final UserPollQuestion pollMenu, final int position) {

            ConstraintLayout contentItem = itemView.findViewById(R.id.content_item);

            TextView body = itemView.findViewById(R.id.textView5);
            TextView status = itemView.findViewById(R.id.textView6);
            TextView title = itemView.findViewById(R.id.textView4);

            title.setVisibility(View.GONE);
            title.setText(pollMenu.getTitle());

            body.setText(pollMenu.getQuestionOrder()+"._ "+pollMenu.getDescription());

            if(pollMenu.getAnswerPolls() != null && pollMenu.getAnswerPolls().size()>0) {
                status.setTextColor(context.getResources().getColor(R.color.primary_text_color));
                status.setText(pollMenu.getAnswerPolls().get(0).getValue());
            }else {
                status.setText("Sin contestar");
                status.setTextColor(context.getResources().getColor(R.color.black__38));
            }
            contentItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemSelected(position, userPollQuestionRealmList.get(position));
                }
            });


        }
    }


    public interface onItemListener{
        void itemSelected(int position, UserPollQuestion userPollQuestion);
    }
}
