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
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.AllMessagesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MyFavouritesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ChatUsersModel.ChatUsersModel;
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

public class ChatUsersFragment extends Fragment {
    @BindView(R.id.rv_chat_users)
    RecyclerView rv_chat_users;

    AllMessagesAdapter allMessagesAdapter;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_users_fragment, container, false);
        ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        allMessagesAdapter = new AllMessagesAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_chat_users.setLayoutManager(linearLayoutManager);
        rv_chat_users.setAdapter(allMessagesAdapter);
        ((HomeActivity) getContext()).tv_toolbar_title.setText("محادثاتي");
        initEventDriven();
        get_chat_users();
        return view;
    }

    void initEventDriven() {

    }

    void get_chat_users() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).get_chat_users(String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId())).enqueue(new Callback<ChatUsersModel>() {
            @Override
            public void onResponse(Call<ChatUsersModel> call, Response<ChatUsersModel> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                allMessagesAdapter.addAll(response.body().getData());
                rv_chat_users.setAdapter(allMessagesAdapter);
            }

            @Override
            public void onFailure(Call<ChatUsersModel> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getContext(), t);
                t.printStackTrace();
            }
        });
    }
}
