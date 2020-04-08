package com.imed.medcare.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.imed.medcare.R;
import com.imed.medcare.ui.login.LoginActivity;
import com.imed.medcare.ui.webview.Webview;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by felipe on 21-02-18.
 */

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.transition_view)
    View transition;
    private long lastClickTime = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register_button)
    void onRegisterButtonClicked() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        Intent intent = new Intent(this, Webview.class);
        intent.putExtra("URL", "http://mimed.jumpittlabs.cl/mobile/register/rut");
        intent.putExtra("TITLE", "Registro");
        startActivity(intent);
    }

    @OnClick(R.id.login_button)
    void onLoginButtonClicked() {
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();
        Intent register = new Intent(this, LoginActivity.class);
        startActivity(register);
    }

}
