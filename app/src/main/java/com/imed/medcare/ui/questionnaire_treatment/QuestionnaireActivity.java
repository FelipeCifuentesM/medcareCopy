package com.imed.medcare.ui.questionnaire_treatment;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;
import com.imed.medcare.ui.questionnaire_treatment.adapter.QuestionnaireAdapter;
import com.imed.medcare.utils.BaseActivity;
import com.imed.medcare.utils.MedcareUtils;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;

import static com.imed.medcare.utils.Constants.DATA_POSITION;
import static com.imed.medcare.utils.Constants.DATA_TYPE;
import static com.imed.medcare.utils.Constants.ID_TREATMENTPOLLQUESTION_ID;
import static com.imed.medcare.utils.MedcareUtils.dateFormatDocument;

/**
 * Created by Ramiro on 08-06-2018.
 */

public class QuestionnaireActivity extends BaseActivity implements QuestionnaireContract.View, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.title_page)
    TextView titlePage;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.rv_option)
    RecyclerView rvOption;
    @BindView(R.id.title_item)
    TextView titleItem;
    @BindView(R.id.body_content)
    ViewGroup bodyContent;
    String dataType;
    @BindView(R.id.tv_body_item)
    TextView tvBodyItem;
    @BindView(R.id.tv_last_measurement_item)
    TextView tvLastMeasurementItem;
    @BindView(R.id.content_questionnarie)
    RelativeLayout contentQuestionnarie;
    MaterialDialog materialDialog;
    QuestionnaireAdapter adapter;
    int positionSelected;
    int position;
    int treatmentPollId;
    QuestionnaireContract.Presenter presenter;
    TreatmentPoll treatmentPoll;
    TreatmentPollQuestion treatmentPollQuestion;
    TextInputEditText dateTextInputGlobal;
    RealmList<AnswerPoll> answerPollRealmList;
    @BindView(R.id.tv_last_answer)
    TextView tvLastAnswer;
    @BindView(R.id.tv_last_date)
    TextView tvLastDate;
    @BindView(R.id.content_progressbar_questionnaire)
    RelativeLayout contentProgressbar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            dataType = getIntent().getStringExtra(DATA_TYPE);
            position = getIntent().getIntExtra(DATA_POSITION, 0);
            treatmentPollId = getIntent().getIntExtra(ID_TREATMENTPOLLQUESTION_ID, 0);
        }

        positionSelected = position;
        presenter = new QuestionnairePresent(this);
        presenter.getValues(treatmentPollId);

    }

    void setNewSelectedItem(Integer idChoice, String value, String date) {

        presenter.setNewAnswer(answerPollRealmList, treatmentPollQuestion, idChoice, value, date);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.stand, R.anim.slide_out_bottom);
    }

    @OnClick({R.id.btn_close, R.id.btn_check, R.id.btn_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                presenter.verifyLocalData(treatmentPollQuestion);
                break;
            case R.id.btn_check:

                presenter.postPollResponse(treatmentPoll);

                break;
            case R.id.btn_back:
                MedcareUtils.hideKeyBoard(this, view);
                positionSelected--;
                titlePage.setText(String.valueOf(positionSelected + 1) + " de " + String.valueOf(treatmentPoll.getTreatmentPollQuestionsRealmList().size()));
                if (positionSelected == 0) {
                    btnBack.setVisibility(View.GONE);
                }
                if (btnNext.getVisibility() == View.GONE) {
                    btnNext.setVisibility(View.VISIBLE);
                }
                treatmentPollQuestion = treatmentPoll.getTreatmentPollQuestionsRealmList().get(positionSelected);
                adapter = new QuestionnaireAdapter(treatmentPollQuestion.getChoices(), treatmentPollQuestion.getAnswerPolls(), treatmentPollQuestion.getMeasuremtPoll(), treatmentPollQuestion.getType(), R.layout.item_questionnaire_button, R.layout.item_questionaire_input, R.layout.item_questionaire_input_and_date, new QuestionnaireAdapter.onItemListener() {
                    @Override
                    public void onButtonListener(Integer idChoice, String value) {
                        setNewSelectedItem(idChoice, value, null);
                    }

                    @Override
                    public void onTextChangeListener(String value, String date) {
                        setNewSelectedItem(null, value, date);
                    }

                    @Override
                    public void onDateAndTextChangeListener(String value, String date) {
                        setNewSelectedItem(null, value, date);
                    }

                    @Override
                    public void setCalendar(TextInputEditText textInputLayoutDate) {
                        openDateSelector();
                        dateTextInputGlobal = textInputLayoutDate;
                    }
                });
                titleItem.setVisibility(View.GONE);
                tvBodyItem.setVisibility(View.GONE);
                titleItem.setText(treatmentPollQuestion.getTitle());
                tvBodyItem.setText(treatmentPollQuestion.getDescription());


                TransitionManager.beginDelayedTransition(bodyContent,
                        new TransitionSet()
                                .addTransition(new Fade())
                                .addTransition(new Slide(Gravity.RIGHT)));
                bodyContent.setVisibility(View.GONE);
                rvOption.setAdapter(adapter);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        titleItem.setVisibility(View.VISIBLE);
                        tvBodyItem.setVisibility(View.VISIBLE);
                        bodyContent.setVisibility(View.VISIBLE);
                    }
                }, 500);
                break;
            case R.id.btn_next:
                MedcareUtils.hideKeyBoard(this, view);
                positionSelected++;
                titlePage.setText(String.valueOf(positionSelected + 1) + " de " + String.valueOf(treatmentPoll.getTreatmentPollQuestionsRealmList().size()));
                if ((positionSelected + 1) == treatmentPoll.getTreatmentPollQuestionsRealmList().size()) {
                    btnNext.setVisibility(View.GONE);
                }
                if (btnBack.getVisibility() == View.GONE) {
                    btnBack.setVisibility(View.VISIBLE);
                }
                treatmentPollQuestion = treatmentPoll.getTreatmentPollQuestionsRealmList().get(positionSelected);


                TransitionManager.beginDelayedTransition(bodyContent,
                        new TransitionSet()
                                .addTransition(new Fade())
                                .addTransition(new Slide(Gravity.LEFT)));

                titleItem.setText(treatmentPollQuestion.getTitle());
                tvBodyItem.setText(treatmentPollQuestion.getDescription());
                titleItem.setVisibility(View.GONE);
                tvBodyItem.setVisibility(View.GONE);
                bodyContent.setVisibility(View.GONE);


                adapter = new QuestionnaireAdapter(treatmentPollQuestion.getChoices(), treatmentPollQuestion.getAnswerPolls(), treatmentPollQuestion.getMeasuremtPoll(), treatmentPollQuestion.getType(), R.layout.item_questionnaire_button, R.layout.item_questionaire_input, R.layout.item_questionaire_input_and_date, new QuestionnaireAdapter.onItemListener() {
                    @Override
                    public void onButtonListener(Integer idChoice, String value) {
                        setNewSelectedItem(idChoice, value, null);
                    }

                    @Override
                    public void onTextChangeListener(String value, String date) {
                        setNewSelectedItem(null, value, date);
                    }

                    @Override
                    public void onDateAndTextChangeListener(String value, String date) {
                        setNewSelectedItem(null, value, date);
                    }

                    @Override
                    public void setCalendar(TextInputEditText textInputLayoutDate) {
                        openDateSelector();
                        dateTextInputGlobal = textInputLayoutDate;
                    }
                });
                rvOption.setAdapter(adapter);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        titleItem.setVisibility(View.VISIBLE);
                        tvBodyItem.setVisibility(View.VISIBLE);
                        bodyContent.setVisibility(View.VISIBLE);
                    }
                }, 500);


                break;
        }
    }

    @Override
    public void setValues(TreatmentPoll treatmentPoll, RealmList<AnswerPoll> answerPollRealmList) {
        this.treatmentPoll = treatmentPoll;
        this.answerPollRealmList = answerPollRealmList;
        tvBodyItem.setVisibility(View.VISIBLE);
        tvBodyItem.setText(treatmentPoll.getDescription());
        tvLastMeasurementItem.setVisibility(View.VISIBLE);


        titlePage.setText(String.valueOf(positionSelected + 1) + " de " + String.valueOf(treatmentPoll.getTreatmentPollQuestionsRealmList().size()));
        if ((positionSelected + 1) == treatmentPoll.getTreatmentPollQuestionsRealmList().size()) {
            btnNext.setVisibility(View.GONE);
        }
        if (positionSelected == 0) {
            btnBack.setVisibility(View.GONE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvOption.setLayoutManager(linearLayoutManager);
        treatmentPollQuestion = treatmentPoll.getTreatmentPollQuestionsRealmList().get(positionSelected);
        tvLastAnswer.setText(treatmentPollQuestion.getLastAnswer());
        tvLastDate.setText(treatmentPollQuestion.getLastDate());
        titleItem.setText(treatmentPollQuestion.getTitle());
        tvBodyItem.setText(treatmentPollQuestion.getDescription());
        adapter = new QuestionnaireAdapter(treatmentPollQuestion.getChoices(), treatmentPollQuestion.getAnswerPolls(), treatmentPollQuestion.getMeasuremtPoll(), treatmentPollQuestion.getType(), R.layout.item_questionnaire_button, R.layout.item_questionaire_input, R.layout.item_questionaire_input_and_date, new QuestionnaireAdapter.onItemListener() {
            @Override
            public void onButtonListener(Integer idChoice, String value) {
                setNewSelectedItem(idChoice, value, null);
            }

            @Override
            public void onTextChangeListener(String value, String date) {
                setNewSelectedItem(null, value, date);

            }

            @Override
            public void onDateAndTextChangeListener(String value, String date) {
                setNewSelectedItem(null, value, date);
            }

            @Override
            public void setCalendar(TextInputEditText textInputLayoutDate) {
                openDateSelector();
                dateTextInputGlobal = textInputLayoutDate;
            }
        });
        rvOption.setAdapter(adapter);
    }

    void openDateSelector() {
        Calendar now = Calendar.getInstance();
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.YEAR, -35);

        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance
                (
                QuestionnaireActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        setTheme(R.style.DialogPickerTheme);
        datePickerDialog.setFirstDayOfWeek(1);
        datePickerDialog.setMaxDate(now);
        datePickerDialog.setMinDate(minDate);
        datePickerDialog.setAccentColor(ContextCompat.getColor(this, R.color.marine_blue));
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
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
        Toast.makeText(QuestionnaireActivity.this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
        overridePendingTransition(R.anim.stand, R.anim.slide_out_bottom);
        finish();
    }

    @Override
    public void notifyDataSetChangedAdapter() {
        adapter.notifyDataSetChanged();
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
        materialDialog = new MaterialDialog.Builder(QuestionnaireActivity.this)
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

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar dateSetted = Calendar.getInstance();
        SimpleDateFormat datePicker = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateSetted.set(year, monthOfYear, dayOfMonth);
        String strDate = datePicker.format(dateSetted.getTime());
        dateTextInputGlobal.setText(dateFormatDocument(strDate));

    }
}
