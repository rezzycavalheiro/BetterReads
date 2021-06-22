package com.example.betterreads.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betterreads.R;
import com.example.betterreads.model.BookInfo;
import com.example.betterreads.model.BookModel;

import org.jetbrains.annotations.NotNull;

public class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {
    @NonNull
    @NotNull
    @Override
    public ItemArrayAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        // cria os componentes da lista
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(
                R.layout.recyclerview_item, parent, false
        );
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemArrayAdapter.ViewHolder holder, int position) {
        Log.v("ItemArrayAdapter", "onBindViewHolder " + position);
        BookInfo bookInfo = BookModel.getInstance().bookArray.get(position);
        holder.textTitle.setText(bookInfo.getTitle());
        holder.textAuthor.setText(bookInfo.getAuthor());
        holder.textStatus.setText(bookInfo.getStatus());
    }

    @Override
    public int getItemCount() {
        return BookModel.getInstance().bookArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        TextView textAuthor;
        TextView textStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textAuthor = itemView.findViewById(R.id.textAuthor);
            textStatus = itemView.findViewById(R.id.textStatus);

        }
    }
}
