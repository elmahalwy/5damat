package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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

import java.util.Arrays;

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
    @BindView(R.id.iv_face)
    ImageView iv_face;
    @BindView(R.id.tv_register)
    TextView tv_register;
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    String mobile_token;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 9001;

    CallbackManager callbackManager;
    private static final String EMAIL = "email";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        ButterKnife.bind(this);
        SharedPreferences prefs = getSharedPreferences("mobile_token", MODE_PRIVATE);
        mobile_token = prefs.getString("m_token", "");
        initUi();
        initEventDriven();
    }

    void initUi() {
    }

    void initEventDriven() {
        //// login google////
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        /// end login google ////
        //// login facebook /////

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
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
            }
        });
        iv_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_facebook();
            }
        });
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
                        startActivity(intent);


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

    void updateUI(GoogleSignInAccount account) {
        Log.e("accccount", account + "aaa");

    }

    private void google_sign_in() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            Log.e("jjjjj", "kkk");

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.e("email", account.getEmail());
            Log.e("display_name", account.getDisplayName());
            Log.e("id", account.getId());
//            Log.e("id_token",account.getIdToken());
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("signInResult:failed", e.getStatusCode() + "ppp");
            updateUI(null);
        }
    }

    void login_facebook() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.e("facebook_token", loginResult.getAccessToken() + "face");
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.e("facebook_token", "face");

                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.e("facebook_exception", exception + "execption");

                    }
                });

    }
}
