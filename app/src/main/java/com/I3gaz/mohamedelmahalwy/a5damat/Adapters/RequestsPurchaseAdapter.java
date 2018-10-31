package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.AddServiceFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.InComingOrdersFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.RequestsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.RequestsModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddOrDeleteItemsToFavourites.AddOrDeleteItemToFavourit;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.RequestsChangeStatusModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Requests_Tab_Model;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.sharedPrefManager;

public class RequestsPurchaseAdapter extends RecyclerView.Adapter<RequestsPurchaseAdapter.ViewHolder> {
    List<Datum> requests_purchases_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    Dialog pop_up_cancel_request;
    EditText et_reason_of_cancle_process;
    Button btn_choose;
    int id;

    public RequestsPurchaseAdapter(Context context, List<Datum> requests_purchases_list) {
        this.requests_purchases_list = requests_purchases_list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.requests_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        pop_up_cancel_request = new Dialog(context);
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
        holder.tv_request_code.setText(String.valueOf(requests_purchases_list.get(position).getOrderId()));
        holder.tv_request_title.setText(requests_purchases_list.get(position).getTitle());
        holder.tv_category.setText(requests_purchases_list.get(position).getCategory());
        holder.tv_price.setText(requests_purchases_list.get(position).getPrice());
        holder.tv_user_name.setText(requests_purchases_list.get(position).getBuyer());
        try {
            Log.e("type", requests_purchases_list.get(position).getType());

        } catch (Exception e) {

        }
        if (requests_purchases_list.get(position).getType().equals("waiting")) {
            if (requests_purchases_list.get(position).getTab_type().equals("purchase")) {
                holder.tv_accept.setVisibility(View.GONE);
                holder.tv_refuse.setVisibility(View.GONE);
            } else {
                holder.tv_accept.setVisibility(View.VISIBLE);
                holder.tv_refuse.setVisibility(View.VISIBLE);
                holder.tv_accept.setText("قبول");
                holder.tv_refuse.setText("رفض");
            }
        }
        if (requests_purchases_list.get(position).getType().equals("in_progress")) {
            holder.tv_accept.setVisibility(View.VISIBLE);
            holder.tv_refuse.setVisibility(View.VISIBLE);
            holder.tv_accept.setText("تمت العملية");
            holder.tv_refuse.setText("الغاء");
        }
        if (requests_purchases_list.get(position).getType().equals("delivered")) {
            holder.tv_accept.setVisibility(View.GONE);
            holder.tv_refuse.setVisibility(View.GONE);
        }
        if (requests_purchases_list.get(position).getType().equals("rejected")) {
            holder.tv_accept.setVisibility(View.GONE);
            holder.tv_refuse.setVisibility(View.GONE);
        }
        holder.tv_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requests_purchases_list.get(position).getType().equals("in_progress")) {
                    pop_up_cancel_request.show();
                    id = requests_purchases_list.get(position).getOrderId();
                } else {
                    ((HomeActivity) context).showdialog();
                    RetroWeb.getClient().create(ServiceApi.class).change_request_status(
                            String.valueOf(requests_purchases_list.get(position).getOrderId()), "rejected", "").enqueue(new Callback<RequestsChangeStatusModel>() {
                        @Override
                        public void onResponse(Call<RequestsChangeStatusModel> call, Response<RequestsChangeStatusModel> response) {
                            ((HomeActivity) context).dismis_dialog();
                            try {
                                Log.e("response_change_status", response.toString());
                                if (response.body().isValue()) {
                                    Toast.makeText(context, "تمت العمليه بنجاح", Toast.LENGTH_SHORT).show();
                                    requests_purchases_list.remove(position);
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "حدث خطأ ما", Toast.LENGTH_SHORT).show();

                                }
                            } catch (Exception e) {

                            }

                        }

                        @Override
                        public void onFailure(Call<RequestsChangeStatusModel> call, Throwable t) {
                            ((HomeActivity) context).dismis_dialog();
                            handleException(context, t);
                            t.printStackTrace();
                        }
                    });
                }
            }
        });
        holder.tv_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status_to_send = "";
                if (requests_purchases_list.get(position).getType().equals("waiting")) {
                    status_to_send = "in_progress";
                }
                if (requests_purchases_list.get(position).getType().equals("in_progress")) {
                    status_to_send = "delivered";

                }
                ((HomeActivity) context).showdialog();
                Log.e("order_idd", String.valueOf(requests_purchases_list.get(position).getOrderId()));
                Log.e("status_to_send", status_to_send);
                RetroWeb.getClient().create(ServiceApi.class).change_request_status(
                        String.valueOf(requests_purchases_list.get(position).getOrderId()), status_to_send, "").enqueue(new Callback<RequestsChangeStatusModel>() {
                    @Override
                    public void onResponse(Call<RequestsChangeStatusModel> call, Response<RequestsChangeStatusModel> response) {
                        ((HomeActivity) context).dismis_dialog();
                        try {
                            Log.e("response_change_status", response.toString());
                            if (response.body().isValue()) {
                                Toast.makeText(context, "تمت العمليه بنجاح", Toast.LENGTH_SHORT).show();
                                requests_purchases_list.remove(position);
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "حدث خطأ ما", Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<RequestsChangeStatusModel> call, Throwable t) {
                        ((HomeActivity) context).dismis_dialog();
                        handleException(context, t);
                        t.printStackTrace();
                    }
                });
            }
        });
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) context).showdialog();
                RetroWeb.getClient().create(ServiceApi.class).change_request_status(
                        String.valueOf(id), "rejected", et_reason_of_cancle_process.getText().toString()).enqueue(new Callback<RequestsChangeStatusModel>() {
                    @Override
                    public void onResponse(Call<RequestsChangeStatusModel> call, Response<RequestsChangeStatusModel> response) {
                        ((HomeActivity) context).dismis_dialog();
                        try {
                            Log.e("response_change_status", response.toString());
                            if (response.body().isValue()) {
                                Toast.makeText(context, "تمت العمليه بنجاح", Toast.LENGTH_SHORT).show();
                                requests_purchases_list.remove(position);
                                pop_up_cancel_request.dismiss();
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, "حدث خطأ ما", Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<RequestsChangeStatusModel> call, Throwable t) {
                        ((HomeActivity) context).dismis_dialog();
                        handleException(context, t);
                        t.printStackTrace();
                    }
                });

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("order_id", String.valueOf(requests_purchases_list.get(position).getOrderId()));
                InComingOrdersFragment inComingOrdersFragment = new InComingOrdersFragment();
                inComingOrdersFragment.setArguments(args);
                HomeActivity.replaceFragment(inComingOrdersFragment);

            }
        });

    }

    @Override
    public int getItemCount() {
        return requests_purchases_list.size();
    }

    public void addAll(List<Datum> data) {
        requests_purchases_list.clear();
        requests_purchases_list.addAll(data);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_request_code)
        TextView tv_request_code;
        @BindView(R.id.tv_request_title)
        TextView tv_request_title;
        @BindView(R.id.tv_category)
        TextView tv_category;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_user_name)
        TextView tv_user_name;
        @BindView(R.id.tv_accept)
        TextView tv_accept;
        @BindView(R.id.tv_refuse)
        TextView tv_refuse;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
