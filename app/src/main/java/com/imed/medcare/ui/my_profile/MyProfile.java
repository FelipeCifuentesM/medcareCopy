package com.imed.medcare.ui.my_profile;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.ui.welcome.WelcomeActivity;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends AppCompatActivity implements MyProfileContract.MyProfileView {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_my_profile)
    CoordinatorLayout contentMyProfile;
    MyProfileContract.MyProfilePresenter presenter;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ButterKnife.bind(this);
        presenter = new MyProfilePresenter(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "font/poppins_regular.ttf");

        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter.getUserData();

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

    @OnClick({R.id.cond_and_terms, R.id.frecuentquestions, R.id.send_feedback, R.id.log_out, R.id.btn_go_to_my_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cond_and_terms:
                Intent intentTerms = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.i-med.cl/wp-content/uploads/2018/07/C%C3%B3digo-de-%C3%89tica.pdf"));
                startActivity(intentTerms);
                break;
            case R.id.frecuentquestions:
                Intent intentQuestions = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pre-medcare.i-med.cl/faq"));
                startActivity(intentQuestions);
                break;
            case R.id.send_feedback:
                Intent intentFeedback = new Intent(Intent.ACTION_VIEW, Uri.parse("http://pre-medcare.i-med.cl/feedback"));
                startActivity(intentFeedback);
                break;
            case R.id.log_out:
                presenter.logOut(MyProfile.this);
                break;
            case R.id.btn_go_to_my_account:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mimed.jumpittlabs.cl/profile"));
                startActivity(browserIntent);
                break;
                
        }
    }

    @Override
    public void showUserData(User user) {
        name.setText(user.getName() + " " + user.getLastName());
        rut.setText(user.getRut());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhone());
        MedcareUtils.glideImage(userCardImage, user.getAvatar().trim(), R.drawable.dummy_avatar, this);

    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(contentMyProfile, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
