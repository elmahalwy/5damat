package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.I3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.ButterKnife;

public class InComingOrdersFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.in_coming_orders_fragment, container, false);
        ButterKnife.bind(this, view);
        initUI();
        initEventDrivn();
        return view;
    }

    private void initUI() {
    }

    private void initEventDrivn() {
    }
}
