package com.example.dev.lovehoanthien.Service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import com.example.dev.lovehoanthien.MainActivity;
import com.example.dev.lovehoanthien.R;


import static com.example.dev.lovehoanthien.Service.App.CHANNEL_ID;

public class ExampleService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Chúc mừng hai bạn")
                .setContentText(input)
                .setSmallIcon(R.drawable.love_1)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);



        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
