package com.imed.medcare.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.imed.medcare.R;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.ui.my_profile.MyProfile;
import com.imed.medcare.ui.personal_profile.PersonalProfileActivity;
import com.imed.medcare.ui.treatment.TreatmentActivity;
import com.imed.medcare.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.imed.medcare.utils.Constants.ID_TREATMENT;

/**
 * Created by Ramiro on 26-02-2018.
 */

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @BindView(R.id.view_pager)
    ViewPager homePager;
    @BindView(R.id.navigation_view)
    BottomNavigationViewEx navigationViewEx;


    private HomeContract.Presenter homePresenter;

    private HomePagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_home);
        ButterKnife.bind(this);
        homePresenter = new HomePresenter(this);
        setupViewPager();
        setupNavigationBar();
    }

    private void setupViewPager() {
        homePager.setOffscreenPageLimit(4);
        adapter = new HomePagerAdapter(this.getSupportFragmentManager());
        homePager.setAdapter(adapter);
    }

    private void setupNavigationBar() {

        navigationViewEx.setupWithViewPager(homePager);
        if (getIntent() != null && getIntent().getStringExtra("time") != null) {
            navigationViewEx.setCurrentItem(1);
        }
    }

    @Override
    public void onBackPressed() {
        if (homePager.getCurrentItem() == 0)
            finish();
        else {
            homePager.setCurrentItem(0);
        }
    }


    @Override
    public void showTreatment(Treatment treatment) {

        Intent intent = new Intent(this, TreatmentActivity.class);
        intent.putExtra(ID_TREATMENT, treatment.getId());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void showProfile() {
        Intent intent = new Intent(this, MyProfile.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void showPersonalHabit() {
        Intent intent = new Intent(this, PersonalProfileActivity.class);
        intent.putExtra("type", 3);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void showPersonalMedic() {
        Intent intent = new Intent(this, PersonalProfileActivity.class);
        intent.putExtra("type", 2);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void showPersonalProfile() {
        Intent intent = new Intent(this, PersonalProfileActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}
