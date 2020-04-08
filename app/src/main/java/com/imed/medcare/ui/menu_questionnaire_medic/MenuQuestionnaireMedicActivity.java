package com.imed.medcare.ui.menu_questionnaire_medic;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
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
import android.widget.TextView;
import com.imed.medcare.R;
import com.imed.medcare.model.MedicPollQuestion;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.ui.menu_questionnaire_medic.adapter.MenuQuestionnaireMedicAdapter;
import com.imed.medcare.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class MenuQuestionnaireMedicActivity extends AppCompatActivity implements MenuQuestionnaireMedicContract.View {

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
    MenuQuestionnaireMedicAdapter adapter;
    private int treatmentId;
    private MenuQuestionnaireMedicContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_menu);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        presenter = new MenuQuestionnaireMedicPresenter(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");

        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);

        if(getIntent().getExtras()!= null) {
            collapsingToolbar.setTitle("Antecedentes de transplante");
        }
        treatmentId = getIntent().getIntExtra(Constants.ID_TREATMENT,1);
        presenter.getQuestionnaireMedic(treatmentId);

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
    public void showLoader() {
        if(contentLoader.getVisibility() == View.GONE){
            contentLoader.setVisibility(View.VISIBLE);
        }else {
            contentLoader.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar
                .make(contentPollMenu, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showQuestionnaireMedic(RealmResults<MedicPollQuestion> questionnaireMedicRealmList, Treatment treatment) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        rvPollMenu.setLayoutManager(linearLayoutManager);
        adapter = new MenuQuestionnaireMedicAdapter(questionnaireMedicRealmList,treatment, R.layout.item_questionnaire_medic_type1,R.layout.item_questionnaire_medic_type2);
        rvPollMenu.setAdapter(adapter);
    }
}
