package com.graduation.farmerfriend.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TipsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class TipsViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewContent;

        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContent = itemView.findViewById(R.id.tips_container_content_text_view);
            textViewTitle = itemView.findViewById(R.id.tips_container_title_text_view);
        }
    }
}
