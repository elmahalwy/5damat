package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import java.util.List;

import butterknife.ButterKnife;

public class SplashActivity extends ParentClass {
    String prefix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        if (intent != null && intent.getData() != null) {
            Uri uri = getIntent().getData();
            Log.e("deep_intent", intent.getData() + "");
            Log.e("uri", uri + "");
            Log.e("deep_link_work", "deep_link_success");
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() > 0){
                prefix = pathSegments.get(0);
                Log.e("prefix", prefix);
            }
            Log.e("prefix1", prefix);
        }
        ToMainActivity();
    }

    private void ToMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefManager.getLoginStatus()) {
                    Intent mainIntent;
                    mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(mainIntent);

                    SplashActivity.this.finish();
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
