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

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MessagesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MyFavouritesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.MessagesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.MyFavouritesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesFragment extends Fragment {
    @BindView(R.id.rv_my_favourites)
    RecyclerView rv_messages;
    List<MessagesModel> messagesModelList;
    MessagesAdapter messagesAdapter;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages_fragment, container, false);
        ButterKnife.bind(this, view);
        messagesModelList = new ArrayList<>();
        messagesAdapter = new MessagesAdapter(messagesModelList, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_messages.setLayoutManager(linearLayoutManager);
        rv_messages.setAdapter(messagesAdapter);
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {

    }
}
