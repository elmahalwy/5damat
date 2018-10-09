package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AllServices.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavouritesAdapter extends RecyclerView.Adapter<MyFavouritesAdapter.ViewHolder> {
    List<com.I3gaz.mohamedelmahalwy.a5damat.Models.MyFavourites.Datum> my_favourites_list;
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public MyFavouritesAdapter(Context context) {
        this.my_favourites_list = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.my_favourites_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return my_favourites_list.size();
    }

    public void addAll(List<com.I3gaz.mohamedelmahalwy.a5damat.Models.MyFavourites.Datum> data) {
        my_favourites_list.clear();
        my_favourites_list.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_service_title)
        TextView tv_service_title;
        @BindView(R.id.tv_category)
        TextView tv_category;
        @BindView(R.id.tv_user_name)
        TextView tv_user_name;
        @BindView(R.id.tv_delete)
        TextView tv_delete;
        @BindView(R.id.tv_date)
        TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}