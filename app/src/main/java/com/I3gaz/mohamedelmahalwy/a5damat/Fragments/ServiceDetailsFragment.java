package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.HomeAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.HomeModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ServiceDetailsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_details_fragment_layout, container, false);
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
