package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

public class SignInActivity extends ParentClass {
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_did_you_forger_your_password)
    TextView tv_did_you_forger_your_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.iv_google)
    ImageView iv_google;
    @BindView(R.id.iv_face)
    ImageView iv_face;
    @BindView(R.id.tv_register)
    TextView tv_register;
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    String mobile_token;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        ButterKnife.bind(this);
        SharedPreferences prefs = getSharedPreferences("mobile_token", MODE_PRIVATE);
        mobile_token = prefs.getString("m_token", "");
        initUi();
        initEventDriven();
    }

    void initUi() {
    }

    void initEventDriven() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_in();
            }
        });
        tv_did_you_forger_your_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent);
                Bungee.fade(SignInActivity.this);
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                Bungee.fade(SignInActivity.this);
            }
        });
    }


    void log_in() {
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(et_email.getText().toString()) || !et_email.getText().toString().matches(emailPattern)) {
            et_email.setError("برجاء ادخال البريد الالكتروني بطريقة صحيحة");
            focusView = et_email;
            cancel = true;
        }
        if (TextUtils.isEmpty(et_password.getText().toString())) {
            et_password.setError("برجاء ادخال كلمة المرور");
            focusView = et_password;
            cancel = true;
        }
        if (et_password.getText().toString().length() < 6) {
            et_password.setError("يجب الا تقل كلمة المرور عن 6 حروف او ارقام");
            focusView = et_password;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            focusView.requestFocus();
        } else {
            show_custom_loading_dialog();
            RetroWeb.getClient().create(ServiceApi.class).Sign_in_response_call(et_email.getText().toString()
                    , et_password.getText().toString(), mobile_token, Build.SERIAL).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    dismiss_custom_loading_dialog();
                    if (response.body().isValue()) {
                        sharedPrefManager.setLoginStatus(true);
                        sharedPrefManager.setUserDate(response.body().getData());

                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    dismiss_custom_loading_dialog();
                    handleException(SignInActivity.this, t);
                    t.printStackTrace();
                }
            });
        }
    }

}
