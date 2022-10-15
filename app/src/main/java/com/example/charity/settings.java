package com.example.charity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class settings extends AppCompatActivity {
    String petlist[] = {"Theme","Change Password","About"};
    int imagelist[] = {R.drawable.theme, R.drawable.changepass, R.drawable.about};
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        listview = (ListView) findViewById(R.id.settings);
        customBaseAdapter customadp = new customBaseAdapter(getApplicationContext(),petlist,imagelist);
        listview.setAdapter(customadp);
    }
}