package com.imed.medcare.ui.responded_polls;

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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.imed.medcare.R;
import com.imed.medcare.network.response.RespondedPollResponse;
import com.imed.medcare.ui.responded_polls.adapter.RespondedPollAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RespondedPollsActivity extends AppCompatActivity implements RespondedPollsContract.View{


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
    RespondedPollAdapter adapter;
    boolean isFromAttachment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_menu);
        ButterKnife.bind(this);

        int treatmentId = getIntent().getIntExtra("id_poll", 1);
        String title = getIntent().getStringExtra("title");
        isFromAttachment = getIntent().getBooleanExtra("isFromAttachment",true);

        RespondedPollsContract.Presenter presenter = new RespondedPollsPresenter(this);
        presenter.getPoll(treatmentId);

        setSupportActionBar(toolbar);
        Typeface font = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");
        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.secondary_text_color));
        collapsingToolbar.setTitle(title);
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
    public void manageLoader() {
        if(contentLoader.getVisibility() == View.GONE){
            contentLoader.setVisibility(View.VISIBLE);
        }else {
            contentLoader.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(contentPollMenu, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showPoll(List<RespondedPollResponse.DataBean> listRespondedPolls) {

        Log.i("sizelistRespondedPolls ",String.valueOf(listRespondedPolls.size()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        rvPollMenu.setLayoutManager(linearLayoutManager);
        adapter = new RespondedPollAdapter(R.layout.item_poll_menu,listRespondedPolls,isFromAttachment);
        rvPollMenu.setAdapter(adapter);
    }
}
