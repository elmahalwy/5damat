package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.I3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment {
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.iv_search)
    ImageView iv_search;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragemnt, container, false);
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
