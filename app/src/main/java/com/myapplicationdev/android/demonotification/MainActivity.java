package com.myapplicationdev.android.demonotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int requestCode = 123;
    int notificationID = 888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        Button notify1Btn = findViewById(R.id.notify_1_button);
        Button notify2Btn = findViewById(R.id.notify_2_button);
        notify1Btn.setOnClickListener(this);
        notify2Btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify_1_button:
                notify1();
                break;
            case R.id.notify_2_button:
                notify2();
                break;
        }
    }

    private void notify2() {
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        // importance - default, high (banner)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        // This Activity is launched when Notification is Clicked
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity
                ( MainActivity.this, requestCode, intent,
                        PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle()
                .setBigContentTitle("Big Text – Long Content")
                .bigText("This is one big text" +
                        " - A quick brown fox jumps over a lazy brown dog "+
                        "\nLorem ipsum dolor sit amet, sea eu quod des")
                .setSummaryText("Reflection Journal?");

        // Build notification
        // Auto cancel - notification will be removed from the shade after clicking on it
        // small icon  - icon displayed on status bar and notification box
        // sound       - default ringtone (below oreo)
        // priority    - high is a banner on screen (below oreo)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default")
                .setContentTitle("Amazing Offer!")
                .setContentText("Subject")
                .setSmallIcon(android.R.drawable.btn_star_big_off)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setStyle(bigText);

        Notification notification = builder.build();

        // Create Notification
        notificationManager.notify(notificationID, notification);
        finish();

    }

    private void notify1() {
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        // importance - default, high (banner)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "default", "Default Channel", NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        // This Activity is launched when Notification is Clicked
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity
                ( MainActivity.this, requestCode, intent,
                        PendingIntent.FLAG_CANCEL_CURRENT);

        // Build notification
        // Auto cancel - notification will be removed from the shade after clicking on it
        // small icon  - icon displayed on status bar and notification box
        // sound       - default ringtone (below oreo)
        // priority    - high is a banner on screen (below oreo)
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default")
                .setContentTitle("Amazing Offer!")
                .setContentText("Subject")
                .setSmallIcon(android.R.drawable.btn_star_big_off)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(Notification.PRIORITY_HIGH);

        Notification notification = builder.build();

        // Create Notification
        notificationManager.notify(notificationID, notification);
        finish();
    }
}