package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.Development;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment.tv_total_price;
import static com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment.tv_total_price_title;

public class ServiceDevelopmentsDetailsAdapter extends RecyclerView.Adapter<ServiceDevelopmentsDetailsAdapter.ViewHolder> {
    List<Development> developments_list;
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;
    public static List<String> selected_ids_devlopments;
    public static long toal_price;


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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        toal_price = Long.parseLong(ServiceDetailsFragment.tv_main_price.getText().toString());
        selected_ids_devlopments = new ArrayList<>();
        holder.tv_details.setText(developments_list.get(position).getTitle());
        holder.tv_price.setText(developments_list.get(position).getDeadline());
        holder.tv_sub_price.setText(developments_list.get(position).getPrice());


        holder.iv_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (toal_price != 0) {
//
//
//                }
                if (!selected_ids_devlopments.contains(String.valueOf(developments_list.get(position).getId()))) {
                    ServiceDetailsFragment.tv_total_price_title.setVisibility(View.VISIBLE);
                    ServiceDetailsFragment.tv_total_price.setVisibility(View.VISIBLE);
                    holder.iv_choose.setImageResource(R.mipmap.check_mark);
                    selected_ids_devlopments.add(String.valueOf(developments_list.get(position).getId()));
                    try {
                        toal_price += Integer.parseInt(developments_list.get(position).getPrice());
                        ServiceDetailsFragment.tv_total_price.setText(String.valueOf(toal_price));


                    } catch (NumberFormatException e) {

                    }
                    Log.e("selected_ids_sizes", selected_ids_devlopments.size() + "");
                    Log.e("selected_ids", selected_ids_devlopments.toString() + "");
                    Log.e("total_price", toal_price + "ndhg");
                } else {
                    holder.iv_choose.setImageResource(R.mipmap.box);
                    try {
                        toal_price -= Integer.parseInt(developments_list.get(position).getPrice());
                        ServiceDetailsFragment.tv_total_price.setText(String.valueOf(toal_price));


                    } catch (Exception e) {
                    }
                    selected_ids_devlopments.remove(String.valueOf(developments_list.get(position).getId()));
                    Log.e("selected_ids_sizes", selected_ids_devlopments.size() + "");
                    Log.e("selected_ids", selected_ids_devlopments.toString() + "");
                    Log.e("total_price", toal_price + "ndhg");
                }
            }
        });


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

    public void addAll(List<Development> data) {
        developments_list.clear();
        developments_list.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_details)
        TextView tv_details;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_sub_price)
        TextView tv_sub_price;
        @BindView(R.id.iv_choose)
        ImageView iv_choose;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
