package com.i3gaz.mohamedelmahalwy.a5damat.Activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.i3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        ButterKnife.bind(this);
        initUi();
        initEventDriven();
    }

     void initEventDriven() {
    }

    void initUi() {
    }
}
