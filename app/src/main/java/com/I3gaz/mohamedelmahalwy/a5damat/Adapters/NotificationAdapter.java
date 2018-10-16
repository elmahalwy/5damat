package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.UserNotifications.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    List<Datum> notificationModelList;
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public NotificationAdapter(Context context) {
        this.notificationModelList = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_content.setText(notificationModelList.get(position).getBody());
        holder.tv_time.setText(notificationModelList.get(position).getCreatedDate());
        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    public void addAll(List<Datum> data) {
        notificationModelList.clear();
        notificationModelList.addAll(data);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tv_content;
        @BindView(R.id.tv_time)
        TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
