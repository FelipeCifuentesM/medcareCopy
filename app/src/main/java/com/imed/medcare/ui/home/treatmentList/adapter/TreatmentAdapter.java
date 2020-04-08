package com.imed.medcare.ui.home.treatmentList.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imed.medcare.R;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.ui.home.treatmentList.TreatmentListContract;
import com.imed.medcare.ui.home.treatmentList.viewholders.TreatmentViewHolder;

import io.realm.RealmList;

/**
 * Created by Ramiro on 23-05-2018.
 */

public class TreatmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



    private RealmList<Treatment> data;

    private TreatmentListContract.View treatmentListener;
    private Context context;

    public TreatmentAdapter(RealmList<Treatment> treatments, TreatmentListContract.View treatmentFragment, Context context) {
        this.data = treatments;
        this.context = context;
        this.treatmentListener = treatmentFragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_treatment, parent, false);
        return new TreatmentViewHolder(view,treatmentListener,context);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TreatmentViewHolder) holder).bind(data.get(position),position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }
}
