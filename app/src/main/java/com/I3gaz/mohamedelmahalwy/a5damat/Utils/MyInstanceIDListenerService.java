package com.I3gaz.mohamedelmahalwy.a5damat.Utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by cz on 22/02/2018.
 */

public class MyInstanceIDListenerService extends FirebaseInstanceIdService {
    String TAG = "firebase";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);
        SharedPreferences sharedPreferences = getSharedPreferences("mobile_token",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("m_token",refreshedToken);
        edit.apply();
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }


    private void sendRegistrationToServer(String token) {

    }
}
