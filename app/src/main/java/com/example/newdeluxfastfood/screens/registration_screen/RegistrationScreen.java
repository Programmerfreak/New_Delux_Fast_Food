package com.example.newdeluxfastfood.screens.registration_screen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newdeluxfastfood.databinding.ActivityRegistrationScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationScreen extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private ActivityRegistrationScreenBinding binding;
    private String email, password;
    private final Context currentContext = RegistrationScreen.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mFirebaseAuth = FirebaseAuth.getInstance();

        binding.registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = binding.emailEditText.getText().toString();
                password = binding.passwordEditText.getText().toString();

                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(currentContext,"Please fill all details",Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    finish();
                                } else {
                                    Toast.makeText(currentContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }catch (Exception e) {
                        Log.i("Error", e.toString());
                    }
                }
            }
        });
    }
}
