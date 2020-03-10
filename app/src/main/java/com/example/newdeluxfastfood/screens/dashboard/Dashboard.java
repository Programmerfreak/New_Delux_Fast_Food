package com.example.newdeluxfastfood.screens.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newdeluxfastfood.databinding.ActivityMainBinding;
import com.example.newdeluxfastfood.screens.login_screen.LoginScreen;
import com.example.newdeluxfastfood.screens.place_new_order_screen.PlaceOrder;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth mFirebaseAuth;
    private final Context currentContext = Dashboard.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        View decorView = getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOption);

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
                Toast.makeText(currentContext, "App is under development", Toast.LENGTH_SHORT).show();
            }
        });

        binding.locateHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(currentContext, "App is under development", Toast.LENGTH_SHORT).show();
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
