package com.I3gaz.mohamedelmahalwy.a5damat.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.Data;


/**
 * Created by omarn on 3/5/2018.
 */

public class SharedPrefManager {
    final static String SHARED_PREF_NAME = "masari_shared";
    final static String LOGIN_STATUS = "masari_shared_login_status";
    final static String FIRST_TIME = "masari_shared_first_time";
    Context mContext;


    public SharedPrefManager(Context mContext) {
        this.mContext = mContext;
    }

    public Boolean getLoginStatus() {
        final SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        Boolean value = sharedPreferences.getBoolean(LOGIN_STATUS, false);
        return value;
    }

    public void setLoginStatus(Boolean status) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,
                0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_STATUS, status);
        editor.apply();
    }

    public Boolean isFirstTime() {
        final SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                SHARED_PREF_NAME, 0);
        Boolean value = sharedPreferences.getBoolean(FIRST_TIME, true);
        return value;
    }

    public void setFirstTime(Boolean status) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME,
                0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FIRST_TIME, status);
        editor.apply();
    }

    /**
     * return userModel which hold all user data
     *
     * @return user model
     */
    public Data getUserDate() {
        Data userModel = new Data();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, 0);
        userModel.setId(Integer.parseInt(sharedPreferences.getString("id", "")));
        userModel.setUsername(sharedPreferences.getString("name", ""));
        userModel.setEmail(sharedPreferences.getString("email", ""));
        userModel.setMobile(sharedPreferences.getString("phone", ""));
        userModel.setImage(sharedPreferences.getString("image", ""));
        userModel.setApiToken(sharedPreferences.getString("token", ""));
        userModel.setGender(sharedPreferences.getString("gender", ""));
        userModel.setCountry(sharedPreferences.getString("country", ""));
        userModel.setCountryCode(sharedPreferences.getString("country_code", ""));
        userModel.setBirthday(sharedPreferences.getString("birthday", ""));
        return userModel;
    }

    /**
     * saving user data to be used in profile
     *
     * @param user is the model which hold all user data
     */
    public void setUserDate(Data user) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id", String.valueOf(user.getId()));
        editor.putString("name", user.getUsername());
        editor.putString("email", user.getEmail());
        editor.putString("phone", user.getMobile());
        editor.putString("token", user.getApiToken());
        editor.putString("image", user.getImage());
        editor.putString("gender",user.getGender());
        editor.putString("country",user.getCountry());
        editor.putString("country_code",user.getCountryCode());
        editor.putString("birthday",user.getBirthday());
        editor.apply();
    }

    /**
     * this method is responsible for user logout and clearing cache
     */
    public void Logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        setFirstTime(false);
    }


}
