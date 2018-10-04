package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.MessagesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.MyFavouritesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    List<MessagesModel> messagesModelList = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public MessagesAdapter(List<MessagesModel> messagesModelList, Context context) {
        this.messagesModelList = messagesModelList;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messagesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_message_title)
        TextView tv_message_title;
        @BindView(R.id.tv_number_of_comments)
        TextView tv_number_of_comments;
        @BindView(R.id.tv_user_name)
        TextView tv_user_name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
