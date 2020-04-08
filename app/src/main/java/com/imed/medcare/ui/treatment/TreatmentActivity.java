package com.imed.medcare.ui.treatment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.imed.medcare.R;
import com.imed.medcare.model.Document;
import com.imed.medcare.ui.history_list.HistoryListActivity;
import com.imed.medcare.ui.all_documents.AllDocumentsActivity;
import com.imed.medcare.ui.menu_questionnaire_medic.MenuQuestionnaireMedicActivity;
import com.imed.medcare.ui.show_document.ShowDocument;
import com.imed.medcare.ui.treatment.treatmentDetail.TreatmentDetailFragment;
import com.imed.medcare.ui.treatment.treatmentInfo.TreatmentInfoFragment;
import com.imed.medcare.ui.treatment_poll_menu.PollMenuActivity;
import com.imed.medcare.utils.BaseActivity;
import com.imed.medcare.utils.Constants;
import com.imed.medcare.utils.ErrorDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.imed.medcare.utils.Constants.ID_POLLTREATMENT;
import static com.imed.medcare.utils.Constants.ID_TREATMENT;

/**
 * Created by Ramiro on 23-05-2018.
 */

public class TreatmentActivity extends BaseActivity implements TreatmentContract.View {

    private TreatmentContract.Presenter treatmentPresenter;
    private int idTreatment;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.content_progressbar_treatment)
    RelativeLayout contentProgressbar;
    TreatmentInfoFragment treatmentInfoFragment;
    Bundle bundle;
    int idPollTreatment;
    private long lastClickTime = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_treatment);
        ButterKnife.bind(this);

        this.treatmentPresenter = new TreatmentPresent(this);

        bundle = new Bundle();
        idTreatment = getIntent().getIntExtra(ID_TREATMENT, 0);
        bundle.putInt(ID_TREATMENT, idTreatment);
        treatmentPresenter.getPolls(idTreatment);
    }

    @Override
    public void showDetail() {
        Bundle bundle = new Bundle();
        TreatmentDetailFragment treatmentDetailFragment = new TreatmentDetailFragment();
        bundle.putInt(ID_TREATMENT, idTreatment);
        treatmentDetailFragment.setArguments(bundle);
        showFragment(treatmentDetailFragment);
    }

    @Override
    public void showPollTreatment() {


        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        Intent intent = new Intent(TreatmentActivity.this, PollMenuActivity.class);
        intent.putExtra(ID_POLLTREATMENT, idPollTreatment);
        startActivity(intent);
    }

    @Override
    public void showPollMedic() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        Intent intent = new Intent(TreatmentActivity.this, MenuQuestionnaireMedicActivity.class);
        intent.putExtra(ID_TREATMENT, idTreatment);
        startActivity(intent);
    }

    @Override
    public void seeAllDocuments(int idTreatment) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        Intent intent = new Intent(TreatmentActivity.this, AllDocumentsActivity.class);
        intent.putExtra(ID_TREATMENT, idTreatment);
        startActivity(intent);
    }

    @Override
    public void showHistorical() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        Intent intent = new Intent(this, HistoryListActivity.class);
        startActivity(intent);
    }


    @Override
    public void showDocument(Document document) {

        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        Intent intent = new Intent(TreatmentActivity.this, ShowDocument.class);
        intent.putExtra(Constants.ID_DOCUMENT, document.getId());
        intent.putExtra(Constants.NAME_DOCUMENT, document.getName());
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() == 1)
            finish();
        else
            super.onBackPressed();
    }

    @Override
    public void backPressed() {
        onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void showLoading() {
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void manageLoader() {
        if(contentProgressbar.getVisibility() == View.VISIBLE){
            contentProgressbar.setVisibility(View.GONE);
        }else {
            contentProgressbar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showError(String message) {
        //progressBar.setVisibility(View.GONE);
        ErrorDialog.ShowErrorDialog(this, message);
    }

    @Override
    public void successPolls(int idPollTreatment) {
        this.idPollTreatment = idPollTreatment;
        //progressBar.setVisibility(View.GONE);
        bundle.putInt(Constants.ID_POLLTREATMENT, idPollTreatment);
        treatmentInfoFragment = new TreatmentInfoFragment();
        treatmentInfoFragment.setArguments(bundle);
        setFirstFragment(treatmentInfoFragment);
    }

}
