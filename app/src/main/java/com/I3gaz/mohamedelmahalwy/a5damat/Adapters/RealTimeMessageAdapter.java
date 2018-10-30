package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AllServices.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RealTimeMessage.Data;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.RealTimeMessage.Message;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RealTimeMessageAdapter extends RecyclerView.Adapter<RealTimeMessageAdapter.ViewHolder> {
    List<Message> chat_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    SharedPrefManager sharedPrefManager;
    int lastPosition = -1;


    public RealTimeMessageAdapter( Context context) {
        this.chat_list = chat_list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.real_time_message_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_for_chat.setText(chat_list.get(position).getMsg());
        holder.relative_for_chat.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        sharedPrefManager = new SharedPrefManager(context);
        if (chat_list.get(position).getSender().equals(sharedPrefManager.getUserDate().getUsername())) {
            holder.relative_for_chat.setGravity(Gravity.RIGHT);
            if (!sharedPrefManager.getUserDate().getImage().isEmpty()) {
                Picasso.with(context)
                        .load(sharedPrefManager.getUserDate().getImage())
                        .into(holder.chat_image);
            }else {

                Picasso.with(context)
                        .load(R.mipmap.logo)
                        .into(holder.chat_image);
            }
        } else {

            Picasso.with(context)
                    .load(chat_list.get(position).getSender_image())
                    .into(holder.chat_image);
            holder.relative_for_chat.setGravity(Gravity.LEFT);
            holder.tv_for_chat.setBackgroundResource(R.drawable.tv_background_chat2);
            holder.tv_for_chat.setTextColor(Color.parseColor("#FFFFFF"));

//            if (sharedPrefManager.getUserDate().getImage().equals(chat_list.get(position).getFrom_user_avatar())) {
//                if (!chat_list.get(position).getTo_user_avatar().isEmpty()) {
//                    Picasso.with(context)
//                            .load(chat_list.get(position).getTo_user_avatar())
//                            .into(holder.chat_image);
//                }else {
//
//                    Picasso.with(context)
//                            .load(R.mipmap.profile_pic)
//                            .into(holder.chat_image);
//                }
//            } else {
//                if (!chat_list.get(position).getFrom_user_avatar().isEmpty()) {
//                    Picasso.with(context)
//                            .load(chat_list.get(position).getFrom_user_avatar())
//                            .into(holder.chat_image);
//                }else {
//
//                    Picasso.with(context)
//                            .load(R.mipmap.profile_pic)
//                            .into(holder.chat_image);
//                }
//            }
        }

        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return chat_list.size();
    }
    public void addAll(List<Message> Message) {
        chat_list.clear();
        chat_list.addAll(Message);
        notifyDataSetChanged();
    }

    public int getItemViewType(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.relative_for_chat)
        RelativeLayout relative_for_chat;
        @BindView(R.id.tv_for_chat)
        TextView tv_for_chat;
        @BindView(R.id.chat_image)
        ImageView chat_image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
