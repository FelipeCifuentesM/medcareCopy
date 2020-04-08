package com.imed.medcare.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.imed.medcare.R;
import com.imed.medcare.ui.login.credentials.CredentialsFragment;
import com.imed.medcare.ui.home.HomeActivity;
import com.imed.medcare.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by felipe on 23-02-18.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.barlayout)
    AppBarLayout barLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container_register_login)
    CoordinatorLayout containerRegisterLogin;
    @BindView(R.id.content_progressbar_login)
    RelativeLayout contentProgressbar;
    private AlertDialog alertDialog;

    private LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_register_login);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loginPresenter = new LoginPresenter(this,this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animateBarLayout();
                showFragment(new CredentialsFragment());
            }
        }, 200);
    }

    private void animateBarLayout() {
        barLayout.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        barLayout.setAnimation(animation);
    }

    @Override
    public void showMessage(String message){
        Snackbar snackbar = Snackbar
                .make(containerRegisterLogin, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() == 1)
            finish();
        else {
            showAlertDialogOnBack();
        }
    }


    @OnClick(R.id.nav_back)
    void onBackClicked(){
        onBackPressed();
    }

    @Override
    public void sendCredentials(String rut, String password) {
        loginPresenter.setCredentials(rut,password);
        //showFragment(new PhoneVerificationFragment());
    }

    @Override
    public void showHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public void showAlertDialogOnBack(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyAlertDialogStyle);

        String titleText = "Salir del inicio de sesión";
        String messageText = "Perderá todo el avance y sus datos no serán guardados";

        builder.setTitle(titleText)
                .setMessage(messageText);


        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void closeKeyboard() {
        closeKeyboard(this);
    }

    @Override
    public void manageLoader() {
        if (contentProgressbar.getVisibility() == View.VISIBLE) {
            contentProgressbar.setVisibility(View.GONE);
        } else {
            contentProgressbar.setVisibility(View.VISIBLE);
        }
    }

}
