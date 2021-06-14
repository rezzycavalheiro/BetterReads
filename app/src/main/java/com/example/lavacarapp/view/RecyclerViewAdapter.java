package com.example.lavacarapp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lavacarapp.R;
import com.example.lavacarapp.model.CarModel;
import com.example.lavacarapp.model.CarPictures;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<CarPictures> carPath;

    public RecyclerViewAdapter(Context context, List<CarPictures> carPath) {
        this.context = context;
        this.carPath = carPath;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_pic_cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        CarPictures carPictures = CarModel.getInstance().carsArray.get(position);
        holder.carPic.setImageBitmap(BitmapFactory.decodeFile(carPictures.getCarPath()));
    }

    @Override
    public int getItemCount() {
        return CarModel.getInstance().carsArray.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView carPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carPic = (ImageView) itemView.findViewById(R.id.car_pic);
        }
    }
}
