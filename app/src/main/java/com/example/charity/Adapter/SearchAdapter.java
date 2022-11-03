package com.example.charity.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.charity.Fragment.PostDetailsFragment;
import com.example.charity.Model.User;
import com.example.charity.Model.post;
import com.example.charity.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    public Context context;
    public List<post> mspost;
    FirebaseUser firebaseUser;
    //private String postid;

    public SearchAdapter(Context context, List<post> mspost) {
        this.context = context;
        this.mspost = mspost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Context context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);

        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        post pst = mspost.get(position);
        //String postid = getRef
        if(context==null){
            return;
        }
        //getpostinfo(holder.postimage,holder.catagory,holder.location);
        Glide.with(context).load(pst.getPostimage()).into(holder.postimage);
        holder.catagory.setVisibility(View.VISIBLE);
        holder.catagory.setText(pst.getCatagory());
        holder.location.setVisibility(View.VISIBLE);
        holder.location.setText(pst.getLocation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
                //pst = mpost.get(position);
                SharedPreferences.Editor editor = context.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("postid",pst.getPostid());
                editor.apply();
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PostDetailsFragment()).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mspost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView postimage;
        public TextView catagory,location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postimage = itemView.findViewById(R.id.post_image);
            location = itemView.findViewById(R.id.location);
            catagory = itemView.findViewById(R.id.catagory);
        }
    }
    private void getpostinfo(ImageView imageView,TextView catagory,TextView location){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // User user = snapshot.getValue(User.class);
                post pst = snapshot.getValue(post.class);
                if(pst!=null) {
                    Glide.with(context).load(pst.getPostimage()).into(imageView);
                    catagory.setText(pst.getCatagory());
                    location.setText(pst.getLocation());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
