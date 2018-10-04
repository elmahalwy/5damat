package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends Fragment {
    @BindView(R.id.iv_profile_pic)
    ImageView iv_profile_pic;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.tv_user_services)
    TextView tv_user_services;
    @BindView(R.id.tv_user_rate)
    TextView tv_user_rate;
    @BindView(R.id.tv_number_of_clients)
    TextView tv_number_of_clients;
    @BindView(R.id.tv_last_online)
    TextView tv_last_online;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {

    }
}
