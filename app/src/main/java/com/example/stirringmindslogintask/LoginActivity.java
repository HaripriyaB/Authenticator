package com.example.stirringmindslogintask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView goto_signup;
    EditText muserid,mpassword;
    Button mlogin;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        goto_signup=findViewById(R.id.signup);
        goto_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.logprogress);
        mlogin=findViewById(R.id.logloginbutton);
        muserid=findViewById(R.id.logusername);
        mpassword=findViewById(R.id.logpassword);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String user = muserid.getText().toString();
                String password = mpassword.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(main);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Not a valid user", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }
}
