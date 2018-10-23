package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.HomeAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MessagesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.RealTimeMessageAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AllServices.AllServices;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RealTimeMessage.RealTimeMessageModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.ServiceDetails;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.SharedPrefManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;

public class RealTimeMessageFragment extends Fragment {
    RecyclerView chat_recycler;
    LinearLayoutManager linearLayoutManager;
    RealTimeMessageAdapter realTimeMessageAdapter;
    @BindView(R.id.iv_send)
    ImageView iv_send;
    @BindView(R.id.et_chat)
    EditText et_chat;
    SharedPrefManager sharedPrefManager;
    ServiceDetails serviceDetails;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.real_time_message_fragment, container, false);
        ButterKnife.bind(this, view);
        sharedPrefManager = new SharedPrefManager(getContext());
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        ((HomeActivity) getContext()).tv_toolbar_title.setText("الرسائل");
        chat_recycler = (RecyclerView) view.findViewById(R.id.chat_recycler);
        realTimeMessageAdapter = new RealTimeMessageAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        chat_recycler.setLayoutManager(linearLayoutManager);
        initUi();
        initEventDriven();
        return view;
    }

    void initUi() {
        serviceDetails = (ServiceDetails) getArguments().getParcelable("response");
//        get_messages();
        Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                // your code here...
                get_messages();
            }
        };

// schedule the task to run starting now and then every hour...
        timer.schedule (hourlyTask, 0l, 1000*60);   // 1000*10*60 every 1 minut
    }

    void initEventDriven() {
        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_messages();
            }
        });
    }

    void get_messages() {
//        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).send_real_time_messages(String.valueOf(sharedPrefManager.getUserDate().getId()), String.valueOf(serviceDetails.getData().getOwnerId()), String.valueOf(serviceDetails.getData().getId()), "").enqueue(new Callback<RealTimeMessageModel>() {
            @Override
            public void onResponse(Call<RealTimeMessageModel> call, Response<RealTimeMessageModel> response) {
                try {
                    Log.e("message_respone", response.toString());
//                    ((HomeActivity) getActivity()).dismis_dialog();
                    if (response.body().isValue()) {
                        realTimeMessageAdapter.addAll(response.body().getData().getMessages());
                        chat_recycler.setAdapter(realTimeMessageAdapter);
                    }
                } catch (Exception e) {
                    Log.e("e", String.valueOf(e));
                }
            }

            @Override
            public void onFailure(Call<RealTimeMessageModel> call, Throwable t) {
//                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
                Log.e("t", String.valueOf(t));

            }

        });
    }

    void send_messages() {
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(et_chat.getText().toString())) {
            cancel = true;
            Toast.makeText(getContext(), "برجاء إضافه رساله لارسالها", Toast.LENGTH_SHORT).show();
        }
        if (cancel) {

        } else {
            RetroWeb.getClient().create(ServiceApi.class).send_real_time_messages(String.valueOf(sharedPrefManager.getUserDate().getId()),
                    String.valueOf(serviceDetails.getData().getOwnerId()),
                    String.valueOf(serviceDetails.getData().getId()),
                    et_chat.getText().toString()).enqueue(new Callback<RealTimeMessageModel>() {
                @Override
                public void onResponse(Call<RealTimeMessageModel> call, Response<RealTimeMessageModel> response) {
                    try {
                        Log.e("message_response_send", response.toString());
                        if (response.body().isValue()) {
                            et_chat.setText("");
                            get_messages();

                        }
                    } catch (Exception e) {
                        Log.e("e", String.valueOf(e));
                    }
                }

                @Override
                public void onFailure(Call<RealTimeMessageModel> call, Throwable t) {
                    handleException(getActivity(), t);
                    t.printStackTrace();
                    Log.e("t", String.valueOf(t));

                }

            });
        }
    }

}
