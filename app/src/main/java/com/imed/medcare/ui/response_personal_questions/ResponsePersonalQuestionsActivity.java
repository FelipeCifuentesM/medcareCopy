package com.imed.medcare.ui.response_personal_questions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.imed.medcare.R;
import com.imed.medcare.model.PersonalProfile;
import com.imed.medcare.ui.response_personal_questions.adapter.ResponsePersonalQuestionsAdapter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.imed.medcare.utils.MedcareUtils.dateFormatDocument;
import static com.imed.medcare.utils.MedcareUtils.hideKeyBoard;

public class ResponsePersonalQuestionsActivity extends AppCompatActivity implements ResponsePersonalQuestionsContract.View, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.content_progressbar_questions)
    RelativeLayout contentProgressbar;
    @BindView(R.id.btn_close)
    ImageButton btnClose;
    @BindView(R.id.btn_check)
    Button btnCheck;
    @BindView(R.id.title_item)
    TextView titleItem;
    @BindView(R.id.rv_option)
    RecyclerView rvOption;
    @BindView(R.id.content_questionnarie)
    RelativeLayout contentQuestionnarie;
    ResponsePersonalQuestionsContract.Presenter presenter;
    PersonalProfile personalProfile;
    TextInputEditText dateTextInputGlobal;
    ResponsePersonalQuestionsAdapter adapter;
    String valueGlobal;
    Integer viewType;
    public static final String EXTRA_DATA = "EXTRA_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_personal_questions);
        ButterKnife.bind(this);
        int id = getIntent().getIntExtra("id", 0);
        viewType = getIntent().getIntExtra("type",0);

        presenter = new ResponsePersonalQuestionsPresenterImpl(this);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(ResponsePersonalQuestionsActivity.this, contentQuestionnarie);
                finish();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(ResponsePersonalQuestionsActivity.this, contentQuestionnarie);
                presenter.setData(personalProfile,viewType);
            }
        });
        initializeView(id);
    }

    void initializeView(int id){
        personalProfile = new PersonalProfile(presenter.getData(id));
        titleItem.setText(personalProfile.getName());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvOption.setLayoutManager(linearLayoutManager);
        adapter = new ResponsePersonalQuestionsAdapter(personalProfile.getPersonalOptionsRealmList(), personalProfile.getCurrentName(), personalProfile.getType(), R.layout.item_questionnaire_button, R.layout.item_questionaire_input, new ResponsePersonalQuestionsAdapter.onItemListener() {
            @Override
            public void onButtonListener(String choiceId, String value) {
                presenter.setAnswer(value,choiceId,personalProfile);
            }

            @Override
            public void onTextChangeListener(String value, Integer type) {
                if(type != 4) {
                    presenter.setAnswer(value, null, personalProfile);
                }else {
                    if(valueGlobal != null) {
                        presenter.setAnswer(valueGlobal, null, personalProfile);
                    }else {
                        presenter.setAnswer(personalProfile.getCurrentName(), null, personalProfile);

                    }
                }
            }

            @Override
            public void setCalendar(TextInputEditText inputDate) {
                dateTextInputGlobal=inputDate;
                openDateSelector();
            }
        });
        rvOption.setAdapter(adapter);
    }


    void openDateSelector() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                ResponsePersonalQuestionsActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        setTheme(R.style.DialogPickerTheme);
        datePickerDialog.setFirstDayOfWeek(1);
        datePickerDialog.setMaxDate(now);
        datePickerDialog.setAccentColor(ContextCompat.getColor(this, R.color.marine_blue));
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
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
    public void showResult(String message) {
        hideKeyBoard(ResponsePersonalQuestionsActivity.this, contentQuestionnarie);
        final Intent data = new Intent();
        data.putExtra(EXTRA_DATA, message);
        setResult(Activity.RESULT_OK, data);
        finish();
    }

    @Override
    public void notifyDataSetChangedAdapter() {
        if(personalProfile.getType() == 2 ||personalProfile.getType() == 3) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar dateSetted = Calendar.getInstance();
        SimpleDateFormat datePicker = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("es", "ES"));
        dateSetted.set(year, monthOfYear, dayOfMonth);
        String strDate = datePicker.format(dateSetted.getTime());
        valueGlobal = strDate.split(" ")[0];
        dateTextInputGlobal.setText(dateFormatDocument(strDate));
    }
}
