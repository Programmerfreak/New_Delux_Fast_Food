package com.example.newdeluxfastfood.screens.order_summary_screen.ui.constants;

import android.content.Context;

import java.lang.ref.WeakReference;

public class Constants {
    //This class is for avoiding screen orientation problem
    //To save content of fragment in this class and access this when
    //screen is rotated.

    private WeakReference<Context> context;
    private static Constants instance;

    public static Constants getInstance() {
        if(instance == null)
            instance = new Constants();
        return instance;
    }

    public Context getContext() {
        return context.get();
    }

    public void setContext(Context context) {
        this.context = new WeakReference<>(context);
    }

}
