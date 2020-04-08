package com.imed.medcare.ui.treatment.treatmentInfo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.Document;
import com.imed.medcare.ui.treatment.treatmentInfo.TreatmentInfoContract;
import com.imed.medcare.ui.treatment.treatmentInfo.adapter.DocumentAdapter;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static com.imed.medcare.App.getContext;

/**
 * Created by Ramiro on 25-05-2018.
 */

public class DocumentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.document_name)
    TextView documentName;
    @BindView(R.id.document_img)
    ImageView documentImg;
    private TreatmentInfoContract.View treatmentInfoListener;
    DocumentAdapter.onItemListener listener;
    Document document;
    int position;
    public DocumentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }

    public void bind(Document document, TreatmentInfoContract.View treatmentInfoListener,DocumentAdapter.onItemListener listener,int position) {


        this.treatmentInfoListener = treatmentInfoListener;
        documentName.setText(document.getName());
        MedcareUtils.glideImage(documentImg,document.getPicture(),R.drawable.ic_file_placeholder,getContext());
        //Log.e("nombre "+ document.getName(), "ruta "+document.getPicture());
        this.listener = listener;
        this.document = document;
        this.position = position;
    }

    @OnClick(R.id.cardview_document)
    void onCardviewClicked(){
        listener.onShowDocument(document);
    }

    @OnLongClick(R.id.cardview_document)
    boolean onCardviewLongClicked(){
        listener.onDeleteDocument(document,position);
        return true;
    }
}
