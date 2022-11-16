package com.example.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Feedback extends AppCompatActivity {

    RatingBar rbStars;
    TextView tfeed;

    EditText messagedata;
   // FirebaseUser firebaseUser;
    Button send;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        rbStars = findViewById(R.id.rbstars);
        tfeed = findViewById(R.id.tfeed);
        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float r, boolean b) {
                if(r==0){
                    tfeed.setText("Very Dissatisfied :((");
                }
                else if(r==1){
                    tfeed.setText("Dissatisfied :(");
                }
                else if(r==2){
                    tfeed.setText("Okayish");
                }
                else if(r==3){
                    tfeed.setText("Satisfied :)");
                }
                else if(r==4){
                    tfeed.setText("Very Satisfied :))");
                }
                else if(r==5){
                    tfeed.setText("Amesome!! ^-^");
                }
                else{
                    tfeed.setText("");
                }

            }
        });

        //for dbms part
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        messagedata = findViewById(R.id.msg);
        send = findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Smessagedata = messagedata.getText().toString();
                if(Smessagedata.equals("")){
                    Toast.makeText(Feedback.this,"You can't send empty Feedback",Toast.LENGTH_SHORT).show();
                }else{
                    sendFeedback(Smessagedata);
                    Toast.makeText(Feedback.this, "Sent Successfully", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(CommentsActivity.this,homepage.class));
                }
            }
        });
        ImageView btn=findViewById(R.id.backfee);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Feedback.this,homepage.class));
            }
        });
      

    }
    private void sendFeedback(String Smessage){
 
        reference = FirebaseDatabase.getInstance().getReference("Feedback");
       // String feedbackid = reference.push().getKey();
        HashMap<String,Object> hashMap = new HashMap<>();
       // hashMap.put("userid",userid);
        hashMap.put("feedback",Smessage);

       // hashMap.put("cid",commentid);
        reference.setValue(hashMap);
               
    }
}
