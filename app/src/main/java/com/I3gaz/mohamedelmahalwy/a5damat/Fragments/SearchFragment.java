package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
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
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initUI();
        initEventDriven();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {
        ((HomeActivity) getContext()).tv_toolbar_title.setText("البحث المتقدم");
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cancel = false;
                View focusView = null;
                if (TextUtils.isEmpty(et_search.getText().toString())) {
                    et_search.setError("برجاء ادخال كلمة البحث");
                    focusView = et_search;
                    cancel = true;
                }
                if (cancel) {

                } else {
                    Bundle args = new Bundle();
                    args.putString("type", "search");
                    args.putString("search_key", et_search.getText().toString());
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    HomeFragmnet homeFragmnet = new HomeFragmnet();
                    homeFragmnet.setArguments(args);
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_out_right, R.anim.enter_from_right, R.anim.exit_out_left);
                    fragmentTransaction.replace(R.id.frame_container, homeFragmnet);
                    fragmentTransaction.commit();
                }
            }
        });
    }
}
