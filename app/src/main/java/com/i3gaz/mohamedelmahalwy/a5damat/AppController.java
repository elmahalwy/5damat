package com.i3gaz.mohamedelmahalwy.a5damat;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import java.util.Locale;

import in.myinnos.customfontlibrary.TypefaceUtil;

/**
 * Created by cz on 19/03/2018.
 */

public class AppController extends Application {
    SharedPreferences sharedPreferences_language;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences_language = getSharedPreferences("language", MODE_PRIVATE);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "src/assets/fonts/Cairo_Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLocale();
    }
    private void setLocale() {
        Locale locale = new Locale(sharedPreferences_language.getString("lang", ""));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
