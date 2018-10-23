package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.UserProfile.UserProfile;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.makeToast;

public class UserFragment extends Fragment {
    @BindView(R.id.iv_profile_pic)
    ImageView iv_profile_pic;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    //    @BindView(R.id.tv_location)
//    TextView tv_location;
    @BindView(R.id.tv_user_services)
    TextView tv_user_services;
    @BindView(R.id.tv_user_rate)
    TextView tv_user_rate;
    @BindView(R.id.tv_number_of_clients)
    TextView tv_number_of_clients;
    @BindView(R.id.tv_last_online)
    TextView tv_last_online;
    boolean has_service = false;
    String user_id;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        get_user_profile();
        initEventDriven();
        return view;
    }


    void initEventDriven() {
        if (has_service == true) {
            tv_user_services.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putString("user_id", String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId()));
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    MyServiceFragment myServiceFragment = new MyServiceFragment();
                    myServiceFragment.setArguments(args);

                    fragmentTransaction.replace(R.id.frame_container, myServiceFragment);
                    fragmentTransaction.commit();
                }
            });
        } else {
            tv_user_services.setVisibility(View.GONE);
        }
    }

    void get_user_profile() {
        RetroWeb.getClient().create(ServiceApi.class).user_profile(getArguments().getString("user_id")).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.body().isValue()) {
                    user_id = String.valueOf(response.body().getData().getId());
                    Picasso.with(getContext()).load(response.body().getData().getImage()).into(iv_profile_pic);
                    tv_user_name.setText(response.body().getData().getUsername());
                    tv_user_rate.setText("التقييم: " + String.valueOf(response.body().getData().getUserRate()));
                    tv_number_of_clients.setText("عدد العملاء: " + response.body().getData().getCustomerCount());
                    if (response.body().getData().getLast_online().equals("")) {

                        tv_last_online.setText("الان");
                    } else {
                        tv_last_online.setText("منذ " + response.body().getData().getLast_online() + " ساعة");
                    }
                    if (response.body().getData().isHasServices()) {
                        has_service = true;
                    } else {
                        has_service = false;
                    }

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
