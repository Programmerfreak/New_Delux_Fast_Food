package com.example.newdeluxfastfood.custom_loading_screen;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.newdeluxfastfood.R;

import java.lang.ref.WeakReference;

public class LoginCustomLoadingDialog {
    private Dialog dialog;
    private WeakReference<Context> mWeakReference;

    public LoginCustomLoadingDialog(Context context) {
        mWeakReference = new WeakReference<>(context);
    }

    public void showLoadingDialog() {
        dialog = new Dialog(mWeakReference.get());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_custom_loading_screen);
        dialog.setCancelable(false);

        ImageView imageView = dialog.findViewById(R.id.imageView);

        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView);

        Glide.with(mWeakReference.get())
                .load(R.drawable.login_loading)
                .placeholder(R.drawable.login_loading)
                .centerCrop()
                .crossFade()
                .into(target);

        dialog.show();
    }

    public void dismissLoadingDialog() {
        dialog.dismiss();
    }
}
