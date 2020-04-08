package com.imed.medcare.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;

import com.imed.medcare.R;
import com.imed.medcare.ui.home.HomeActivity;

import static com.imed.medcare.utils.MedcareUtils.getCosetsTime;

public class NotificationHandler extends ContextWrapper {

    private NotificationManager manager;

    public static final String CHANNEL_HIGH_ID = "1";
    private final String CHANNEL_HIGH_NAME = "HIGH CHANNEL";
    private final int SUMMARY_GROUP_ID = 1001;
    private final String SUMMARY_GROUP_NAME = "GROUPING_NOTIFICATION";
    private String time;


    public NotificationHandler(Context context) {
        super(context);
        createChannels();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getCosetsTime();
            }
        }, 5000);

    }

    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    private void createChannels() {
        if (Build.VERSION.SDK_INT >= 26) {
            // Creating High Channel
            NotificationChannel highChannel = new NotificationChannel(
                    CHANNEL_HIGH_ID, CHANNEL_HIGH_NAME, NotificationManager.IMPORTANCE_HIGH);

            // ...Extra Config...
            highChannel.enableLights(true);
            highChannel.setLightColor(Color.GREEN);
            highChannel.setShowBadge(true);
            highChannel.enableVibration(true);
            highChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            // Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            // highChannel.setSound(defaultSoundUri, null);

            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(highChannel);

        }
    }

    public Notification.Builder createNotification(String time, String title, String message) {
        //old versions, don't have channels


        this.time = time;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return this.createNotificationWithChannel(title, message);
        }
        return this.createNotificationWithoutChannel(title, message);
    }

    private Notification.Builder createNotificationWithChannel(String title, String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

//            Intent intent = new Intent(this, HomeActivity.class);
//            intent.putExtra("title", title);
//            intent.putExtra("message", message);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//            Notification.Action action =
//                    new Notification.Action.Builder(Icon.createWithResource
//                            (this, android.R.drawable.ic_menu_send), "See Details", pIntent).build();

            Context context = getApplicationContext();

            Intent intent1 = new Intent(context, HomeActivity.class);
            intent1.putExtra("time", time);
            return new Notification.Builder(context, NotificationHandler.CHANNEL_HIGH_ID)
                    .setContentTitle(title)
                    .setContentText(message)
                    // .addAction(action)
                    .setColor(getColor(R.color.colorPrimary))
                    .setSmallIcon(R.drawable.ic_pill)
                    .setGroup(SUMMARY_GROUP_NAME)
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT));

        }
        return null;
    }

    private Notification.Builder createNotificationWithoutChannel(String title, String message) {
        Context context = getApplicationContext();
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("time", time);
        return new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_pill)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public void publishNotificationSummaryGroup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification summaryNotification = new Notification.Builder(getApplicationContext(), CHANNEL_HIGH_ID)
                    .setSmallIcon(R.drawable.ic_pill)
                    .setGroup(SUMMARY_GROUP_NAME)
                    .setGroupSummary(true)
                    .setAutoCancel(true)
                    .build();
            getManager().notify(SUMMARY_GROUP_ID, summaryNotification);
        }
    }
}

