package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.CategoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.SubCatigoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories.MainCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SubCategories.SubCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;

public class SubCatigoriesFragment extends Fragment {
    RecyclerView rv_subcatigories;
    SubCatigoriesAdapter subCatigoriesAdapter;
//
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subcatigories_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        rv_subcatigories = (RecyclerView) view.findViewById(R.id.rv_subcatigories);
        HomeFragmnet.in_home = "sub";

        subCatigoriesAdapter = new SubCatigoriesAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_subcatigories.setLayoutManager(linearLayoutManager);
        rv_subcatigories.setAdapter(subCatigoriesAdapter);
        initUI();
        initEventDrivn();
        get_sub_categroies();

        return view;
    }

    private void initUI() {
    }

    private void initEventDrivn() {
    }

    void get_sub_categroies() {
        Log.e("iiiid", String.valueOf(CategoriesAdapter.id));
        ((HomeActivity) getActivity()).showdialog();

        RetroWeb.getClient().create(ServiceApi.class).get_sub_categories(String.valueOf(CategoriesAdapter.id)).enqueue(new Callback<SubCategories>() {
            @Override
            public void onResponse(Call<SubCategories> call, Response<SubCategories> response) {
                ((HomeActivity) getActivity()).dismis_dialog();

                try {
                    if (response.body().isValue()) {
                        if (!response.body().getData().isEmpty()) {
                            subCatigoriesAdapter.addAll(response.body().getData());
                        }
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<SubCategories> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
