package com.example.charity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.charity.Fragment.HomeFragment;
import com.example.charity.Fragment.NotificationFragment;
import com.example.charity.Fragment.ProfileFragment;
import com.example.charity.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageView bgapp;
    LinearLayout menus,hometext;
    BottomNavigationView navi_bar;
    Fragment selectedFragment = null;
    Animation frombottom;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        navi_bar = findViewById(R.id.navi_bar);
        Bundle intent = getIntent().getExtras();
        if(intent!=null){
           String publisher = intent.getString("publisherid");
            SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
            editor.putString("profileid",publisher);
            editor.apply();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        navi_bar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                   case R.id.createpost:
                        startActivity(new Intent(homepage.this,PostActivity.class));
                        break;
                    case R.id.home:
                        // startActivity(new Intent(homepage.this,CreatePost.class));
                        selectedFragment = new HomeFragment();
                        break;

                    case R.id.profile:
                        // startActivity(new Intent(homepage.this,CreatePost.class));
                        SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
                        editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        editor.apply();
                        selectedFragment = new ProfileFragment();
                        break;
                    /*case R.id.search:
                        //startActivity(new Intent(homepage.this,profile.class));
                        selectedFragment = new SearchFragment();
                        break;*/
                    case R.id.notifications:
                        // startActivity(new Intent(homepage.this,CreatePost.class));
                        selectedFragment = new NotificationFragment();
                        break;

                    case R.id.vet:
                        startActivity(new Intent(homepage.this,pet_details.class));
                        break;
                }if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.contact:
                startActivity(new Intent(homepage.this,contact.class));
                break;
            /*case R.id.profile:
                startActivity(new Intent(homepage.this,profile.class));
                break;
                SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                editor.putString("profileid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                startActivity(new Intent(homepage.this,profile.class));
                break;*/
            case R.id.settings:
                startActivity(new Intent(homepage.this,settings.class));
                break;
            case R.id.logout:
                startActivity(new Intent(homepage.this,login.class));
                break;
        }
        return true;
    }

}