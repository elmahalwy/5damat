package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.AddServiceFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.DevelopmentModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.SubCatigoriesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.SharedPrefManager;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DevelopmentsAdapter extends RecyclerView.Adapter<DevelopmentsAdapter.ViewHolder> {
    List<DevelopmentModel> developments_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;
    int size = 1;
    SharedPrefManager sharedPrefManager;
    int sp_price_for_development_id = 0;
    String sp_price_for_development_name;
    int sp_time_for_development_id = 0;
    String sp_time_for_development_name;
    int sp_time_id = 0;
    String sp_time_name;


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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        sharedPrefManager = new SharedPrefManager(context);
        holder.et_developments.setText(developments_list.get(position).getEt_developments());
        developments_list.get(position).setSp_price_for_development(String.valueOf(sp_price_for_development_id));
        developments_list.get(position).setSp_time_for_development(String.valueOf(sp_time_for_development_id));
        developments_list.get(position).setSp_time(String.valueOf(sp_time_id));
        holder.btn_add_more_developments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
                Log.e("new_position", String.valueOf(position));
                AddServiceFragment.rv_developments.smoothScrollToPosition(size - 1);
                YoYo.with(Techniques.FadeInUp)
                        .duration(800)
                        .playOn(AddServiceFragment.rv_developments);
//                MultipleCitiesFragment.rv_multible_cities.smoothScrollToPosition(size - 1);
                developments_list.get(size - 1).setSp_time_for_development("0");
                developments_list.get(size - 1).setSp_time("0");
                developments_list.get(size - 1).setSp_price_for_development("0");
                developments_list.get(size - 1).setEt_developments("0");
            }
        });
        holder.et_developments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                developments_list.get(position).setEt_developments(holder.et_developments.getText().toString());

            }
        });
        if (!developments_list.get(position).getAdded().equals(true)) {
            ((HomeActivity) context).fill_spinner(holder.sp_price_for_development, "سعر التطوير",
                    "#3558B9", "#3558B9", "air_classes");
        }
        holder.sp_price_for_development.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp_price_for_development_name = ((HomeActivity) context).list_names.get(i);
                sp_price_for_development_id = ((HomeActivity) context).list_idss.get(i);
                Log.e("_for_development_id", sp_price_for_development_id + "w");
                developments_list.get(position).setSp_price_for_development(String.valueOf(sp_price_for_development_id));
                developments_list.get(position).setAdded(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        if (!developments_list.get(position).getAdded().equals(true)) {
            ((HomeActivity) context).fill_spinner(holder.sp_time_for_development, "سيزيد من مدة التنفيذ",
                    "#3558B9", "#3558B9", "air_classes");
        }
        holder.sp_time_for_development.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp_time_for_development_name = ((HomeActivity) context).list_names.get(i);
                sp_time_for_development_id = ((HomeActivity) context).list_idss.get(i);
                Log.e("sp_time_development_id", sp_time_for_development_id + "w");
                developments_list.get(position).setSp_time_for_development(String.valueOf(sp_time_for_development_id));
                developments_list.get(position).setAdded(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        if (!developments_list.get(position).getAdded().equals(true)) {
            ((HomeActivity) context).fill_spinner(holder.sp_time, "مدة التنفيذ",
                    "#3558B9", "#3558B9", "air_classes");
        }
        holder.sp_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp_time_name = ((HomeActivity) context).list_names.get(i);
                sp_time_id = ((HomeActivity) context).list_idss.get(i);
                Log.e("sp_time_id", sp_time_id + "w");
                developments_list.get(position).setSp_time(String.valueOf(sp_time_id));
                developments_list.get(position).setAdded(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }

    @Override
    public int getItemCount() {
        return size;
    }

    public int getItemViewType(int position) {
        return position;
    }

    public void refreshData() {
        DevelopmentModel developmentModel = new DevelopmentModel();
        developments_list.add(developmentModel);
        size = developments_list.size();
        int pos = size - 1;
        notifyDataSetChanged();
        notifyItemRangeChanged(pos, size);

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
        @BindView(R.id.btn_add_more_developments)
        Button btn_add_more_developments;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
