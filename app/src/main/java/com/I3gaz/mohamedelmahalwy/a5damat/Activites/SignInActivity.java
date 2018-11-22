package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.PrefUtil;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Currency;

public class SignInActivity extends ParentClass {
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_did_you_forger_your_password)
    TextView tv_did_you_forger_your_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.iv_google)
    ImageView iv_google;
    @BindView(R.id.iv_face1)
    ImageView iv_face1;
    @BindView(R.id.tv_register)
    TextView tv_register;
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String uid;
    String mobile_token;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 9001;

    CallbackManager callbackManager;
    private static final String EMAIL = "email";

    LoginButton iv_face;
    PrefUtil prefUtil;
    GoogleSignInAccount account;
    String email, display_name, google_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        ButterKnife.bind(this);
        prefUtil = new PrefUtil(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        SharedPreferences prefs = getSharedPreferences("mobile_token", MODE_PRIVATE);
        mobile_token = prefs.getString("m_token", "");
        initUi();
        initEventDriven();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.packagename",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }

    void initUi() {
        dismiss_keyboard();
        iv_face = (LoginButton) findViewById(R.id.iv_face);
        iv_face.setReadPermissions(Arrays.asList(
                "public_profile", "email"));
        iv_face.setBackgroundResource(R.drawable.icon_facebook);
        iv_face.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

    }

    void initEventDriven() {
        //// login google////
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        account = GoogleSignIn.getLastSignedInAccount(this);

        /// end login google ////
        //// login facebook /////


        callbackManager = CallbackManager.Factory.create();


        /// end login facebook/////
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_in();
            }
        });
        tv_did_you_forger_your_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent);
                Bungee.fade(SignInActivity.this);
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                Bungee.fade(SignInActivity.this);
            }
        });
        iv_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                google_sign_in();
//                updateUI(account);

            }
        });
        iv_face1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_facebook();

            }
        });
//        iv_face.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
    }


    void log_in() {
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(et_email.getText().toString()) || !et_email.getText().toString().matches(emailPattern)) {
            et_email.setError("برجاء ادخال البريد الالكتروني بطريقة صحيحة");
            focusView = et_email;
            cancel = true;
        }
        if (TextUtils.isEmpty(et_password.getText().toString())) {
            et_password.setError("برجاء ادخال كلمة المرور");
            focusView = et_password;
            cancel = true;
        }
        if (et_password.getText().toString().length() < 6) {
            et_password.setError("يجب الا تقل كلمة المرور عن 6 حروف او ارقام");
            focusView = et_password;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            Log.e("ffff", "ffff");
            focusView.requestFocus();
        } else {
            Log.e("email", et_email.getText().toString());
            Log.e("password", et_password.getText().toString());
            Log.e("mobile_token", mobile_token + "");
            Log.e("serial", Build.SERIAL);
            show_custom_loading_dialog();
            RetroWeb.getClient().create(ServiceApi.class).Sign_in_response_call(et_email.getText().toString()
                    , et_password.getText().toString(), mobile_token, Build.SERIAL).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.e("response_login", response + "");
                    dismiss_custom_loading_dialog();
                    if (response.body().isValue()) {
                        sharedPrefManager.setLoginStatus(true);
                        sharedPrefManager.setUserDate(response.body().getData());
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent.putExtra("type", "home");
                        intent.putExtra("service_id", "");
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignInActivity.this, "حدث خطأ ما", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    dismiss_custom_loading_dialog();
                    handleException(SignInActivity.this, t);
                    t.printStackTrace();
                }
            });
        }
    }

    void updateUI(GoogleSignInAccount account, String display_name, String email, String id) {
        Log.e("accccount", account + "aaa");
        showdialog();
        Log.e("name", display_name);
        Log.e("email", email);
        Log.e("gooele_id", id);
        RetroWeb.getClient().create(ServiceApi.class).Sign_in_response_call_social(display_name,
                email, mobile_token, Build.SERIAL, id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                dismis_dialog();
                Log.e("response_login", response + "");
                try {
                    if (response.body().isValue()) {
                        sharedPrefManager.setLoginStatus(true);
                        sharedPrefManager.setUserDate(response.body().getData());
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent.putExtra("type", "home");
                        intent.putExtra("service_id", "");
                        startActivity(intent);
                    } else {
                        makeToast(getApplicationContext(), response.body().getMsg());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("t_google", t + "");
                dismis_dialog();
                handleException(SignInActivity.this, t);
                t.printStackTrace();
            }
        });


    }

    private void google_sign_in() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        dismis_dialog();
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            Log.e("jjjjj", "kkk");
            Log.e("EMAILaa", EMAIL);

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);
            Log.e("email", account.getEmail());
            Log.e("display_name", account.getDisplayName());
            Log.e("id", account.getId());
//            Log.e("id_token",account.getIdToken());
            // Signed in successfully, show authenticated UI.
            email = account.getEmail();
            display_name = account.getDisplayName();
            google_id = account.getId();
            updateUI(account, account.getDisplayName(), account.getEmail(), account.getId());



        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("signInResult:failed", e.getStatusCode() + "ppp" );
//            updateUI(null);
        }
    }

    void login_facebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        iv_face.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.e("facebook_token", loginResult.getAccessToken() + "face");
                String name = loginResult.getAccessToken() + "";
                Log.e("EMAILzz", EMAIL);

                String accessToken = loginResult.getAccessToken().getToken();

                // save accessToken to SharedPreference
                prefUtil.saveAccessToken(accessToken);

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject jsonObject,
                                                    GraphResponse response) {

                                // Getting FB User Data
                                Bundle facebookData = getFacebookData(jsonObject);
                                showdialog();
                                Log.e("idFacebook", facebookData.getString("idFacebook"));
                                RetroWeb.getClient().create(ServiceApi.class).Sign_in_response_call_social(facebookData.getString("first_name"),
                                        facebookData.getString("email"), mobile_token, Build.SERIAL, facebookData.getString("idFacebook")).enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        dismis_dialog();
                                        Log.e("response_login", response + "");
                                        dismis_dialog();
                                        if (response.body().isValue()) {
                                            sharedPrefManager.setLoginStatus(true);
                                            sharedPrefManager.setUserDate(response.body().getData());
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                            intent.putExtra("type", "home");
                                            intent.putExtra("service_id", "");
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(SignInActivity.this, "حدث خطأ ما", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        dismis_dialog();
                                        handleException(SignInActivity.this, t);
                                        t.printStackTrace();
                                    }
                                });

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,email,gender");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
                // App code
                Log.e("facebook_token", "face");

            }

            @Override
            public void onError(FacebookException e) {
                // App code
                Log.e("facebook_exception", e + "execption");
                e.printStackTrace();
                Log.d("a7o", "Login attempt failed.");
                deleteAccessToken();

            }
        });

    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));


            prefUtil.saveFacebookUserInfo(object.getString("first_name"),
                    object.getString("last_name"), object.getString("email"),
                    object.getString("gender"), profile_pic.toString());

        } catch (Exception e) {
            Log.d("a7a", "BUNDLE Exception : " + e.toString());
        }

        return bundle;
    }

    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null) {
                    //User logged out
                    prefUtil.clearToken();
                    LoginManager.getInstance().logOut();
                }
            }
        };
    }


}
