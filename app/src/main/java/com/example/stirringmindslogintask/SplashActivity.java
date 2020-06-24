package com.example.stirringmindslogintask;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(3000);
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        FirebaseUser currentuser=mauth.getCurrentUser();
        if(currentuser!=null)
        {
            startActivity(new Intent(this,MainActivity.class));
        }
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
