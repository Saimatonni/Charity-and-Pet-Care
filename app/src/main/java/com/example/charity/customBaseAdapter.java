package com.example.charity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customBaseAdapter extends BaseAdapter {
    Context context;
    String pets[];
    int images[];
    LayoutInflater inflater;
    public customBaseAdapter(Context cts, String [] petlist, int [] imagelist){
        this.context = cts;
        this.pets = petlist;
        this.images=imagelist;
        inflater=LayoutInflater.from(cts);
    }
    @Override
    public int getCount() {
        return pets.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_vet, null);
        TextView txtview = (TextView) view.findViewById(R.id.ptextview);
        ImageView imgview = (ImageView) view.findViewById(R.id.imageicon);
        txtview.setText(pets[i]);
        imgview.setImageResource(images[i]);
        return view;
    }
}
