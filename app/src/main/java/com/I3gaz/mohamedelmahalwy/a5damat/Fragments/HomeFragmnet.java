package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.CategoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.HomeAdapter;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.SubCatigoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AllServices.AllServices;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories.MainCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;
import com.kaopiz.kprogresshud.KProgressHUD;


import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.sharedPrefManager;

public class HomeFragmnet extends Fragment {
    RecyclerView rv_home;
    SwipeRefreshLayout swipe_home;
    HomeAdapter homeAdapter;

    LinearLayoutManager linearLayoutManager;
    public static String in_home = "";


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        swipe_home = (SwipeRefreshLayout) view.findViewById(R.id.swipe_home);
        rv_home = (RecyclerView) view.findViewById(R.id.rv_home);
        homeAdapter = new HomeAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_home.setLayoutManager(linearLayoutManager);
        CategoriesAdapter.selected_postion = 0;
        initUI();
        initEventDrivn();
        if (getArguments().getString("type").equals("search")) {
            ((HomeActivity) getContext()).tv_toolbar_title.setText("البحث المتقدم");
            search();
        }
        if (getArguments().getString("type").equals("home")) {
            ((HomeActivity) getContext()).tv_toolbar_title.setText("الرئيسية");
            get_home_data();
        }
        if (!SubCatigoriesAdapter.Sub.equals("sub")) {
            get_main_categories();
        }


        return view;
    }

    private void initUI() {
    }

    private void initEventDrivn() {
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.VISIBLE);
        swipe_home.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                search_bar.setText("");
                if (getArguments().getString("type").equals("search")) {
                    search();
                }
                if (getArguments().getString("type").equals("home")) {
                    get_home_data();
                }

                // Load complete
                onItemsLoadComplete();
            }
        });
    }

    void get_home_data() {
        ///
        Log.e("sub_Catigories_id", getArguments().getInt("id") + "");
        ((HomeActivity) getActivity()).showdialog();
        if (getArguments().getInt("id") == 0) {
            RetroWeb.getClient().create(ServiceApi.class).get_all_service(String.valueOf(sharedPrefManager.getUserDate().getId())).enqueue(new Callback<AllServices>() {
                @Override
                public void onResponse(Call<AllServices> call, Response<AllServices> response) {
                    try {
                        Log.e("home_respone", response.toString());
                        ((HomeActivity) getActivity()).dismis_dialog();
                        if (response.body().isValue()) {
                            homeAdapter.addAll(response.body().getData());
                            rv_home.setAdapter(homeAdapter);
                        }
                    } catch (Exception e) {
                        Log.e("e", String.valueOf(e));
                    }
                }

                @Override
                public void onFailure(Call<AllServices> call, Throwable t) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    handleException(getActivity(), t);
                    t.printStackTrace();
                    Log.e("t", String.valueOf(t));

                }

            });
        } else {
            Log.e("sss", "sssss");

            RetroWeb.getClient().create(ServiceApi.class).get_service_category(String.valueOf(getArguments().getInt("id"))).enqueue(new Callback<AllServices>() {
                @Override
                public void onResponse(Call<AllServices> call, Response<AllServices> response) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    if (response.body().isValue()) {
                        homeAdapter.addAll(response.body().getData());
                        rv_home.setAdapter(homeAdapter);
                    }
                }

                @Override
                public void onFailure(Call<AllServices> call, Throwable t) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    handleException(getActivity(), t);
                    t.printStackTrace();
                }
            });

        }
    }

    void search() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).search(getArguments().getString("search_key")).enqueue(new Callback<AllServices>() {
            @Override
            public void onResponse(Call<AllServices> call, Response<AllServices> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                if (response.body().isValue()) {
                    homeAdapter.addAll(response.body().getData());
                    rv_home.setAdapter(homeAdapter);
                }
            }

            @Override
            public void onFailure(Call<AllServices> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });

    }


    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...
        // Stop refresh animation
        swipe_home.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        CategoriesAdapter.selected_postion = 0;
    }

    void get_main_categories() {
        RetroWeb.getClient().create(ServiceApi.class).Get_main_categories().enqueue(new Callback<MainCategories>() {
            @Override
            public void onResponse(Call<MainCategories> call, Response<MainCategories> response) {
                if (response.body().isValue()) {
                    ((HomeActivity) getActivity()).categoriesAdapter.addAll(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<MainCategories> call, Throwable t) {
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });

    }

}
