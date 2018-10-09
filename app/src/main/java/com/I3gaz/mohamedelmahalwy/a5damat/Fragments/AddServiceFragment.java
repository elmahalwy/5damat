package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.CategoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.DevelopmentsAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.HomeAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.VideoAndImageAdapater;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.DevelopmentModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.VideoAndImageModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerssModelss;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.Urls;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class AddServiceFragment extends Fragment {
    @BindView(R.id.et_what_would_you_do_for_exchange_of_this_service)
    EditText et_what_would_you_do_for_exchange_of_this_service;
    @BindView(R.id.sp_service_price)
    Spinner sp_service_price;
    List<String> sp_service_price_list;
    String service_price_name;
    @BindView(R.id.sp_category)
    Spinner sp_category;
    List<String> sp_category_list;
    List<Integer> sp_category_list_ids;
    int sp_category_id = 0;
    String sp_category_name;
    @BindView(R.id.sp_sub_category)
    Spinner sp_sub_category;
    List<String> sp_sub_category_list;
    List<Integer> sp_sub_category_list_ids;
    int sp_sub_category_id = 0;
    String sp_sub_category_name;
    @BindView(R.id.et_service_details)
    EditText et_service_details;
    RecyclerView rv_videos_and_images;
    VideoAndImageAdapater videoAndImageAdapater;
    List<VideoAndImageModel> videoAndImageList;
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.tv_add_photo_or_video)
    TextView tv_add_photo_or_video;
    @BindView(R.id.add_more)
    TextView add_more;
    @BindView(R.id.et_key_words)
    EditText et_key_words;
    @BindView(R.id.sp_service_delivery_time)
    Spinner sp_service_delivery_time;
    List<String> sp_service_delivery_time_list;
    String sp_service_delivery_time_name;
    @BindView(R.id.et_service_instructions_to_buyer_title)
    EditText et_service_instructions_to_buyer_title;
    public static RecyclerView rv_developments;
    List<DevelopmentModel> developmentList;
    LinearLayoutManager linearLayoutManager1;
    DevelopmentsAdapter developmentsAdapter;
    @BindView(R.id.tv_sign_up)
    TextView tv_sign_up;
    @BindView(R.id.view_for_add_image_at_first_Time)
    View view_for_add_image_at_first_Time;
    Dialog pop_up_add_photo_video;
    ImageView iv_from_my_device;
    ImageView iv_from_link;
    EditText et_image_from_link;
    ImageView iv_video_youtube;
    EditText et_video_from_link;
    Button btn_choose;
    String type = "null";
    static final int PICK_IMAGE_REQUEST = 1;
    List<File> image_files_list = new ArrayList<>();
    List<String> image_links_list = new ArrayList<>();
    List<String> video_links_list = new ArrayList<>();
    String filePath;
    File file;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_service_fragment_layout, container, false);
        ButterKnife.bind(this, view);

        /////////////////////////////////videos_and_images//////////////////////////////
        videoAndImageList = new ArrayList<>();
        rv_videos_and_images = (RecyclerView) view.findViewById(R.id.rv_videos_and_images);
        videoAndImageAdapater = new VideoAndImageAdapater(videoAndImageList, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);
        rv_videos_and_images.setLayoutManager(linearLayoutManager);
        rv_videos_and_images.setAdapter(videoAndImageAdapater);
        ////////////////////////////Developments///////////////////////////////////////
        rv_developments = (RecyclerView) view.findViewById(R.id.rv_developments);
        developmentList = new ArrayList<>();
        developmentsAdapter = new DevelopmentsAdapter(developmentList, getContext());
        linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        rv_developments.setLayoutManager(linearLayoutManager1);
        rv_developments.setAdapter(developmentsAdapter);
        pop_up_add_photo_video = new Dialog(getActivity());
        pop_up_add_photo_video.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pop_up_add_photo_video.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        pop_up_add_photo_video.setContentView(R.layout.pop_up_add_photo_video);

        // to set width and height
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(pop_up_add_photo_video.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        iv_from_my_device = (ImageView) pop_up_add_photo_video.findViewById(R.id.iv_from_my_device);
        iv_from_link = (ImageView) pop_up_add_photo_video.findViewById(R.id.iv_from_link);
        et_image_from_link = (EditText) pop_up_add_photo_video.findViewById(R.id.et_image_from_link);
        iv_video_youtube = (ImageView) pop_up_add_photo_video.findViewById(R.id.iv_video_youtube);
        et_video_from_link = (EditText) pop_up_add_photo_video.findViewById(R.id.et_video_from_link);
        btn_choose = (Button) pop_up_add_photo_video.findViewById(R.id.btn_choose);

        initUI();
        initEventDrivn();
        return view;
    }

    private void initUI() {
        ((HomeActivity) getActivity()).dismiss_keyboard();
        getsp_service_price();
        getsp_category();
        getsp_sub_category();
        getsp_service_delivery_time();
        initialize_list();
    }

    private void initEventDrivn() {
        view_for_add_image_at_first_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop_up_add_photo_video.show();
            }
        });
        add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop_up_add_photo_video.show();
            }
        });
        ///////////Dialog_Actions///////////////////////
        iv_from_my_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_from_my_device.setImageResource(R.mipmap.circle2);
                iv_from_link.setImageResource(R.mipmap.circle_grey);
                iv_video_youtube.setImageResource(R.mipmap.circle_grey);
                et_image_from_link.setVisibility(View.GONE);
                et_video_from_link.setVisibility(View.GONE);
                btn_choose.setText("اختيار الصورة");
                type = "iv_from_my_device";

            }
        });
        iv_from_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_from_my_device.setImageResource(R.mipmap.circle_grey);
                iv_from_link.setImageResource(R.mipmap.circle2);
                iv_video_youtube.setImageResource(R.mipmap.circle_grey);
                et_image_from_link.setVisibility(View.VISIBLE);
                et_video_from_link.setVisibility(View.GONE);
                btn_choose.setText("اختيار الصورة من الرابط");
                type = "iv_from_link";


            }
        });
        iv_video_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_from_my_device.setImageResource(R.mipmap.circle_grey);
                iv_from_link.setImageResource(R.mipmap.circle_grey);
                iv_video_youtube.setImageResource(R.mipmap.circle2);
                et_image_from_link.setVisibility(View.GONE);
                et_video_from_link.setVisibility(View.VISIBLE);
                btn_choose.setText("اختيار الڤيديو من الرابط");
                type = "iv_video_youtube";

            }
        });
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("null")) {
                    Toast.makeText(getContext(), "برجاء إختيار صورة او ڤيديو", Toast.LENGTH_SHORT).show();
                }
                if (type.equals("iv_from_my_device")) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
                }
                if (type.equals("iv_from_link")) {
                    if (TextUtils.isEmpty(et_image_from_link.getText().toString())) {
                        Toast.makeText(getContext(), "برجاء إضافة رابط الصورة", Toast.LENGTH_SHORT).show();
                    } else {
                        tv_add_photo_or_video.setVisibility(View.GONE);
                        view_for_add_image_at_first_Time.setVisibility(View.GONE);
                        rv_videos_and_images.setVisibility(View.VISIBLE);
                        add_more.setVisibility(View.VISIBLE);
                        VideoAndImageModel videoAndImageModel = new VideoAndImageModel();
                        videoAndImageModel.setIv_service(et_image_from_link.getText().toString());
                        videoAndImageModel.setYoutube_player_view("empty");
                        videoAndImageModel.setType("image_link");
                        videoAndImageList.add(videoAndImageModel);
                        image_links_list.add(et_image_from_link.getText().toString());
                        rv_videos_and_images.getAdapter().notifyDataSetChanged();
                        rv_videos_and_images.scrollToPosition(videoAndImageList.size() - 1);
                        pop_up_add_photo_video.dismiss();

                    }
                }
                if (type.equals("iv_video_youtube")) {
                    if (TextUtils.isEmpty(et_video_from_link.getText().toString())) {
                        Toast.makeText(getContext(), "برجاء إضافة رابط الڤيديو", Toast.LENGTH_SHORT).show();
                    } else {
                        tv_add_photo_or_video.setVisibility(View.GONE);
                        view_for_add_image_at_first_Time.setVisibility(View.GONE);
                        rv_videos_and_images.setVisibility(View.VISIBLE);
                        add_more.setVisibility(View.VISIBLE);
                        video_links_list.add(et_video_from_link.getText().toString());

                        VideoAndImageModel videoAndImageModel = new VideoAndImageModel();
                        videoAndImageModel.setIv_service("empty");
                        videoAndImageModel.setYoutube_player_view(get_youtube_id(et_video_from_link.getText().toString()));
                        Log.e("video_id", get_youtube_id(et_video_from_link.getText().toString()));
                        videoAndImageModel.setType("video_link");
                        videoAndImageList.add(videoAndImageModel);
                        video_links_list.add(get_youtube_id(et_video_from_link.getText().toString()));
                        rv_videos_and_images.getAdapter().notifyDataSetChanged();
                        rv_videos_and_images.scrollToPosition(videoAndImageList.size() - 1);

                        pop_up_add_photo_video.dismiss();

                    }
                }
            }
        });
        ///////////////////////////////////////////////////
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {

            final Uri imageUri = data.getData();
            filePath = getRealPathFromUri(getActivity(), imageUri);
            file = new File(filePath);
            Log.e("file", "" + file);
            try {
                pop_up_add_photo_video.dismiss();
                tv_add_photo_or_video.setVisibility(View.GONE);
                view_for_add_image_at_first_Time.setVisibility(View.GONE);
                rv_videos_and_images.setVisibility(View.VISIBLE);
                add_more.setVisibility(View.VISIBLE);
                VideoAndImageModel videoAndImageModel = new VideoAndImageModel();
                videoAndImageModel.setIv_service("empty");
                videoAndImageModel.setYoutube_player_view("empty");
                videoAndImageModel.setImage_uri(imageUri);
                videoAndImageModel.setType("file");
                videoAndImageList.add(videoAndImageModel);
                image_files_list.add(file);
                rv_videos_and_images.getAdapter().notifyDataSetChanged();
                rv_videos_and_images.scrollToPosition(videoAndImageList.size() - 1);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "حدث خطأ ما", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getActivity(), "لا يمكن رفع الصورة", Toast.LENGTH_LONG).show();
        }
    }

    private String get_youtube_id(String url) {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|watch\\?v%3D|%2Fvideos%2F|embed%2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return "0";
        }
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void getsp_service_price() {
        sp_service_price_list = new ArrayList<>();
        sp_service_price_list.add("السعر");
        RetroWeb.getClient().create(ServiceApi.class).fill_add_service_spinner("main_prices").enqueue(new Callback<SpinnerssModelss>() {
            @Override
            public void onResponse(Call<SpinnerssModelss> call, Response<SpinnerssModelss> response) {
                try {
                    sp_service_price_list.addAll(response.body().getData());
                    ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(getContext(), R.layout.text_spinner, sp_service_price_list) {

                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);


                            if (((TextView) v).getText().toString().equals("السعر")) {
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
                    sp_service_price.setAdapter(levels_list_adapter);
                    sp_service_price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            service_price_name = sp_service_price_list.get(i);
                            Log.e("service_price_name", service_price_name + "w");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SpinnerssModelss> call, Throwable t) {
                ((HomeActivity) getActivity()).handleException(getContext(), t);
                t.printStackTrace();

            }
        });
    }

    private void getsp_category() {
        sp_category_list = new ArrayList<>();
        sp_category_list_ids = new ArrayList<>();
        sp_category_list.add("القسم");
        sp_category_list_ids.add(0);
        RetroWeb.getClient().create(ServiceApi.class).fill_spinner("category").enqueue(new Callback<SpinnerModel>() {
            @Override
            public void onResponse(Call<SpinnerModel> call, Response<SpinnerModel> response) {
                try {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        sp_category_list.add(response.body().getData().get(i).getName());
                        sp_category_list_ids.add(response.body().getData().get(i).getId());
                    }
                    ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(getContext(), R.layout.text_spinner, sp_category_list) {

                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);


                            if (((TextView) v).getText().toString().equals("القسم")) {
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
                    sp_category.setAdapter(levels_list_adapter);
                    sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sp_category_name = sp_category_list.get(i);
                            sp_category_id = sp_category_list_ids.get(i);
                            Log.e("sp_category_name", sp_category_name + "w");
                            getsp_sub_category();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SpinnerModel> call, Throwable t) {
                ((HomeActivity) getActivity()).handleException(getContext(), t);
                t.printStackTrace();

            }
        });
    }

    private void getsp_sub_category() {
        sp_sub_category_list = new ArrayList<>();
        sp_sub_category_list_ids = new ArrayList<>();
        sp_sub_category_list.add("القسم الفرعي");
        sp_sub_category_list_ids.add(0);
        Log.e("sp_category_id",sp_category_id+"");
        RetroWeb.getClient().create(ServiceApi.class).fill_spinner_sub_category(String.valueOf(sp_category_id)).enqueue(new Callback<SpinnerModel>() {
            @Override
            public void onResponse(Call<SpinnerModel> call, Response<SpinnerModel> response) {
                try {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        sp_sub_category_list.add(response.body().getData().get(i).getName());
                        sp_sub_category_list_ids.add(response.body().getData().get(i).getId());
                    }
                    ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(getContext(), R.layout.text_spinner, sp_sub_category_list) {

                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);


                            if (((TextView) v).getText().toString().equals("القسم الفرعي")) {
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
                    sp_sub_category.setAdapter(levels_list_adapter);
                    sp_sub_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sp_category_name = sp_sub_category_list.get(i);
                            sp_category_id = sp_sub_category_list_ids.get(i);
                            Log.e("sp_sub_category_name", sp_sub_category_name + "w");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SpinnerModel> call, Throwable t) {
                ((HomeActivity) getActivity()).handleException(getContext(), t);
                t.printStackTrace();

            }
        });
    }

    private void getsp_service_delivery_time() {
        sp_service_delivery_time_list = new ArrayList<>();
        sp_service_delivery_time_list.add("مدة التسليم");
        RetroWeb.getClient().create(ServiceApi.class).fill_add_service_spinner("deadlines").enqueue(new Callback<SpinnerssModelss>() {
            @Override
            public void onResponse(Call<SpinnerssModelss> call, Response<SpinnerssModelss> response) {
                try {
                    sp_service_delivery_time_list.addAll(response.body().getData());
                    ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(getContext(), R.layout.text_spinner, sp_service_delivery_time_list) {

                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);


                            if (((TextView) v).getText().toString().equals("مدة التسليم")) {
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
                    sp_service_delivery_time.setAdapter(levels_list_adapter);
                    sp_service_delivery_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            sp_service_delivery_time_name = sp_service_delivery_time_list.get(i);
                            Log.e("sp_delivery_time_name", sp_service_delivery_time_name + "w");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SpinnerssModelss> call, Throwable t) {
                ((HomeActivity) getActivity()).handleException(getContext(), t);
                t.printStackTrace();

            }
        });
    }

    private void initialize_list() {
        DevelopmentModel developmentModel = new DevelopmentModel();
        developmentModel.setEt_developments("0");
        developmentModel.setSp_price_for_development("0");
        developmentModel.setSp_time("0");
        developmentModel.setSp_time_for_development("0");
        developmentList.add(developmentModel);
    }


}
