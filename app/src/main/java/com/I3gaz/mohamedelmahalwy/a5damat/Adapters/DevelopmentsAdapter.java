package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.DevelopmentModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.SubCatigoriesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DevelopmentsAdapter extends RecyclerView.Adapter<DevelopmentsAdapter.ViewHolder> {
    List<DevelopmentModel> developments_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public DevelopmentsAdapter(List<DevelopmentModel> developments_list, Context context) {
        this.developments_list = developments_list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.developments_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return developments_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.et_developments)
        EditText et_developments;
        @BindView(R.id.sp_price_for_development)
        Spinner sp_price_for_development;
        @BindView(R.id.sp_time_for_development)
        Spinner sp_time_for_development;
        @BindView(R.id.sp_time)
        Spinner sp_time;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
