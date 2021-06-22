package com.example.betterreads.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betterreads.R;
import com.example.betterreads.model.PhotoModel;
import com.example.betterreads.model.Pictures;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Pictures> picPath;

    public RecyclerViewAdapter(Context context, List<Pictures> picPath) {
        this.context = context;
        this.picPath = picPath;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_pic_cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        Pictures pictures = PhotoModel.getInstance().picturesArrayList.get(position);
        holder.pic.setImageBitmap(BitmapFactory.decodeFile(pictures.getPicturePath()));
    }

    @Override
    public int getItemCount() {
        return PhotoModel.getInstance().picturesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.car_pic);
        }
    }
}
