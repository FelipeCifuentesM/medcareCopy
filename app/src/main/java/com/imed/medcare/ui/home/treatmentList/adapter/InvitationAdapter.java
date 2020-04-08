package com.imed.medcare.ui.home.treatmentList.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.imed.medcare.model.Professional;
import com.imed.medcare.ui.home.treatmentList.viewholders.InvitationTitleViewHolder;
import com.imed.medcare.ui.home.treatmentList.viewholders.InvitationViewHolder;

import io.realm.RealmList;

public class InvitationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int layoutInvitation;
    private int layoutTitle;
    private RealmList<Professional> professionalRealmList;
    private onItemListener listener;

    public InvitationAdapter(int layoutInvitation, int layoutTitle, RealmList<Professional> professionalRealmList, onItemListener listener){
        this.layoutInvitation = layoutInvitation;
        this.layoutTitle = layoutTitle;
        this.professionalRealmList = professionalRealmList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0:
                return new InvitationTitleViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutTitle, parent, false));
            default:
                return new InvitationViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutInvitation, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InvitationViewHolder) {
            ((InvitationViewHolder) holder).bind(professionalRealmList.get(position),position, listener);
        }
    }

    @Override
    public int getItemCount() {
        return professionalRealmList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else {
            return 1;
        }
    }

    public  interface onItemListener{
        void acceptInvitation(Professional professional, int position);
    }
}
