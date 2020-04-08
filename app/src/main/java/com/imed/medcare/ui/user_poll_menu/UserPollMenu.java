package com.imed.medcare.ui.user_poll_menu;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.UserPoll;

import com.imed.medcare.model.UserPollQuestion;
import com.imed.medcare.ui.questionnaire_user.QuestionnaireUser;
import com.imed.medcare.ui.user_poll_menu.adapter.UserPollMenuAdapter;
import com.imed.medcare.utils.Constants;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.imed.medcare.utils.Constants.DATA_POSITION;
import static com.imed.medcare.utils.Constants.ID_USERPOLLQUESTION_ID;
import static com.imed.medcare.utils.MedcareUtils.getCantDays;

public class UserPollMenu extends AppCompatActivity implements UserPollMenuContract.UserPollMenuView {

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
    @BindView(R.id.content_loader)
    RelativeLayout contentLoader;

    UserPollMenuAdapter adapter;
    private boolean fromHistory = false;
    private int treatmentId;
    UserPollMenuContract.userPollMenuPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_menu);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        presenter = new UserPollMenuPresenter(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");

        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);
        collapsingToolbar.setTitle("Cuestionario apego");
        if(getIntent().getExtras()!= null) {
            collapsingToolbar.setTitle(MedcareUtils.getDateToday());
            fromHistory = getIntent().getExtras().getBoolean("fromHistory",false);
        }
        treatmentId = getIntent().getIntExtra(Constants.ID_POLLTREATMENT,-1);
        presenter.getData();


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
    public void setData(UserPoll userPoll) {
        contentLoader.setVisibility(View.GONE);

        if(fromHistory){
            tvTimeToAnswer.setText("");
        }else {
            tvTimeToAnswer.setText(getCantDays(userPoll.getFinishAt()));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        rvPollMenu.setLayoutManager(linearLayoutManager);

        adapter = new UserPollMenuAdapter(userPoll.getuserPollQuestionsRealmList().sort("questionOrder"), R.layout.item_poll_menu, new UserPollMenuAdapter.onItemListener() {
            @Override
            public void itemSelected(int position, UserPollQuestion userPollQuestion) {
                if(!fromHistory) {

                    Intent intent = new Intent(UserPollMenu.this, QuestionnaireUser.class);
                    intent.putExtra(DATA_POSITION, position);
                    intent.putExtra(ID_USERPOLLQUESTION_ID,userPollQuestion.getId());
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.stand);
                }
            }
        });
        rvPollMenu.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String message) {
        contentLoader.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar
                .make(contentPollMenu, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showLoading() {
        contentLoader.setVisibility(View.VISIBLE);
    }
}
