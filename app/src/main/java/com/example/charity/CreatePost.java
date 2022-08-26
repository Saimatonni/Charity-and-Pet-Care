package com.example.charity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreatePost extends AppCompatActivity {
    private Spinner cattextfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        cattextfile = findViewById(R.id.catspinner);
        String[] text2=getResources().getStringArray(R.array.post_type);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,text2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cattextfile.setAdapter(adapter2);
    }
}