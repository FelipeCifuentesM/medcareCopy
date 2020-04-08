package com.imed.medcare.ui.questionnaire_user;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;

import com.imed.medcare.model.UserPoll;
import com.imed.medcare.model.UserPollQuestion;

import com.imed.medcare.ui.questionnaire_user.adapter.QuestionnaireUserAdapter;
import com.imed.medcare.utils.MedcareUtils;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;

import static com.imed.medcare.utils.Constants.DATA_POSITION;
import static com.imed.medcare.utils.Constants.DATA_TYPE;
import static com.imed.medcare.utils.Constants.ID_USERPOLLQUESTION_ID;
import static com.imed.medcare.utils.MedcareUtils.dateFormatDocument;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;


public class QuestionnaireUser extends AppCompatActivity implements QuestionnaireUserContract.View, DatePickerDialog.OnDateSetListener {


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
    @BindView(R.id.tv_last_measurement_item)
    TextView tvLastMeasurementItem;
    @BindView(R.id.content_questionnarie)
    RelativeLayout contentQuestionnarie;
    @BindView(R.id.content_progressbar_questionnaire)
    RelativeLayout contentProgressbar;
    MaterialDialog materialDialog;
    QuestionnaireUserAdapter adapter;
    int positionSelected;
    int position;
    int userPollId;
    QuestionnaireUserContract.Presenter presenter;
    UserPoll userPoll;
    UserPollQuestion userPollQuestion;
    TextInputEditText dateTextInputGlobal;
    RealmList<AnswerPoll> answerPollRealmList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            dataType = getIntent().getStringExtra(DATA_TYPE);
            position = getIntent().getIntExtra(DATA_POSITION, 0);
            userPollId = getIntent().getIntExtra(ID_USERPOLLQUESTION_ID, 0);
        }

        positionSelected = position;
        presenter = new QuestionnaireUserPresenter(this);
        presenter.getValues(userPollId);

    }

    void setNewSelectedItem(Integer idChoice,String value,String date) {

        presenter.setNewAnswer(answerPollRealmList,userPollQuestion, idChoice, value,date);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.stand, R.anim.slide_out_bottom);
    }


    public void showDialogPollReady() {
        final Dialog dialog = new Dialog(QuestionnaireUser.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_poll_ready);
        Button dialogButton = dialog.findViewById(R.id.btn_done);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    @OnClick({R.id.btn_close, R.id.btn_check, R.id.btn_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                presenter.verifyLocalData(userPollQuestion);
                break;
            case R.id.btn_check:

                presenter.postPollResponse(userPoll);

                break;
            case R.id.btn_back:
                MedcareUtils.hideKeyBoard(this, view);
                positionSelected--;
                titlePage.setText(String.valueOf(positionSelected + 1) + " de " + String.valueOf(userPoll.getuserPollQuestionsRealmList().size()));
                if (positionSelected == 0) {
                    btnBack.setVisibility(View.GONE);
                }
                if (btnNext.getVisibility() == View.GONE) {
                    btnNext.setVisibility(View.VISIBLE);
                }
                userPollQuestion = userPoll.getuserPollQuestionsRealmList().get(positionSelected);
                adapter = new QuestionnaireUserAdapter(userPollQuestion.getChoices(), userPollQuestion.getAnswerPolls(),userPollQuestion.getMeasuremtPoll(), userPollQuestion.getType(), R.layout.item_questionnaire_button, R.layout.item_questionaire_input,R.layout.item_questionaire_input_and_date, new QuestionnaireUserAdapter.onItemListener() {
                    @Override
                    public void onButtonListener(Integer idChoice,String value) {
                        setNewSelectedItem(idChoice,value,null);
                    }

                    @Override
                    public void onTextChangeListener(String value) {
                        setNewSelectedItem(null,value,null);
                    }

                    @Override
                    public void onDateAndTextChangeListener(String value, String date) {
                        setNewSelectedItem(null,value,date);
                    }

                    @Override
                    public void setCalendar(TextInputEditText textInputLayoutDate) {
                        openDateSelector();
                        dateTextInputGlobal = textInputLayoutDate;
                    }
                });
                titleItem.setVisibility(View.GONE);
                titleItem.setText(userPollQuestion.getDescription());


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
                        bodyContent.setVisibility(View.VISIBLE);
                    }
                }, 500);
                break;
            case R.id.btn_next:
                MedcareUtils.hideKeyBoard(this, view);
                positionSelected++;
                titlePage.setText(String.valueOf(positionSelected + 1) + " de " + String.valueOf(userPoll.getuserPollQuestionsRealmList().size()));
                if ((positionSelected + 1) == userPoll.getuserPollQuestionsRealmList().size()) {
                    btnNext.setVisibility(View.GONE);
                }
                if (btnBack.getVisibility() == View.GONE) {
                    btnBack.setVisibility(View.VISIBLE);
                }
                userPollQuestion = userPoll.getuserPollQuestionsRealmList().get(positionSelected);


                TransitionManager.beginDelayedTransition(bodyContent,
                        new TransitionSet()
                                .addTransition(new Fade())
                                .addTransition(new Slide(Gravity.LEFT)));

                titleItem.setText(userPollQuestion.getDescription());
                titleItem.setVisibility(View.GONE);
                bodyContent.setVisibility(View.GONE);



                adapter = new QuestionnaireUserAdapter(userPollQuestion.getChoices(), userPollQuestion.getAnswerPolls(),userPollQuestion.getMeasuremtPoll(), userPollQuestion.getType(), R.layout.item_questionnaire_button, R.layout.item_questionaire_input,R.layout.item_questionaire_input_and_date, new QuestionnaireUserAdapter.onItemListener() {
                    @Override
                    public void onButtonListener(Integer idChoice,String value) {
                        setNewSelectedItem(idChoice,value,null);
                    }

                    @Override
                    public void onTextChangeListener(String value) {
                        setNewSelectedItem(null,value,null);
                    }

                    @Override
                    public void onDateAndTextChangeListener(String value, String date) {
                        setNewSelectedItem(null,value,date);
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
                        bodyContent.setVisibility(View.VISIBLE);
                    }
                }, 500);


                break;
        }
    }

    @Override
    public void setValues(UserPoll userPoll,RealmList<AnswerPoll> answerPollRealmList) {
        this.userPoll = userPoll;
        this.answerPollRealmList = answerPollRealmList;
        titleItem.setText(userPoll.getDescription());
        tvLastMeasurementItem.setVisibility(View.VISIBLE);



        titlePage.setText(String.valueOf(positionSelected + 1) + " de " + String.valueOf(userPoll.getuserPollQuestionsRealmList().size()));
        if ((positionSelected + 1) == userPoll.getuserPollQuestionsRealmList().size()) {
            btnNext.setVisibility(View.GONE);
        }
        if (positionSelected == 0) {
            btnBack.setVisibility(View.GONE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvOption.setLayoutManager(linearLayoutManager);
        userPollQuestion = userPoll.getuserPollQuestionsRealmList().get(positionSelected);
        titleItem.setText(userPollQuestion.getDescription());
        adapter = new QuestionnaireUserAdapter(userPollQuestion.getChoices(), userPollQuestion.getAnswerPolls(),userPollQuestion.getMeasuremtPoll(), userPollQuestion.getType(), R.layout.item_questionnaire_button, R.layout.item_questionaire_input,R.layout.item_questionaire_input_and_date, new QuestionnaireUserAdapter.onItemListener() {
            @Override
            public void onButtonListener(Integer idChoice,String value) {
                setNewSelectedItem(idChoice,value,null);
            }

            @Override
            public void onTextChangeListener(String value) {
                setNewSelectedItem(null,value,null);

            }

            @Override
            public void onDateAndTextChangeListener(String value, String date) {
                setNewSelectedItem(null,value,date);
            }

            @Override
            public void setCalendar(TextInputEditText textInputLayoutDate) {
                openDateSelector();
                dateTextInputGlobal = textInputLayoutDate;
            }
        });
        rvOption.setAdapter(adapter);
    }

    void openDateSelector(){
        Toast.makeText(this,"Tema: "+getTheme(),Toast.LENGTH_LONG).show();
        Calendar now = Calendar.getInstance();
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.YEAR, -35);
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                QuestionnaireUser.this,
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
    public void successPostPollResponse(boolean allResponded) {

        if(allResponded){
            showDialogPollReady();
        }else {
            Toast.makeText(QuestionnaireUser.this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
            overridePendingTransition(R.anim.stand, R.anim.slide_out_bottom);
            finish();
        }
    }

    @Override
    public void notifyDataSetChangedAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onVerifyLocalDataFound() {
        Typeface font_regular = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");
        materialDialog = new MaterialDialog.Builder(QuestionnaireUser.this)
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
                        presenter.deleteLocalValues(userPollQuestion);
                        dialog.dismiss();
                        finish();
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
        SimpleDateFormat datePicker = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateSetted.set(year, monthOfYear, dayOfMonth);
        String strDate = datePicker.format(dateSetted.getTime());

        dateTextInputGlobal.setText(dateFormatDocument(strDate));
    }
}
