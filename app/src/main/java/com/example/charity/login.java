package com.example.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText inputPassword,inputemail;
    TextView forgotpass;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
   // String email,password;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.hereregister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,register.class));
            }
        });

        inputemail = findViewById(R.id.inputemail);
        inputPassword = findViewById(R.id.inputPassword);
        forgotpass = findViewById(R.id.forgotpass);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,ForgotPassword.class));
            }
        });
        Button btn2=findViewById(R.id.btnlogin);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // PerLog();
                ProgressDialog pd = new ProgressDialog(login.this);
                progressDialog.setMessage("Please wait for Login");
                progressDialog.setTitle("Login");
                pd.show();
                String str_email = inputemail.getText().toString();
                String str_password = inputPassword.getText().toString();
                if(!str_email.matches(emailPattern)){
                    inputemail.setError("Enter Correct Email");
                }else if(str_password.isEmpty() || str_password.length()<6){
                    inputPassword.setError("Enter at least 6 length password");
                }else{
                    firebaseAuth.signInWithEmailAndPassword(str_email,str_password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseAuth.getCurrentUser().getUid());
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                                      //  }
                                        pd.dismiss();
                                        Intent intent = new Intent(login.this,homepage.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        pd.dismiss();
                                    }
                                });
                            }else{
                                pd.dismiss();
                                Toast.makeText(login.this,"Wrong Email or Password, try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}