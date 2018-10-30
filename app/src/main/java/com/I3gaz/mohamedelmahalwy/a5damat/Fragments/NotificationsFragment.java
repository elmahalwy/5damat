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


import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.NotificationAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.UserNotifications.Notifications;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;

public class NotificationsFragment extends Fragment {
    @BindView(R.id.rv_notifications)
    RecyclerView rv_notifications;

    NotificationAdapter notificationAdapter;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notifications_fragment, container, false);
        ButterKnife.bind(this, view);
        initUI();
        get_notifications();
        return view;
    }

    void initUI() {
        ((HomeActivity) getContext()).tv_toolbar_title.setText("الاشعارات");
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        notificationAdapter = new NotificationAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_notifications.setLayoutManager(linearLayoutManager);
    }


    void get_notifications() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).user_notifications(String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId())).enqueue(new Callback<Notifications>() {
            @Override
            public void onResponse(Call<Notifications> call, Response<Notifications> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    if (response.body().isValue()) {
                        if (!response.body().getData().isEmpty()) {
                            notificationAdapter.addAll(response.body().getData());
                            rv_notifications.setAdapter(notificationAdapter);
                        }
                    }
                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<Notifications> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
