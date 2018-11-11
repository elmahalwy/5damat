package com.I3gaz.mohamedelmahalwy.a5damat.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Activites.SplashActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by cz on 22/02/2018.
 */

public class MyFcmPushReceiver extends FirebaseMessagingService {
    String TAG = "firebase";
    public static String show_dialog = "no";
    public static String order_id = "";
    SharedPreferences sharedPreferences_title;

    public MyFcmPushReceiver() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences_title = getSharedPreferences("title", MODE_PRIVATE);

    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        String clickAction = String.valueOf(remoteMessage.getData());


//        String id= remoteMessage.getNotification().get
//        Intent intent = new Intent(getApplicationContext(), ReservationDetailsActivity.class);
//        intent.putExtra("id", remoteMessage.getData().get("id"));
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
            Log.e("title", remoteMessage.getNotification().getTitle() + "1");
            Log.e("body", remoteMessage.getNotification().getBody() + "2");
            Log.e("click_Action", remoteMessage.getNotification().getClickAction() + "3");
            Log.e("data", remoteMessage.getData().get("service_id") + "3");
            SharedPreferences.Editor editor = sharedPreferences_title.edit();
            editor.putString("title", "yes");
            editor.putString("service_id", remoteMessage.getData().get("service_id"));
            editor.apply();
            startService(new Intent(this, MyService.class));
        } else {
            SharedPreferences.Editor editor = sharedPreferences_title.edit();
            editor.putString("title", "no");
            editor.apply();
            startService(new Intent(this, MyService.class));


        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            startService(new Intent(this, MyService.class));
            startNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getData().get("service_id"));
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());


        } else {
            Log.e("msg", "empty");
            startService(new Intent(this, MyService.class));


        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void startNotification(String title, String service_id) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("type_notification", "requests");
        intent.putExtra("type", "requests");
        intent.putExtra("service_id", "");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBulder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.notifications)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(notification)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBulder.build());
    }


}
