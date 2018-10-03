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

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MyServiceAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.MyServiceModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyServiceFragment extends Fragment {
    @BindView(R.id.rv_my_service)
    RecyclerView rv_my_service;
    List<MyServiceModel> myServiceModelList;
    MyServiceAdapter myServiceAdapter;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_services_fragment, container, false);
        ButterKnife.bind(this, view);
        myServiceModelList = new ArrayList<>();
        myServiceAdapter = new MyServiceAdapter(myServiceModelList,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false);
        rv_my_service.setLayoutManager(linearLayoutManager);
        rv_my_service.setAdapter(myServiceAdapter);
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {

    }
}
