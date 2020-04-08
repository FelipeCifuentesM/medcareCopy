package com.imed.medcare.ui.personal_profile.personal_profile_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.PersonalProfile;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class ProfilePersonalAdapter extends RecyclerView.Adapter<ProfilePersonalAdapter.ViewHolder> {

    private RealmResults<PersonalProfile> personalHabitRealmResults;
    private int layout;
    private onItemClick listener;

    public ProfilePersonalAdapter(int layout, RealmResults<PersonalProfile> personalHabitRealmResults, onItemClick listener){
        this.layout = layout;
        this.personalHabitRealmResults = personalHabitRealmResults;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ProfilePersonalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePersonalAdapter.ViewHolder holder, int position) {
        holder.bind(personalHabitRealmResults.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return personalHabitRealmResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_answer)
        TextView tvAnswer;
        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.item_content)
        LinearLayout itemContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final PersonalProfile personalProfile, final onItemClick listener) {

            tvQuestion.setText(personalProfile.getName());
            tvAnswer.setText(personalProfile.getCurrentName());
            itemContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.showEditItem(personalProfile.getId());
                }
            });
        }
    }

    public interface onItemClick{
        void showEditItem(int id);
    }
}
