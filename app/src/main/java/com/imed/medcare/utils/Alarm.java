package com.imed.medcare.utils;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.imed.medcare.App;


public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String notificationTitle = "Medcare";
        String notificationText = intent.getStringExtra("name");
        final int idNotification = intent.getIntExtra("id_notification", 0);
        int idPrescription = intent.getIntExtra("id", 0);
        String time = intent.getStringExtra("time");
        Log.e("Broad_ALRMTIME-IDNOTI", notificationText + " " + time+" "+idNotification);
        final NotificationHandler notificationHandler = new NotificationHandler(App.getContext());
        final Notification.Builder nb = notificationHandler.createNotification(time, notificationTitle, notificationText);
        notificationHandler.getManager().notify(idNotification, nb.build());
        notificationHandler.publishNotificationSummaryGroup();
    }

}