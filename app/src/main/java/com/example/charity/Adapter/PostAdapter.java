package com.example.charity.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.charity.CommentsActivity;
import com.example.charity.Fragment.PostDetailsFragment;
import com.example.charity.Fragment.ProfileFragment;
import com.example.charity.Model.User;
import com.example.charity.Model.post;
import com.example.charity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
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
        Glide.with(mContext).load(pst.getPostimage()).apply(new RequestOptions().placeholder(R.drawable.praceholder)).into(holder.postimage);
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
        morep(holder.more, pst.getPostid(), pst.getPublisher());
        holder.image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("profileid",pst.getPublisher());
                editor.apply();
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();

            }
        });
        holder.uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("profileid",pst.getPublisher());
                editor.apply();
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();

            }
        });
        holder.publisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("profileid",pst.getPublisher());
                editor.apply();
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();

            }
        });
        holder.postimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("postid",pst.getPostid());
                editor.apply();
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PostDetailsFragment()).commit();

            }
        });
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
        /*holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit:
                                editPost(pst.getPostid());
                                return true;
                            case R.id.delete:
                                FirebaseDatabase.getInstance().getReference("Posts").child(pst.getPostid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(mContext,"Deleted",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                return true;
                            case R.id.report:
                                Toast.makeText(mContext,"reported",Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.inflate(R.menu.post_menu);
                if(!pst.getPublisher().equals(firebaseUser.getUid())){
                    popupMenu.getMenu().findItem(R.id.edit).setVisible(false);
                    popupMenu.getMenu().findItem(R.id.delete).setVisible(false);
                }
                popupMenu.show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mpost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_profile,postimage,like,comment,more;
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
            more = itemView.findViewById(R.id.more);
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
    private void editPost(String postid){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Edit Post");
        EditText editText = new EditText(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(lp);
        alertDialog.setView(editText);
        getText(postid,editText);
        alertDialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<String, Object>hashMap = new HashMap<>();
                hashMap.put("description",editText.getText().toString());
                FirebaseDatabase.getInstance().getReference("Posts").child(postid).updateChildren(hashMap);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
    private void getText(String postid,EditText editText){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts").child(postid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                editText.setText(snapshot.child("description").getValue(String.class));
                // editText.setText(snapshot.getValue(post.class).getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void morep(ImageView more,String postid,String publisher){
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit:
                                editPost(postid);
                                return true;
                            case R.id.delete:
                                FirebaseDatabase.getInstance().getReference("Posts").child(postid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(mContext,"Deleted",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                return true;
                            case R.id.report:
                                Toast.makeText(mContext,"reported",Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.inflate(R.menu.post_menu);
                if(!publisher.equals(firebaseUser.getUid())){
                    popupMenu.getMenu().findItem(R.id.edit).setVisible(false);
                    popupMenu.getMenu().findItem(R.id.delete).setVisible(false);
                }
                popupMenu.show();
            }
        });
    }

}