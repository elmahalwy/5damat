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
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyServices.MyServices;
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

public class MyServiceFragment extends Fragment {
    @BindView(R.id.rv_my_service)
    RecyclerView rv_my_service;

    MyServiceAdapter myServiceAdapter;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_services_fragment, container, false);
        ButterKnife.bind(this, view);
        myServiceAdapter = new MyServiceAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_my_service.setLayoutManager(linearLayoutManager);

        get_my_services();
        return view;
    }

    void get_my_services() {
        RetroWeb.getClient().create(ServiceApi.class).get_my_services(
                String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId()))
                .enqueue(new Callback<MyServices>() {
                    @Override
                    public void onResponse(Call<MyServices> call, Response<MyServices> response) {
                        myServiceAdapter.addAll(response.body().getData());
                        rv_my_service.setAdapter(myServiceAdapter);
                    }

                    @Override
                    public void onFailure(Call<MyServices> call, Throwable t) {
                        handleException(getActivity(), t);
                        t.printStackTrace();
                    }
                });

    }
}
