package com.example.betterreads.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.betterreads.R;
import com.example.betterreads.model.CarPictures;

import java.util.List;

public class OrderPage extends AppCompatActivity {

    List<CarPictures> carPaths;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        recyclerView = findViewById(R.id.recyclerview_car);
        adapter = new RecyclerViewAdapter(this, carPaths);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }

    // BOTÃO PARA ADICIONAR FOTOS
    public void openCameraButton(View view){
        Intent intent = new Intent(this, CameraPage.class);
        startActivity(intent);
    }
}