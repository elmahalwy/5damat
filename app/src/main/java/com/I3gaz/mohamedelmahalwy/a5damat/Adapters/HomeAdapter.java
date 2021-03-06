package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AllServices.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    List<Datum> home_list;
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    public HomeAdapter(Context context) {
        home_list = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_title.setText(home_list.get(position).getTitle());
        holder.tv_time.setText(home_list.get(position).getCreatedDate());
        holder.tv_number_of_buyers.setText(String.valueOf(home_list.get(position).getOrderCount()));
        if (!(home_list.get(position).getImages().size() == 0)) {
            Picasso.with(context).load(home_list.get(position).getImages().get(0))
                    .into(holder.iv_img);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("service_id", String.valueOf(home_list.get(position).getId()));
                ServiceDetailsFragment serviceDetailsFragment = new ServiceDetailsFragment();
                serviceDetailsFragment.setArguments(args);
                HomeActivity.replaceFragment(serviceDetailsFragment);

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
        return home_list.size();
    }

    public void addAll(List<Datum> data) {
        home_list.clear();
        home_list.addAll(data);
        notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView iv_img;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_number_of_buyers)
        TextView tv_number_of_buyers;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
