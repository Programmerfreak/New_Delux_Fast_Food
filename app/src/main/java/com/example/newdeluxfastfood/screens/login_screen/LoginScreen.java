package com.example.newdeluxfastfood.screens.login_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newdeluxfastfood.screens.dashboard.Dashboard;
import com.example.newdeluxfastfood.databinding.ActivityLoginBinding;
import com.example.newdeluxfastfood.screens.registration_screen.RegistrationScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private String email, password;
    private ActivityLoginBinding binding;
    private final Context currentContext = LoginScreen.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mFirebaseAuth = FirebaseAuth.getInstance();

        binding.logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.emailEditText.getText().toString();
                password = binding.passwordEditText.getText().toString();

                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(currentContext, "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(currentContext, Dashboard.class));
                                    finish();
                                } else {
                                    Exception exception = task.getException();
                                    Log.i("Error", exception.toString());
                                }
                            }
                        });
                    } catch (Exception e) {
                        Log.i("Error", e.toString());
                    }
                }
            }
        });

        binding.registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(currentContext, RegistrationScreen.class));
            }
        });
    }
}
