package com.i3gaz.mohamedelmahalwy.a5damat.Activites;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.i3gaz.mohamedelmahalwy.a5damat.Models.ResetPassword.ResetPassword;
import com.i3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.i3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.i3gaz.mohamedelmahalwy.a5damat.R;
import com.i3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends ParentClass {
    @BindView(R.id.et_email_or_password)
    EditText et_email_or_password;
    @BindView(R.id.btn_login)
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        initUi();
        initEventDriven();

    }

    private void initUi() {
    }

    private void initEventDriven() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset_password();
            }
        });
    }

    void Reset_password() {
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(et_email_or_password.getText().toString())) {
            et_email_or_password.setError("برجاء ادخال البيانات بطريقة صحيحة");
            focusView = et_email_or_password;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            focusView.requestFocus();
        } else {
            show_custom_loading_dialog();
            RetroWeb.getClient().create(ServiceApi.class).RESET_PASSWORD_CALL(et_email_or_password.getText().toString()).enqueue(new Callback<ResetPassword>() {
                @Override
                public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                    dismiss_custom_loading_dialog();
                    if (response.body().isValue()) {
                        finish();
                        Toast.makeText(ForgetPasswordActivity.this, "يمكنك تسجيل الدخول الان بالبيانات الجديدة", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResetPassword> call, Throwable t) {
                    dismiss_custom_loading_dialog();
                    handleException(ForgetPasswordActivity.this, t);
                    t.printStackTrace();
                }
            });

        }
    }
}