package com.example.projectinsplayjava.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectinsplayjava.R;
import com.example.projectinsplayjava.domains.Items;;


import java.util.ArrayList;

public class MusicListAdapter  extends RecyclerView.Adapter<MusicListAdapter.MusicViewHolder> {
    ArrayList<Items> items;
    Context context;


    public MusicListAdapter(ArrayList<Items> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MusicListAdapter.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MusicViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicListAdapter.MusicViewHolder holder, int position) {
        Items item = items.get(position);
        holder.titleTxt.setText(item.getTitle());
        holder.singerTxt.setText(item.getSinger());
        Glide.with(context)
                .load(item.getImage())
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(16)))
                .into(holder.coverImage);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, singerTxt;
        ImageView coverImage;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            singerTxt = itemView.findViewById(R.id.singerTxt);
            coverImage = itemView.findViewById(R.id.coverImage);
        }
    }
}
