package com.imed.medcare.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by Ramiro on 02-03-2018.
 */

public class ValidationsUtils {
    private static final String SERIAL_REGEX = "[a]{0,1}[0-9]{9}";
    private static final String CUID_REGEX = "[0-9]{1,2}[0-9]{3}[0-9]{3}[kK0-9]{1}";
                            //old [0-9]{1,2}[.]{0,1}[0-9]{3}[.]{0,1}[0-9]{3}[-]{1}[kK0-9]{1}
    public static boolean validateSerial(CharSequence c){
        return Pattern.matches(SERIAL_REGEX,c);
    }

    public static boolean validateCuuid(String rut){
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                return true;
            }

        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean validateEmail(CharSequence c){
        return Patterns.EMAIL_ADDRESS.matcher(c).matches();
    }
}
