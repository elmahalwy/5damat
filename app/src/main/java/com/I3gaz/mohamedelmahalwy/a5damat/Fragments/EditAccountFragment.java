package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Activites.SignInActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AddServiceJson.Image;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.EditProfile.EditProfile;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;
import com.rilixtech.CountryCodePicker;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

import static android.app.Activity.RESULT_OK;
import static com.I3gaz.mohamedelmahalwy.a5damat.Fragments.AddServiceFragment.PICK_IMAGE_REQUEST;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.makeToast;
import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.sharedPrefManager;

public class EditAccountFragment extends Fragment {
    @BindView(R.id.iv_profile_pic)
    ImageView iv_profile_pic;
    @BindView(R.id.frame_update_profile)
    FrameLayout frame_update_profile;

    @BindView(R.id.et_user_name)
    EditText et_user_name;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.et_mobile)
    EditText et_mobile;
    @BindView(R.id.sp_gender)
    Spinner sp_gender;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.et_current_password)
    EditText et_current_password;
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @BindView(R.id.et_confirm_new_password)
    EditText et_confirm_new_password;
    @BindView(R.id.tv_save)
    TextView tv_save;

    List<String> gender_list;
    List<Integer> gender_list_ids;
    public static int gender_id = 0;
    String gender_name = "";
    MultipartBody.Part multipartBody;
    Bitmap bitmap;
    boolean selected_imge = false;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_account_fragment, container, false);
        ButterKnife.bind(this, view);
        ccp = (CountryCodePicker) view.findViewById(R.id.ccp);
        ccp.isPhoneAutoFormatterEnabled();
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {
        ((HomeActivity) getContext()).tv_toolbar_title.setText("تعديل الحساب");
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        et_user_name.setText(sharedPrefManager.getUserDate().getUsername());
        et_email.setText(sharedPrefManager.getUserDate().getEmail());
        et_mobile.setText(sharedPrefManager.getUserDate().getMobile());
        tv_birthday.setText(sharedPrefManager.getUserDate().getBirthday());
        if (!sharedPrefManager.getUserDate().getCountryCode().isEmpty()) {
            ccp.setCountryForPhoneCode(Integer.parseInt(sharedPrefManager.getUserDate().getCountryCode()));
        }
        if (!sharedPrefManager.getUserDate().getImage().isEmpty()) {
            Picasso.with(getContext())
                    .load(sharedPrefManager.getUserDate().getImage())
                    .into(iv_profile_pic);
        }
        get_gender();
        tv_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(v.getContext(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date_from = year + "-" + (month + 1) + "-" + day;
                        tv_birthday.setText(date_from);
                    }
                }, y, m, d);

                dialog.show();
            }
        });
        frame_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageBrowse();
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_profile();
            }
        });
    }

    private void get_gender() {
        gender_list = new ArrayList<>();
        gender_list_ids = new ArrayList<>();
        gender_list.add("الجنس");
        gender_list.add("ذكر");
        gender_list.add("انثي");
        gender_list_ids.add(0);
        gender_list_ids.add(1);
        gender_list_ids.add(2);


        ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(getContext(), R.layout.text_spinner, gender_list) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);


                if (((TextView) v).getText().toString().equals("الجنس")) {
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
        sp_gender.setAdapter(levels_list_adapter);
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender_name = gender_list.get(i);
                gender_id = gender_list_ids.get(i);
                Log.e("gender_name", gender_name + "w");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        if (!sharedPrefManager.getUserDate().getGender().isEmpty()) {
            int spinnerPosition = levels_list_adapter.getPosition(sharedPrefManager.getUserDate().getGender());
            sp_gender.setSelection(spinnerPosition);
        }


    }

    void edit_profile() {

        if (selected_imge) {
            ((HomeActivity) getActivity()).showdialog();
            RetroWeb.getClient().create(ServiceApi.class).update_profile_with_image(et_user_name.getText().toString(),
                    String.valueOf(sharedPrefManager.getUserDate().getId()),
                    et_mobile.getText().toString(), et_email.getText().toString(), ccp.getSelectedCountryCode(),
                    gender_name, et_current_password.getText().toString(), et_new_password.getText().toString(),
                    et_confirm_new_password.getText().toString(), tv_birthday.getText().toString(), multipartBody).enqueue(new Callback<EditProfile>() {
                @Override
                public void onResponse(Call<EditProfile> call, Response<EditProfile> response) {
                    Log.e("response_edit_profile", response + "");
                    ((HomeActivity) getActivity()).dismis_dialog();
                    if (response.body().isValue()) {
                        sharedPrefManager.setLoginStatus(true);
                        sharedPrefManager.setUserDate(response.body().getData());

                    }
                }

                @Override
                public void onFailure(Call<EditProfile> call, Throwable t) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    handleException(getContext(), t);
                    t.printStackTrace();
                }
            });
        } else {
            ((HomeActivity) getActivity()).showdialog();
            RetroWeb.getClient().create(ServiceApi.class).update_profile_without_image(et_user_name.getText().toString(),
                    String.valueOf(sharedPrefManager.getUserDate().getId()),
                    et_mobile.getText().toString(), et_email.getText().toString(), ccp.getSelectedCountryCode(),
                    gender_name, et_current_password.getText().toString(), et_new_password.getText().toString(),
                    et_confirm_new_password.getText().toString(), tv_birthday.getText().toString()).enqueue(new Callback<EditProfile>() {
                @Override
                public void onResponse(Call<EditProfile> call, Response<EditProfile> response) {
                    Log.e("response_edit_profile", response + "");
                    ((HomeActivity) getActivity()).dismis_dialog();
                    if (response.body().isValue()) {
                        sharedPrefManager.setLoginStatus(true);
                        sharedPrefManager.setUserDate(response.body().getData());

                    }
                }

                @Override
                public void onFailure(Call<EditProfile> call, Throwable t) {
                    ((HomeActivity) getActivity()).dismis_dialog();
                    handleException(getContext(), t);
                    t.printStackTrace();
                }
            });
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            selected_imge = true;
            File file = new File(getRealPathFromURI(getContext(), data.getData()));
            final Uri imageUri = data.getData();
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            multipartBody = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            Log.e("multipart", multipartBody.body() + "l");
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                iv_profile_pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                ParentClass.makeToast(getContext(), "حدث خطأ ما");
            }

        }
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
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

    void ImageBrowse() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }
}
