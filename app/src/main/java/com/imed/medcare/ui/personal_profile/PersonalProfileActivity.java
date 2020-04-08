package com.imed.medcare.ui.personal_profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.PersonalProfile;
import com.imed.medcare.model.User;
import com.imed.medcare.ui.personal_profile.personal_profile_adapter.ProfilePersonalAdapter;
import com.imed.medcare.ui.response_personal_questions.ResponsePersonalQuestionsActivity;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;

public class PersonalProfileActivity extends AppCompatActivity implements PersonalProfileContract.View {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_my_profile)
    CoordinatorLayout contentMyProfile;
    @BindView(R.id.user_card_image)
    CircleImageView userCardImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rut)
    TextView rut;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.content_progressbar_personal)
    RelativeLayout contentProgressBar;
    @BindView(R.id.rv_profile_personal)
    RecyclerView rvProfilePersonal;
    @BindView(R.id.content_personal_data)
    RelativeLayout contentPersonalData;

    private ProfilePersonalAdapter adapter;
    private PersonalProfileContract.Presenter presenter;
    int REQUEST_CODE = 200;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type",1);
        initView();


    }

    private void initView() {
        Typeface font = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");
        presenter = new PersonalProfilePresenter(this);
        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter.getProfilePersonal(type);
        switch (type){
            case 1:
                contentPersonalData.setVisibility(View.VISIBLE);
                collapsingToolbar.setTitle("Personales");
                break;
            case 2:
                contentPersonalData.setVisibility(View.GONE);
                collapsingToolbar.setTitle("Médicos");
                break;
            case 3:
                contentPersonalData.setVisibility(View.GONE);
                collapsingToolbar.setTitle("Hábitos");
                break;
        }
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
    public void showLoader() {
        if(contentProgressBar.getVisibility() == View.VISIBLE){
            contentProgressBar.setVisibility(View.GONE);
        }else {
            contentProgressBar.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void setData(User user, RealmResults<PersonalProfile> profilePersonalRealmResults) {

        name.setText(user.getName() + " " + user.getLastName());
        rut.setText(user.getRut());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhone());
        MedcareUtils.glideImage(userCardImage, user.getAvatar().trim(), R.drawable.dummy_avatar, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        rvProfilePersonal.setLayoutManager(linearLayoutManager);
        adapter = new ProfilePersonalAdapter(R.layout.item_profile_personal, profilePersonalRealmResults, new ProfilePersonalAdapter.onItemClick() {

            @Override
            public void showEditItem(int id) {
                Intent intent = new Intent(PersonalProfileActivity.this, ResponsePersonalQuestionsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type",type);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        rvProfilePersonal.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                assert data != null;
                final String result = data.getStringExtra(ResponsePersonalQuestionsActivity.EXTRA_DATA);
                Snackbar.make(contentMyProfile, result, Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter !=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar
                .make(contentMyProfile, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
