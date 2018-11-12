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
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MessagesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyMessages.MyMessages;
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

public class MessagesFragment extends Fragment {
    @BindView(R.id.rv_messages)
    RecyclerView rv_messages;
    MessagesAdapter messagesAdapter;
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.no_messages)
    TextView no_messages;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages_fragment, container, false);
        ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        messagesAdapter = new MessagesAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_messages.setLayoutManager(linearLayoutManager);
        ((HomeActivity) getContext()).tv_toolbar_title.setText("الرسائل");
        get_my_messages();
        return view;
    }

    void get_my_messages() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).get_my_messages(
                String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId())).enqueue(new Callback<MyMessages>() {
            @Override
            public void onResponse(Call<MyMessages> call, Response<MyMessages> response) {
                ((HomeActivity) getActivity()).dismis_dialog();

                try {
                    if (response.body().getData().size() == 0) {
                        no_messages.setVisibility(View.VISIBLE);
                        rv_messages.setVisibility(View.GONE);
                    }
                    else {
                        no_messages.setVisibility(View.GONE);
                        rv_messages.setVisibility(View.VISIBLE);
                    }
                    if (response.body().isValue()) {
                        if (!response.body().getData().isEmpty()) {
                            messagesAdapter.addAll(response.body().getData());
                            rv_messages.setAdapter(messagesAdapter);
                        }
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<MyMessages> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
