package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.RequestsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.RequestsModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RequestsModel.Requests_Tab_Model;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

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
    public static String type;

    public RequestsPurchaseAdapter(Context context) {
        this.requests_purchases_list = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tv_request_code.setText(String.valueOf(requests_purchases_list.get(position).getOrderId()));
        holder.tv_request_title.setText(requests_purchases_list.get(position).getTitle());
        holder.tv_category.setText(requests_purchases_list.get(position).getCategory());
        holder.tv_price.setText(requests_purchases_list.get(position).getPrice());
        holder.tv_user_name.setText(requests_purchases_list.get(position).getBuyer());
        if (type.equals("waiting")) {
            holder.tv_accept.setVisibility(View.VISIBLE);
            holder.tv_refuse.setVisibility(View.VISIBLE);
            holder.tv_accept.setText("قبول");
            holder.tv_refuse.setText("رفض");
        }
        if (type.equals("in_progress")) {
            holder.tv_accept.setVisibility(View.VISIBLE);
            holder.tv_refuse.setVisibility(View.VISIBLE);
            holder.tv_accept.setText("تمت العملية");
            holder.tv_refuse.setText("الغاء");
        }
        if (type.equals("deliverd")) {
            holder.tv_accept.setVisibility(View.GONE);
            holder.tv_refuse.setVisibility(View.GONE);
        }
        if (type.equals("rejected")) {
            holder.tv_accept.setVisibility(View.GONE);
            holder.tv_refuse.setVisibility(View.GONE);
        }
        holder.tv_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.tv_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
