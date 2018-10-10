package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import butterknife.ButterKnife;

public class SplashActivity extends ParentClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

//        YoYo.with(Techniques.FadeInUp)
//                .duration(500)
//                .repeat(5)
//                .playOn(iv_logo);

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
