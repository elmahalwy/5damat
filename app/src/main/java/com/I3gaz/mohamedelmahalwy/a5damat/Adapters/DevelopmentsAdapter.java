package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.AddServiceFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.HomeFragmnet;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.InComingOrdersFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.DevelopmentModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddService;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.AddServiceJson;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.Development;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.Image;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.ImageFilesSize;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.ImagesLink;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.Video;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerssModelss;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.Urls;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ServiceGenerator;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.SharedPrefManager;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;

public class DevelopmentsAdapter extends RecyclerView.Adapter<DevelopmentsAdapter.ViewHolder> {
    List<DevelopmentModel> developments_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;
    int size = 1;
    SharedPrefManager sharedPrefManager;
    List<String> sp_price_for_development_list;
    String sp_price_for_development_name;
    List<String> sp_time_for_development_list;
    String sp_time_for_development_name;
    List<String> sp_time;
    String sp_time_name = "";
    String development_list_is_empty = "no";
    public static String service_id = "";


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
        developments_list.get(position).setSp_price_for_development(developments_list.get(position).getSp_price_for_development());
        developments_list.get(position).setSp_time_for_development(developments_list.get(position).getSp_time_for_development());
        developments_list.get(position).setSp_time(developments_list.get(position).getSp_time());
        Log.e("00" + "{" + position + "}", developments_list.get(position).getSp_price_for_development());
        Log.e("000" + "{" + position + "}", developments_list.get(position).getSp_time_for_development());
        Log.e("0000" + "{" + position + "}", developments_list.get(position).getSp_time());
        Log.e("00000" + "{" + position + "}", developments_list.get(position).getEt_developments());
        if (developments_list.size() < 2) {
            holder.iv_remove.setVisibility(View.GONE);
        }
        if (developments_list.size() > 1) {
            holder.iv_remove.setVisibility(View.VISIBLE);
        }
        holder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    developments_list.remove(position);
                    size--;
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, size);
                } catch (Exception e) {
                    Log.e("e", e.toString());
                }

            }
        });
        holder.btn_add_more_developments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
                Log.e("new_position", String.valueOf(position));

                developments_list.get(size - 1).setSp_time_for_development("0");
                developments_list.get(size - 1).setSp_time("0");
                developments_list.get(size - 1).setSp_price_for_development("0");
                developments_list.get(size - 1).setEt_developments("");
                Log.e("11", developments_list.get(position).getSp_price_for_development());
                Log.e("22", developments_list.get(position).getSp_price_for_development());
                Log.e("33", developments_list.get(position).getSp_price_for_development());
                Log.e("44", developments_list.get(position).getSp_price_for_development());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AddServiceFragment.scrol_view.fullScroll(View.FOCUS_DOWN);
                        YoYo.with(Techniques.FadeInUp)
                                .duration(800)
                                .playOn(AddServiceFragment.rv_developments);
                    }
                }, 200);
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
        if (!developments_list.get(position).isAdded_to_list()) {
            sp_price_for_development_list = new ArrayList<>();
            sp_price_for_development_list.add("سعر التطوير");
            ((HomeActivity) context).showdialog();
            RetroWeb.getClient().create(ServiceApi.class).fill_add_service_spinner("sub_prices").enqueue(new Callback<SpinnerssModelss>() {
                @Override
                public void onResponse(Call<SpinnerssModelss> call, Response<SpinnerssModelss> response) {
                    ((HomeActivity) context).dismis_dialog();
                    try {
                        sp_price_for_development_list.addAll(response.body().getData());
                        ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(context, R.layout.text_spinner, sp_price_for_development_list) {

                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                if (((TextView) v).getText().toString().equals("سعر التطوير")) {
                                    ((TextView) v).setTextColor(Color.parseColor("#3558B9"));
                                } else {
                                    ((TextView) v).setTextColor(Color.parseColor("#3558B9"));
                                }
                                return v;

                            }

                            @Override
                            public boolean isEnabled(int position) {
                                if (position == 0) {
                                    // Disable the first item from Spinner
                                    // First item will be use for hint
                                    return false;
                                } else {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                if (position == 0) {
                                    // Set the hint text color gray
                                    tv.setTextColor(Color.parseColor("#3558B9"));
                                } else {
                                    tv.setTextColor(Color.parseColor("#3558B9"));
                                }

                                return view;
                            }

                        };


                        // Drop down layout style
                        levels_list_adapter.setDropDownViewResource(R.layout.text_spinner);
                        // attaching data adapter to spinner
                        holder.sp_price_for_development.setAdapter(levels_list_adapter);
                        holder.sp_price_for_development.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                sp_price_for_development_name = sp_price_for_development_list.get(i);
                                developments_list.get(position).setSp_price_for_development(sp_price_for_development_name);
                                developments_list.get(position).setAdded_to_list(true);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });
                        if ((((HomeActivity) context).came_from.equals("edit"))) {
                            for (int i =0; i<developments_list.size();i++){
                                if (!developments_list.get(i).getSp_price_for_development().isEmpty()) {
                                    int spinnerPosition = levels_list_adapter.getPosition(developments_list.get(i).getSp_price_for_development());
                                    holder.sp_price_for_development.setSelection(spinnerPosition);
                                }
                            }
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<SpinnerssModelss> call, Throwable t) {
                    ((HomeActivity) context).dismis_dialog();
                    ((HomeActivity) context).handleException(context, t);
                    t.printStackTrace();

                }
            });
        }
        if (!developments_list.get(position).isAdded_to_list()) {
            sp_time_for_development_list = new ArrayList<>();
            sp_time_for_development_list.add("هل سيزيد من مدة التنفيذ");
            sp_time_for_development_list.add("نعم");
            sp_time_for_development_list.add("لا");
            ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(context, R.layout.text_spinner, sp_time_for_development_list) {

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);


                    if (((TextView) v).getText().toString().equals("هل سيزيد من مدة التنفيذ")) {
                        ((TextView) v).setTextColor(Color.parseColor("#3558B9"));
                    } else {
                        ((TextView) v).setTextColor(Color.parseColor("#3558B9"));
                    }
                    return v;

                }

                @Override
                public boolean isEnabled(int position) {
                    if (position == 0) {
                        // Disable the first item from Spinner
                        // First item will be use for hint
                        return false;
                    } else {
                        return true;
                    }
                }

                @Override
                public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if (position == 0) {
                        // Set the hint text color gray
                        tv.setTextColor(Color.parseColor("#3558B9"));
                    } else {
                        tv.setTextColor(Color.parseColor("#3558B9"));
                    }

                    return view;
                }

            };


            // Drop down layout style
            levels_list_adapter.setDropDownViewResource(R.layout.text_spinner);
            // attaching data adapter to spinner
            holder.sp_time_for_development.setAdapter(levels_list_adapter);
            holder.sp_time_for_development.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    sp_time_for_development_name = sp_time_for_development_list.get(i);
                    developments_list.get(position).setSp_time_for_development(sp_time_for_development_name);
                    if (developments_list.get(position).getSp_time_for_development().equals("لا")) {
                        holder.sp_time.setVisibility(View.GONE);
                    } else {
                        holder.sp_time.setVisibility(View.VISIBLE);
                    }
                    developments_list.get(position).setAdded_to_list(true);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            if ((((HomeActivity) context).came_from.equals("edit"))) {
                for (int i =0; i<developments_list.size();i++){
                    if (!developments_list.get(i).getSp_time_for_development().isEmpty()) {
                        int spinnerPosition = levels_list_adapter.getPosition(developments_list.get(i).getSp_time_for_development());
                        holder.sp_time_for_development.setSelection(spinnerPosition);
                    }
                }
            }
        }
        if (!developments_list.get(position).isAdded_to_list()) {
            sp_time = new ArrayList<>();
            sp_time.add("مده التسليم");
            ((HomeActivity) context).showdialog();
            RetroWeb.getClient().create(ServiceApi.class).fill_add_service_spinner("deadlines").enqueue(new Callback<SpinnerssModelss>() {
                @Override
                public void onResponse(Call<SpinnerssModelss> call, Response<SpinnerssModelss> response) {
                    ((HomeActivity) context).dismis_dialog();
                    try {
                        sp_time.addAll(response.body().getData());
                        ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(context, R.layout.text_spinner, sp_time) {

                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);


                                if (((TextView) v).getText().toString().equals("مده التسليم")) {
                                    ((TextView) v).setTextColor(Color.parseColor("#3558B9"));
                                } else {
                                    ((TextView) v).setTextColor(Color.parseColor("#3558B9"));
                                }
                                return v;

                            }

                            @Override
                            public boolean isEnabled(int position) {
                                if (position == 0) {
                                    // Disable the first item from Spinner
                                    // First item will be use for hint
                                    return false;
                                } else {
                                    return true;
                                }
                            }

                            @Override
                            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getDropDownView(position, convertView, parent);
                                TextView tv = (TextView) view;
                                if (position == 0) {
                                    // Set the hint text color gray
                                    tv.setTextColor(Color.parseColor("#3558B9"));
                                } else {
                                    tv.setTextColor(Color.parseColor("#3558B9"));
                                }

                                return view;
                            }

                        };


                        // Drop down layout style
                        levels_list_adapter.setDropDownViewResource(R.layout.text_spinner);
                        // attaching data adapter to spinner
                        holder.sp_time.setAdapter(levels_list_adapter);
                        holder.sp_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                sp_time_name = sp_time.get(i);
                                developments_list.get(position).setSp_time(sp_time_name);
                                developments_list.get(position).setAdded_to_list(true);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });
                        if ((((HomeActivity) context).came_from.equals("edit"))) {
                            for (int i =0; i<developments_list.size();i++){
                                if (!developments_list.get(i).getSp_time().isEmpty()) {
                                    int spinnerPosition = levels_list_adapter.getPosition(developments_list.get(i).getSp_time());
                                    holder.sp_time.setSelection(spinnerPosition);
                                }
                            }
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<SpinnerssModelss> call, Throwable t) {
                    ((HomeActivity) context).dismis_dialog();
                    ((HomeActivity) context).handleException(context, t);
                    t.printStackTrace();

                }
            });
        }
        AddServiceFragment.tv_add_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cancel = false;
                View focusView = null;
                if (TextUtils.isEmpty(AddServiceFragment.et_what_would_you_do_for_exchange_of_this_service.getText().toString())) {
                    AddServiceFragment.et_what_would_you_do_for_exchange_of_this_service.setError("برجاء اكمال البيانات");
                    focusView = AddServiceFragment.et_what_would_you_do_for_exchange_of_this_service;
                    cancel = true;
                }
                if (AddServiceFragment.service_price_name.equals("")) {
                    Toast.makeText(context, "برجاء تحديد سعر الخدمة", Toast.LENGTH_SHORT).show();
                    cancel = true;
                }
                if (AddServiceFragment.sp_category_id == 0) {
                    Toast.makeText(context, "برجاء تحديد القسم", Toast.LENGTH_SHORT).show();
                    cancel = true;
                }
//                if (AddServiceFragment.sp_sub_category_id == 0) {
//                    Toast.makeText(context, "برجاء تحديد القسم الفرعي", Toast.LENGTH_SHORT).show();
//                    cancel = true;
//                }
                if (TextUtils.isEmpty(AddServiceFragment.et_service_details.getText().toString())) {
                    AddServiceFragment.et_service_details.setError("برجاء اكمال البيانات");
                    focusView = AddServiceFragment.et_service_details;
                    cancel = true;
                }
                if (TextUtils.isEmpty(AddServiceFragment.et_key_words.getText().toString())) {
                    AddServiceFragment.et_key_words.setError("برجاء اكمال البيانات");
                    focusView = AddServiceFragment.et_key_words;
                    cancel = true;
                }
                if (AddServiceFragment.sp_service_delivery_time_name.equals("")) {
                    Toast.makeText(context, "برجاء تحديد مدة التسليم", Toast.LENGTH_SHORT).show();
                    cancel = true;
                }
                if (TextUtils.isEmpty(AddServiceFragment.et_service_instructions_to_buyer_title.getText().toString())) {
                    AddServiceFragment.et_service_instructions_to_buyer_title.setError("برجاء اكمال البيانات");
                    focusView = AddServiceFragment.et_service_instructions_to_buyer_title;
                    cancel = true;
                }
                if (AddServiceFragment.compressedImageFileList.size() == 0 &&
                        AddServiceFragment.image_links_list.size() == 0 &&
                        AddServiceFragment.video_links_list.size() == 0) {
                    Toast.makeText(context, "برجاء اختيار علي الاقل صوره واحده او فيديو واحد", Toast.LENGTH_SHORT).show();
                    cancel = true;
                }
                if (developments_list.size() > 0) {
                    Log.e("test1", developments_list.get(0).getSp_price_for_development());
                    Log.e("test2", developments_list.get(0).getSp_time());
                    Log.e("test3", developments_list.get(0).getSp_time_for_development());
                    Log.e("test4", developments_list.get(0).getEt_developments());

                    if (developments_list.size() < 2) {
                        if (developments_list.get(0).getSp_price_for_development().equals("سعر التطوير") &&
                                developments_list.get(0).getSp_time().equals("مده التسليم") &&
                                developments_list.get(0).getSp_time_for_development().equals("هل سيزيد من مدة التنفيذ") &&
                                TextUtils.isEmpty(developments_list.get(0).getEt_developments())) {
                            development_list_is_empty = "yes";
                            cancel = true;
                            Log.e("development_list_empty", development_list_is_empty);

                        } else if (developments_list.get(0).getSp_time_for_development().equals("لا")) {
                            if (developments_list.get(0).getSp_price_for_development().equals("سعر التطوير") ||
                                    developments_list.get(0).getSp_time_for_development().equals("هل سيزيد من مدة التنفيذ") ||
                                    TextUtils.isEmpty(developments_list.get(0).getEt_developments())) {
                                Toast.makeText(context, "برجاء اكمال كل بيانات التطوير", Toast.LENGTH_SHORT).show();
                                cancel = true;
                                Log.e("development_list_emptyy", development_list_is_empty);
                            }

                        } else if (developments_list.get(0).getSp_time_for_development().equals("نعم")) {
                            if (developments_list.get(0).getSp_price_for_development().equals("سعر التطوير") || developments_list.get(0).getSp_time().equals("مده التسليم") ||
                                    developments_list.get(0).getSp_time_for_development().equals("هل سيزيد من مدة التنفيذ") || TextUtils.isEmpty(developments_list.get(0).getEt_developments())) {
                                Toast.makeText(context, "برجاء اكمال كل بيانات التطوير", Toast.LENGTH_SHORT).show();
                                cancel = true;
                                Log.e("development_list_emptyy", development_list_is_empty);
                            }

                        }

                    }
                    if (developments_list.size() > 1) {
                        for (int i = 0; i < developments_list.size(); i++) {
                            if (developments_list.get(i).getSp_price_for_development().equals("0") ||
                                    developments_list.get(i).getSp_time().equals("0") ||
                                    developments_list.get(i).getSp_time_for_development().equals("0") ||
                                    developments_list.get(i).getEt_developments().equals("")) {
                                Toast.makeText(context, "برجاء اكمال كل بيانات التطوير", Toast.LENGTH_SHORT).show();
                                cancel = true;
                                Log.e("development_liemptyyy", development_list_is_empty);
                            }
                        }
                    }
                }

                if (cancel) {

                } else {
                    ((HomeActivity) context).showdialog();
                    JSONObject main = new JSONObject();
                    /////////////////////////////video_links///////////////////////////////////////
                    JSONArray video_links = new JSONArray();
                    for (int i = 0; i < AddServiceFragment.video_links_list.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("video_id", AddServiceFragment.video_links_list.get(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        video_links.put(jsonObject);
                    }
                    /////////////////////////////image_links///////////////////////////////////////
                    JSONArray image_links = new JSONArray();
                    for (int i = 0; i < AddServiceFragment.image_links_list.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("image_link", AddServiceFragment.image_links_list.get(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        image_links.put(jsonObject);
                    }
                    /////////////////////////////image_files///////////////////////////////////////
                    JSONArray image_files = new JSONArray();
                    for (int i = 0; i < AddServiceFragment.compressedImageFileList.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("image", String.valueOf(AddServiceFragment.compressedImageFileList.get(i)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        image_files.put(jsonObject);
                    }
                    /////////////////////////////image_files_sizes///////////////////////////////////////
                    JSONArray image_files_sizes = new JSONArray();
                    for (int i = 0; i < AddServiceFragment.image_file_size_list.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("size", AddServiceFragment.image_file_size_list.get(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        image_files_sizes.put(jsonObject);
                    }
                    /////////////////////////////developments_list///////////////////////////////////////
                    JSONArray developments = new JSONArray();
                    if (developments_list.size() > 0) {
                        for (int i = 0; i < developments_list.size(); i++) {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("developments_details", developments_list.get(i).getEt_developments());
                                jsonObject.put("development_price", developments_list.get(i).getSp_price_for_development());
                                jsonObject.put("will_expand_the_delivery_date", developments_list.get(i).getSp_time_for_development());
                                if (developments_list.get(i).getSp_time_for_development().equals("لا")) {
                                    jsonObject.put("delivery_date", "");
                                } else {
                                    jsonObject.put("delivery_date", developments_list.get(i).getSp_time());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            developments.put(jsonObject);
                        }
                    }
                    /////////////////////////////adding_every_thing_to_JSON_OBJECT///////////////////////////////////////
                    try {
                        main.put("title",
                                AddServiceFragment.et_what_would_you_do_for_exchange_of_this_service.getText().toString());
                        main.put("price", AddServiceFragment.service_price_name);
                        main.put("category_id", AddServiceFragment.sp_category_id);
                        main.put("sub_category_id", AddServiceFragment.sp_sub_category_id);
                        main.put("note", AddServiceFragment.et_service_details.getText().toString());
                        main.put("key_words", AddServiceFragment.et_key_words.getText().toString());
                        main.put("deadline", AddServiceFragment.sp_service_delivery_time_name);
                        main.put("roles", AddServiceFragment.et_service_instructions_to_buyer_title.getText().toString());
                        main.put("user_id", sharedPrefManager.getUserDate().getId());
                        main.put("videos", video_links);
                        main.put("images_links", image_links);
                        main.put("images", image_files);
                        main.put("image_files_sizes", image_files_sizes);
                        main.put("developments", developments);
                        Log.e("main", String.valueOf(main));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    /////////////////////////////sending_request/////////////////////////////////////////////////////////
                    String url = "http://e3gaz.net/5dmat/public/api/v1/" + "service";
                    AndroidNetworking.post(url)
                            .addHeaders("Content-Type", "application/json")
                            .addJSONObjectBody(main)
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    ((HomeActivity) context).dismis_dialog();
                                    try {
                                        Log.e("response_add_service", response.toString());
                                        if (response.getString("value").equals("true")) {
                                            service_id = response.getString("data");
                                            Log.e("service_id", service_id + "popopop");
                                            FragmentManager fragmentManager = ((HomeActivity) context).getSupportFragmentManager();
                                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                            HomeFragmnet serviceDetailsFragment = new HomeFragmnet();
                                            fragmentTransaction.replace(R.id.frame_container, serviceDetailsFragment);
                                            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                            fragmentTransaction.remove(new AddServiceFragment());
                                            fragmentTransaction.commit();
                                            fragmentManager.popBackStack();
                                        } else {
                                            Toast.makeText(context, "حدث خطأ ما", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(ANError error) {
                                    ((HomeActivity) context).dismis_dialog();

                                    if (error.getErrorCode() != 0) {

                                        // received error from server
                                        // error.getErrorCode() - the error code from server
                                        // error.getErrorBody() - the error body from server
                                        // error.getErrorDetail() - just an error detail
                                        Log.e("onError errorCode : ", String.valueOf(error.getErrorCode()));
                                        Log.e("onError errorBody : ", error.getErrorBody());
                                        if (error.getErrorCode() == 400) {
                                            Toast.makeText(context, "حدث خطأ ما...", Toast.LENGTH_SHORT).show();
                                        }
                                        if (error.getErrorCode() == 500) {
                                            Toast.makeText(context, "خطأ فى الاتصال بالسيرفر...", Toast.LENGTH_SHORT).show();
                                        }
                                        // get parsed error object (If ApiError is your class)

                                    } else {
                                        // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                        Log.e("onError errorDetail : ", error.getErrorDetail());
                                        if (error.getErrorDetail().equals("connectionError")) {
                                            Toast.makeText(context, "حطأ فى الاتصال بالانترنت...", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });

                }
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
        @BindView(R.id.iv_remove)
        ImageView iv_remove;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
