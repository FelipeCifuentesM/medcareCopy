package com.imed.medcare.ui.home.treatmentList.viewholders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Professional;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.ui.home.treatmentList.TreatmentListContract;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.imed.medcare.network.RestClient.BASE_URL;

/**
 * Created by Ramiro on 23-05-2018.
 */

public class TreatmentViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.treatment_name)
    TextView treatmentName;
    @BindView(R.id.doctor_name)
    TextView medicName;
    @BindView(R.id.textView26)
    TextView speciality;
    @BindView(R.id.empty_doctor_image)
    CircleImageView ivDoctor;


    private Context context;
    private TreatmentListContract.View treatmentFragmentListener;

    public TreatmentViewHolder(View itemView, TreatmentListContract.View treatmentFragmentListener, Context context) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        this.treatmentFragmentListener = treatmentFragmentListener;
        this.context = context;
    }

    public void bind(Treatment treatment, final int position){

        Log.e("MEDICAVATAR", treatment.getProfessional().getAvatar());
        MedcareUtils.glideImage(ivDoctor,BASE_URL+"professional/avatar/"+treatment.getProfessional().getAvatar(),R.drawable.dummy_avatar, App.getContext());

        if(treatment.getTreatmentName() != null)
            treatmentName.setText(treatment.getTreatmentName());

        if(treatment.getProfessional().getName() != null) {
            String surnameFormatted = MedcareUtils.getFormatted(treatment.getProfessional().getName());
            medicName.setText(treatment.getProfessional().getPreFix() + " " + surnameFormatted);
        }
        if(treatment.getProfessional().getSpeciality() != null)
            speciality.setText(treatment.getProfessional().getSpeciality()+" - "+treatment.getProfessional().getBranchName());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treatmentFragmentListener.showTreatment(position);
            }
        });
    }

}
