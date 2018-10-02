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

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.SubCatigoriesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCatigoriesAdapter extends RecyclerView.Adapter<SubCatigoriesAdapter.ViewHolder> {
    List<SubCatigoriesModel> sub_catigories_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public SubCatigoriesAdapter(List<SubCatigoriesModel> sub_catigories_list, Context context) {
        this.sub_catigories_list = sub_catigories_list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.sub_catigories_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(sub_catigories_list.get(position).getTv_title());
        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return sub_catigories_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tv_title;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
