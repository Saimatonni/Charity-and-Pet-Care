package com.example.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class pet_details extends AppCompatActivity {
    BottomNavigationView navi_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);
        navi_bar = findViewById(R.id.navi_bar);
        navi_bar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.createpost:
                        startActivity(new Intent(pet_details.this,CreatePost.class));
                        break;
                    case R.id.profile:
                        startActivity(new Intent(pet_details.this,profile.class));
                        break;
                    case R.id.home:
                        startActivity(new Intent(pet_details.this,homepage.class));
                        break;
                }
                return true;
            }
        });
    }
}