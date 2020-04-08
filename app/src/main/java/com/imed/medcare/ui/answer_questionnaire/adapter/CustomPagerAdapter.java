package com.imed.medcare.ui.answer_questionnaire.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.TreatmentPollQuestion;
import com.imed.medcare.ui.answer_questionnaire.AnswerQuestionnaireContract;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import io.realm.RealmResults;

import static com.imed.medcare.utils.MedcareUtils.dateFormatDocument;

public class CustomPagerAdapter extends PagerAdapter {

    private RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmResults;
    private Context context;
    private Activity activity;
    private AnswerQuestionnaireContract.Presenter presenter;
    TypeAnswerAdapter adapter;
    private EditText textInputLayoutDate;
    private int rvPosition;

    public CustomPagerAdapter(AnswerQuestionnaireContract.Presenter presenter,RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmResults, Activity activity){
        this.treatmentPollQuestionRealmResults =treatmentPollQuestionRealmResults;
        this.activity = activity;
        context = App.getContext();
        this.presenter = presenter;

    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup)inflater.inflate(R.layout.item_answer_questionnaire, container, false);



        TextView tvTitle = layout.findViewById(R.id.tv_title_item);
        TextView tvBody  = layout.findViewById(R.id.tv_body_item);
        RecyclerView rvOption = layout.findViewById(R.id.rv_option);

        tvTitle.setText(treatmentPollQuestionRealmResults.get(position).getTitle());
        tvBody.setText(treatmentPollQuestionRealmResults.get(position).getDescription());

        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(activity);

        adapter = new TypeAnswerAdapter(activity, treatmentPollQuestionRealmResults.get(position).getChoices(),treatmentPollQuestionRealmResults.get(position).getAnswerPolls(),treatmentPollQuestionRealmResults.get(position).getMeasuremtPoll(),treatmentPollQuestionRealmResults.get(position).getType(), R.layout.item_questionnaire_button, R.layout.item_questionaire_input, R.layout.item_questionaire_input_and_date, new TypeAnswerAdapter.onItemListener() {
            @Override
            public void onButtonListener(Integer idChoice, String value, int rvPosition) {

                Log.i("instantiateItem",String.valueOf(treatmentPollQuestionRealmResults.get(position).getAnswerPolls().size()));
                CustomPagerAdapter.this.rvPosition = rvPosition;
                presenter.setNewAnswer(treatmentPollQuestionRealmResults.get(position).getAnswerPolls(), treatmentPollQuestionRealmResults.get(position), idChoice, value, null);

            }

            @Override
            public void onTextChangeListener(String value, String date) {

                presenter.setNewAnswer(treatmentPollQuestionRealmResults.get(position).getAnswerPolls(), treatmentPollQuestionRealmResults.get(position), null, value, date);

            }

            @Override
            public void onDateAndTextChangeListener(String value, String date) {
                presenter.setNewAnswer(treatmentPollQuestionRealmResults.get(position).getAnswerPolls(), treatmentPollQuestionRealmResults.get(position), null, value, date);
            }

            @Override
            public void setCalendar(TextInputEditText textInputLayoutDate) {
                CustomPagerAdapter.this.textInputLayoutDate = textInputLayoutDate;
                openDateSelector();
            }
        });

        rvOption.setLayoutManager(linearLayoutManager);
        rvOption.setAdapter(adapter);

        if(rvPosition > 4) {
            linearLayoutManager.scrollToPositionWithOffset(rvPosition, 20);
        }

        container.addView(layout);
        return layout;
    }

    void openDateSelector() {


        Calendar now = Calendar.getInstance();
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.YEAR, -35);
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                (DatePickerDialog.OnDateSetListener) activity,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.setFirstDayOfWeek(1);
        datePickerDialog.setMaxDate(now);
        datePickerDialog.setMinDate(minDate);
        datePickerDialog.setAccentColor(ContextCompat.getColor(activity, R.color.marine_blue));
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
        datePickerDialog.show(activity.getFragmentManager(), "Datepickerdialog");
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return treatmentPollQuestionRealmResults.size();
    }

    @Override
    public int getItemPosition(Object item) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;

    }

    public void setDateTextInputGlobal(String strDate) {

        textInputLayoutDate.setText(dateFormatDocument(strDate));
    }

    public void notifyDataSetChangedTypeAnswer() {
        notifyDataSetChanged();
    }
}
