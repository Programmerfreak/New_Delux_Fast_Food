package com.example.newdeluxfastfood.screens.order_summary_screen.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.newdeluxfastfood.R;
import com.example.newdeluxfastfood.screens.order_summary_screen.ui.constants.Constants;
import com.example.newdeluxfastfood.screens.order_summary_screen.ui.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class OrderSummaryScreen extends AppCompatActivity {
    private static final String TAG = "OrderSummaryScreen";
    private Constants mConstants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summay_screen);

        mConstants = Constants.getInstance();
        mConstants.setContext(this);

        Log.d(TAG, "onCreate: called");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}