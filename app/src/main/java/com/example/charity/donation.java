package com.example.donation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declaring TextView from the Layout
    Button button;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://albshelter.com/donate/");
            }

            private void goLink(String s) {
                Uri uri=Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        // initializing the TextView
        textview = findViewById(R.id.textview);

        // Creating a string that contains the information to be displayed
        String para ="ALB animal shelter was created mainly for the stray animals of Bangladesh." +
                "They rescue, foster and give them up for adoption. " +
                "They try to educate pet owners about urgent veterinary help, " +
                "vaccinations and sterilizations. They use their ALB group as a platform " +
                "for helping and saving animals. It's located in Narayanganj, Bangladesh.";

        // set value to the given TextView
        textview.setText(para);

        // to perform the movement action
        // Moves the cursor or scrolls to the
        // top or bottom of the document
        textview.setMovementMethod(new ScrollingMovementMethod());
    }
}
