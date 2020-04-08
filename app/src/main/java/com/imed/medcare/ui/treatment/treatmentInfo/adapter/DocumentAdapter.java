package com.imed.medcare.ui.treatment.treatmentInfo.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imed.medcare.R;
import com.imed.medcare.model.Document;
import com.imed.medcare.ui.treatment.treatmentInfo.TreatmentInfoContract;
import com.imed.medcare.ui.treatment.treatmentInfo.viewholders.DocumentAddViewHolder;
import com.imed.medcare.ui.treatment.treatmentInfo.viewholders.DocumentViewHolder;

import io.realm.RealmList;

/**
 * Created by Ramiro on 25-05-2018.
 */

public class DocumentAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ADD_DOCUMENT = 0;
    private static final int TYPE_DOCUMENT = 1;

    private RealmList<Document> data;

    private TreatmentInfoContract.View treatmentInfoListener;
    onItemListener listener;

    public DocumentAdapter(RealmList<Document> data, TreatmentInfoContract.View treatmentInfoFragment, onItemListener listener) {
        this.data = data;
        this.treatmentInfoListener = treatmentInfoFragment;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {

            case TYPE_ADD_DOCUMENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document_add, parent, false);
                return new DocumentAddViewHolder(view);

            case TYPE_DOCUMENT:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
                return new DocumentViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (data.get(position).getId() == -1 ) {
            ((DocumentAddViewHolder) holder).bind(treatmentInfoListener);
        }else {
            ((DocumentViewHolder) holder).bind(data.get(position), treatmentInfoListener,listener,position);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (data.get(position).getId() == -1) {
            return TYPE_ADD_DOCUMENT;
        }else {
            return TYPE_DOCUMENT;
        }
    }

    @Override
    public int getItemCount() {
        if(data.size()>5){
            return data.size();
        }else{
            return data.size();
        }
    }

    public void removeAt(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }


    public interface onItemListener{
        void onDeleteDocument(Document document,int position);
        void onShowDocument(Document document);
    }
}
