package com.example.charity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class profile extends AppCompatActivity {
    private Spinner spinnertextfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        spinnertextfile = findViewById(R.id.profilespinner);
        String[] text=getResources().getStringArray(R.array.font_sizes);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertextfile.setAdapter(adapter);
    }
}