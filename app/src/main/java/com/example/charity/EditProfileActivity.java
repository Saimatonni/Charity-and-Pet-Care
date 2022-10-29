package com.example.charity;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.charity.Model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {
    ImageView close;
    ImageButton image_profile;
    TextView update,tv_change;
    MaterialEditText fullname,username,address,contact;
    FirebaseUser firebaseUser;
    private Uri mimageuri;
    DatabaseReference mref;
    FirebaseStorage mstorage;
   // StorageReference storageReference;
    private StorageTask uploadtask;
    private static final int Gallery_code=2;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        close = findViewById(R.id.close);
        image_profile = findViewById(R.id.image_profile);
        update = findViewById(R.id.update);
       // tv_change = findViewById(R.id.tv_change);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.contact);
       // ProgressDialog progressDialog = new ProgressDialog(this);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                //assert user != null;
                fullname.setText(user.getFullname());
                username.setText(user.getUsername());
                address.setText(user.getAddress());
                contact.setText(user.getContact());
                Glide.with(getApplicationContext()).load(user.getImageurl()).into(image_profile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ProgressDialog progressDialog = new ProgressDialog(this);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((Intent.ACTION_GET_CONTENT));
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_code);
                //uploadImage();
            }
        });
       /* update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile(fullname.getText().toString(),username.getText().toString(),address.getText().toString(),contact.getText().toString());
                uploadImage();
                //Toast.makeText(EditProfileActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
    private void updateProfile(String fullname,String username,String address,String contact){
       DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(firebaseUser.getUid());
        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("fullname",fullname);
        hashMap.put("username",username);
        hashMap.put("address",address);
        hashMap.put("contact",contact);
        reference.updateChildren(hashMap);
       // uploadImage();
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Gallery_code && resultCode==RESULT_OK){
            mimageuri = data.getData();
            image_profile.setImageURI(mimageuri);
            //uploadImage();
        }else{
            Toast.makeText(EditProfileActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //updateProfile(fullname.getText().toString(),username.getText().toString(),address.getText().toString(),contact.getText().toString());
                if((mimageuri!=null)){
                    ProgressDialog progressDialog = new ProgressDialog(EditProfileActivity.this);
                    progressDialog.setMessage("Updating.....");
                    progressDialog.show();
                    StorageReference filereference = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(mimageuri));
                    uploadtask = filereference.putFile(mimageuri);
                    uploadtask.continueWithTask(new Continuation() {
                        @Override
                        public Object then(@NonNull Task task) throws Exception {
                            if(!task.isSuccessful()){
                                throw task.getException();
                            }
                            return filereference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if(task.isSuccessful()){
                                Uri downloadUri = task.getResult();
                                String myUri = downloadUri.toString();
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(firebaseUser.getUid());
                                HashMap<String,Object> hashMap = new HashMap<>();
                                hashMap.put("imageurl",""+myUri);
                                hashMap.put("fullname",fullname.getText().toString());
                                hashMap.put("username",username.getText().toString());
                                hashMap.put("address",address.getText().toString());
                                hashMap.put("contact",contact.getText().toString());
                                reference.updateChildren(hashMap);
                                progressDialog.dismiss();
                                Toast.makeText(EditProfileActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(EditProfileActivity.this, EditProfileActivity.class));
                            }else{
                                Toast.makeText(EditProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditProfileActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(EditProfileActivity.this, "No Image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


