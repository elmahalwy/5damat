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
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MyFavouritesAdapter;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyFavourites.Favourites;
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

public class MyFavouritesFragment extends Fragment {
    @BindView(R.id.rv_my_favourites)
    RecyclerView rv_my_favourites;

    MyFavouritesAdapter myFavouritesAdapter;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_favourites_fragment, container, false);
        ButterKnife.bind(this, view);
        myFavouritesAdapter = new MyFavouritesAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_my_favourites.setLayoutManager(linearLayoutManager);
        rv_my_favourites.setAdapter(myFavouritesAdapter);
        ((HomeActivity)getContext()).tv_toolbar_title.setText("المفضلة");
        get_my_favourites();
        return view;
    }


    void get_my_favourites() {
        RetroWeb.getClient().create(ServiceApi.class).get_my_favourites(String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId())).enqueue(new Callback<Favourites>() {
            @Override
            public void onResponse(Call<Favourites> call, Response<Favourites> response) {
                if (response.body().isValue()) {
                    myFavouritesAdapter.addAll(response.body().getData());
                    rv_my_favourites.setAdapter(myFavouritesAdapter);
                }
            }

            @Override
            public void onFailure(Call<Favourites> call, Throwable t) {
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
