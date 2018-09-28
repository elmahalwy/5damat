package com.i3gaz.mohamedelmahalwy.a5damat.Activites;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.i3gaz.mohamedelmahalwy.a5damat.Models.LoginData.Data;
import com.i3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.i3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.i3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.i3gaz.mohamedelmahalwy.a5damat.R;
import com.i3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

public class SignUpActivity extends ParentClass {
    @BindView(R.id.et_full_name)
    EditText et_full_name;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.iv_accept_credatinals)
    ImageView iv_accept_credatinals;
    @BindView(R.id.tv_sign_up)
    TextView tv_sign_up;
    @BindView(R.id.iv_google)
    ImageView iv_google;
    @BindView(R.id.iv_face)
    ImageView iv_face;
    @BindView(R.id.tv_register)
    TextView tv_register;
    String mobile_token;

    boolean checked_accept_terms = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initUi();
        initEventDriven();
    }

    void initUi() {

    }

    void initEventDriven() {
        dismiss_keyboard();
        iv_accept_credatinals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checked_accept_terms) {
//                    iv_accept_credatinals.setBackgroundResource(R.mipmap.checkmark);
                    checked_accept_terms = false;
                } else if (!checked_accept_terms) {
//                    iv_accept_credatinals.setBackgroundResource(R.mipmap.checkmark);
                    checked_accept_terms = true;
                }
            }
        });
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    void signUp() {
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(et_full_name.getText().toString())) {
            et_full_name.setError("برجاء ادخال الاسم");
            focusView = et_full_name;
            cancel = true;
        }

        if (TextUtils.isEmpty(et_phone.getText().toString())) {
            et_phone.setError("برجاء ادخال رقم الجوال");
            focusView = et_phone;
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
        if (checked_accept_terms == false) {
            makeToast(SignUpActivity.this, "يجب عليك الموافقة ع شروط التطبيق");
        }
        if (cancel) {
        } else {
            show_custom_loading_dialog();
            RetroWeb.getClient().create(ServiceApi.class).Sign_up_response_call(et_full_name.getText().toString(),
                    et_phone.getText().toString(), et_email.getText().toString(), mobile_token,
                    Build.SERIAL, et_password.getText().toString()).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    dismiss_custom_loading_dialog();
                    if (response.body().isValue()) {
                        sharedPrefManager.setLoginStatus(true);
                        sharedPrefManager.setUserDate(response.body().getData());
//                    Intent intent = new Intent(getApplicationContext(), ActivateYourAccountActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                    startActivity(intent);
//                    Bungee.zoom(SignUpActivity.this);

                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    dismiss_custom_loading_dialog();
                    handleException(SignUpActivity.this, t);
                    t.printStackTrace();
                }
            });
        }
    }
}
