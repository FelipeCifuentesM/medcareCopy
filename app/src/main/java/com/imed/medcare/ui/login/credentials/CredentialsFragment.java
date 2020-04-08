package com.imed.medcare.ui.login.credentials;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.imed.medcare.R;
import com.imed.medcare.ui.login.LoginContract;
import com.imed.medcare.ui.webview.Webview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * Created by felipe on 23-02-18.
 */

public class CredentialsFragment extends Fragment implements CredentialsContract.View{
    @BindView(R.id.rut_input)
    EditText rutInput;
    @BindView(R.id.password_input)
    EditText password;
    @BindView(R.id.til_rut)
    TextInputLayout tilRut;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.login_button_disable)
    Button loginButtonDisable;
    private LoginContract.View activityListener;
    private CredentialsContract.Presenter credentialsPresenter;
    private long lastClickTime = 0;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        activityListener = (LoginContract.View) getActivity();
        credentialsPresenter = new CredentialsPresenter(this);

        Typeface font = Typeface.createFromAsset(getContext().getAssets(),"font/poppins_regular.ttf");
        tilRut.setTypeface(font);
        tilPassword.setTypeface(font);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.login_button)
    void onLoginButtonClicked() {
        activityListener.closeKeyboard();
        activityListener.sendCredentials(
                String.valueOf(rutInput.getText().toString()),
                String.valueOf(password.getText().toString())
        );
    }

    @OnClick(R.id.btn_forgot_password)
    void onForgotPassowrdClicked(){
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        Intent intent = new Intent(getActivity(), Webview.class);
        intent.putExtra("URL", "http://mimed.jumpittlabs.cl/password/recovery");
        intent.putExtra("TITLE", "Cambiar contraseña");
        startActivity(intent);
    }

    @OnClick(R.id.login_button_disable)
    void onLoginButtonDisableClicked() {
        credentialsPresenter.validateRut(rutInput.getText().toString());
        credentialsPresenter.evaluatePasswordLength(password.getText().length());

        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.button_vibration);
        loginButtonDisable.startAnimation(animation);
    }

    @OnFocusChange(R.id.rut_input)
    void onRutFocusChange(View view, boolean hasFocus){
        if (!hasFocus) {
            credentialsPresenter.validateRut(rutInput.getText().toString());
        }
    }

    @OnFocusChange(R.id.password_input)
    void onPasswordFocusChange(View view, boolean hasFocus){
        if (!hasFocus) {
            credentialsPresenter.evaluatePasswordLength(password.getText().length());
        }
    }

    @OnTextChanged(R.id.rut_input)
    void onRutChange(CharSequence charSequence){
        credentialsPresenter.validateRut(charSequence);
        credentialsPresenter.verifyInputs(
                tilRut.getError(),
                tilPassword.getError(),
                rutInput.length(),
                password.length()
        );
    }

    @OnTextChanged(R.id.password_input)
    void onPasswordChange(CharSequence charSequence){
        credentialsPresenter.evaluatePasswordLength(password.getText().length());
        credentialsPresenter.verifyInputs(
                tilRut.getError(),
                tilPassword.getError(),
                rutInput.length(),
                password.length()
        );
    }

    @Override
    public void showRutError(boolean cond) {
        if(cond)
            tilRut.setError(getString(R.string.not_valid_rut));
        else
            tilRut.setError(null);
    }

    @Override
    public void showPasswordError(boolean cond) {
        if(cond)
            tilPassword.setError(getString(R.string.not_valid_password));
        else
            tilPassword.setError(null);
    }

    @Override
    public void toggleLoginButton(boolean isDisable) {
        if(isDisable){
            loginButton.setVisibility(View.GONE);
            loginButtonDisable.setVisibility(View.VISIBLE);
        }
        else{
            loginButton.setVisibility(View.VISIBLE);
            loginButtonDisable.setVisibility(View.GONE);
        }
    }
}
