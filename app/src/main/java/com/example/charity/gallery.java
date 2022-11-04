package com.example.charity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class gallery extends AppCompatActivity {
    RecyclerView recyclerView;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        videoView=findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.strays);
        MediaController mc=new MediaController(this);
        videoView.setMediaController(mc);
        mc.setAnchorView(videoView);
        videoView.start();
        ImageView btn=findViewById(R.id.backgp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gallery.this,pet_details.class));
            }
        });
    }
}