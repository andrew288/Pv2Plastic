package com.example.myfirstapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.RecordItem;
import com.example.myfirstapplication.db.daos.DaoRecordItem;

import java.util.List;


public class AdapterRecordItems extends RecyclerView.Adapter<AdapterRecordItems.ViewHolder>{

    List<RecordItem> recordItems;
    Context mContext;

    public AdapterRecordItems(Context mContext, List<RecordItem> recordItems){
        this.mContext = mContext;
        this.recordItems = recordItems;
    }

    @NonNull
    @Override
    public AdapterRecordItems.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item_single_card_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecordItems.ViewHolder holder, int position) {
        RecordItem recordItemSelected =  recordItems.get(position);
        holder.tvNameElement.setText(recordItemSelected.getName());
        holder.tvTotalGrams.setText(String.valueOf(recordItemSelected.getGrams()));
        holder.tvTotalScore.setText(String.valueOf(recordItemSelected.getScore()));
    }

    @Override
    public int getItemCount() {
        return recordItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNameElement;
        TextView tvTotalGrams;
        TextView tvTotalScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameElement = (TextView) itemView.findViewById(R.id.record_item_name_product);
            tvTotalGrams = (TextView) itemView.findViewById(R.id.record_item_grams_total);
            tvTotalScore = (TextView) itemView.findViewById(R.id.record_item_score_total);
        }
    }
}
