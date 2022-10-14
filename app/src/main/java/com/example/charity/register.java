package com.example.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register extends AppCompatActivity {

    EditText inputUsername,inputEmail,inputPassword,inputConfirmPassword;
    Button btnregister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String username,email,password,confirmpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn1=findViewById(R.id.login);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,login.class));
            }
        });

        inputUsername = findViewById(R.id.inputemail);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        btnregister = findViewById(R.id.btnlogin);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAut();
            }
        });

    }

    private void PerforAut() {
         username = inputUsername.getText().toString();
         email = inputEmail.getText().toString();
         password = inputPassword.getText().toString();
         confirmpass = inputConfirmPassword.getText().toString();
        if(!email.matches(emailPattern)){
           inputEmail.setError("Enter Correct Email");
        }else if(password.isEmpty() || password.length()<6){
           inputPassword.setError("Enter at least 6 length password");
        }else if(!password.matches(confirmpass)){
            inputConfirmPassword.setError("Password doesn't match");
        }/*else{
            progressDialog.setMessage("Please wait for Registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       progressDialog.dismiss();
                       sendUserToNextActivity();
                       Toast.makeText(register.this, "Registration Successful",Toast.LENGTH_SHORT).show();
                   }else{
                       progressDialog.dismiss();
                       Toast.makeText(register.this, ""+task.getException(),Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }*/
        createAccount();

    }

    private void createAccount() {
        progressDialog.setMessage("Please wait for Registration");
        progressDialog.setTitle("Registration");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        sendDatatoDb();
    }

    private void sendDatatoDb() {
        String regtime=""+System.currentTimeMillis();
        HashMap<String,Object> data = new HashMap<>();
        data.put("username",username);
        data.put("email",email);
        data.put("password",password);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        reference.child(username).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                sendUserToNextActivity();
                Toast.makeText(register.this, "Registration Successful",Toast.LENGTH_SHORT).show();
            }
        });
        reference.child(username).setValue(data).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(register.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(register.this,login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}