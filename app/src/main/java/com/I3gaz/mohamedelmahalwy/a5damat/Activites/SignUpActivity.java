package com.I3gaz.mohamedelmahalwy.a5damat.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.LoginData.User;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;

public class SignUpActivity extends ParentClass {
    @BindView(R.id.et_full_name)
    EditText et_full_name;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.iv_accept_credatinals)
    ImageView iv_accept_credatinals;
    @BindView(R.id.tv_sign_up)
    TextView tv_sign_up;
//    @BindView(R.id.iv_google)
//    ImageView iv_google;
//    @BindView(R.id.iv_face)
//    ImageView iv_face;
    @BindView(R.id.tv_register)
    TextView tv_register;
    String mobile_token;

    boolean checked_accept_terms = false;
    GoogleSignInClient mGoogleSignInClient;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        SharedPreferences prefs = getSharedPreferences("mobile_token", MODE_PRIVATE);
        mobile_token = prefs.getString("m_token", "");
        initUi();
        initEventDriven();
    }

    void initUi() {

    }

    void initEventDriven() {
        dismiss_keyboard();
        iv_accept_credatinals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checked_accept_terms) {
                    iv_accept_credatinals.setImageResource(R.mipmap.box);
                    checked_accept_terms = false;
                    Log.e("checked_accept_terms",checked_accept_terms+"1");
                } else if (!checked_accept_terms) {
                    iv_accept_credatinals.setImageResource(R.mipmap.check_mark);
                    checked_accept_terms = true;
                    Log.e("checked_accept_terms",checked_accept_terms+"2");

                }
            }
        });
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    void signUp() {
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(et_full_name.getText().toString())) {
            et_full_name.setError("برجاء ادخال الاسم");
            focusView = et_full_name;
            cancel = true;
        }

        if (TextUtils.isEmpty(et_phone.getText().toString())) {
            et_phone.setError("برجاء ادخال رقم الجوال");
            focusView = et_phone;
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
        if (checked_accept_terms == false) {
            makeToast(SignUpActivity.this, "يجب عليك الموافقة ع شروط التطبيق");
        }
        if (cancel) {
        } else {
            show_custom_loading_dialog();
            RetroWeb.getClient().create(ServiceApi.class).Sign_up_response_call(et_full_name.getText().toString(),
                    et_phone.getText().toString(), et_email.getText().toString(), mobile_token,
                    Build.SERIAL, et_password.getText().toString()).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    dismiss_custom_loading_dialog();
                    if (response.body().isValue()) {
                        sharedPrefManager.setLoginStatus(true);
                        sharedPrefManager.setUserDate(response.body().getData());
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("type","sign_up");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        Bungee.zoom(SignUpActivity.this);

                    } else {
                        makeToast(getApplicationContext(), response.body().getMsg());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    dismiss_custom_loading_dialog();
                    handleException(SignUpActivity.this, t);
                    t.printStackTrace();
                }
            });
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//            updateUI(account);
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
    }
}
