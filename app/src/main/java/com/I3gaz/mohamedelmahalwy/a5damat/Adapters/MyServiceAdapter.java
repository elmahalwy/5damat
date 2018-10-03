package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.HomeModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.MyServiceModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyServiceAdapter extends RecyclerView.Adapter<MyServiceAdapter.ViewHolder> {
    List<MyServiceModel> my_service_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public MyServiceAdapter(List<MyServiceModel> my_service_list, Context context) {
        this.my_service_list = my_service_list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.my_service_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return my_service_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_my_service)
        ImageView iv_my_service;
        @BindView(R.id.tv_service_name)
        TextView tv_service_name;
        @BindView(R.id.tv_category)
        TextView tv_category;
        @BindView(R.id.tv_status)
        TextView tv_status;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
