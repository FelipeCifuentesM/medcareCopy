package com.imed.medcare.ui.treatment.treatmentInfo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.imed.medcare.R;
import com.imed.medcare.ui.treatment.treatmentInfo.TreatmentInfoContract;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ramiro on 25-05-2018.
 */

public class DocumentAddViewHolder extends RecyclerView.ViewHolder{
    private TreatmentInfoContract.View treatmentInfoListener;

    public DocumentAddViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(TreatmentInfoContract.View treatmentInfoListener) {
        this.treatmentInfoListener = treatmentInfoListener;
    }

    @OnClick(R.id.cardview_add_document)
    void onCardviewClicked(){
        treatmentInfoListener.addDocument();
    }
}
