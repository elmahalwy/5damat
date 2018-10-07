package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.I3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssistanceFragment extends Fragment {
    @BindView(R.id.web_view)
    WebView web_view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assistance_fragmant, container, false);
        ButterKnife.bind(this, view);
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {

    }
}
