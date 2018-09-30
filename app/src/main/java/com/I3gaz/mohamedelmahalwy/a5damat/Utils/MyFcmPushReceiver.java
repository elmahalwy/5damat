package com.I3gaz.mohamedelmahalwy.a5damat.Utils;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by cz on 22/02/2018.
 */

public class MyFcmPushReceiver extends FirebaseMessagingService {
    String TAG = "firebase";

    public MyFcmPushReceiver() {

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
            Log.e("title",remoteMessage.getNotification().getTitle()+"1");
            Log.e("body",remoteMessage.getNotification().getBody()+"2");
            Log.e("click_Action",remoteMessage.getNotification().getClickAction()+"3");

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
//            title = remoteMessage.getNotification().getTitle();
//            body= remoteMessage.getNotification().getBody();
//            SharedPreferences.Editor editor = sharedPreferences_title.edit();
//            editor.putString("title", remoteMessage.getNotification().getTitle());
//            editor.apply();
//            startService(new Intent(this,MyService.class));
            startNotification(remoteMessage.getNotification().getTitle());

            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        } else {
            Log.e("msg", "empty");
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void startNotification(String title) {
//        Intent intent = new Intent(getApplicationContext(), NotificationsActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBulder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.notifications)
//                .setContentTitle(title)
//                .setAutoCancel(true)
//                .setSound(notification)
//                .setContentIntent(pendingIntent);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0,notificationBulder.build());
    }



}
