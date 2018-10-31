package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.HomeFragmnet;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.SubCatigoriesFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories.Datum;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    List<Datum> categories_list;
    Context context;
    LayoutInflater layoutInflater;
    public static int id;
    int selected_postion = -1;

    public CategoriesAdapter(Context context) {
        categories_list = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.categories_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesAdapter.ViewHolder holder, final int position) {
        holder.tv_category.setText(categories_list.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = categories_list.get(position).getId();
                selected_postion = holder.getAdapterPosition();
                notifyDataSetChanged();
                if (id == 0) {
                    Bundle args = new Bundle();
                    args.putString("type", "home");
//                    FragmentManager fragmentManager = ((HomeActivity) context).getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    HomeFragmnet homeFragmnet = new HomeFragmnet();
                    homeFragmnet.setArguments(args);
                    HomeActivity.replaceFragment(homeFragmnet);
//                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
//                    fragmentTransaction.replace(R.id.frame_container, homeFragmnet);
//                    fragmentTransaction.commit();
                } else {
//                    FragmentManager fragmentManager = ((HomeActivity) context).getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SubCatigoriesFragment subCatigoriesFragment = new SubCatigoriesFragment();
                    HomeActivity.replaceFragment(subCatigoriesFragment);
                    //
//      fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
//
//                    fragmentTransaction.replace(R.id.frame_container, subCatigoriesFragment);
//                    fragmentTransaction.commit();
                }

            }
        });
        if (selected_postion == position) {

            holder.tv_category.setTextColor(Color.parseColor("#3558B9"));
        } else {
            holder.tv_category.setTextColor(Color.parseColor("#B2BBC9"));
        }
    }


    @Override
    public int getItemCount() {
        return categories_list.size();
    }

    public int getItemViewType(int position) {
        return position;
    }


    public void addAll(List<Datum> data) {
        categories_list.clear();
        categories_list.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_category)
        TextView tv_category;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
