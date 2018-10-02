package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.HomeAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AddServiceFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_service_fragment_layout, container, false);
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
