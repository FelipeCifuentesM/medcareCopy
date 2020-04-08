package com.imed.medcare.utils;

/**
 * Created by felipe on 23-02-18.
 */

public class Constants {

    //Paswoord lenght
    public static final int PASSWORD_MIN_LENGTH = 6;

    //Navigation Fragments
    public static final String FRAGMENT_HOME = "fragmentHome";
    public static final String FRAGMENT_PILLBOX = "fragmentPillbox";
    public static final String FRAGMENT_TREATMENT = "fragmentTreatment";
    public static final String FRAGMENT_PROFILE = "fragmentProfile";
    public static final int FRAGMENT_PILLBOX_POSITION = 0;
    public static final int FRAGMENT_TREATMENT_POSITION = 1;
    public static final int FRAGMENT_PROFILE_POSITION = 2;
    public static final String ID_TREATMENT = "id_treatament";

    //My Bottom Sheet Type
    public static final int BOTTOM_SHEET_SKIP_MEDICINE = 0;
    public static final int BOTTOM_SHEET_TAKE_MEDICINE = 1;


    //User details types
    public static final String USER_DETAIL_PERSONAL = "Personal";
    public static final String USER_DETAIL_MEDICS = "Médicas";
    public static final String USER_DETAIL_LIFESTYLE = "Estilo de vida";
    //User details value types
    public static final String VALUE_TYPE_STRING = "valueTypeString";
    public static final String VALUE_TYPE_INT = "valueTypeInt";
    public static final String VALUE_TYPE_FLOAT = "valueTypeFloat";


    //Intents
    public static final String ID_DOCUMENT = "id_document";
    public static final String NAME_DOCUMENT = "name_document";
    public static final String ID_POLLTREATMENT = "id_polltreatment";
    public static final String DATA_TYPE = "data_type";
    public static final String DATA_POSITION = "data_position";
    public static final String ID_TREATMENTPOLLQUESTION_ID = "id_treatmentpollquestion_id";
    public static final String ID_USERPOLLQUESTION_ID = "id_userpollquestion_id";



    public static final String DATE_INPUT_FORMAT_MEASUREMENT = "yyy-MM-dd";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String DATE_INPUT_FORMAT_INFO_TREATMENT = "dd/MM/yyyy";
    public static final String DATE_OUTPUT_FORMAT_MEASUREMENT = "dd/MM";
    public static final String DATE_INPUT_FORMAT_DOCUMENT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_HOUR_MINUTE_SEG = "HH:mm:ss";
    public static final String DATE_OUTPUT_FORMAT_DOCUMENT = "EEE dd MMM yyyy";
    public static final String DATE_OUTPUT_FORMAT_CHART = "EEE dd MMM";
    public static final String DATE_OUTPUT_FORMAT_HISTORY = "dd 'de' MMMM yyyy";

}
