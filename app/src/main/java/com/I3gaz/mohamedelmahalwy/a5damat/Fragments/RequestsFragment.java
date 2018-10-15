package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.HomeAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.RequestsPurchaseAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.RequestsModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestsFragment extends Fragment {
    @BindView(R.id.relative_purchase)
    RelativeLayout relative_purchase;
    @BindView(R.id.tv_purchase)
    TextView tv_purchase;
    @BindView(R.id.view_purchaes)
    View view_purchaes;
    @BindView(R.id.relative_requests)
    RelativeLayout relative_requests;
    @BindView(R.id.tv_requests)
    TextView tv_requests;
    @BindView(R.id.view_requests)
    View view_requests;
    @BindView(R.id.tv_in_done)
    TextView tv_in_done;
    @BindView(R.id.tv_done)
    TextView tv_done;
    @BindView(R.id.tv_cancle)
    TextView tv_cancle;
    @BindView(R.id.tv_in_waiting)
    TextView tv_in_waiting;
    RecyclerView rv_requests;
    RequestsPurchaseAdapter requestsPurchaseAdapter;
    LinearLayoutManager linearLayoutManager;
    List<RequestsModel> list;
    String in_tab = "requests";
    String in_status = "waiting";
    String status_color_selected = "#000000";
    String status_color_unselected = "#7c7c7c";
    String tab_selected = "#3558B9";
    String tab_unselected = "#000000";


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requests_fragment, container, false);
        ButterKnife.bind(this, view);
        rv_requests = (RecyclerView) view.findViewById(R.id.rv_requests);
        list = new ArrayList<>();
        requestsPurchaseAdapter = new RequestsPurchaseAdapter(list, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_requests.setLayoutManager(linearLayoutManager);
        rv_requests.setAdapter(requestsPurchaseAdapter);

        initUI();
        initEventDrivn();
        return view;
    }

    void initUI() {

    }

    void initEventDrivn() {
        relative_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_purchase.setTextColor(Color.parseColor(tab_selected));
                tv_requests.setTextColor(Color.parseColor(tab_unselected));
                in_tab = "purchase";
                in_status = "waiting";
            }
        });
        relative_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_purchase.setTextColor(Color.parseColor(tab_unselected));
                tv_requests.setTextColor(Color.parseColor(tab_selected));
                in_tab = "requests";
                in_status = "waiting";
            }
        });
        tv_in_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_in_waiting.setTextColor(Color.parseColor(status_color_unselected));
                tv_cancle.setTextColor(Color.parseColor(status_color_unselected));
                tv_done.setTextColor(Color.parseColor(status_color_unselected));
                tv_in_done.setTextColor(Color.parseColor(status_color_selected));
                in_status = "in_progress";
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_in_waiting.setTextColor(Color.parseColor(status_color_unselected));
                tv_cancle.setTextColor(Color.parseColor(status_color_unselected));
                tv_done.setTextColor(Color.parseColor(status_color_selected));
                tv_in_done.setTextColor(Color.parseColor(status_color_unselected));
                in_status = "deliverd";
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_in_waiting.setTextColor(Color.parseColor(status_color_unselected));
                tv_cancle.setTextColor(Color.parseColor(status_color_selected));
                tv_done.setTextColor(Color.parseColor(status_color_unselected));
                tv_in_done.setTextColor(Color.parseColor(status_color_unselected));
                in_status = "rejected";
            }
        });
        tv_in_waiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_in_waiting.setTextColor(Color.parseColor(status_color_selected));
                tv_cancle.setTextColor(Color.parseColor(status_color_unselected));
                tv_done.setTextColor(Color.parseColor(status_color_unselected));
                tv_in_done.setTextColor(Color.parseColor(status_color_unselected));
                in_status = "waiting";
            }
        });
    }
}
