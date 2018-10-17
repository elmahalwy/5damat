package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyServices.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyServiceAdapter extends RecyclerView.Adapter<MyServiceAdapter.ViewHolder> {
    List<Datum> my_service_list;
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public MyServiceAdapter(Context context) {
        this.my_service_list = new ArrayList<>();
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
        holder.tv_category.setText(my_service_list.get(position).getCategory());
        holder.tv_service_name.setText(my_service_list.get(position).getTitle());
        holder.tv_status.setText(String.valueOf(my_service_list.get(position).getOrderCount()));
        Picasso.with(context)
                .load(my_service_list.get(position).getImages().get(0))
                .into(holder.iv_my_service);
        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return my_service_list.size();
    }

    public void addAll(List<Datum> data) {
        my_service_list.clear();
        my_service_list.addAll(data);
        notifyDataSetChanged();
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
            ButterKnife.bind(this, itemView);
        }
    }
}
