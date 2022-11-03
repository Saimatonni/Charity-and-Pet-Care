package com.example.charity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declaring TextView from the Layout
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing the TextView
        textview = findViewById(R.id.text);

        // Creating a string that contains the information to be displayed
        String para = "CFP (Care for Paws):\n" +
                "Phone: 01635817270 // 01750578138\n" +
                "Address: House no. 68/3C, Shwapno Express Lane, Zigatola Post Office Road, Dhanmondi.\n" +
                "\n\n"+
                "Dr. Md. Luthfor Rahman: **He attends house Calls.\n" +
                "Veterinary Surgeon, Central Veterinary Hospital, Dhaka.\n"+
                "Cell # 01552457085, 01731492093\n" +
                "Email: luthfor75@gmail.com\n" +
                "Residene: Nikunja-2, Khilkhet, Dhaka.\n" +
                "\n\n"+
                "PAW Life Care:\n" +
                "Address: 1/12, Block-G, Lalmatia, Dhaka, Bangladesh\n" +
                "Contact number: +8801909617994\n" +
                "Clinic hours: 9AM to 11AM and 4PM to 8PM\n" +
                "\n\n"+
                "Vet and Pet care (Dr. K. B. M. Saiful Islam)\n" +
                "Address: House# 68/10, Europa International School Lane, Zigatola Post Office Road, Dhanmondi, Dhaka, Bangladesh.\n" +
                "Contact : 01835666696\n" +
                "\n\n"+
                "Dr.Kazi Mujibur Rahman:\n" +
                "Chief vet,Central Veterinary Hospital,Dhaka\n"+
                "Cell #01715016218 (has a clinic in Bashundhara r/a)\n"+
                "\n\n"+
                "Dr.Azmat Ali (and Dr. Chowdhury Sultana Sabrina):\n" +
                "**He attends House calls\n" +
                "Address (Gulshan Branch): Gulshan Pet-Animal Clinic\n" +
                "(A-Z Pet Animal Solution in Dhaka) 4-5 DCC Super MarketGulshan-2, Dhaka-1212\n" +
                "Visiting Hours: Call the vet before going\n" +
                "Phone: 9883486, 01912-013615, 01715-078434\n" +
                "\n\n"+
                "Dr.Siamak : **He attends house calls.\n" +
                "Address: H- 56, R- 9/B, Sector: 5, Uttara.\n" +
                "Phone # 8917249\n" +
                "Cell # 01711561155\n"+
                "\n\n"+
                "Dr. Motahar Hossain: **He attends house calls.\n" +
                "Enlisted vet of USA embassy\n" +
                "Cell # 01711541070,01971541070\n" +
                "Gulshan 2 Dcc Market, 2nd floor.\n" +
                "Visitng House : 10/11 to 2pm (call the vet before going)\n" +
                "\n\n"+
                "Chittagaong Vetenary and Animal Science University:\n" +
                "zakir hossain road, khulshi. ctg-4202\n" +
                "\n\n"+
                "Dr. Bibek Chandra:\n" +
                "Cell # 01711057533 sutradhar\n" +
                "\n\n"+
                "Dr.Monowar sayeed:\n" +
                "Cell # 01736930901 pallab\n" +
                "\n\n"+
                "Dr. Morshed\n" +
                "Cell # 01192046813\n" +
                "\n\n"+
                "Dr. Farhad\n" +
                "Cell # 01711172139\n" +
                "\n\n"+
                "Dr. A T M Mahbub E Elahi\n" +
                "Professor, Dept of Microbiology, SAU\n" +
                "Cell # 01711301042\n" +
                "Email: atm.mahbub.elahi@gmail.com\n"+
                "\n\n"+
                "Dr. Syed Sayem Uddin Ahmed\n" +
                "Dept of Public Health\n" +
                "Cell # 01947706956\n"+
                "Email: sayeem_2000@yahoo.com\n"+
                "\n\n"+
                "DR. Animesh Roy\n"+
                "VS, SAU Vet Clinics\n"+
                "Cell # 01717896167\n"+
                "Email: royanimeshvet@yahoo.com\n"+
                "\n\n"+
                "DR. Mahbub\n" +
                "VS, District Vet. Hospital, Sylhet\n" +
                "Cell # 01711287533\n"+
                "\n\n"+
                "Dr. Rofiqul Islam\n" +
                "Dept of Medicine,SAU\n"+
                "Cell # 01199008496\n" +
                "Email : dr.rafiqsau@yahoo.com\n"+
                "\n\n"+
                "Dr. MD Siddiqul Islam\n" +
                "Dept of Pharmacology, SAU\n" +
                "Cell # 01711148450\n"+
                "Email: siddiqulislam27@yahoo.com\n"+
                "\n\n"+
                "DR. Sultan Ahmed\n" +
                "Dept of Microbiology, SAU\n"+
                "Cell # 01711140389\n"+
                "Email: drsultanahmed@yahoo.com\n"+
                "\n\n"+
                "Upozilla Livestock Officer\n"+
                "Sylhet Sadar\n"+
                "Cell# 01711230811\n"+
                "\n\n"+
                "Dr. Md. Karim Uddin\n"+
                "Additional Veterinary Surgeon,Dhaka Metro.\n"+
                "Emergency Centre for Transboundry Animal Diseases(ECTAD)\n"+
                "Food and Agriculture Organization of the United Nations\n"+
                "Bangladesh\n"+
                "Cell # 01814287986\n";

        // set value to the given TextView
        textview.setText(para);

        // to perform the movement action
        // Moves the cursor or scrolls to the
        // top or bottom of the document
        textview.setMovementMethod(new ScrollingMovementMethod());
    }
}
