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
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.MessagesDetailsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MyMessages.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    List<Datum> messagesModelList;
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public MessagesAdapter(Context context) {
        this.messagesModelList = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_message_title.setText(messagesModelList.get(position).getServiceTitle());
        holder.tv_user_name.setText(messagesModelList.get(position).getSender());
        holder.tv_number_of_comments.setText(String.valueOf(messagesModelList.get(position).getCount()));
        holder.tv_date.setText(messagesModelList.get(position).getCreatedDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("message_id", String.valueOf(messagesModelList.get(position).getId()));
                args.putString("user_id", String.valueOf(messagesModelList.get(position).getSender_id()));
                args.putString("service_id", String.valueOf(messagesModelList.get(position).getService_id()));
                args.putString("reciever_id", String.valueOf(messagesModelList.get(position).getSender_id()));
                args.putString("room_id", String.valueOf(messagesModelList.get(position).getRoom_id()));
                FragmentManager fragmentManager = ((HomeActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MessagesDetailsFragment messagesDetailsFragment = new MessagesDetailsFragment();
                messagesDetailsFragment.setArguments(args);
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
                fragmentTransaction.replace(R.id.frame_container, messagesDetailsFragment);
                fragmentTransaction.commit();
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
        return messagesModelList.size();
    }

    public void addAll(List<Datum> data) {
        messagesModelList.clear();
        messagesModelList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_message_title)
        TextView tv_message_title;
        @BindView(R.id.tv_number_of_comments)
        TextView tv_number_of_comments;
        @BindView(R.id.tv_user_name)
        TextView tv_user_name;
        @BindView(R.id.tv_date)
        TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
