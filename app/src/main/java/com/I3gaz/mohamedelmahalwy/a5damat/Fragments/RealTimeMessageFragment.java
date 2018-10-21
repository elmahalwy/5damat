package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Adapters.MessagesAdapter;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.ButterKnife;

public class RealTimeMessageFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.real_time_message_fragment, container, false);
        ButterKnife.bind(this, view);
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        ((HomeActivity) getContext()).tv_toolbar_title.setText("الرسائل");
        return view;
    }
}
