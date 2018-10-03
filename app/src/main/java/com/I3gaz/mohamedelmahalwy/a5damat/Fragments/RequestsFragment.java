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

    @BindView(R.id.rv_requests)
    RecyclerView rv_requests;
    RequestsPurchaseAdapter requestsPurchaseAdapter;
    LinearLayoutManager linearLayoutManager;
    List<RequestsModel> list;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requests_fragment, container, false);
        ButterKnife.bind(this, view);
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

    }
}
