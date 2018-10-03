package com.I3gaz.mohamedelmahalwy.a5damat.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerData;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.SpinnerModel.SpinnerModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentClass extends AppCompatActivity {
    public static int paginate = 0;
    public static String lat;
    public static String lng;
    public static int time_end = 0;
    static boolean checked = true;
    protected LocationManager locationManager;
    CustomLoadingDialog customLoadingDialog;
    public SharedPrefManager sharedPrefManager;
    public List<String> list_names;
    public List<Integer> list_idss;
    public List<SpinnerData> spinner_list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customLoadingDialog = new CustomLoadingDialog(ParentClass.this, R.style.DialogSlideAnim);
        sharedPrefManager = new SharedPrefManager(this);
    }


    public void languageConfiguration(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
    }

    public boolean check_empty(EditText check) {
        if (TextUtils.isEmpty(check.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLocationPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permisson", "Permission is granted");
                return true;
            } else {

                Log.v("permisson", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permisson", "Permission is granted");
            return true;
        }
    }

    public boolean isStoargePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permisson", "Permission is granted");
                return true;
            } else {

                Log.v("permisson", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permisson", "Permission is granted");
            return true;
        }
    }

    public String getRealPathFromUri(Context context, Uri contentUri) {
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

    public void imageBrowse(int PICK_IMAGE_REQUEST) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    public void getLocation(int REQUEST_LOCATION) {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lat = String.valueOf(latti);
                lng = String.valueOf(longi);


            } else if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lat = String.valueOf(latti);
                lng = String.valueOf(longi);

            } else if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lat = String.valueOf(latti);
                lng = String.valueOf(longi);
            } else {
                Toast.makeText(this, "Unble to Trace your location", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void replaceFragment(Fragment fragment, FrameLayout frameLayout) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(frameLayout.getId(), fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    public static void paginate(int dy, int visibleItemCount, int totalItemCount, int pastVisiblesItems,
                                GridLayoutManager gridLayoutManager, RecyclerView recyclerView, boolean loading) {
        if (dy > 0) //check for scroll down
        {
            visibleItemCount = gridLayoutManager.getChildCount();
            totalItemCount = gridLayoutManager.getItemCount();
            pastVisiblesItems = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                    Log.e("LOOOAAAD", nextUrl);
            if (loading) {
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    loading = false;
                    Log.e("...", "Last Item Wow !");
                    paginate = 1;
                } else {
                    paginate = 0;
                }
            } else {
                paginate = 0;
            }
        }
    }


    public static void makeToast(Context context, int msg) {
        Toast.makeText(context, context.getResources().getString(msg), Toast.LENGTH_SHORT).show();
    }

    public static void makeToast(Context context, String msg) {


        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    public static int colour_converter(String hexa_Color) {
        return Color.parseColor(hexa_Color);
    }

    public static void Peform_log(String log_name, String log_msg) {
        Log.e(log_name, log_msg);
    }

    public void time_handler(int time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                time_end = 1;
            }
        }, time);
    }

    public void configuration_for_language(String language) {
        int currentOrientation = this.getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config,
                    getResources().getDisplayMetrics());
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);

        }

        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config,
                    getResources().getDisplayMetrics());
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        }
    }

    public static void date(View v, final TextView text) {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(v.getContext(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date_from = year + "-" + (month + 1) + "-" + day;
                text.setText(date_from);
            }
        }, y, m, d);
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();

    }


    public void dismiss_custom_loading_dialog() {
        customLoadingDialog.dismiss();
    }

    public void show_custom_loading_dialog() {
        customLoadingDialog.show();
    }

//    public void setupMenu() {
//        FragmentManager fm = getSupportFragmentManager();
//        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
//        if (mMenuFragment == null) {
//            mMenuFragment = new MenuListFragment();
//            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
//        }
//
//    }

    public static int add_number(int number) {
        number++;
        return number;
    }

    public int sub_number(int number) {
        if (number != 0) {
            number--;
            return number;
        } else {
            Toast.makeText(this, "برجاء ادخال رقم صحيح", Toast.LENGTH_SHORT).show();
            return 0;
        }

    }

    public static boolean check_clicked() {
        if (!checked) {
            checked = true;
        } else if (checked) {
            checked = false;
        }
        return checked;
    }

    public void fill_spinner(final Spinner spinner,
                             final String spinner_init, final String color_inputs_0, final String color_selected, final String url) {
        RetroWeb.getClient().create(ServiceApi.class).fill_spinner(url).enqueue(new Callback<SpinnerModel>() {
            @Override
            public void onResponse(Call<SpinnerModel> call, Response<SpinnerModel> response) {
                Log.e("url", url);
                Log.e("spinner_init", spinner_init);
                list_names = new ArrayList<>();
                list_idss = new ArrayList<>();
                spinner_list = new ArrayList<>();
                list_names.add(spinner_init);
                list_idss.add(0);
                spinner_list.addAll(response.body().getData());
                for (int i = 0; i < spinner_list.size(); i++) {
                    list_names.add(spinner_list.get(i).getName());
                    list_idss.add(spinner_list.get(i).getId());
                }
            }

            @Override
            public void onFailure(Call<SpinnerModel> call, Throwable t) {
                handleException(getApplicationContext(), t);
                t.printStackTrace();

            }
        });
        Log.e("list_names", list_names + "");
        Log.e("ssssize", list_names.size() + "");
        ArrayAdapter<String> levels_list_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.text_spinner, list_names) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);


                if (((TextView) v).getText().toString().equals(spinner_init)) {
                    ((TextView) v).setTextColor(Color.parseColor(color_inputs_0));
                } else {
                    ((TextView) v).setTextColor(Color.parseColor(color_selected));
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
                    tv.setTextColor(Color.parseColor(color_inputs_0));
                } else {
                    tv.setTextColor(Color.parseColor(color_selected));
                }

                return view;
            }

        };


        // Drop down layout style
        levels_list_adapter.setDropDownViewResource(R.layout.text_spinner);
        // attaching data adapter to spinner
        spinner.setAdapter(levels_list_adapter);


    }

    public static void handleException(Context context, Throwable t) {
        if (t instanceof SocketTimeoutException)
            makeToast(context, "خطأ فى الانترنت");
        else if (t instanceof UnknownHostException)
            makeToast(context, "خطأ فى الاتصال");

        else if (t instanceof ConnectException)
            makeToast(context, "خطأ فى الاتصال");
        else
            makeToast(context, t.getLocalizedMessage());

    }

    public void dismiss_keyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public Bitmap roundCornerImage(Bitmap raw, float round) {
        int width = raw.getWidth();
        int height = raw.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawARGB(0, 0, 0, 0);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#000000"));

        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        canvas.drawRoundRect(rectF, round, round, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        canvas.drawBitmap(raw, rect, rect, paint);

        return result;
    }
}
