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
import com.example.charity.Fragment.ProfileFragment;
import com.example.charity.Model.Notification;
import com.example.charity.Model.User;
import com.example.charity.Model.post;
import com.example.charity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    private Context mcontext;
    private List<Notification> mNotification;
   // private List<post> mpost;

    public NotificationAdapter(Context mcontext, List<Notification> mNotification) {
        this.mcontext = mcontext;
        this.mNotification = mNotification;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.motification_item,parent,false);
        return new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = mNotification.get(position);
       // post pst = mpost.get(position);
        holder.text.setText(notification.getText());
        if(mcontext==null){
            return;
        }
        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        getUserInfo(holder.image_profile,holder.username,notification.getUserid());
        if (notification.isIspost()){
            holder.postimage.setVisibility(View.VISIBLE);
            getPostImage(holder.postimage,notification.getPostid());
        }else {
            holder.postimage.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notification.isIspost()){
                    SharedPreferences.Editor editor = mcontext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                    editor.putString("postid",notification.getPostid());
                    editor.apply();
                    ((FragmentActivity)mcontext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PostDetailsFragment()).commit();
                }/*else{
                    SharedPreferences.Editor editor = mcontext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                    editor.putString("profileid",notification.getUserid());
                    editor.apply();
                    ((FragmentActivity)mcontext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                }*/
            }
        });
        holder.image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mcontext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("profileid",notification.getUserid());
                editor.apply();
                ((FragmentActivity)mcontext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotification.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image_profile, postimage;
        public TextView username,text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_profile = itemView.findViewById(R.id.image_profile);
            postimage = itemView.findViewById(R.id.post_image);
            username = itemView.findViewById(R.id.username);
            text = itemView.findViewById(R.id.comment);

        }
    }
    private void getUserInfo(final ImageView imageView,final TextView username,String publisherid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(publisherid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               User user = snapshot.getValue(User.class);
               if(user!=null){
                   Glide.with(mcontext).load(user.getImageurl()).into(imageView);
                   username.setText(user.getUsername());
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getPostImage(final ImageView imageView, String postid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               post pst = snapshot.getValue(post.class);
               if(pst!=null){
                   Glide.with(mcontext).load(pst.getPostimage()).into(imageView);
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
