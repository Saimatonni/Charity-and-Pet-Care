package com.example.charity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.charity.CommentsActivity;
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

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    public Context mContext;
    public List<post> mpost;
    FirebaseUser firebaseUser;

    public PostAdapter(Context mContext, List<post> mpost) {
        this.mContext = mContext;
        this.mpost = mpost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post_item,parent,false);

        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        post pst = mpost.get(position);
        Glide.with(mContext).load(pst.getPostimage()).into(holder.postimage);
         /*if(pst.getDescription().equals("")){
             holder.description.setVisibility(View.GONE);
             holder.catagory.setVisibility(View.GONE);
             holder.location.setVisibility(View.GONE);
         }else{*/
        holder.description.setVisibility(View.VISIBLE);
        holder.description.setText(pst.getDescription());
        holder.catagory.setVisibility(View.VISIBLE);
        holder.catagory.setText(pst.getCatagory());
        holder.location.setVisibility(View.VISIBLE);
        holder.location.setText(pst.getLocation());
        //}
        publisherinfo(holder.image_profile,holder.uname,holder.publisher,pst.getPublisher());
        isLikes(pst.getPostid(),holder.like);
        mrLikes(holder.likes,pst.getPostid());
        getComments(pst.getPostid(),holder.comments);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.like.getTag().equals("like")){
                    FirebaseDatabase.getInstance().getReference().child("likes").child(pst.getPostid()).child(firebaseUser.getUid()).setValue(true);
                }else{
                    FirebaseDatabase.getInstance().getReference().child("likes").child(pst.getPostid()).child(firebaseUser.getUid()).removeValue();
                }
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommentsActivity.class);
                intent.putExtra("postid",pst.getPostid());
                intent.putExtra("publisherid",pst.getPublisher());
                mContext.startActivity(intent);
            }
        });
        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommentsActivity.class);
                intent.putExtra("postid",pst.getPostid());
                intent.putExtra("publisherid",pst.getPublisher());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mpost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_profile,postimage,like,comment;
        public TextView uname,likes,description,comments,publisher,location,catagory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_profile = itemView.findViewById(R.id.image_profile);
            postimage = itemView.findViewById(R.id.post_image);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            uname = itemView.findViewById(R.id.uname);
            location = itemView.findViewById(R.id.location);
            catagory = itemView.findViewById(R.id.catagory);
            likes = itemView.findViewById(R.id.likes);
            description = itemView.findViewById(R.id.description);
            comments = itemView.findViewById(R.id.comments);
            publisher = itemView.findViewById(R.id.publisher);
        }
    }
    private void getComments(String postid,TextView comments){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Comments").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comments.setText("See "+snapshot.getChildrenCount() + " Comments");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void isLikes(String postid,ImageView imageView){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("likes").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.child(firebaseUser.getUid()).exists()){
                   imageView.setImageResource(R.drawable.ic_likes);
                   imageView.setTag("liked");
               }else{
                   imageView.setImageResource(R.drawable.ic_baseline_favorite_24);
                   imageView.setTag("like");
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void mrLikes(TextView likes,String postid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("likes").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               likes.setText(snapshot.getChildrenCount()+" likes");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void publisherinfo(final ImageView image_profile,final TextView uname,final TextView publisher,final String userid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                Glide.with(mContext).load(user.getImageurl()).into(image_profile);
                uname.setText(user.getUsername());
                publisher.setText(user.getUsername());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

