package com.graduation.farmerfriend.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.Tips;

import java.util.ArrayList;
import java.util.HashMap;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {
    ArrayList<Tips> tips;

    public TipsAdapter(ArrayList<Tips> tips) {
        this.tips = tips;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TipsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        if (tips.get(position) != null) {
            holder.textViewTitle.setText(tips.get(position).getHeader());
            holder.textViewContent.setText(tips.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public static class TipsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewContent;

        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContent = itemView.findViewById(R.id.tips_container_content_text_view);
            textViewTitle = itemView.findViewById(R.id.tips_container_title_text_view);
        }
    }
}
