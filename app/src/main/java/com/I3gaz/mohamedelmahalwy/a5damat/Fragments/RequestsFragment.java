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
    public RelativeLayout relative_purchase;
    public TextView tv_purchase;
    public View view_purchaes;
    public RelativeLayout relative_requests;
    public TextView tv_requests;
    public View view_requests;
    public TextView tv_in_done;
    public TextView tv_done;
    public TextView tv_cancle;
    public TextView tv_in_waiting;
    public RecyclerView rv_requests;
    public RequestsPurchaseAdapter requestsPurchaseAdapter;
    LinearLayoutManager linearLayoutManager;
    public String in_tab = "requests";
    public String in_status = "waiting";
    public String status_color_selected = "#000000";
    public String status_color_unselected = "#7c7c7c";
    public String tab_selected = "#3558B9";
    public String tab_unselected = "#000000";
    List<Datum> requests_purchases_list;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requests_fragment, container, false);
        ButterKnife.bind(this, view);
        in_tab = "requests";
        in_status = "waiting";
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

        requests_purchases_list = new ArrayList<>();
        rv_requests = (RecyclerView) view.findViewById(R.id.rv_requests);
        requestsPurchaseAdapter = new RequestsPurchaseAdapter(getContext(), requests_purchases_list);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_requests.setLayoutManager(linearLayoutManager);
        rv_requests.setAdapter(requestsPurchaseAdapter);
        get_incoming_orders();
        initUI();
        initEventDrivn();
        return view;
    }

    void initUI() {
        ((HomeActivity) getContext()).tv_toolbar_title.setText("الطلبات والمشتريات");
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
                Log.e("status", in_status);
                Log.e("tab", in_tab);
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
                Log.e("status", in_status);
                Log.e("tab", in_tab);


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
                if (in_tab.equals("purchase")) {
                    get_purchaes();
                }
                if (in_tab.equals("requests")) {
                    get_incoming_orders();
                }
                Log.e("status", in_status);
                Log.e("tab", in_tab);
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_in_waiting.setTextColor(Color.parseColor(status_color_unselected));
                tv_cancle.setTextColor(Color.parseColor(status_color_unselected));
                tv_done.setTextColor(Color.parseColor(status_color_selected));
                tv_in_done.setTextColor(Color.parseColor(status_color_unselected));
                in_status = "delivered";
                if (in_tab.equals("purchase")) {
                    get_purchaes();
                }
                if (in_tab.equals("requests")) {
                    get_incoming_orders();
                }
                Log.e("status", in_status);
                Log.e("tab", in_tab);
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
                if (in_tab.equals("purchase")) {
                    get_purchaes();
                }
                if (in_tab.equals("requests")) {
                    get_incoming_orders();
                }
                Log.e("status", in_status);
                Log.e("tab", in_tab);
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
                if (in_tab.equals("purchase")) {
                    get_purchaes();
                }
                if (in_tab.equals("requests")) {
                    get_incoming_orders();
                }
                Log.e("status", in_status);
                Log.e("tab", in_tab);
            }
        });
    }

    public void get_purchaes() {
        ((HomeActivity) getActivity()).showdialog();
        requests_purchases_list.clear();
        RetroWeb.getClient().create(ServiceApi.class).purchases(String.valueOf(sharedPrefManager.getUserDate().getId()), in_status).enqueue(new Callback<Requests_Tab_Model>() {
            @Override
            public void onResponse(Call<Requests_Tab_Model> call, Response<Requests_Tab_Model> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    Log.e("purchases_orders", response.toString());
                    if (response.body().isValue()) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            Datum datum = new Datum();
                            datum.setBuyer(response.body().getData().get(i).getBuyer());
                            datum.setCategory(response.body().getData().get(i).getCategory());
                            datum.setId(response.body().getData().get(i).getId());
                            datum.setOrderId(response.body().getData().get(i).getOrderId());
                            datum.setPrice(response.body().getData().get(i).getPrice());
                            datum.setTitle(response.body().getData().get(i).getTitle());
                            datum.setType(in_status);
                            requests_purchases_list.add(datum);
                        }
                        InComingOrdersFragment.type_of_request = in_status;
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
        requests_purchases_list.clear();
        RetroWeb.getClient().create(ServiceApi.class).incoming_orders(String.valueOf(sharedPrefManager.getUserDate().getId()), in_status).enqueue(new Callback<Requests_Tab_Model>() {
            @Override
            public void onResponse(Call<Requests_Tab_Model> call, Response<Requests_Tab_Model> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    Log.e("incoming_orders", response.body().toString());
                    if (response.body().isValue()) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            Datum datum = new Datum();
                            datum.setBuyer(response.body().getData().get(i).getBuyer());
                            datum.setCategory(response.body().getData().get(i).getCategory());
                            datum.setId(response.body().getData().get(i).getId());
                            datum.setOrderId(response.body().getData().get(i).getOrderId());
                            datum.setPrice(response.body().getData().get(i).getPrice());
                            datum.setTitle(response.body().getData().get(i).getTitle());
                            datum.setType(in_status);
                            requests_purchases_list.add(datum);
                        }
                        InComingOrdersFragment.type_of_request = in_status;
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
