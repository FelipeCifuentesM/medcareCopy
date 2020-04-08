package com.imed.medcare.ui.home.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.imed.medcare.model.UserMeasurement;
import com.imed.medcare.ui.home.home.ViewHolder.AttachmentViewHolder;
import com.imed.medcare.ui.home.home.ViewHolder.CharterViewHolder;
import com.imed.medcare.ui.home.home.ViewHolder.MessageViewHolder;
import com.imed.medcare.ui.home.home.ViewHolder.TitleCharterViewHolder;
import io.realm.RealmList;


public class CharterAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int layoutCharter;
    private int layoutAttachment;
    private int layoutMessage;
    private int layoutTitleCharter;
    private RealmList<UserMeasurement> userMeasurementRealmList;
    private onItemListener listener;

    public CharterAdapter(RealmList<UserMeasurement> userMeasurementRealmList,int layoutCharter, int layoutAttachment, int layoutMessage, int layoutTitleCharter,onItemListener listener){
        this.layoutCharter = layoutCharter;
        this.userMeasurementRealmList = userMeasurementRealmList;
        this.layoutAttachment = layoutAttachment;
        this.layoutMessage = layoutMessage;
        this.layoutTitleCharter = layoutTitleCharter;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewType) {
            case 0:
                return new AttachmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutAttachment, parent, false));
            case 1:
                return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutMessage, parent, false));
            case 2:
                return new TitleCharterViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutTitleCharter, parent, false));
            default:
                return new CharterViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutCharter, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        switch (getItemViewType(position)) {
            case 0:
                ((AttachmentViewHolder) holder).bind(listener);
                break;
            case 1:
                ((MessageViewHolder) holder).bind(listener, position);
                break;
            case 2:
                ((TitleCharterViewHolder) holder).bind();
                break;
            default:
                ((CharterViewHolder) holder).bind(userMeasurementRealmList.get(position));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return userMeasurementRealmList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(userMeasurementRealmList.get(position).getTypeView() != null && userMeasurementRealmList.get(position).getTypeView() == 0) {
            return 0;
        }else if(userMeasurementRealmList.get(position).getTypeView() != null && userMeasurementRealmList.get(position).getTypeView() == 1) {
            return 1;
        }else if(userMeasurementRealmList.get(position).getTypeView() != null && userMeasurementRealmList.get(position).getTypeView() == 2) {
            return 2;
        }else {
            return 3;
        }
    }

    public interface onItemListener{
        void btnMessage(int position);
        void btnAttachmentAnswer();
        void btnAttachmentHistoy();
    }
}
