package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.RequestsPurchaseAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.RequestsModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Requests_Tab_Model;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.sharedPrefManager;

public class RequestsFragment extends Fragment {
    public static RelativeLayout relative_purchase;
    public static TextView tv_purchase;
    public static View view_purchaes;
    public static RelativeLayout relative_requests;
    public static TextView tv_requests;
    public static View view_requests;
    public static TextView tv_in_done;
    public static TextView tv_done;
    public static TextView tv_cancle;
    public static TextView tv_in_waiting;
    public static RecyclerView rv_requests;
    public static RequestsPurchaseAdapter requestsPurchaseAdapter;
    LinearLayoutManager linearLayoutManager;
    public static String in_tab = "requests";
    public static String in_status = "waiting";
    public static String status_color_selected = "#000000";
    public static String status_color_unselected = "#7c7c7c";
    public static String tab_selected = "#3558B9";
    public static String tab_unselected = "#000000";


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requests_fragment, container, false);
        ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        tv_purchase = (TextView) view.findViewById(R.id.tv_purchase);
        view_purchaes = (View) view.findViewById(R.id.view_purchaes);
        relative_requests = (RelativeLayout) view.findViewById(R.id.relative_requests);
        tv_requests = (TextView) view.findViewById(R.id.tv_requests);
        view_requests = (View) view.findViewById(R.id.view_requests);
        relative_purchase = (RelativeLayout) view.findViewById(R.id.relative_purchase);
        tv_in_done = (TextView) view.findViewById(R.id.tv_in_done);
        tv_done = (TextView) view.findViewById(R.id.tv_done);
        tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);
        tv_in_waiting = (TextView) view.findViewById(R.id.tv_in_waiting);


        rv_requests = (RecyclerView) view.findViewById(R.id.rv_requests);
        requestsPurchaseAdapter = new RequestsPurchaseAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_requests.setLayoutManager(linearLayoutManager);
        rv_requests.setAdapter(requestsPurchaseAdapter);
        get_incoming_orders();
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
                tv_in_waiting.setTextColor(Color.parseColor(status_color_selected));
                tv_cancle.setTextColor(Color.parseColor(status_color_unselected));
                tv_done.setTextColor(Color.parseColor(status_color_unselected));
                tv_in_done.setTextColor(Color.parseColor(status_color_unselected));
                view_purchaes.setBackgroundColor(Color.parseColor(tab_selected));
                view_requests.setBackgroundColor(Color.parseColor("#E0E0E0"));

                in_tab = "purchase";
                in_status = "waiting";
                get_purchaes();
            }
        });
        relative_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_purchase.setTextColor(Color.parseColor(tab_unselected));
                tv_requests.setTextColor(Color.parseColor(tab_selected));
                tv_in_waiting.setTextColor(Color.parseColor(status_color_selected));
                tv_cancle.setTextColor(Color.parseColor(status_color_unselected));
                tv_done.setTextColor(Color.parseColor(status_color_unselected));
                tv_in_done.setTextColor(Color.parseColor(status_color_unselected));
                view_purchaes.setBackgroundColor(Color.parseColor("#E0E0E0"));
                view_requests.setBackgroundColor(Color.parseColor(tab_selected));
                in_tab = "requests";
                in_status = "waiting";
                get_incoming_orders();

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
                if (RequestsFragment.in_tab.equals("purchase")) {
                    get_purchaes();
                }
                if (RequestsFragment.in_tab.equals("requests")) {
                    get_incoming_orders();
                }
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
                if (RequestsFragment.in_tab.equals("purchase")) {
                    get_purchaes();
                }
                if (RequestsFragment.in_tab.equals("requests")) {
                    get_incoming_orders();
                }
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
                if (RequestsFragment.in_tab.equals("purchase")) {
                    get_purchaes();
                }
                if (RequestsFragment.in_tab.equals("requests")) {
                    get_incoming_orders();
                }
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
                if (RequestsFragment.in_tab.equals("purchase")) {
                    get_purchaes();
                }
                if (RequestsFragment.in_tab.equals("requests")) {
                    get_incoming_orders();
                }
            }
        });
    }

    public void get_purchaes() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).purchases(String.valueOf(sharedPrefManager.getUserDate().getId()), in_status).enqueue(new Callback<Requests_Tab_Model>() {
            @Override
            public void onResponse(Call<Requests_Tab_Model> call, Response<Requests_Tab_Model> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    Log.e("purchases_orders", response.toString());
                    if (response.body().isValue()) {
                        requestsPurchaseAdapter.addAll(response.body().getData());
                        rv_requests.setAdapter(requestsPurchaseAdapter);
                    }
                } catch (Exception e) {
                    Log.e("e", e.toString());
                }

            }

            @Override
            public void onFailure(Call<Requests_Tab_Model> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }

    public void get_incoming_orders() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).incoming_orders(String.valueOf(sharedPrefManager.getUserDate().getId()), in_status).enqueue(new Callback<Requests_Tab_Model>() {
            @Override
            public void onResponse(Call<Requests_Tab_Model> call, Response<Requests_Tab_Model> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    Log.e("incoming_orders", response.body().toString());
                    if (response.body().isValue()) {
                        requestsPurchaseAdapter.type=in_status;
                        requestsPurchaseAdapter.addAll(response.body().getData());
                        rv_requests.setAdapter(requestsPurchaseAdapter);
                    }
                } catch (Exception e) {
                    Log.e("e", e.toString());
                }

            }

            @Override
            public void onFailure(Call<Requests_Tab_Model> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
