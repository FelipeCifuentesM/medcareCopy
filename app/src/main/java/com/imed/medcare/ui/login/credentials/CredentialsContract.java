package com.imed.medcare.ui.login.credentials;

/**
 * Created by Ramiro on 02-04-2018.
 */

public interface CredentialsContract {
    interface View{

        void showRutError(boolean cond);
        void showPasswordError(boolean cond);
        void toggleLoginButton(boolean isDisable);
    }

    interface Presenter{

        void evaluatePasswordLength(int length);
        void validateRut(CharSequence charSequence);

        void verifyInputs(CharSequence rutError, CharSequence passError,
                          int lengthRut, int lengthPass);
    }
}
