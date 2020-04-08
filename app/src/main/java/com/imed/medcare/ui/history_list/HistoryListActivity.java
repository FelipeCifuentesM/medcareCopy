package com.imed.medcare.ui.history_list;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.imed.medcare.R;
import com.imed.medcare.network.response.HistoryResponse;
import com.imed.medcare.ui.history_list.adapter.HistoryListAdapter;
import com.imed.medcare.ui.responded_polls.RespondedPollsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryListActivity extends AppCompatActivity implements HistoryListContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.content_progressbar)
    RelativeLayout contentProgressbar;
    @BindView(R.id.content_history)
    CoordinatorLayout contentHistory;
    @BindView(R.id.rv_list_history)
    RecyclerView rvListHistory;
    boolean isFromAttachment;
    HistoryListContract.Presenter presenter;
    HistoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        ButterKnife.bind(this);
        presenter = new HistoryListPresenter(this);
        initializeView();

    }

    void initializeView(){

        isFromAttachment = getIntent().getBooleanExtra("isFromAttachment",false);
        Typeface font = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");
        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);

        if(isFromAttachment){
            collapsingToolbar.setTitle("Historial apego");
        }else {
            collapsingToolbar.setTitle("Historial cuestionarios");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter.getListHistory(isFromAttachment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
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
        if(contentProgressbar.getVisibility() == View.VISIBLE){
            contentProgressbar.setVisibility(View.GONE);
        }else {
            contentProgressbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(contentHistory, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showList(List<HistoryResponse.DataBean> historyResponseList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        rvListHistory.setLayoutManager(linearLayoutManager);
        adapter = new HistoryListAdapter(historyResponseList,R.layout.item_history,R.layout.item_placeholder_history,isFromAttachment, new HistoryListAdapter.onItemListener() {
            @Override
            public void showHistory(int id, String title) {
                Intent intent = new Intent(HistoryListActivity.this, RespondedPollsActivity.class);
                intent.putExtra("id_poll", id);
                intent.putExtra("title", title);
                intent.putExtra("isFromAttachment",isFromAttachment);
                startActivity(intent);
            }
        });
        rvListHistory.setAdapter(adapter);
    }
}
