package com.example.charity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class pets_info extends AppCompatActivity {
     String petlist[] = {"Cat","Dog","Other pets"};
     int imagelist[] = {R.drawable.cat, R.drawable.dog, R.drawable.pet};
     ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_info);
        listview = (ListView) findViewById(R.id.vets);
        customBaseAdapter customadp = new customBaseAdapter(getApplicationContext(),petlist,imagelist);
        listview.setAdapter(customadp);
    }
}