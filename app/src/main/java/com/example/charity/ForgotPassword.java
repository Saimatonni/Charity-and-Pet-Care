package com.example.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    TextView bk_login;
    EditText txtEmail;
    Button forgot_pass;
    private String email;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        bk_login = findViewById(R.id.bk_login);
        txtEmail = findViewById(R.id.forgotemail);
        forgot_pass = findViewById(R.id.forgot_pass);
        bk_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this,login.class));
            }
        });
        auth = FirebaseAuth.getInstance();
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();
            }
        });

    }
    private void validatedata(){
        email = txtEmail.getText().toString();
        if(email.isEmpty()){
            txtEmail.setError("Required");
        }else{
            forgotpass();
        }
    }
    private void forgotpass(){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Check your Email",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPassword.this,login.class));
                    finish();
                }else{
                    Toast.makeText(ForgotPassword.this,"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}