package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.CategoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.AddServiceFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.HomeFragmnet;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.MySettingsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories.MainCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends ParentClass {
    @BindView(R.id.frame_container)
    FrameLayout frame_container;
    @BindView(R.id.relative_requests)
    RelativeLayout relative_requests;
    @BindView(R.id.iv_requests)
    ImageView iv_requests;
    @BindView(R.id.tv_requests)
    TextView tv_requests;
    @BindView(R.id.relative_messages)
    RelativeLayout relative_messages;
    @BindView(R.id.iv_messages)
    ImageView iv_messages;
    @BindView(R.id.tv_messages)
    TextView tv_messages;
    @BindView(R.id.relative_search)
    RelativeLayout relative_search;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.relative_home)
    RelativeLayout relative_home;
    @BindView(R.id.iv_home)
    ImageView iv_home;
    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.iv_add)
    ImageView iv_add;

    AddServiceFragment addServiceFragment;
    RecyclerView rv_categories;

    CategoriesAdapter categoriesAdapter;
    LinearLayoutManager linearLayoutManager;

    FragmentManager fm;
    HomeFragmnet homeFragmnet;
    MySettingsFragment mySettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init_fragments();
        replaceFragment(homeFragmnet);
        initUi();
        initEventDriven();
        handle_clicks_bottom_tab();
        get_main_categories();
    }

    void initUi() {

        rv_categories = (RecyclerView) findViewById(R.id.rv_categories);
        categoriesAdapter = new CategoriesAdapter(HomeActivity.this);
        linearLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayout.HORIZONTAL, false);
        rv_categories.setLayoutManager(linearLayoutManager);
        rv_categories.setAdapter(categoriesAdapter);
    }

    void initEventDriven() {


    }

    void init_fragments() {
        homeFragmnet = new HomeFragmnet();
        mySettingsFragment = new MySettingsFragment();
        addServiceFragment = new AddServiceFragment();
    }

    void handle_clicks_bottom_tab() {
        relative_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(homeFragmnet);
                tv_home.setTextColor(Color.parseColor("#174BB0"));
                iv_home.setImageResource(R.mipmap.home);
                tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
                iv_messages.setImageResource(R.mipmap.message_grey);
                tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
                iv_requests.setImageResource(R.mipmap.requests_grey);
                tv_search.setTextColor(Color.parseColor("#B2BBC9"));
                iv_search.setImageResource(R.mipmap.search_grey);

            }
        });
        relative_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_home.setTextColor(Color.parseColor("#B2BBC9"));
                iv_home.setImageResource(R.mipmap.home_grey);
                tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
                iv_messages.setImageResource(R.mipmap.message_grey);
                tv_requests.setTextColor(Color.parseColor("#174BB0"));
                iv_requests.setImageResource(R.mipmap.requests);
                tv_search.setTextColor(Color.parseColor("#B2BBC9"));
                iv_search.setImageResource(R.mipmap.search_grey);

            }
        });
        relative_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_home.setTextColor(Color.parseColor("#B2BBC9"));
                iv_home.setImageResource(R.mipmap.home_grey);
                tv_messages.setTextColor(Color.parseColor("#174BB0"));
                iv_messages.setImageResource(R.mipmap.message);
                tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
                iv_requests.setImageResource(R.mipmap.requests_grey);
                tv_search.setTextColor(Color.parseColor("#B2BBC9"));
                iv_search.setImageResource(R.mipmap.search_grey);
            }
        });
        relative_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_home.setTextColor(Color.parseColor("#B2BBC9"));
                iv_home.setImageResource(R.mipmap.home_grey);
                tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
                iv_messages.setImageResource(R.mipmap.message_grey);
                tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
                iv_requests.setImageResource(R.mipmap.requests_grey);
                tv_search.setTextColor(Color.parseColor("#174BB0"));
                iv_search.setImageResource(R.mipmap.search);
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(addServiceFragment);
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }


    void get_main_categories() {
        RetroWeb.getClient().create(ServiceApi.class).Get_main_categories().enqueue(new Callback<MainCategories>() {
            @Override
            public void onResponse(Call<MainCategories> call, Response<MainCategories> response) {
                if (response.body().isValue()) {
                    categoriesAdapter.addAll(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<MainCategories> call, Throwable t) {
                handleException(HomeActivity.this, t);
                t.printStackTrace();
            }
        });

    }
}
