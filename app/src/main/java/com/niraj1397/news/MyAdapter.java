package com.niraj1397.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.niraj1397.news.parameter.Articles;


import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DataViewHolder> {

    Context mContext;
    List<Articles> articlesList;

    public MyAdapter(Context mContext, List<Articles> articlesList) {
        this.mContext = mContext;
        this.articlesList = articlesList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        final Articles articles = articlesList.get(position);
        String url=articles.getUrl();
        holder.newsTitle.setText(articles.getTitle());
        holder.newsDate.setText(articles.getPublishedAt());
        String imageUrl=articles.getUrlToImage();
        //Picasso.get().load(imageUrl).into(holder.imageView);
        Glide.with(mContext).load(imageUrl).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,NewsINDetail.class);
                intent.putExtra("url",articles.getUrl());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle,newsDate;
        ImageView imageView;
        CardView cardView;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            imageView = itemView.findViewById(R.id.image1);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
   /* public String getCountry(){
        Locale locale = Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }*/
}
