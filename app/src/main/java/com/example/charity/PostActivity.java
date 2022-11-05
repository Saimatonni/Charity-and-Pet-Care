package com.example.charity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class PostActivity extends AppCompatActivity {
    Uri imageUrl=null;
    String myUrl = "";
    StorageTask uploadTask;
    FirebaseDatabase mdatabase;
    DatabaseReference mref;
    FirebaseStorage mstorage;
    StorageReference storageReference;
    ImageView close;
    ImageButton imageButton;
    TextView post;
    EditText description, catagory,location;
    private static final int Gallery_code=1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        close = findViewById(R.id.close);
        imageButton = findViewById(R.id.imageButton);
        post = findViewById(R.id.post);
        description = findViewById(R.id.description);
        catagory = findViewById(R.id.catagory);
        location = findViewById(R.id.location);
        mdatabase= FirebaseDatabase.getInstance();
        // mref = mdatabase.getReference().child("Users");
         mref = mdatabase.getReference("Posts");
        mstorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        //storageReference = FirebaseStorage.getInstance().getReference("posts");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostActivity.this, homepage.class));
                finish();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((Intent.ACTION_GET_CONTENT));
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_code);
            }
        });
        /*post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Gallery_code && resultCode==RESULT_OK){
            imageUrl = data.getData();
            imageButton.setImageURI(imageUrl);
        }else{
            Toast.makeText(PostActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PostActivity.this,homepage.class));
            finish();
        }
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String des = description.getText().toString().trim();
               // String postid = mref.push().getKey();
                String publisher = description.getText().toString().trim();
                String cat = catagory.getText().toString().trim();
                String loca = location.getText().toString().trim();
                if(!(des.isEmpty() && loca.isEmpty() && cat.isEmpty() && imageUrl!=null)){
                    progressDialog.setTitle("Uploading......");
                    progressDialog.show();
                    StorageReference filepath = mstorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
                                    String postid = reference.push().getKey();
                                    DatabaseReference newPost=mref.push();
                                    newPost.child("postid").setValue(postid);

                                    newPost.child("postimage").setValue(task.getResult().toString());
                                    newPost.child("description").setValue(des);
                                    newPost.child("catagory").setValue(cat.toLowerCase(Locale.ROOT));
                                    newPost.child("location").setValue(loca);
                                    newPost.child("publisher").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                    //newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();
                                    Toast.makeText(PostActivity.this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(PostActivity.this, homepage.class));
                                    finish();
                                }
                            });
                        }
                    });
                }else{
                    Toast.makeText(PostActivity.this, "Please, fill all the information", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
/*HashMap<String, Object>hashMap = new HashMap<>();
                                    hashMap.put("postid",postid);
                                    hashMap.put("postimage",task.getResult().toString());
                                    hashMap.put("description",description.getText().toString());
                                    hashMap.put("location",location.getText().toString());
                                    hashMap.put("catagory",catagory.getText().toString());
                                    hashMap.put("publisher",FirebaseAuth.getInstance().getCurrentUser().getUid());
                                    reference.setValue(hashMap);*/

