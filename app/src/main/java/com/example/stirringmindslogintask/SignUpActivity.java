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

public class SignUpActivity extends AppCompatActivity {

    EditText muserid,mpassword,mcnfpassword;
    Button msignup;
    ProgressBar mprogress;
    private FirebaseAuth mAuth;
    TextView goto_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        goto_login=findViewById(R.id.login);
        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        mprogress=findViewById(R.id.sigprogress);
        muserid=findViewById(R.id.sigusername);
        mpassword=findViewById(R.id.sigpassword);
        mcnfpassword=findViewById(R.id.sigconfirmpass);
        msignup=findViewById(R.id.sigsignupbutton);
        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = muserid.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String cnfpassword = mcnfpassword.getText().toString().trim();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cnfpassword)) {
                    Toast.makeText(SignUpActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(cnfpassword)){
                    mcnfpassword.setError("Should be same as Password");
                }
                else {
                    mprogress.setVisibility(View.VISIBLE);
                        mAuth.createUserWithEmailAndPassword(user, password)
                                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        mprogress.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, "SignedUp successfully!", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                                            startActivity(i);
                                            finish();
                                        } else {
                                            Toast.makeText(SignUpActivity.this, "Authentification failed!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                }
            }
        });
    }
}



