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
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.RealTimeMessageFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.AllMessagesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ChatUsersModel.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllMessagesAdapter extends RecyclerView.Adapter<AllMessagesAdapter.ViewHolder> {
    List<Datum> messagesList;
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public AllMessagesAdapter(Context context) {
        this.messagesList = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.msgs_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_date.setText(messagesList.get(position).getMessageDate());
        holder.tv_details.setText(messagesList.get(position).getMessage());
        holder.tv_msg_owner.setText(messagesList.get(position).getUserName());
        Picasso.with(context).load(messagesList.get(position).getUserImage()).into(holder.iv_msg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = ((HomeActivity) context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("reciver_id", String.valueOf(messagesList.get(position).getUserId()));
                args.putString("room_id", String.valueOf(messagesList.get(position).getRoomId()));
                args.putString("service_id", String.valueOf(messagesList.get(position).getService_id()));
                args.putString("order_id", String.valueOf(messagesList.get(position).getOrder_id()));
                ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
                RealTimeMessageFragment realTimeMessageFragment = new RealTimeMessageFragment();
                realTimeMessageFragment.setArguments(args);
                ft.replace(R.id.frame_container, realTimeMessageFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    public void addAll(List<Datum> data) {
        messagesList.clear();
        messagesList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_msg)
        ImageView iv_msg;
        @BindView(R.id.tv_msg_owner)
        TextView tv_msg_owner;
        @BindView(R.id.tv_date)
        TextView tv_date;
        @BindView(R.id.tv_details)
        TextView tv_details;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
