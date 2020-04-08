package com.imed.medcare.ui.home.treatmentList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.imed.medcare.R;
import com.imed.medcare.model.Professional;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.ui.home.HomeContract;
import com.imed.medcare.ui.home.treatmentList.adapter.InvitationAdapter;
import com.imed.medcare.ui.home.treatmentList.adapter.TreatmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Ramiro on 08-05-2018.
 */

public class TreatmentListFragment extends Fragment implements TreatmentListContract.View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rv_invitation)
    RecyclerView rvInvitation;
    @BindView(R.id.content_treatment)
    ConstraintLayout contentTreatment;
    @BindView(R.id.treatment_empty)
    LinearLayout treatmentEmpty;
    @BindView(R.id.content_progressbar_home_treatment)
    RelativeLayout contentProgressbar;

    private HomeContract.View activityListener;
    private TreatmentListContract.Presenter treatmentPresenter;
    private InvitationAdapter invitationAdapter;
    private TreatmentAdapter treatmentAdapter;
    private Unbinder unbinder;
    private Professional professional;
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_treatment,container,false);
        unbinder = ButterKnife.bind(this,view);

        activityListener = (HomeContract.View) getActivity();
        treatmentPresenter = new TreatmentListPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        treatmentPresenter.getDataView();
    }

    @Override
    public void showTreatments(RealmList<Treatment> treatments, int sizeInvitation) {

        if(treatments.size() == 0 && sizeInvitation == 1) {
            treatmentEmpty.setVisibility(View.VISIBLE);
        }else {
            treatmentEmpty.setVisibility(View.GONE);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            treatmentAdapter = new TreatmentAdapter(treatments, this, getContext());
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(treatmentAdapter);
            recyclerView.invalidate();
        }
    }

    @Override
    public void showInvitations(RealmList<Professional> professionalRealmList) {

        if(professionalRealmList.size()==1){
            rvInvitation.setVisibility(View.GONE);
        }else {
            rvInvitation.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        invitationAdapter = new InvitationAdapter(R.layout.item_invitation_doctor, R.layout.item_title_invitation, professionalRealmList, new InvitationAdapter.onItemListener() {
            @Override
            public void acceptInvitation(Professional professional, int position) {
                treatmentPresenter.acceptInvitation(professional);
                TreatmentListFragment.this.professional = professional;
                TreatmentListFragment.this.position = position;
            }
        });
        rvInvitation.setLayoutManager(llm);
        rvInvitation.setAdapter(invitationAdapter);
        rvInvitation.invalidate();
    }

    @Override
    public void showStatus(String message) {
        Snackbar.make(contentTreatment, message, Snackbar.LENGTH_LONG).show();
        Realm realm =Realm.getDefaultInstance();
        realm.beginTransaction();
        professional.setStatus(2);
        realm.commitTransaction();
        realm.close();
        invitationAdapter.notifyItemChanged(position);
    }

    @Override
    public void manageLoader() {
        if (contentProgressbar.getVisibility() == View.VISIBLE) {
            contentProgressbar.setVisibility(View.GONE);
        } else {
            contentProgressbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showTreatment(int position) {
        Object item = treatmentAdapter.getItem(position);
        if(item instanceof Treatment){
            Treatment treatment = (Treatment) item;
            activityListener.showTreatment(treatment);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
