package com.example.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class pet_details extends AppCompatActivity {
    BottomNavigationView navi_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        Button btn3=findViewById(R.id.btn_gallery);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pet_details.this,gallery.class));
            }
        });
        ImageView btn=findViewById(R.id.backh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pet_details.this,homepage.class));
            }
        });
        Button btnv=findViewById(R.id.vet_for_pet);
        btnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pet_details.this,vet_for_pet.class));
            }
        });
        Button btnt=findViewById(R.id.treat_for_pet);
        btnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pet_details.this,treat_for_pet.class));
            }
        });
        Button btnd=findViewById(R.id.donate);
        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pet_details.this,DonateActivity.class));
            }
        });
    }
}