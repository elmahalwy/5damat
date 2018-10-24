package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyBalance.Balance;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyBalance.AddOrTransferBalance;
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
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.makeToast;

public class BalanceFragment extends Fragment {
    @BindView(R.id.tv_total_balance)
    TextView tv_total_balance;
    @BindView(R.id.tv_pending_balance)
    TextView tv_pending_balance;
    @BindView(R.id.et_my_acccount_in_pay_pal_to_add)
    EditText et_my_acccount_in_pay_pal_to_add;
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
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


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
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer_balance();
            }
        });
        tv_paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_balance();
            }
        });
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

    void add_balance() {
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(et_my_acccount_in_pay_pal_to_add.getText().toString()) || !et_my_acccount_in_pay_pal_to_add.getText().toString().matches(emailPattern)) {
            et_my_acccount_in_pay_pal_to_add.setError("برجاء ادخال البريد الالكتروني بطريقة صحيحة");
            focusView = et_my_acccount_in_pay_pal_to_add;
            cancel = true;
        }
        if (TextUtils.isEmpty(et_add_balance.getText().toString())) {
            et_add_balance.setError("برجاء ادخال المبلغ المراد شحنه");
            focusView = et_add_balance;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            focusView.requestFocus();
        } else {
            ((HomeActivity) getActivity()).showdialog();
            RetroWeb.getClient().create(ServiceApi.class).add_balance(String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId()),
                    et_add_balance.getText().toString(), et_my_acccount_in_pay_pal_to_add.getText().toString()).enqueue(new Callback<AddOrTransferBalance>() {
                @Override
                public void onResponse(Call<AddOrTransferBalance> call, Response<AddOrTransferBalance> response) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    makeToast(getContext(), response.body().getMsg());

                }

                @Override
                public void onFailure(Call<AddOrTransferBalance> call, Throwable t) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    handleException(getActivity(), t);
                    t.printStackTrace();
                }
            });
        }
    }

    void transfer_balance() {
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(et_my_acccount_in_pay_pal.getText().toString()) || !et_my_acccount_in_pay_pal.getText().toString().matches(emailPattern)) {
            et_my_acccount_in_pay_pal.setError("برجاء ادخال البريد الالكتروني بطريقة صحيحة");
            focusView = et_my_acccount_in_pay_pal;
            cancel = true;
        }
        if (TextUtils.isEmpty(et_required_balance.getText().toString())) {
            et_required_balance.setError("برجاء ادخال المبلغ المطلوب");
            focusView = et_required_balance;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            focusView.requestFocus();
        } else {
            ((HomeActivity) getActivity()).showdialog();
            RetroWeb.getClient().create(ServiceApi.class).transfer_balance(String.valueOf(ParentClass.sharedPrefManager.getUserDate().getId()),
                    et_required_balance.getText().toString(), et_my_acccount_in_pay_pal.getText().toString()).enqueue(new Callback<AddOrTransferBalance>() {
                @Override
                public void onResponse(Call<AddOrTransferBalance> call, Response<AddOrTransferBalance> response) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    makeToast(getContext(), response.body().getMsg());
                }

                @Override
                public void onFailure(Call<AddOrTransferBalance> call, Throwable t) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    handleException(getActivity(), t);
                    t.printStackTrace();
                }
            });
        }
    }
}
