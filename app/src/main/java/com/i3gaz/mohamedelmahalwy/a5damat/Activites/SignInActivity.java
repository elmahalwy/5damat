package com.i3gaz.mohamedelmahalwy.a5damat.Activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.i3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {
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
