package com.imed.medcare.ui.treatment_poll_menu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;
import com.imed.medcare.ui.answer_questionnaire.AnswerQuestionnaireActivity;
import com.imed.medcare.ui.questionnaire_treatment.QuestionnaireActivity;
import com.imed.medcare.ui.treatment_poll_menu.adapter.AdapterPollMenu;
import com.imed.medcare.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

import static com.imed.medcare.utils.Constants.DATA_POSITION;
import static com.imed.medcare.utils.Constants.DATA_TYPE;
import static com.imed.medcare.utils.Constants.ID_TREATMENTPOLLQUESTION_ID;
import static com.imed.medcare.utils.MedcareUtils.getCantDays;

public class PollMenuActivity extends AppCompatActivity implements PollMenuContract.PollMenuView {

    @BindView(R.id.tv_time_to_answer)
    TextView tvTimeToAnswer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.rv_poll_menu)
    RecyclerView rvPollMenu;
    @BindView(R.id.content_poll_menu)
    CoordinatorLayout contentPollMenu;
    AdapterPollMenu adapter;
    private boolean fromHistory = false;
    private int treatmentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_menu);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        PollMenuContract.PollMenuPresenter presenter = new PollMenuPresenter(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");

        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);

        if(getIntent().getExtras()!= null) {
            collapsingToolbar.setTitle("Cuestionario médico");
            fromHistory = getIntent().getExtras().getBoolean("fromHistory",false);
        }
        treatmentId = getIntent().getIntExtra(Constants.ID_POLLTREATMENT,-1);

        presenter.getData(treatmentId);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.secondary_text_color));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showData(TreatmentPoll treatmentPoll, RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmList) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        rvPollMenu.setLayoutManager(linearLayoutManager);
        if(fromHistory){
            tvTimeToAnswer.setText("");
        }else{
            tvTimeToAnswer.setText(getCantDays(treatmentPoll.getFinishAt()));
        }


        adapter = new AdapterPollMenu(PollMenuActivity.this,treatmentPollQuestionRealmList, R.layout.item_poll_menu, new AdapterPollMenu.onItemListener() {
            @Override
            public void itemSelected( int position) {
                if(!fromHistory) {
                    Log.e("positionIntent",String.valueOf(position));

                    //TODO changethis QuestionnaireActivity
                    //Intent intent = new Intent(PollMenuActivity.this, AnswerQuestionnaireActivity.class);
                    Intent intent = new Intent(PollMenuActivity.this, QuestionnaireActivity.class);
                    intent.putExtra(DATA_TYPE, "treatament");
                    intent.putExtra(DATA_POSITION, position);
                    intent.putExtra(ID_TREATMENTPOLLQUESTION_ID, treatmentId);

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.stand);
                }
            }
        });
        rvPollMenu.setAdapter(adapter);
    }
}
