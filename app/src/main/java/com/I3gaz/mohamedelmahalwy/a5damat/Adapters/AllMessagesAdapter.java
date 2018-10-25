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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_date.setText(messagesList.get(position).getMessageDate());
        holder.tv_details.setText(messagesList.get(position).getMessage());
        holder.tv_msg_owner.setText(messagesList.get(position).getUserName());
        Picasso.with(context).load(messagesList.get(position).getUserImage()).into(holder.iv_msg);

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
