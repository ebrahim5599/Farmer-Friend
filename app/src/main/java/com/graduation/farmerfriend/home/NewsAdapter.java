package com.graduation.farmerfriend.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.news_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.textViewNum.setText(String.format("%d", position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNewsTitle;
        TextView textViewNewsSubTitle;
        TextView textViewNum;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewNewsTitle = itemView.findViewById(R.id.txt_news_title);
            textViewNewsSubTitle = itemView.findViewById(R.id.txt_news_sub_title);
            textViewNum = itemView.findViewById(R.id.txt_news_num);
        }

    }
}
