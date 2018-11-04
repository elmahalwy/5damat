package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.SignInActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.CurrencyTypeModel.CurrencyTypeModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.RequestsChangeStatusModel;
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

import static android.content.Context.MODE_PRIVATE;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.sharedPrefManager;

public class MySettingsFragment extends Fragment {
    @BindView(R.id.iv_profile_pic)
    ImageView iv_profile_pic;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_my_favourites)
    TextView tv_my_favourites;
    @BindView(R.id.tv_my_balance)
    TextView tv_my_balance;
    @BindView(R.id.tv_my_services)
    TextView tv_my_services;
    @BindView(R.id.tv_my_chats)
    TextView tv_my_chats;
    @BindView(R.id.tv_edit_my_account)
    TextView tv_edit_my_account;
    @BindView(R.id.tv_support)
    TextView tv_support;
    @BindView(R.id.tv_log_out)
    TextView tv_log_out;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @BindView(R.id.tv_convert_currency)
    TextView tv_convert_currency;
    String current_currency = "SAR";
    SharedPreferences sharedPreferences_currency;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_settings_fragment, container, false);
        ButterKnife.bind(this, view);
        sharedPreferences_currency = getActivity().getSharedPreferences("currency", MODE_PRIVATE);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {
        ((HomeActivity) getContext()).tv_toolbar_title.setText("صفحتى الشخصية");
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        if (!ParentClass.sharedPrefManager.getUserDate().getImage().isEmpty()) {
            Picasso.with(getActivity())
                    .load(((HomeActivity) getContext()).sharedPrefManager.getUserDate().getImage())
                    .into(iv_profile_pic);
        }
        tv_user_name.setText(((HomeActivity) getContext()).sharedPrefManager.getUserDate().getUsername());
        if (sharedPreferences_currency.getString("currency", "").equals("SAR")) {
            tv_convert_currency.setText("ريال سعودي");

        } else if (sharedPreferences_currency.getString("currency", "").equals("USD")) {
            tv_convert_currency.setText("دولار امريكي");
        }
    }

    void initEventDriven() {
        tv_my_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFavouritesFragment myFavouritesFragment = new MyFavouritesFragment();
                HomeActivity.replaceFragment(myFavouritesFragment);
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
//                fragmentTransaction.replace(R.id.frame_container, myFavouritesFragment);
//                fragmentTransaction.commit();
            }
        });
        tv_edit_my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAccountFragment editAccountFragment = new EditAccountFragment();
                HomeActivity.replaceFragment(editAccountFragment);
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
//                fragmentTransaction.replace(R.id.frame_container, editAccountFragment);
//                fragmentTransaction.commit();
            }
        });
        tv_my_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BalanceFragment balanceFragment = new BalanceFragment();
                HomeActivity.replaceFragment(balanceFragment);
//                fragmentTransaction.replace(R.id.frame_container, balanceFragment);
//                fragmentTransaction.commit();
            }

        });
        tv_my_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("user_id", String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId()));
                MyServiceFragment myServiceFragment = new MyServiceFragment();
                myServiceFragment.setArguments(args);
                HomeActivity.replaceFragment(myServiceFragment);
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
//                fragmentTransaction.replace(R.id.frame_container, myServiceFragment);
//                fragmentTransaction.commit();
            }
        });
        tv_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssistanceFragment assistanceFragment = new AssistanceFragment();
                HomeActivity.replaceFragment(assistanceFragment);
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
//                fragmentTransaction.replace(R.id.frame_container, assistanceFragment);
//                fragmentTransaction.commit();
            }
        });
        tv_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).sharedPrefManager.Logout();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        tv_my_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatUsersFragment chatUsersFragment = new ChatUsersFragment();
                HomeActivity.replaceFragment(chatUsersFragment);
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
//                fragmentTransaction.replace(R.id.frame_container, chatUsersFragment);
//                fragmentTransaction.commit();
            }
        });
        tv_convert_currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences_currency.getString("currency", "").equals("SAR")) {
                    current_currency = "USD";
                    currency_type(current_currency);
                } else if (sharedPreferences_currency.getString("currency", "").equals("USD")) {
                    current_currency = "SAR";
                    currency_type(current_currency);

                }
            }
        });

    }

    void currency_type(final String currency) {
        Log.e("currency", currency);
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).currency_type(
                String.valueOf(sharedPrefManager.getUserDate().getId()), currency).enqueue(new Callback<CurrencyTypeModel>() {
            @Override
            public void onResponse(Call<CurrencyTypeModel> call, Response<CurrencyTypeModel> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    Log.e("responsechange_currency", response.toString());
                    if (response.body().isValue()) {
                        Toast.makeText(getActivity(), "تمت العمليه بنجاح", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedPreferences_currency.edit();
                        editor.putString("currency", currency);
                        editor.apply();
                        if (currency.equals("SAR")) {
                            tv_convert_currency.setText("ريال سعودي");

                        } else if (currency.equals("USD")) {
                            tv_convert_currency.setText("دولار امريكي");
                        }

                    } else {
                        Toast.makeText(getActivity(), "حدث خطأ ما", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<CurrencyTypeModel> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
