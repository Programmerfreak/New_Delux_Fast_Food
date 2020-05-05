package com.example.newdeluxfastfood.custom_loading_screen;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.newdeluxfastfood.R;

import java.lang.ref.WeakReference;

public class PaytmCustomLoadingDialog {
    private Dialog mDialog;

    //Using weak reference to avoid memory leak as CustomLoadingDialog's instance
    //is static in PaymentOption.
    private WeakReference<Context> mWeakReference;

    public PaytmCustomLoadingDialog(Context context) {
        mWeakReference = new WeakReference<Context>(context);
    }

    public void showLoadingDialog() {
        mDialog = new Dialog(mWeakReference.get());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.custom_progress_dialog_view);

        mDialog.show();
    }

    public void dismissLoadingDialog() {
        mDialog.dismiss();
    }
}
