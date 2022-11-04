package com.example.charity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class treat_for_pet extends AppCompatActivity {
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treat_for_pet);

        textview = findViewById(R.id.textview);

        // Creating a string that contains the information to be displayed
        String para = "\n\n"+
                "While looking after a pet or a stray animal, food is an important part." +
                "Some of the recommended pet foods or treats are listed for new owners so that they can easily compare values. "+
                "These information are collected from multiple sources."+
                "\n\n\n"+
                " \t🛒. Bonnie Adult Cat Food Chicken 1.5kg BDT700\n\n"+
                " \t🛒. Bonnie Adult Cat Food Salmon 1.5kg BDT700\n\n"+
                " \t🛒. Bonnie Kitten Cat Food Chicken 1.5kg BDT700\n\n"+
                " \t🛒. Enjoy Adult Cat Food Chicken 1kg BDT350\n\n"+
                " \t🛒. New Cat Adult Cat Food Chicken 15kg BDT4000\n\n"+
                " \t🛒. Spectrum Adult Cat Food Hairball & Indoor 2kg BDT1400\n\n"+
                " \t🛒. Spectrum Derm26 Hypoallergenic Dog Food 12 kg BDT6300\n\n"+
                " \t🛒. Spectrum Low Grain Adult Cat Food Chicken 2kg BDT1400\n\n"+
                " \t🛒. Spectrum Sensitive26 Medium & Large Breed Dog Food 3kg BDT1800\n\n"+
                " \t🛒. Spectrum Sensitive26 Small Breed Dog Food 3kg BDT1800\n\n"+
                " \t🛒. Spectrum Starter30 All Breed Puppy Dog Food Lamb &  Rice 3kg BDT1800\n\n"+
                " \t🛒. Spectrum Starter30 Large Breed Dog Food 3kg BDT1800\n\n"+
                " \t🛒. Spectrum Starter32 Kitten Cat Food Chicken 400gm BDT320\n\n"+
                " \t🛒. Spectrum Starter32 Mini & Medium Breed Puppy Food 3kg BDT1800\n\n"+
                " \t🛒. Brit Fresh Chicken With Potato Puppy Healthy Growth 2.5kg BDT900\n\n"+
                " \t🛒. Brit Premium Dog Food By Nature Junior Medium 3kg BDT1150\n\n"+
                " \t🛒. Kiki Calcium Block For Birds Box(100pcs) BDT2500\n\n"+
                " \t🛒. Brit Animal ALFALFA Snack For Rabbit 100gm BDT250\n\n"+
                " \t🛒. Purina Friskies Adult Cat Food Meaty Grill 1.1kg BDT650\n\n"+
                " \t🛒. Purina Friskies Adult Cat Food Surfin Favorites 1.1kg BDT650\n\n"+
                " \t🛒. Purina Friskies Kitten Cat Food 1.1kg BDT650\n\n"+
                " \t🛒. Reflex Plus Adult Cat Food Chicken 8kg BDT4500\n\n"+
                " \t🛒. Reflex Plus Adult Cat Food Salmon 1.5kg BDT1200\n\n"+
                " \t🛒. Reflex Plus Adult Cat Food Choosy with Salmon 1.5kg BDT1200\n\n"+
                " \t🛒. Reflex Plus Adult Cat Food Hairball & Indoor With Salmon 1.5kg BDT1200\n\n"+
                " \t🛒. Reflex Plus Adult Cat Food Lamb & Rice 1.5kg BDT1200\n\n"+
                " \t🛒. Reflex Plus Adult Cat Food Sterilized Chicken 1.5kg BDT1200\n\n"+
                " \t🛒. Reflex Plus Adult Cat Food Urinary Health Support 1.5kg BDT1200\n\n"+
                " \t🛒. Reflex Plus Adult Cat Food Mother & Baby 1.5kg BDT1200\n\n"+
                " \t🛒. Reflex Plus Kitten Cat Food Chicken 1.5kg BDT1200\n\n"+"" +
                " \t🛒. Smart Heart Adult Cat Food Chicken & Tuna 3kg BDT1150\n\n"+
                " \t🛒. Smart Heart Adult Cat Food Hairball Control 2.8kg BDT1100\n\n"+
                " \t🛒. Smart Heart Adult Cat Food Mackerel 3kg BDT1150\n\n"+
                " \t🛒. Smart Heart Adult Cat Food Salmon 3kg BDT1150\n\n"+
                " \t🛒. Smart Heart Adult Cat Food Seafood 3kg BDT1150\n\n"+
                " \t🛒. Smart Heart Adult Cat Food Tuna & Shrimp 3kg BDT1150\n\n"+
                " \t🛒. Smart Heart Adult Dog Food Chicken & Egg 10kg BDT2800\n\n"+
                " \t🛒. Smart Heart Adult Dog Food Chicken & Liver 20kg BDT4700\n\n"+
                " \t🛒. Smart Heart Adult Dog Food Power Maxx 10k BDT4100\n\n"+
                " \t🛒. Smart Heart Adult Dog Food Power Pack 10kg BDT3800\n\n"+
                " \t🛒. Smart Heart Adult Dog Food Smoked Liver 3kg BDT900\n\n"+
                " \t🛒. Smart Heart Adult Dog Gold Fit & Firm 20kg BDT5400\n\n"+
                " \t🛒. Smart Heart Junior Rabbit Food 1kg BDT320\n\n"+
                " \t🛒. Smart Heart Kitten Cat Food 1.1kg BDT650\n\n"+
                " \t🛒. Smart Heart Puppy Dog Food Beef & Milk 8kg BDT2450\n\n"+
                " \t🛒. Smart Heart Puppy Dog Food Chicken Egg & Milk 8kg BDT2450\n\n"+
                " \t🛒. Brit Animal Rabbit Food Junior 1.5kg BDT650\n\n\n\n"
                ;

        // set value to the given TextView
        textview.setText(para);

        // to perform the movement action
        // Moves the cursor or scrolls to the
        // top or bottom of the document
        textview.setMovementMethod(new ScrollingMovementMethod());
        ImageView btn=findViewById(R.id.backtp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(treat_for_pet.this,pet_details.class));
            }
        });
    }
}