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

import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.SubService;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceDevelopmentsDetailsAdapter extends RecyclerView.Adapter<ServiceDevelopmentsDetailsAdapter.ViewHolder> {
    List<SubService> developments_list;
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;


    public ServiceDevelopmentsDetailsAdapter(Context context) {
        this.developments_list = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.service_developments_details_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_details.setText(developments_list.get(position).getTitle());
        holder.tv_price.setText(developments_list.get(position).getDeadline());
        holder.tv_sub_price.setText(developments_list.get(position).getSubPrices());


        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return developments_list.size();
    }

    public void addAll(List<SubService> data) {
        developments_list.clear();
        developments_list.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_details)
        TextView tv_details;
        @BindView(R.id.tv_price)
        TextView tv_sub_price;
        @BindView(R.id.tv_sub_price)
        TextView tv_price;
        @BindView(R.id.iv_choose)
        ImageView iv_choose;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
