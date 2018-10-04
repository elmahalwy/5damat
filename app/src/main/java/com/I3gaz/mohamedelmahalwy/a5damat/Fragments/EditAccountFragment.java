package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.rilixtech.CountryCodePicker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditAccountFragment extends Fragment {
    @BindView(R.id.et_user_name)
    EditText et_user_name;
    @BindView(R.id.et_email)
    EditText et_email;
    CountryCodePicker ccp;
    @BindView(R.id.et_mobile)
    EditText et_mobile;
    @BindView(R.id.sp_country)
    Spinner sp_country;
    @BindView(R.id.sp_gender)
    Spinner sp_gender;
    @BindView(R.id.et_birthdate)
    EditText et_birthdate;
    @BindView(R.id.et_current_password)
    EditText et_current_password;
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @BindView(R.id.et_confirm_new_password)
    EditText et_confirm_new_password;
    @BindView(R.id.tv_save)
    TextView tv_save;


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

    }
}
