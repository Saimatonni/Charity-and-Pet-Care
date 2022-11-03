package com.example.charity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.charity.Adapter.PostAdapter;
import com.example.charity.Adapter.SearchAdapter;
import com.example.charity.Model.post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<post> postList;
    SearchAdapter searchAdapter;
    DatabaseError databaseError;
    String postid;
    EditText search_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        postid = intent.getStringExtra("postid");
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        search_bar = findViewById(R.id.search_bar);
        postList = new ArrayList<>();
        searchAdapter = new SearchAdapter(this,postList);
        recyclerView.setAdapter(searchAdapter);
        //searchAdapter = new SearchAdapter(postList,this);
        readsPost();
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchPosts(charSequence.toString().toLowerCase(Locale.ROOT));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void searchPosts(String s){
        Query query = FirebaseDatabase.getInstance().getReference("Posts").orderByChild("catagory")
                .startAt(s)
                .endAt(s+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    post pst = snapshot1.getValue(post.class);
                    postList.add(pst);
                }
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void readsPost(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    post pst = snapshot1.getValue(post.class);

                    postList.add(pst);
                   // searchAdapter = new SearchAdapter(getActivity(),postList,postid);
                   // recyclerView.setAdapter(searchAdapter);


                }
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SearchActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}