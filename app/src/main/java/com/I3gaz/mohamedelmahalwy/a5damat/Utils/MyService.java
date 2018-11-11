package com.I3gaz.mohamedelmahalwy.a5damat.Utils;

import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.RequestsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import static com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity.replaceFragment;


public class MyService extends Service {
    RequestsFragment requestsFragment;

    //    BroadcastReceiver mMessageReceiver;
    SharedPreferences sharedPreferences_title;


    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences_title = getSharedPreferences("title", MODE_PRIVATE);
        requestsFragment = new RequestsFragment();

        HomeActivity.btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(requestsFragment);
                HomeActivity.dialog_go_to_requests.dismiss();
            }
        });
        HomeActivity.btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.dialog_go_to_requests.dismiss();
            }
        });

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver),
                new IntentFilter("MyData")
        );
    }


    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("shared", sharedPreferences_title.getString("title", "") + "llll");
        if (sharedPreferences_title.getString("title", "").equals("yes")) {
            HomeActivity.dialog_go_to_requests.show();
        }
        if (sharedPreferences_title.getString("title", "").equals("no")) {
            HomeActivity.dialog_go_to_requests.dismiss();
        }
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
