package com.I3gaz.mohamedelmahalwy.a5damat.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.Assistance.Assisatnce;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.RetroWeb;
import com.I3gaz.mohamedelmahalwy.a5damat.Network.ServiceApi;
import com.I3gaz.mohamedelmahalwy.a5damat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.I3gaz.mohamedelmahalwy.a5damat.Utils.ParentClass.handleException;

public class AssistanceFragment extends Fragment {
    @BindView(R.id.web_view)
    WebView web_view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assistance_fragmant, container, false);
        ButterKnife.bind(this, view);
        initUI();
        initEventDriven();
        about_us();
        return view;
    }

    void initUI() {

    }

    void initEventDriven() {
        ((HomeActivity) getActivity()).rv_categories.setVisibility(View.GONE);
        ((HomeActivity) getContext()).tv_toolbar_title.setText("مركز المساعدة");
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }

    void about_us() {
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        ((HomeActivity) getActivity()).showdialog();
        RetroWeb.getClient().create(ServiceApi.class).assisatnce().enqueue(new Callback<Assisatnce>() {
            @Override
            public void onResponse(Call<Assisatnce> call, Response<Assisatnce> response) {
                ((HomeActivity) getActivity()).dismis_dialog();
                try {
                    if (response.body().isValue()) {

                        web_view.setWebChromeClient(new WebChromeClient());
                        WebSettings webSettings = web_view.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        web_view.loadData(response.body().getData(), "text/html; charset=utf-8", null);
                        web_view.loadDataWithBaseURL("https://www.youtube.com", response.body().getData(), "text/html", "UTF-8", null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Assisatnce> call, Throwable t) {
                ((HomeActivity) getActivity()).dismis_dialog();
                handleException(getActivity(), t);
                t.printStackTrace();
            }
        });
    }
}
