package com.imed.medcare.ui.answer_questionnaire;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.imed.medcare.R;
import com.imed.medcare.model.TreatmentPollQuestion;
import com.imed.medcare.ui.answer_questionnaire.adapter.CustomPagerAdapter;
import com.imed.medcare.utils.MedcareUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class AnswerQuestionnaireActivity extends AppCompatActivity implements AnswerQuestionnaireContract.View, DatePickerDialog.OnDateSetListener  {

    @BindView(R.id.content_questionnarie)
    RelativeLayout contentQuestionnarie;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.btn_check)
    Button btnCheck;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.title_page)
    TextView titlePage;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.content_progressbar_answer)
    RelativeLayout contentProgressbar;
    CustomPagerAdapter adapter;
    AnswerQuestionnaireContract.Presenter presenter;
    private int totalPages;
    MaterialDialog materialDialog;
    TreatmentPollQuestion treatmentPollQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questionnaire);
        ButterKnife.bind(this);

        presenter = new AnswerQuestionnairePresenterImpl(this);
        presenter.getData();
    }

    @Override
    public void showData(RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmResults) {

        totalPages = treatmentPollQuestionRealmResults.size();
        titlePage.setText(1 + " de "+ totalPages);
        adapter = new CustomPagerAdapter(presenter,treatmentPollQuestionRealmResults,this);
        viewpager.setAdapter(adapter);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = viewpager.getCurrentItem() +1;
                viewpager.setCurrentItem(nextPage);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int backPage = viewpager.getCurrentItem() -1;
                viewpager.setCurrentItem(backPage);
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                int actualPage = position + 1;
                titlePage.setText(actualPage + " de "+ totalPages);

                if(actualPage == totalPages){
                    btnNext.setVisibility(View.GONE);
                }else {
                    btnNext.setVisibility(View.VISIBLE);
                }

                if(actualPage == 1){
                    btnBack.setVisibility(View.GONE);
                }else {
                    btnBack.setVisibility(View.VISIBLE);
                }



            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar dateSetted = Calendar.getInstance();
        SimpleDateFormat datePicker = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateSetted.set(year, monthOfYear, dayOfMonth);
        String strDate = datePicker.format(dateSetted.getTime());
        adapter.setDateTextInputGlobal(strDate);
    }


    @Override
    public void showError(String message) {
        MedcareUtils.hideKeyBoard(this, contentQuestionnarie);
        Snackbar snackbar = Snackbar
                .make(contentQuestionnarie, message, Snackbar.LENGTH_LONG);
        snackbar.show();
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
    public void successPostPollResponse() {
        Toast.makeText(this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
        overridePendingTransition(R.anim.stand, R.anim.slide_out_bottom);
        finish();
    }

    @Override
    public void notifyDataSetChangedAdapter() {

        adapter.notifyDataSetChangedTypeAnswer();
    }



    @Override
    public void deleteLocalValuesSuccess() {
        if (materialDialog != null && materialDialog.isShowing()) {
            materialDialog.dismiss();
        }
        finish();
    }

    @Override
    public void onVerifyLocalDataFound() {
        Typeface font_regular = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");
        materialDialog = new MaterialDialog.Builder(this)
                .title("¿Desea salir sin guardar?")
                .content("Ud tiene cambios sin guardar")
                .positiveText("Salir")
                .positiveColor(ContextCompat.getColor(this, R.color.subtitle_my_profile))
                .negativeText("Cancelar")
                .negativeColor(ContextCompat.getColor(this, R.color.dodger_blue))
                .buttonRippleColor(ContextCompat.getColor(this, R.color.blue_grey))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        presenter.deleteLocalValues(treatmentPollQuestion);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .typeface(font_regular,font_regular)
                .show();
    }

    @Override
    public void onVerifyLocalDataNotFound() {
        finish();
    }


}
