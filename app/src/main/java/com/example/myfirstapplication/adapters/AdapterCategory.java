package com.example.myfirstapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.db.Category;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder>{

    List<Category> categories;

    public AdapterCategory(List<Category> categories){
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_single_card_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageCardCategory;
        TextView tvCardCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCardCategory = (ImageView) itemView.findViewById(R.id.category_recycler_image);
            tvCardCategory = (TextView) itemView.findViewById(R.id.category_recycler_text);
        }
    }
}
