package com.imed.medcare.ui.treatment.treatmentDetail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.ui.treatment.TreatmentContract;
import com.imed.medcare.utils.Constants;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.imed.medcare.network.RestClient.BASE_URL;
import static com.imed.medcare.utils.MedcareUtils.dateInfoTreatment;

/**
 * Created by Ramiro on 06-06-2018.
 */

public class TreatmentDetailFragment extends Fragment implements TreatmentDetailContract.View {
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout ctLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_back)
    ImageView navBack;
    @BindView(R.id.doctor_image)
    CircleImageView doctorImage;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.doctor_establishment)
    TextView doctorEstablishment;
    @BindView(R.id.treatment_date)
    TextView treatmentDate;
    TreatmentDetailContract.Presenter presenter;
    private TreatmentContract.View activityListener;
    private Unbinder unbinder;
    private long lastClickTime = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treatment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new TreatmentDetailPresenter(this);
        activityListener = (TreatmentContract.View) getActivity();
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/poppins_regular.ttf");

        presenter.getTreatment(getArguments().getInt(Constants.ID_TREATMENT));
        ctLayout.setCollapsedTitleTypeface(font);
        ctLayout.setExpandedTitleTypeface(font);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ctLayout.setExpandedTitleColor(getResources().getColor(R.color.secondary_text_color));
        ctLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.secondary_text_color));


    }

    @OnClick(R.id.nav_back)
    void onNavBackClicked() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        activityListener.backPressed();
    }

    @OnClick(R.id.show_questionnaire)
    void onShowQuestionnaire() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        activityListener.showPollMedic();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setTreatment(Treatment treatment) {
        ctLayout.setTitle("Detalle tratamiento");
        String surnameFormatted = MedcareUtils.getFormatted(treatment.getProfessional().getName());
        doctorName.setText(treatment.getProfessional().getPreFix() + " " + surnameFormatted);
        doctorEstablishment.setText(treatment.getProfessional().getSpeciality()+" - "+ treatment.getProfessional().getBranchName());
        MedcareUtils.glideImage(doctorImage,BASE_URL+"professional/avatar/"+treatment.getProfessional().getAvatar(),R.drawable.dummy_avatar,getContext());
        treatmentDate.setText(dateInfoTreatment(treatment.getCreatedAt()));
    }
}
