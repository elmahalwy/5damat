package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;

import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MySettingsFragment extends Fragment {
    @BindView(R.id.iv_profile_pic)
    ImageView iv_profile_pic;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_my_favourites)
    TextView tv_my_favourites;
    @BindView(R.id.tv_my_balance)
    TextView tv_my_balance;
    @BindView(R.id.tv_my_services)
    TextView tv_my_services;
    @BindView(R.id.tv_edit_my_account)
    TextView tv_edit_my_account;
    @BindView(R.id.tv_support)
    TextView tv_support;
    @BindView(R.id.tv_log_out)
    TextView tv_log_out;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_settings_fragment, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {
        ((HomeActivity)getContext()).tv_toolbar_title.setText("صفحتى الشخصية");
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        if (!ParentClass.sharedPrefManager.getUserDate().getImage().isEmpty()) {
            Picasso.with(getActivity())
                    .load(((HomeActivity) getContext()).sharedPrefManager.getUserDate().getImage())
                    .into(iv_profile_pic);
        }
        tv_user_name.setText(((HomeActivity) getContext()).sharedPrefManager.getUserDate().getUsername());
    }

    void initEventDriven() {
        tv_my_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFavouritesFragment myFavouritesFragment = new MyFavouritesFragment();
                fragmentTransaction.replace(R.id.frame_container, myFavouritesFragment);
                fragmentTransaction.commit();
            }
        });
        tv_edit_my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAccountFragment editAccountFragment = new EditAccountFragment();
                fragmentTransaction.replace(R.id.frame_container, editAccountFragment);
                fragmentTransaction.commit();
            }
        });
        tv_my_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
        tv_my_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("user_id", String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId()));
                MyServiceFragment myServiceFragment = new MyServiceFragment();
                myServiceFragment.setArguments(args);
                fragmentTransaction.replace(R.id.frame_container, myServiceFragment);
                fragmentTransaction.commit();
            }
        });
        tv_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssistanceFragment assistanceFragment = new AssistanceFragment();
                fragmentTransaction.replace(R.id.frame_container, assistanceFragment);
                fragmentTransaction.commit();
            }
        });
        tv_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).sharedPrefManager.Logout();
            }
        });

    }
}
