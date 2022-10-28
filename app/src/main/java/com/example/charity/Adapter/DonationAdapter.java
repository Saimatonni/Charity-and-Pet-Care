package com.example.charity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        Glide.with(context).load(pst.getPostimage()).into(holder.post_image);
        holder.description.setVisibility(View.VISIBLE);
        holder.description.setText(pst.getDescription());
        holder.catagory.setVisibility(View.VISIBLE);
        holder.catagory.setText(pst.getCatagory());


    }

    @Override
    public int getItemCount() {
        return mpost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView post_image;
        public TextView catagory,description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post_image = itemView.findViewById(R.id.post_image);
            catagory = itemView.findViewById(R.id.catagory);
            description = itemView.findViewById(R.id.description);
        }
    }
}
