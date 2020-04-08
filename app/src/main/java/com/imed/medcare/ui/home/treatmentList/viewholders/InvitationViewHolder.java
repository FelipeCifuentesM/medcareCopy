package com.imed.medcare.ui.home.treatmentList.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Professional;
import com.imed.medcare.ui.home.treatmentList.adapter.InvitationAdapter;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class InvitationViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.imageView18)
    CircleImageView ivDoctorAvatar;
    @BindView(R.id.textView22)
    TextView tvDoctorName;
    @BindView(R.id.textView26)
    TextView tvDoctorSpeciality;
    @BindView(R.id.ic_place_holder_invitation)
    LinearLayout icPlaceHolderInvitation;
    @BindView(R.id.button4)
    Button btnAcceptInvitation;
    private InvitationAdapter.onItemListener listener;
    private Professional professional;
    private int position;

    public InvitationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Professional professional, int position,InvitationAdapter.onItemListener listener){


        this.professional = professional;
        this.listener = listener;
        this.position = position;
        MedcareUtils.glideImage(ivDoctorAvatar, professional.getAvatar(), R.drawable.dummy_avatar, App.getContext());
        String surnameFormatted = MedcareUtils.getFormatted(professional.getSurnames());
        tvDoctorName.setText(professional.getPreFix() +" "+professional.getForenames()+" "+surnameFormatted);
        tvDoctorSpeciality.setText(professional.getSpeciality()+" - "+ professional.getBranchName());
        if(professional.getStatus() == 1){
            icPlaceHolderInvitation.setVisibility(View.GONE);
            btnAcceptInvitation.setVisibility(View.VISIBLE);
        }else{
            btnAcceptInvitation.setVisibility(View.GONE);
            icPlaceHolderInvitation.setVisibility(View.VISIBLE);
        }
    }
    @OnClick(R.id.button4)
    public void onViewClicked() {
        listener.acceptInvitation(professional, position);
    }
}
