package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.HomeModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.RequestsModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestsPurchaseAdapter extends RecyclerView.Adapter<RequestsPurchaseAdapter.ViewHolder> {
    List<RequestsModel> requests_purchases_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;


    public RequestsPurchaseAdapter(List<RequestsModel> requests_purchases_list, Context context) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return requests_purchases_list.size();
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
