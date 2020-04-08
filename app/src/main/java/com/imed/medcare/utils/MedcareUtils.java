package com.imed.medcare.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import com.imed.medcare.App;
import com.imed.medcare.GlideApp;
import com.imed.medcare.R;
import com.imed.medcare.model.CustomComparator;
import com.imed.medcare.model.HistoryPrescription;
import com.imed.medcare.model.Pillbox;
import com.imed.medcare.model.Prescription;
import com.imed.medcare.model.Schedule;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.content.Context.ALARM_SERVICE;
import static com.imed.medcare.utils.Constants.DATE_INPUT_FORMAT_DOCUMENT;
import static com.imed.medcare.utils.Constants.DATE_INPUT_FORMAT_MEASUREMENT;
import static com.imed.medcare.utils.Constants.DATE_OUTPUT_FORMAT_CHART;

public class MedcareUtils {

    public static void hideKeyBoard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static String getDateToday() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM", new Locale("es", "ES"));
        String date = sdf.format(currentTime);
        return date.replace("-", " de ");
    }

    public static String getDatePillbox(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM", new Locale("es", "ES"));
        String dateString = sdf.format(date);
        dateString = dateString.replace("-", " de ");
        return dateString;
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static String getMimeType(Uri uri) {
        String mimeType = null;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = App.getContext().getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

    public static String formatRut(String rut) {
        rut.replace(".", "");
        if (!rut.contains("-")) {
            if (rut.length() == 9) {
                rut = rut.substring(0, 8) + "-" + rut.substring(8, rut.length());
            }
            if (rut.length() == 8) {
                rut = rut.substring(0, 7) + "-" + rut.substring(7, rut.length());
            }
        }
        return rut;
    }


    public static void glideImage(CircleImageView imageView, String urlImage, int placeHolder, Context context) {

        GlideApp.with(context)
                .load(urlImage)
                .centerCrop()
                .placeholder(placeHolder)
                .into(imageView);
    }

    public static String dateFormatDocument(String inputDateStr) {
        String outputDateStr;
        DateFormat inputFormat = new SimpleDateFormat(DATE_INPUT_FORMAT_DOCUMENT);
        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_OUTPUT_FORMAT_DOCUMENT, new Locale("es", "ES"));

        try {
            Date date = inputFormat.parse(inputDateStr);
            outputDateStr = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            outputDateStr = "";
        }

        return outputDateStr;
    }

    public static String dateFormatHistory(String inputDateStr) {
        String outputDateStr;
        DateFormat inputFormat = new SimpleDateFormat(DATE_INPUT_FORMAT_DOCUMENT);
        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_OUTPUT_FORMAT_HISTORY, new Locale("es", "ES"));

        try {
            Date date = inputFormat.parse(inputDateStr);
            outputDateStr = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            outputDateStr = "";
        }

        return outputDateStr;
    }

    public static Date YearMonthDayDate(String dateString) {
        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_FORMAT_YEAR_MONTH_DAY);
        Date date = null;
        try {
            date = outputFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date HourMinuteSegDate(String dateString) {

        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_HOUR_MINUTE_SEG);
        Date date = null;
        try {
            date = outputFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String HourMinuteSegString(Date date) {
        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_HOUR_MINUTE_SEG);
        return outputFormat.format(date);
    }

    public static String YearMonthDayString(Date date) {
        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_INPUT_FORMAT_MEASUREMENT);
        return outputFormat.format(date);
    }


    public static String CurrentDateAndHour() {
        Date date = Calendar.getInstance().getTime();
        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_INPUT_FORMAT_DOCUMENT);
        return outputFormat.format(date);
    }

    public static String dateInfoTreatment(String inputDateStr) {
        String outputDateStr;
        DateFormat inputFormat = new SimpleDateFormat(DATE_INPUT_FORMAT_DOCUMENT);
        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_INPUT_FORMAT_INFO_TREATMENT);

        try {
            Date date = inputFormat.parse(inputDateStr);
            outputDateStr = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            outputDateStr = "";
        }

        return outputDateStr;
    }

    public static String dateFormatMeasurement(String inputDateStr) {

        DateFormat inputFormat = new SimpleDateFormat(DATE_INPUT_FORMAT_MEASUREMENT);
        DateFormat outputFormat = new SimpleDateFormat(Constants.DATE_OUTPUT_FORMAT_MEASUREMENT);
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        return outputDateStr;
    }

    public static void glideImage(ImageView imageView, String urlImage, int placeHolder, Context context) {

        GlideApp.with(context)
                .load(urlImage)
                .centerCrop()
                .placeholder(placeHolder)
                .into(imageView);
    }

    public static void glideImageShowDocument(ImageView imageView, String urlImage, int placeHolder, Context context) {

        GlideApp.with(context)
                .load(urlImage)
                .centerInside()
                .placeholder(placeHolder)
                .into(imageView);
    }


    public static String reverseDateFormatDocument(String inputDateStr) {

        DateFormat inputFormat = new SimpleDateFormat(Constants.DATE_OUTPUT_FORMAT_DOCUMENT, new Locale("es", "ES"));
        DateFormat outputFormat = new SimpleDateFormat(DATE_INPUT_FORMAT_DOCUMENT);
        String outputDateStr = "";
        try {
            Date date = inputFormat.parse(inputDateStr);
            outputDateStr = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateStr;
    }

    public static String getDateChartStyle(String inputDateStr) {

        DateFormat inputFormat = new SimpleDateFormat(Constants.DATE_INPUT_FORMAT_DOCUMENT);
        DateFormat outputFormat = new SimpleDateFormat(DATE_OUTPUT_FORMAT_CHART, new Locale("es", "ES"));
        String outputDateStr = "";
        try {
            Date date = inputFormat.parse(inputDateStr);
            outputDateStr = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateStr;
    }

    public static String getCantDays(String finishDate) {

        String realFinishDate = finishDate.replace("00:00:00","23:59:59");
        SimpleDateFormat myFormat = new SimpleDateFormat(DATE_INPUT_FORMAT_DOCUMENT);

        try {
            Date date1 = myFormat.parse(realFinishDate);
            Date date2 = Calendar.getInstance().getTime();
            int cantDays = (int) TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS);
            if (cantDays == 0) {
                return "Queda menos de un día";
            } else if (cantDays == 1) {
                return "Queda " + cantDays + " día";
            } else if (cantDays > 1) {
                return "Quedan " + cantDays + " días";
            }else {
                return "";
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return "indefinido";
        }

    }

    public static File saveBitmap(Bitmap bitmap) {
        File filesDir = App.getContext().getFilesDir();
        File photoFile = new File(filesDir, "document.jpg");
        OutputStream outputStream;

        try {
            outputStream = new FileOutputStream(photoFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.e("TreatmentDocs", "Error writing bitmap", e);
        }

        return photoFile;
    }

    public static String getFormatted(String surnames) {
        if (surnames.split(" ").length > 2) {
            String[] surnameFormatted = surnames.split(" ");
            String surnameBuff = surnames.replace(surnameFormatted[surnameFormatted.length - 1], "");
            return surnameBuff + " " + String.valueOf(surnameFormatted[surnameFormatted.length - 1].charAt(0) + ".");
        } else {
            return surnames;
        }
    }

    public static void getCosetsTime() {

        Realm realm = Realm.getDefaultInstance();
        Prescription prescription = realm.where(Prescription.class).findFirst();
        Schedule schedule = realm.where(Schedule.class).findFirst();

        if (prescription != null && schedule != null) {
            Long closetsTime = null;
            Calendar calendar = Calendar.getInstance();
            int cont = 1;
            while (closetsTime == null && cont != 9) {

                ArrayList<Pillbox> pillboxArrayList = getPillboxSortedbyTime(calendar, true);
                int indexPillbox = -1;

                for (int i = 0; pillboxArrayList.size() > i; i++) {
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
                    Calendar rightNowCalendar = Calendar.getInstance();
                    int hour = rightNowCalendar.get(Calendar.HOUR_OF_DAY);
                    int minute = rightNowCalendar.get(Calendar.MINUTE);
                    int sec = rightNowCalendar.get(Calendar.SECOND);
                    Date rightNownowOclock = new Date();
                    Date pillboxDate = new Date();
                    try {
                        rightNownowOclock = df.parse(hour + ":" + minute + ":" + sec);
                        pillboxDate = df.parse(pillboxArrayList.get(i).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (rightNownowOclock.getTime() <= pillboxDate.getTime() && pillboxArrayList.get(i).getTaken() == null || cont > 1) {
                        indexPillbox = i;
                        break;
                    }
                }

                if (indexPillbox != -1) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    String dateString = dateFormat.format(calendar.getTime());
                    String closestDate = dateString + " " + pillboxArrayList.get(indexPillbox).getTime();
                    Log.e("closestDatelog", closestDate);
                    //Test notification
                    //String closestDate = "2019-01-15 16:30:00";

                    DateFormat outputFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    Date date = null;
                    try {
                        date = outputFormat.parse(closestDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    assert date != null;
                    closetsTime = date.getTime();
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(pillboxArrayList.get(indexPillbox));
                    realm.commitTransaction();
                    Intent intent = new Intent(App.getContext(), Alarm.class);

                    Log.e("Utils_ALRMTIME-IDNOTI", pillboxArrayList.get(indexPillbox).getName() + " " + pillboxArrayList.get(indexPillbox).getTime() + " " + getIdNotification(pillboxArrayList.get(indexPillbox).getIdPrescription(), pillboxArrayList.get(indexPillbox).getIdSchuedule()));
                    //TODO DELETE THIS
                    if (pillboxArrayList.get(indexPillbox).getRepetition() == 0) {
                        intent.putExtra("name", pillboxArrayList.get(indexPillbox).getTime() + " " +pillboxArrayList.get(indexPillbox).getName());
                    } else {
                        intent.putExtra("name", pillboxArrayList.get(indexPillbox).getTime() + " (R)" + pillboxArrayList.get(indexPillbox).getName());
                    }

                    intent.putExtra("id", pillboxArrayList.get(indexPillbox).getIdPrescription());
                    intent.putExtra("id_pillbox", pillboxArrayList.get(indexPillbox).getId());
                    intent.putExtra("id_notification", getIdNotification(pillboxArrayList.get(indexPillbox).getIdPrescription(), pillboxArrayList.get(indexPillbox).getIdSchuedule()));
                    //testNotification
                    //intent.putExtra("time", "16:30:00");
                    intent.putExtra("time", pillboxArrayList.get(indexPillbox).getTime());
                    PendingIntent p1 = PendingIntent.getBroadcast(App.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager a = (AlarmManager) App.getContext().getSystemService(ALARM_SERVICE);
                    assert a != null;
                    a.cancel(p1);
                    a.set(AlarmManager.RTC, closetsTime, p1);

                }
                calendar.add(Calendar.DAY_OF_YEAR, cont);
                cont++;
            }
        }
        if (!realm.isClosed()) {
            realm.close();
        }
    }

    private static int getIdNotification(int idPrescription, int idSchedule) {
        String idNotification = String.valueOf(idPrescription) + String.valueOf(idSchedule);
        return Integer.valueOf(idNotification);
    }

    public static void cancelAlarm() {
        Intent intent = new Intent(App.getContext(), Alarm.class);
        PendingIntent p1 = PendingIntent.getBroadcast(App.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager a = (AlarmManager) App.getContext().getSystemService(ALARM_SERVICE);
        if (a != null) {
            a.cancel(p1);
        }
    }

    public static ArrayList<Pillbox> getPillboxSortedbyTime(Calendar calendar, boolean fromNotification) {

        final ArrayList<Pillbox> pillboxList = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Prescription> prescriptionRealmResults = realm.where(Prescription.class).
                findAll();

        for (Prescription prescription : prescriptionRealmResults) {


            boolean timeCorrect;
            if (prescription.getdateEndGetTime() != -1) {
                timeCorrect = prescription.getdateEndGetTime() >= calendar.getTime().getTime();
            } else {
                timeCorrect = true;
            }

            if (prescription.getdateStartGetTime() <= calendar.getTime().getTime() && timeCorrect) {
                int prescriptionType = 1;
                RealmList<Schedule> scheduleRealmList = prescription.getScheduleRealmList();

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                if (dayOfWeek == 0) dayOfWeek = 7;

                Schedule scheduleType2 = scheduleRealmList.where()
                        .equalTo("type", 2)
                        .equalTo("day", dayOfWeek)
                        .findFirst();

                if (scheduleRealmList.where()
                        .equalTo("type", 2).findFirst() != null)
                    prescriptionType = 2;

                if (scheduleType2 != null) {
                    for (Schedule schedule : scheduleRealmList) {
                        if (schedule.getDay() == null) {
                            pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), schedule.getHour(), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), prescription.getDoseName()));
                            if (prescription.getPriority() == 3 && fromNotification) {
                                pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), manageFifteenMins(schedule.getHour(), true), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), 15));
                                pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), manageThirtyMins(schedule.getHour(), true), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), 30));
                            }
                        }
                    }
                }

                Schedule scheduleType3 = scheduleRealmList.where()
                        .equalTo("type", 3)
                        .findFirst();


                if (scheduleType3 != null) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    String dateStart = prescription.getDateStart().split(" ")[0];
                    int diff = -1;
                    try {
                        Date secondDate = sdf.parse(dateStart);
                        long diffInMillies = Math.abs(secondDate.getTime() - calendar.getTime().getTime());
                        diff = (int) TimeUnit.MILLISECONDS.toDays(diffInMillies);
                    } catch (Exception e) {
                        Log.e("Exception", e.getMessage());
                    }
                    prescriptionType = 3;
                    int result = diff % scheduleType3.getDay();
                    if (result == 0) {
                        for (Schedule schedule : scheduleRealmList) {
                            if (schedule.getDay() == null) {
                                pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), schedule.getHour(), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), prescription.getDoseName()));
                                if (prescription.getPriority() == 3 && fromNotification) {
                                    pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), manageFifteenMins(schedule.getHour(), true), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), 15));
                                    pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), manageThirtyMins(schedule.getHour(), true), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), 30));
                                }
                            }
                        }
                    }
                }

                if (prescriptionType == 1) {
                    for (Schedule schedule : scheduleRealmList) {
                        if (schedule.getDay() == null) {
                            pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), schedule.getHour(), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), prescription.getDoseName()));
                            if (prescription.getPriority() == 3 && fromNotification) {
                                pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), manageFifteenMins(schedule.getHour(), true), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), 15));
                                pillboxList.add(new Pillbox(prescription.getId(), schedule.getId(), manageThirtyMins(schedule.getHour(), true), prescription.getName(), schedule.getDose(), prescription.getImage(), prescription.getPriority(), 30));
                            }
                        }
                    }
                }
            }
        }

        String dateSelected = YearMonthDayString(calendar.getTime());

        for (Pillbox pillbox : pillboxList) {
            String reviewTime;
            if (pillbox.getRepetition() == 0) {
                reviewTime = pillbox.getTime();
            } else {
                if (pillbox.getRepetition() == 15) {
                    reviewTime = manageFifteenMins(pillbox.getTime(), false);
                } else {
                    reviewTime = manageThirtyMins(pillbox.getTime(), false);
                }
            }

            HistoryPrescription historyPrescription = realm.where(HistoryPrescription.class)
                    .equalTo("prescriptionId", pillbox.getIdPrescription())
                    .equalTo("time", reviewTime)
                    .equalTo("date", dateSelected)
                    .findFirst();

            if (historyPrescription != null) {
                pillbox.setTaken(historyPrescription.getTaken());
                pillbox.setOk(true);

                String datePrescriptionString = dateSelected + " " + pillbox.getTime();
                String dateHistoryString = historyPrescription.getResponseDate() + " " + historyPrescription.getResponseTime();

                SimpleDateFormat sdfPrescription = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                Date datePrescription = null;
                Date dateHistory = null;
                try {
                    datePrescription = sdfPrescription.parse(datePrescriptionString);
                    dateHistory = sdfPrescription.parse(dateHistoryString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setDifferenceTime(pillbox, dateHistory.getTime(), datePrescription.getTime());
            }
        }

        Collections.sort(pillboxList, new CustomComparator());
        if (!realm.isClosed()) {
            realm.close();
        }

        return pillboxList;
    }


    private static String manageFifteenMins(String hour, boolean isAddTime) {
        Date currentHour = HourMinuteSegDate(hour);
        long t = currentHour.getTime();
        Date afterAddingFifteenMins;
        if (isAddTime) {
            afterAddingFifteenMins = new Date(t + (900000));
        } else {
            afterAddingFifteenMins = new Date(t - (900000));
        }
        return HourMinuteSegString(afterAddingFifteenMins);
    }

    private static String manageThirtyMins(String hour, boolean isAddTime) {
        Date currentHour = HourMinuteSegDate(hour);
        long t = currentHour.getTime();
        Date afterAddingFifteenMins;
        if (isAddTime) {
            afterAddingFifteenMins = new Date(t + (1800000));
        } else {
            afterAddingFifteenMins = new Date(t - (1800000));
        }
        return HourMinuteSegString(afterAddingFifteenMins);
    }


    public static void setDifferenceTime(Pillbox pillbox, Long date1, Long date2) {
        if (date1 != null && date2 != null) {
            Long difference = date1 - date2;
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;
            int elapsedDays = (int) (difference / daysInMilli);
            difference = difference % daysInMilli;
            int elapsedHours = (int) (difference / hoursInMilli);
            difference = difference % hoursInMilli;
            int elapsedMinutes = (int) (difference / minutesInMilli);

            String numDays;
            String numHrs;
            if (elapsedDays == 0) {
                numDays = "";
            } else if (elapsedDays > 1) {
                numDays = elapsedDays + "días ";
            } else {
                numDays = elapsedDays + "día ";
            }

            if(elapsedHours == 0) {
                numHrs = "";
            }else if (elapsedHours > 1) {
                numHrs = elapsedHours + "hrs ";
            } else {
                numHrs = elapsedHours + "hr ";
            }

            if (elapsedDays <= 0 && elapsedHours <= 0 && elapsedMinutes <= 0) {
                pillbox.setDifferenceTime(null);
            } else {
                pillbox.setDifferenceTime(numDays + numHrs + elapsedMinutes + "min");
            }
        } else {
            pillbox.setDifferenceTime(null);
        }

    }

    public static int dpToPixels(int dp) {
        Resources resources = App.getContext().getResources();
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()));
    }


    public static String ping() {
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        try {
            Process mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 " + "google.cl");
            int mExitValue = mIpAddrProcess.waitFor();
            System.out.println(" mExitValue " + mExitValue);
            if (mExitValue == 0) {
                return App.getContext().getString(R.string.generic_error_message);
            } else {
                return "No tienes conexión a internet";
            }
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
            System.out.println(" Exception:" + ignore);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" Exception:" + e);
        }
        return "No tienes conexión a internet";
    }
}
