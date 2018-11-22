package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.Manifest;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.CategoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.HomeAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.SubCatigoriesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.AddServiceFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.HomeFragmnet;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.MessagesFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.MyFavouritesFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.MySettingsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.NotificationsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.RequestsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.SearchFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.SubCatigoriesFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.MainCategories.MainCategories;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.ServiceDetails;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends ParentClass {
    @BindView(R.id.frame_container)
    FrameLayout frame_container;
    @BindView(R.id.relative_requests)
    RelativeLayout relative_requests;
    //    @BindView(R.id.iv_requests)
    static ImageView iv_requests;
    //    @BindView(R.id.tv_requests)
    static TextView tv_requests;
    @BindView(R.id.relative_messages)
    RelativeLayout relative_messages;
    //    @BindView(R.id.iv_messages)
    static ImageView iv_messages;
    //    @BindView(R.id.tv_messages)
    static TextView tv_messages;
    @BindView(R.id.relative_search)
    RelativeLayout relative_search;
    //    @BindView(R.id.iv_search)
    static ImageView iv_search;
    //    @BindView(R.id.tv_search)
    static TextView tv_search;
    @BindView(R.id.relative_home)
    RelativeLayout relative_home;
    //    @BindView(R.id.iv_home)
    static ImageView iv_home;
    //    @BindView(R.id.tv_home)
    static TextView tv_home;
    @BindView(R.id.iv_add)
    ImageView iv_add;

    @BindView(R.id.iv_notification)
    ImageView iv_notification;
    @BindView(R.id.tv_toolbar_title)
    public TextView tv_toolbar_title;
    @BindView(R.id.iv_profile_pic)
    ImageView iv_profile_pic;

    AddServiceFragment addServiceFragment;
    public RecyclerView rv_categories;

    public CategoriesAdapter categoriesAdapter;
    LinearLayoutManager linearLayoutManager;


    HomeFragmnet homeFragmnet;
    MySettingsFragment mySettingsFragment;
    MyFavouritesFragment myFavouritesFragment;
    SubCatigoriesFragment subCatigoriesFragment;
    MessagesFragment messagesFragment;
    RequestsFragment requestsFragment;
    SearchFragment searchFragment;
    NotificationsFragment notificationsFragment;
    ServiceDetailsFragment serviceDetailsFragment;

    public String came_from = "";
    Bundle args;
    public String handle_tab = "";
    static List<String> fragments = new ArrayList<String>();
    static FragmentManager manager;
    SharedPreferences sharedPreferences_title;
    public static Dialog dialog_go_to_requests;
    public static Button btn_yes;
    public static Button btn_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        dialog_go_to_requests = new Dialog(this);
        dialog_go_to_requests.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_go_to_requests.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_go_to_requests.setContentView(R.layout.dialog_go_to_requests);
        // to set width and height
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog_go_to_requests.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        btn_yes = (Button) dialog_go_to_requests.findViewById(R.id.btn_yes);
        btn_no = (Button) dialog_go_to_requests.findViewById(R.id.btn_no);
        sharedPreferences_title = getSharedPreferences("title", MODE_PRIVATE);
        isStoragePermissionGranted();
        manager = getSupportFragmentManager();
        init_fragments();

        replaceFragment(homeFragmnet);
        initUi();
        initEventDriven();
        handle_clicks_bottom_tab();
        get_main_categories();
        Log.e("iiid", "" + String.valueOf(sharedPrefManager.getUserDate().getId()));
        Log.e("user_name", "" + sharedPrefManager.getUserDate().getUsername());
        Log.e("email", "" + sharedPrefManager.getUserDate().getEmail());
        Log.e("token", "" + sharedPrefManager.getUserDate().getApiToken());
        Log.e("mobile", "" + sharedPrefManager.getUserDate().getMobile());
        Log.e("image", "" + sharedPrefManager.getUserDate().getImage());

        final android.app.FragmentManager fm = getFragmentManager();

        fm.addOnBackStackChangedListener(new android.app.FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                    // dLayout.closeDrawers();
                    finish();
                    for (int i = 0; i < fragments.size(); i++) {
                        Log.e("names_of_fragments" + i, fragments.get(i));
                    }
                } else {
                    // dLayout.closeDrawers();
                    for (int i = 0; i < fragments.size(); i++) {
                        Log.e("names_of_fragmentss" + i, fragments.get(i));
                    }
                    return;

                }
            }
        });

    }

    void initUi() {
        if (sharedPreferences_title.getString("title", "").equals("yes")) {
            replaceFragment(requestsFragment);
            tv_home.setTextColor(Color.parseColor("#B2BBC9"));
            iv_home.setImageResource(R.mipmap.home_grey);
            tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
            iv_messages.setImageResource(R.mipmap.message_grey);
            tv_requests.setTextColor(Color.parseColor("#174BB0"));
            iv_requests.setImageResource(R.mipmap.requests);
            tv_search.setTextColor(Color.parseColor("#B2BBC9"));
            iv_search.setImageResource(R.mipmap.search_grey);
        }
        if (sharedPreferences_title.getString("title", "").equals("no")) {

        }


        if (getIntent().getStringExtra("type").equals("deep_link")) {
            Bundle args = new Bundle();
            args.putString("service_id", getIntent().getStringExtra("service_id"));
            serviceDetailsFragment.setArguments(args);
            replaceFragment(serviceDetailsFragment);
            tv_home.setTextColor(Color.parseColor("#174BB0"));
            iv_home.setImageResource(R.mipmap.home);
            tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
            iv_messages.setImageResource(R.mipmap.message_grey);
            tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
            iv_requests.setImageResource(R.mipmap.requests_grey);
            tv_search.setTextColor(Color.parseColor("#B2BBC9"));
            iv_search.setImageResource(R.mipmap.search_grey);
        }
        if (getIntent().getStringExtra("type").equals("requests")) {
            replaceFragment(requestsFragment);
            tv_home.setTextColor(Color.parseColor("#B2BBC9"));
            iv_home.setImageResource(R.mipmap.home_grey);
            tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
            iv_messages.setImageResource(R.mipmap.message_grey);
            tv_requests.setTextColor(Color.parseColor("#174BB0"));
            iv_requests.setImageResource(R.mipmap.requests);
            tv_search.setTextColor(Color.parseColor("#B2BBC9"));
            iv_search.setImageResource(R.mipmap.search_grey);
        }

        tv_home.setTextColor(Color.parseColor("#174BB0"));
        iv_home.setImageResource(R.mipmap.home);

        rv_categories = (RecyclerView) findViewById(R.id.rv_categories);
        categoriesAdapter = new CategoriesAdapter(HomeActivity.this);
        linearLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayout.HORIZONTAL, true);
        linearLayoutManager.setReverseLayout(true);
        rv_categories.setLayoutManager(linearLayoutManager);
        rv_categories.setAdapter(categoriesAdapter);


//        KeyboardVisibilityEvent.setEventListener(this,
//                new KeyboardVisibilityEventListener() {
//                    @Override
//                    public void onVisibilityChanged(boolean isOpen) {
//                        mBottomTabLayout.setVisibility(isOpen ? View.GONE : View.VISIBLE);
//                    }
//                });
    }

    void initEventDriven() {
        if (!sharedPrefManager.getUserDate().getImage().isEmpty()) {
            Picasso.with(this)
                    .load(sharedPrefManager.getUserDate().getImage())
                    .into(iv_profile_pic);
            Log.e("image", sharedPrefManager.getUserDate().getImage());
        }

        iv_profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(mySettingsFragment);

            }
        });
        iv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(notificationsFragment);
            }
        });

    }

    void init_fragments() {
        iv_messages = findViewById(R.id.iv_messages);
        tv_messages = findViewById(R.id.tv_messages);
        iv_requests = findViewById(R.id.iv_requests);
        tv_requests = findViewById(R.id.tv_requests);
        iv_home = findViewById(R.id.iv_home);
        tv_home = findViewById(R.id.tv_home);
        iv_search = findViewById(R.id.iv_search);
        tv_search = findViewById(R.id.tv_search);

        homeFragmnet = new HomeFragmnet();
        args = new Bundle();
        args.putString("type", "home");
        args.putString("search_key", "");
        homeFragmnet.setArguments(args);

        mySettingsFragment = new MySettingsFragment();
        addServiceFragment = new AddServiceFragment();
        requestsFragment = new RequestsFragment();
        myFavouritesFragment = new MyFavouritesFragment();
        subCatigoriesFragment = new SubCatigoriesFragment();
        messagesFragment = new MessagesFragment();
        searchFragment = new SearchFragment();
        notificationsFragment = new NotificationsFragment();
        serviceDetailsFragment = new ServiceDetailsFragment();
    }

    void handle_clicks_bottom_tab() {
        relative_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(homeFragmnet);
                tv_home.setTextColor(Color.parseColor("#174BB0"));
                iv_home.setImageResource(R.mipmap.home);
                tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
                iv_messages.setImageResource(R.mipmap.message_grey);
                tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
                iv_requests.setImageResource(R.mipmap.requests_grey);
                tv_search.setTextColor(Color.parseColor("#B2BBC9"));
                iv_search.setImageResource(R.mipmap.search_grey);

            }
        });
        relative_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(requestsFragment);
                tv_home.setTextColor(Color.parseColor("#B2BBC9"));
                iv_home.setImageResource(R.mipmap.home_grey);
                tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
                iv_messages.setImageResource(R.mipmap.message_grey);
                tv_requests.setTextColor(Color.parseColor("#174BB0"));
                iv_requests.setImageResource(R.mipmap.requests);
                tv_search.setTextColor(Color.parseColor("#B2BBC9"));
                iv_search.setImageResource(R.mipmap.search_grey);

            }
        });
        relative_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(messagesFragment);
                tv_home.setTextColor(Color.parseColor("#B2BBC9"));
                iv_home.setImageResource(R.mipmap.home_grey);
                tv_messages.setTextColor(Color.parseColor("#174BB0"));
                iv_messages.setImageResource(R.mipmap.message);
                tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
                iv_requests.setImageResource(R.mipmap.requests_grey);
                tv_search.setTextColor(Color.parseColor("#B2BBC9"));
                iv_search.setImageResource(R.mipmap.search_grey);
            }
        });
        relative_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(searchFragment);
                tv_home.setTextColor(Color.parseColor("#B2BBC9"));
                iv_home.setImageResource(R.mipmap.home_grey);
                tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
                iv_messages.setImageResource(R.mipmap.message_grey);
                tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
                iv_requests.setImageResource(R.mipmap.requests_grey);
                tv_search.setTextColor(Color.parseColor("#174BB0"));
                iv_search.setImageResource(R.mipmap.search_blue);
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(addServiceFragment);
                came_from = "add";
            }
        });

    }

    public static void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        Log.e("backStateName", backStateName);

        //fragment not in back stack, create it.
        FragmentTransaction ft = manager.beginTransaction();
        if (!fragments.contains(backStateName)) {
            Log.e("check", "added");
            // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
            ft.replace(R.id.frame_container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();

            fragments.add(backStateName);
            System.out.println("backStateName" + fragments);
        } else {

            Log.e("check", "not_added");
            ft.replace(R.id.frame_container, fragment);
            ft.commit();

        }
        Log.e("frgament_name", fragment.getClass().getName());
        if (fragment.getClass().getName().equals("com.I3gaz.mohamedelmahalwy.a5damat.Fragments.HomeFragment") ||
                fragment.getClass().getName().equals("com.I3gaz.mohamedelmahalwy.a5damat.Fragments.ServiceDetailsFragment") ||
                fragment.getClass().getName().equals("com.I3gaz.mohamedelmahalwy.a5damat.Fragments.SubCatigoriesFragment")) {
            tv_home.setTextColor(Color.parseColor("#174BB0"));
            iv_home.setImageResource(R.mipmap.home);
            tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
            iv_messages.setImageResource(R.mipmap.message_grey);
            tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
            iv_requests.setImageResource(R.mipmap.requests_grey);
            tv_search.setTextColor(Color.parseColor("#B2BBC9"));
            iv_search.setImageResource(R.mipmap.search_grey);
        } else if (fragment.getClass().getName().equals("com.I3gaz.mohamedelmahalwy.a5damat.Fragments.SearchFragment")) {
            tv_home.setTextColor(Color.parseColor("#B2BBC9"));
            iv_home.setImageResource(R.mipmap.home_grey);
            tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
            iv_messages.setImageResource(R.mipmap.message_grey);
            tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
            iv_requests.setImageResource(R.mipmap.requests_grey);
            tv_search.setTextColor(Color.parseColor("#174BB0"));
            iv_search.setImageResource(R.mipmap.search_blue);
        } else if (fragment.getClass().getName().equals("com.I3gaz.mohamedelmahalwy.a5damat.Fragments.InComingOrdersFragment") ||
                fragment.getClass().getName().equals("com.I3gaz.mohamedelmahalwy.a5damat.Fragments.RequestsFragment")) {
            tv_home.setTextColor(Color.parseColor("#B2BBC9"));
            iv_home.setImageResource(R.mipmap.home_grey);
            tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
            iv_messages.setImageResource(R.mipmap.message_grey);
            tv_requests.setTextColor(Color.parseColor("#174BB0"));
            iv_requests.setImageResource(R.mipmap.requests);
            tv_search.setTextColor(Color.parseColor("#B2BBC9"));
            iv_search.setImageResource(R.mipmap.search_grey);
        } else if (fragment.getClass().getName().equals("com.I3gaz.mohamedelmahalwy.a5damat.Fragments.MessagesFragment") ||
                fragment.getClass().getName().equals("com.I3gaz.mohamedelmahalwy.a5damat.Fragments.MessagesDetailsFragment")) {
            tv_home.setTextColor(Color.parseColor("#B2BBC9"));
            iv_home.setImageResource(R.mipmap.home_grey);
            tv_messages.setTextColor(Color.parseColor("#174BB0"));
            iv_messages.setImageResource(R.mipmap.message);
            tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
            iv_requests.setImageResource(R.mipmap.requests_grey);
            tv_search.setTextColor(Color.parseColor("#B2BBC9"));
            iv_search.setImageResource(R.mipmap.search_grey);
        } else {
            tv_home.setTextColor(Color.parseColor("#B2BBC9"));
            iv_home.setImageResource(R.mipmap.home_grey);
            tv_messages.setTextColor(Color.parseColor("#B2BBC9"));
            iv_messages.setImageResource(R.mipmap.message_grey);
            tv_requests.setTextColor(Color.parseColor("#B2BBC9"));
            iv_requests.setImageResource(R.mipmap.requests_grey);
            tv_search.setTextColor(Color.parseColor("#B2BBC9"));
            iv_search.setImageResource(R.mipmap.search_grey);
        }

    }


    void get_main_categories() {
        RetroWeb.getClient().create(ServiceApi.class).Get_main_categories().enqueue(new Callback<MainCategories>() {
            @Override
            public void onResponse(Call<MainCategories> call, Response<MainCategories> response) {
                if (response.body().isValue()) {
                    categoriesAdapter.addAll(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<MainCategories> call, Throwable t) {
                handleException(HomeActivity.this, t);
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onBackPressed() {
        try {
            Log.e("list_size", fragments.size() + "sss");
            if (fragments.size() > 1) {
                fragments.remove(fragments.size() - 1);
                for (int i = 0; i < fragments.size(); i++) {
                    Log.e("names_of_fragmentssssss" + i, fragments.get(i));
                }
                SubCatigoriesAdapter.Sub = "";
            } else {
                finish();
                System.exit(0);
            }
            super.onBackPressed();

        } catch (Exception e) {
            Log.e("kosom_eror", e.toString());

        }

    }

    public boolean isStoragePermissionGranted() {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("permisson_taken", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }

    }
}
