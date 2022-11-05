package com.example.charity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.charity.Fragment.HomeFragment;
import com.example.charity.Fragment.NotificationFragment;
import com.example.charity.Fragment.ProfileFragment;
import com.example.charity.Fragment.SearchFragment;
import com.example.charity.Model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView bgapp;
    FirebaseUser firebaseUser;
    ImageView image_profile;
    TextView username,email,location;
    LinearLayout menus,hometext;
    BottomNavigationView navi_bar;
    Fragment selectedFragment = null;
    private NavigationView nav_view;
    Animation frombottom;
    private View header;
    String profileid;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        nav_view = findViewById(R.id.nav_view);
        header = nav_view.getHeaderView(0);
        image_profile = header.findViewById(R.id.image_profile);
        username = header.findViewById(R.id.username);
        email = header.findViewById(R.id.email);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        navi_bar = findViewById(R.id.navi_bar);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc  = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            username.setText(personName);
            email.setText(personEmail);
        }
       // location = findViewById(R.id.location);
        SharedPreferences prefs = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid = prefs.getString("profileid","none");
        Bundle intent = getIntent().getExtras();
        userInfo();
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
        //header = nav_view.getHeaderView(0);
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
            case R.id.settings:
                startActivity(new Intent(homepage.this,settings.class));
                break;
            case R.id.logout:
                startActivity(new Intent(homepage.this,login.class));
                break;
            case R.id.about:
                startActivity(new Intent(homepage.this,about.class));
                break;
            case R.id.feedback:
                startActivity(new Intent(homepage.this,Feedback.class));
                break;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Download this app to spread happiness \n https://drive.google.com/file/d/1fHwTMVJ5wISDD1LK5u8mWt-8WsPsJ-iU/view?usp=sharing");
                startActivity(Intent.createChooser(sendIntent, "Choose one"));
                break;
        }
        return true;
    }
    private void userInfo(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user!=null) {
                    Glide.with(getApplicationContext()).load(user.getImageurl()).into(image_profile);
                    username.setText(user.getUsername());
                    email.setText(user.getEmail());
                }else{
                    return;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}