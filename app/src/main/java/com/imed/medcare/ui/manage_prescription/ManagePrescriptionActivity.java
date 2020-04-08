package com.imed.medcare.ui.manage_prescription;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.imed.medcare.R;
import com.imed.medcare.model.Pillbox;
import com.imed.medcare.ui.manage_prescription.adapter.ManagePrescriptionAdapter;
import com.imed.medcare.ui.manage_prescription.fragment.SkipPrescriptionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class ManagePrescriptionActivity extends AppCompatActivity implements ManagePrescriptionContract.View {

    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.rv_medicine)
    RecyclerView rvMedicine;
    ManagePrescriptionContract.Presenter presenter;
    @BindView(R.id.btn_take_medicine)
    Button btnTakeMedicine;
    @BindView(R.id.btn_skip_medicine)
    Button btnSkipMedicine;
    @BindView(R.id.content_manage_prescription)
    ConstraintLayout contentManagePrescription;
    String dateSelected;
    RealmList<Pillbox> pillboxRealmList;
    SkipPrescriptionFragment skipPrescriptionFragment;
    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_prescription);
        ButterKnife.bind(this);


        dateSelected = getIntent().getStringExtra("date");
        presenter = new ManagePrescriptionPresenterImpl(this);
        pillboxRealmList = presenter.getListPillbox();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final ManagePrescriptionAdapter adapter = new ManagePrescriptionAdapter(pillboxRealmList, R.layout.item_medicine, new ManagePrescriptionAdapter.onItemListener() {
            @Override
            public void delete(Pillbox pillbox, int position) {
                pillboxRealmList.remove(pillbox);
                if(pillboxRealmList.size()==0){
                    overridePendingTransition(R.anim.stand, R.anim.slide_out_bottom);
                    finish();
                }
            }
        });
        rvMedicine.setLayoutManager(linearLayoutManager);
        rvMedicine.setAdapter(adapter);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.stand, R.anim.slide_out_bottom);
                finish();
            }
        });

        btnTakeMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.managePrescription(pillboxRealmList, dateSelected, "1", "null");
            }
        });
        skipPrescriptionFragment = SkipPrescriptionFragment.newInstance(this);
        btnSkipMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipPrescriptionFragment.show(getSupportFragmentManager(), "skipPrescriptionFragment");
            }
        });

    }

    @Override
    public void showMessage(String message) {
        if(message.contains("Éxito") || message.contains("exitosamente")) {
            final Intent data = new Intent();
            data.putExtra(EXTRA_DATA, message);
            setResult(Activity.RESULT_OK, data);
            finish();
        }else {
            Snackbar.make(contentManagePrescription, message, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void setReason(String reason) {
        presenter.managePrescription(pillboxRealmList, dateSelected, "2", reason);
    }

    @Override
    public void closeBottomSheet() {
        if (skipPrescriptionFragment != null && skipPrescriptionFragment.isVisible()) {
            skipPrescriptionFragment.dismiss();
        }
    }
}
