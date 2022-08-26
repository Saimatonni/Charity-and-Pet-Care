package com.example.charity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ImageView btn1=findViewById(R.id.profile);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this,profile.class));
            }
        });
        ImageView btn=findViewById(R.id.pets);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this,pets_info.class));
            }
        });
        ImageView btn3=findViewById(R.id.createpost);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this,CreatePost.class));
            }
        });
        ImageView btn4=findViewById(R.id.allpost);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this,All_posts.class));
            }
        });
        //final EditText editText = (EditText)findViewById(R.id.urlText);
        Button pagebtn = (Button) findViewById(R.id.btnNavigate);
        pagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String url = editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);*/
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.globalgiving.org"));
                startActivity(intent);

            }
        });
    }
}