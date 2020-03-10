package com.example.newdeluxfastfood.screens.launch_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newdeluxfastfood.screens.dashboard.Dashboard;
import com.example.newdeluxfastfood.R;
import com.example.newdeluxfastfood.screens.login_screen.LoginScreen;
import com.google.firebase.auth.FirebaseAuth;

public class LaunchScreen extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        View decorView = getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOption);

        mFirebaseAuth = FirebaseAuth.getInstance();

        new CountDownTimer(3000,800){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(mFirebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(LaunchScreen.this, LoginScreen.class));
                } else {
                    startActivity(new Intent(LaunchScreen.this, Dashboard.class));
                }
                finish();
            }
        }.start();
    }
}
