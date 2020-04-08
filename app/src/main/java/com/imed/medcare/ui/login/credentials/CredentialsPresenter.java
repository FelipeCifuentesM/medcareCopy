package com.imed.medcare.ui.login.credentials;

import com.imed.medcare.utils.ValidationsUtils;

import static com.imed.medcare.utils.Constants.PASSWORD_MIN_LENGTH;

/**
 * Created by Ramiro on 02-04-2018.
 */

public class CredentialsPresenter implements CredentialsContract.Presenter{
    private CredentialsContract.View credentialsView;


    public CredentialsPresenter(CredentialsContract.View credentialsFragment) {
        this.credentialsView = credentialsFragment;
    }

    @Override
    public void evaluatePasswordLength(int length) {
        if(length < PASSWORD_MIN_LENGTH)
            credentialsView.showPasswordError(true);
        else
            credentialsView.showPasswordError(false);
    }

    @Override
    public void validateRut(CharSequence charSequence) {
        if(ValidationsUtils.validateCuuid(String.valueOf(charSequence)))
            credentialsView.showRutError(false);
        else
            credentialsView.showRutError(true);
    }

    @Override
    public void verifyInputs(CharSequence rutError, CharSequence passError, int lengthRut, int lengthPass) {
        if(rutError == null && passError == null && lengthRut>0 && lengthPass>0) {
            credentialsView.toggleLoginButton(false);
        }
        else
            credentialsView.toggleLoginButton(true);
    }
}
