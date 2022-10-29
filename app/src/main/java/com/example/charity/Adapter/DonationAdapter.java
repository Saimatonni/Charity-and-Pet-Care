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
import com.example.charity.Model.post;
import com.example.charity.R;

import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder>{
    private Context context;
    private List<post> mpost;

    public DonationAdapter(Context context, List<post> mpost) {
        this.context = context;
        this.mpost = mpost;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.dona_items,parent,false);
        return new DonationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        post pst = mpost.get(position);
        Glide.with(context).load(pst.getPostimage()).into(holder.postimage);
        holder.postimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = context.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                editor.putString("postid",pst.getPostid());
                editor.apply();
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PostDetailsFragment()).commit();

            }
        });
        /*holder.description.setVisibility(View.VISIBLE);
        holder.description.setText(pst.getDescription());
        holder.catagory.setVisibility(View.VISIBLE);
        holder.catagory.setText(pst.getCatagory());*/


    }

    @Override
    public int getItemCount() {
        return mpost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView postimage;
        public TextView catagory,description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postimage = itemView.findViewById(R.id.post_image);
            //catagory = itemView.findViewById(R.id.catagory);
           // description = itemView.findViewById(R.id.description);
        }
    }
}
