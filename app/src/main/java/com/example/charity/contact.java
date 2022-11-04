package com.example.charity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class contact extends AppCompatActivity {
    private EditText editemail;
    private EditText editsubject;
    private EditText editmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        editemail = findViewById(R.id.email);
        editsubject = findViewById(R.id.subject);
        editmessage = findViewById(R.id.message);

        Button btn = findViewById(R.id.send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sendMail();
            }
        });
        ImageView btn2=findViewById(R.id.backch);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(contact.this,homepage.class));
            }
        });


    }
    private void sendMail(){
        String recipientList = editemail.getText().toString();
        String[] recipients = recipientList.split(",");
        String sbj = editsubject.getText().toString();
        String mgs = editmessage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,sbj);
        intent.putExtra(Intent.EXTRA_TEXT,mgs);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"chose an email clints"));
    }
}