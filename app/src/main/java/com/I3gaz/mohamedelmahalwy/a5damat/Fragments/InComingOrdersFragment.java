package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.RequestDetailsModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.RequestsChangeStatusModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Requests_Tab_Model;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;

public class InComingOrdersFragment extends Fragment {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_category_title)
    TextView tv_category_title;
    @BindView(R.id.tv_order_price)
    TextView tv_order_price;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_order_number)
    TextView tv_order_number;
    @BindView(R.id.tv_order_delivery_time)
    TextView tv_order_delivery_time;
    @BindView(R.id.tv_purchase_date)
    TextView tv_purchase_date;
    @BindView(R.id.relative_btns)
    RelativeLayout relative_btns;
    @BindView(R.id.btn_refuse)
    Button btn_refuse;
    @BindView(R.id.btn_accept)
    Button btn_accept;
    @BindView(R.id.tv__service_details)
    TextView tv__service_details;
    @BindView(R.id.tv_buyer_instructions_details)
    TextView tv_buyer_instructions_details;
    public static String type_of_request;
    Dialog pop_up_cancel_request;
    EditText et_reason_of_cancle_process;
    Button btn_choose;
    int id;
    String status_to_be_sent = "";
    public static String tabb;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.in_coming_orders_fragment, container, false);
        ButterKnife.bind(this, view);
        pop_up_cancel_request = new Dialog(getActivity());
        pop_up_cancel_request.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pop_up_cancel_request.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        pop_up_cancel_request.setContentView(R.layout.pop_cancle_process);

        // to set width and height
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(pop_up_cancel_request.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        et_reason_of_cancle_process = (EditText) pop_up_cancel_request.findViewById(R.id.et_reason_of_cancle_process);
        btn_choose = (Button) pop_up_cancel_request.findViewById(R.id.btn_choose);
        initUI();
        initEventDrivn();
        return view;
    }

    private void initUI() {
        Log.e("type_of_request", type_of_request + "OOO");
        get_request_details();

        if (type_of_request.equals("waiting")) {
            if (tabb.equals("purchase")) {
                relative_btns.setVisibility(View.GONE);

            } else {
                relative_btns.setVisibility(View.VISIBLE);
                btn_accept.setText("قبول");
                btn_refuse.setText("رفض");
            }
        }
        if (type_of_request.equals("in_progress")) {
            relative_btns.setVisibility(View.VISIBLE);
            btn_accept.setText("تمت العملية");
            btn_refuse.setText("الغاء");
        }
        if (type_of_request.equals("delivered")) {
            relative_btns.setVisibility(View.GONE);
        }
        if (type_of_request.equals("rejected")) {
            relative_btns.setVisibility(View.GONE);

        }

    }

    private void initEventDrivn() {
        btn_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_of_request.equals("in_progress")) {
                    pop_up_cancel_request.show();
                    status_to_be_sent = "rejected";

                } else {
                    change_status_out_side_dialog(status_to_be_sent);
                }
            }
        });
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_of_request.equals("waiting")) {
                    status_to_be_sent = "in_progress";
                }
                if (type_of_request.equals("in_progress")) {
                    status_to_be_sent = "delivered";
                    Log.e("order_id", String.valueOf(id));
                    Log.e("status_to_be_sentt", status_to_be_sent);
                }
                change_status_out_side_dialog(status_to_be_sent);
            }
        });
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_to_be_sent = "rejected";

                change_status_from_dialog(status_to_be_sent);
            }
        });
    }

    public void get_request_details() {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).single_request(getArguments().getString("order_id")).enqueue(new Callback<RequestDetailsModel>() {
            @Override
            public void onResponse(Call<RequestDetailsModel> call, Response<RequestDetailsModel> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    Log.e("RequestDetailsModel", response.body().toString());
                    if (response.body().isValue()) {
                        id = response.body().getData().getOrderId();
                        tv_title.setText(response.body().getData().getServiceTitle());
                        tv_category_title.setText(response.body().getData().getServiceCategory());
                        tv_order_price.setText(response.body().getData().getPrice() + "$");
                        tv_user_name.setText(response.body().getData().getBuyer());
                        tv_order_number.setText("رقم الطلب:" + response.body().getData().getOrderId());
                        tv_order_delivery_time.setText("تاريخ التسليم المتوقع:" + response.body().getData().getDeliveryDate());
                        tv_purchase_date.setText("تاريخ الشراء:\n" + response.body().getData().getOrderDate());
                        tv__service_details.setText(response.body().getData().getServiceNote());
                        tv_buyer_instructions_details.setText(response.body().getData().getServiceRoles());
                    }
                } catch (Exception e) {
                    Log.e("e", e.toString());
                }

            }

            @Override
            public void onFailure(Call<RequestDetailsModel> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }

    public void change_status_from_dialog(String status_to_be_senttt) {
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).change_request_status(
                String.valueOf(id), status_to_be_senttt, et_reason_of_cancle_process.getText().toString()).enqueue(new Callback<RequestsChangeStatusModel>() {
            @Override
            public void onResponse(Call<RequestsChangeStatusModel> call, Response<RequestsChangeStatusModel> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    Log.e("response_change_status", response.toString());
                    if (response.body().isValue()) {
                        Toast.makeText(getActivity(), "تمت العمليه بنجاح", Toast.LENGTH_SHORT).show();
                        HomeFragmnet homeFragmnet = new HomeFragmnet();
                        Bundle args = new Bundle();
                        args.putString("type", "home");
                        args.putString("search_key", "");
                        homeFragmnet.setArguments(args);
                        HomeActivity.replaceFragment(homeFragmnet);


                    } else {
                        Toast.makeText(getActivity(), "حدث خطأ ما", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<RequestsChangeStatusModel> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }

    public void change_status_out_side_dialog(String status_to_be_sentt) {


        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).change_request_status(
                String.valueOf(id), status_to_be_sentt, "").enqueue(new Callback<RequestsChangeStatusModel>() {
            @Override
            public void onResponse(Call<RequestsChangeStatusModel> call, Response<RequestsChangeStatusModel> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    Log.e("response_change_status", response.toString());
                    if (response.body().isValue()) {
                        Toast.makeText(getActivity(), "تمت العمليه بنجاح", Toast.LENGTH_SHORT).show();
                        Bundle args = new Bundle();
                        args.putString("type", "home");
                        HomeFragmnet homeFragmnet = new HomeFragmnet();
                        homeFragmnet.setArguments(args);
                        HomeActivity.replaceFragment(homeFragmnet);
                    } else {
                        Toast.makeText(getActivity(), "حدث خطأ ما", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<RequestsChangeStatusModel> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
