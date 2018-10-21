package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyBalance.Balance;
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

public class BalanceFragment extends Fragment {
    @BindView(R.id.tv_total_balance)
    TextView tv_total_balance;
    @BindView(R.id.tv_pending_balance)
    TextView tv_pending_balance;
    @BindView(R.id.tv_Profitsـcanـbeـwithdrawn_)
    TextView tv_Profitsـcanـbeـwithdrawn_;
    @BindView(R.id.et_add_balance)
    EditText et_add_balance;
    @BindView(R.id.tv_paypal)
    TextView tv_paypal;
    @BindView(R.id.et_my_acccount_in_pay_pal)
    EditText et_my_acccount_in_pay_pal;
    @BindView(R.id.et_required_balance)
    EditText et_required_balance;
    @BindView(R.id.tv_update)
    TextView tv_update;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.balance_fragment, container, false);
        ButterKnife.bind(this, view);
        initUI();
        initEventDriven();
        get_my_balance();

        return view;
    }

    void initUI() {
        ((HomeActivity) getContext()).tv_toolbar_title.setText("رصيد الحساب");
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
    }

    void initEventDriven() {

    }

    void get_my_balance() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).my_balance(String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId())).enqueue(new Callback<Balance>() {
            @Override
            public void onResponse(Call<Balance> call, Response<Balance> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                tv_total_balance.setText(String.valueOf(response.body().getTotalBalance()));
                tv_pending_balance.setText(String.valueOf(response.body().getPausedBalance()));
                tv_Profitsـcanـbeـwithdrawn_.setText(String.valueOf(response.body().getDragableBalance()));

            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getContext(), t);
                t.printStackTrace();
            }
        });
    }
}
