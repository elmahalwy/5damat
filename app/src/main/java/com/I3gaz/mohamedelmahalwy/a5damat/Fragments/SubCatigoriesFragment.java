package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.HomeAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.SubCatigoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.HomeModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.SubCatigoriesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCatigoriesFragment extends Fragment {
    @BindView(R.id.tv_number_of_subcatigories)
    TextView tv_number_of_subcatigories;
    RecyclerView rv_subcatigories;
    SubCatigoriesAdapter subCatigoriesAdapter;
    List<SubCatigoriesModel> subCatigoriesList;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subcatigories_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        rv_subcatigories = (RecyclerView) view.findViewById(R.id.rv_subcatigories);
        subCatigoriesList = new ArrayList<>();
        subCatigoriesAdapter = new SubCatigoriesAdapter(subCatigoriesList, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_subcatigories.setLayoutManager(linearLayoutManager);
        initUI();
        initEventDrivn();
        return view;
    }

    private void initUI() {
    }

    private void initEventDrivn() {
    }
}
