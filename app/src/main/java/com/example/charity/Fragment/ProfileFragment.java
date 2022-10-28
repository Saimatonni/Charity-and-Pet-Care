package com.example.charity.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.charity.Adapter.DonationAdapter;
import com.example.charity.Model.User;
import com.example.charity.Model.post;
import com.example.charity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProfileFragment extends Fragment {

    ImageView image_profile,options;
    TextView posts,fullname,address,username,contact,email;
    Button edit_profile;
    FirebaseUser firebaseUser;
    String profileid;
    TextView my_fotos;
    RecyclerView recyclerView;
    DonationAdapter donationAdapter;
    List<post> postList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences prefs = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid = prefs.getString("profileid","none");
        image_profile = view.findViewById(R.id.image_profile);
        posts = view.findViewById(R.id.posts);
        fullname = view.findViewById(R.id.fullname);
        address = view.findViewById(R.id.address);
        contact = view.findViewById(R.id.contact);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        edit_profile = view.findViewById(R.id.edit_profile);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(linearLayoutManager);
        postList = new ArrayList<>();
        donationAdapter = new DonationAdapter(getContext(),postList);
        recyclerView.setAdapter(donationAdapter);
        userInfo();
        getNRPosts();
        myDonations();
        if(profileid.equals(firebaseUser.getUid())){
            edit_profile.setText("Edit Profile");
        }
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btn = edit_profile.getText().toString();
                if(btn.equals("Edit Profile")){

                }
            }
        });
        return view;
    }
    private void userInfo(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(profileid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(getContext()==null)
                {
                    return;
                }
                User user  = snapshot.getValue(User.class);
                Glide.with(getContext()).load(user.getImageurl()).into(image_profile);
                username.setText(user.getUsername());
                fullname.setText(user.getFullname());
                email.setText(user.getEmail());
                address.setText(user.getAddress());
                contact.setText(user.getContact());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void getNRPosts(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i=0;
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    post pst = snapshot1.getValue(post.class);
                    if(pst.getPublisher().equals(profileid)){
                        i++;
                    }
                }
                posts.setText(""+i);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void myDonations(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               postList.clear();
               for(DataSnapshot snapshot1: snapshot.getChildren()){
                   post pst = snapshot1.getValue(post.class);
                   if(pst.getPublisher().equals(profileid)){
                       postList.add(pst);
                   }
               }
                Collections.reverse(postList);
               donationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}