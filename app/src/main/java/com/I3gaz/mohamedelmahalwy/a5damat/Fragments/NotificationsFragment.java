package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.NotificationAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.NotificationModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.rv_notifications)
    RecyclerView rv_notifications;
    List<NotificationModel> notificationModelList;
    NotificationAdapter notificationAdapter;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notifications_fragment, container, false);
        ButterKnife.bind(this, view);
        notificationModelList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(notificationModelList, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_notifications.setLayoutManager(linearLayoutManager);
        rv_notifications.setAdapter(notificationAdapter);
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {

    }
}
