package com.example.progetto_lam;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID = "channel1ID";
    public static final String channel1Name = "Channel 1";
    public static final String channel2ID = "channel2ID";
    public static final String channel2Name = "Channel 2";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannels() {
        NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel1);

        NotificationChannel channel2 = new NotificationChannel(channel2ID, channel2Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.colorPrimary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel2);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification (String text) {
        Intent activityIntent = new Intent(this, InsertMenu.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,activityIntent, 0);

        Intent setNewTime = new Intent(this, SetNotifiche.class);
        PendingIntent actionIntent = PendingIntent.getActivity(this,0, setNewTime, 0);

        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                .setContentTitle("PERSONAL HEALTH MONITOR")
                .setContentText(text)
                .setSmallIcon(R.drawable.img_inserimento)
                .setContentIntent(contentIntent)
                .addAction(R.mipmap.ic_launcher, "Inserisci", contentIntent)
                .addAction(R.mipmap.ic_launcher, "Imposta un nuovo orario per le notifiche", actionIntent)
                .setAutoCancel(true)
                .setColor(Color.BLUE);

    }

    public NotificationCompat.Builder getChannel2Notification (String text) {
        return new NotificationCompat.Builder(getApplicationContext(), channel2ID)
                .setContentTitle("PERSONAL HEALTH MONITOR")
                .setContentText("ATTENZIONE: hai superato la soglia in " + text)
                .setSmallIcon(R.drawable.img_attenzione)
                .setAutoCancel(true)
                .setColor(Color.BLUE);
    }

}
