package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;

public class SplashActivity extends ParentClass {
    String prefix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ToMainActivity();
    }

    private void ToMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefManager.getLoginStatus()) {
                    Intent intent = getIntent();

                    if (intent != null && intent.getData() != null) {
                        Uri uri = getIntent().getData();
                        uri.getQueryParameter("id");
                        try {

                            String substr2 = String.valueOf(intent.getData()).substring(String.valueOf(intent.getData()).indexOf("=") + 1);
                            Log.e("substr2", substr2);
                            Intent intent1 = new Intent(getApplicationContext(), HomeActivity.class);
                            intent1.putExtra("type", "deep_link");
                            intent1.putExtra("service_id", substr2);
                            startActivity(intent1);

                        } catch (Exception e) {

                        }
                    } else {
                        Intent mainIntent;
                        mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                        mainIntent.putExtra("type", "home");
                        mainIntent.putExtra("service_id", "");
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(mainIntent);

                        SplashActivity.this.finish();
                    }
                }
                if (!sharedPrefManager.getLoginStatus()) {
                    Intent mainIntent;
                    mainIntent = new Intent(SplashActivity.this, SignInActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(mainIntent);

                    SplashActivity.this.finish();
                }
            }
        }, 2000);
    }
}
