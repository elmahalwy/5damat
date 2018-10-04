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

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MyFavouritesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MyServiceAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.MyFavouritesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.MyServiceModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavouritesFragment extends Fragment {
    @BindView(R.id.rv_my_favourites)
    RecyclerView rv_my_favourites;
    List<MyFavouritesModel> myFavouritesModelList;
    MyFavouritesAdapter myFavouritesAdapter;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_favourites_fragment, container, false);
        ButterKnife.bind(this, view);
        myFavouritesModelList = new ArrayList<>();
        myFavouritesAdapter = new MyFavouritesAdapter(myFavouritesModelList, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_my_favourites.setLayoutManager(linearLayoutManager);
        rv_my_favourites.setAdapter(myFavouritesAdapter);
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {

    }
}
