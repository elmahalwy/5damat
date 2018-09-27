package com.i3gaz.mohamedelmahalwy.a5damat.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;

import com.i3gaz.mohamedelmahalwy.a5damat.R;


/**
 * Created by omarn on 3/5/2018.
 */

public class CustomLoadingDialog extends Dialog {
    Context mContext;
    public CustomLoadingDialog(Context mContext,int themeResId) {
        super(mContext,themeResId);
        this.mContext = mContext;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.custom_loading_background));
        setContentView(R.layout.custom_loading_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setGravity(Gravity.CENTER);
        setCancelable(false);
    }
}
