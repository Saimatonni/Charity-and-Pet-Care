package com.example.charity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
<<<<<<< HEAD
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
=======
>>>>>>> origin/master
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class homepage extends AppCompatActivity {
    ImageView bgapp;
    LinearLayout menus;
    Animation frombottom;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
<<<<<<< HEAD
        bgapp = (ImageView) findViewById(R.id.bgapp);
        bgapp.animate().translationY(-2000).setDuration(1000).setStartDelay(200);
        menus = (LinearLayout) findViewById(R.id.menus);
        //menus.startAnimation(frombottom);
       // bgapp.animate().scaleY(400).setDuration(500).setStartDelay(100);

=======
>>>>>>> origin/master

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}