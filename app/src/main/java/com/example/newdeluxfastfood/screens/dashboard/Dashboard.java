package com.example.newdeluxfastfood.screens.dashboard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newdeluxfastfood.databinding.ActivityMainBinding;
import com.example.newdeluxfastfood.screens.login_screen.LoginScreen;
import com.example.newdeluxfastfood.screens.order_summary_screen.ui.OrderSummaryScreen;
import com.example.newdeluxfastfood.screens.place_new_order_screen.PlaceOrder;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mFirebaseAuth;
    private final Context currentContext = Dashboard.this;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        /*if (hasFocus)
            hideSystemUI();*/
    }

    private void hideSystemUI() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mFirebaseAuth = FirebaseAuth.getInstance();

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mFirebaseAuth.signOut();
                    startActivity(new Intent(currentContext, LoginScreen.class));
                    finish();
                } catch (Exception e) {
                    Log.i("Error", "Log out error");
                }

            }
        });

        binding.placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, PlaceOrder.class));
            }
        });

        binding.previousOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, OrderSummaryScreen.class));
                //Toast.makeText(currentContext, "App is under development", Toast.LENGTH_SHORT).show();
            }
        });

        binding.locateHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri intentUri = Uri.parse("google.navigation:q=New+Deluxe+Fast+Food,+Kolhapur+India");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(mapIntent);
                else
                    Toast.makeText(currentContext, "Did not find Google maps", Toast.LENGTH_SHORT).show();
            }
        });

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(currentContext, "App is under development", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
