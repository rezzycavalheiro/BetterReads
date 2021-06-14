package com.example.lavacarapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lavacarapp.R;
import com.example.lavacarapp.model.CarPictures;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends AppCompatActivity {

    private TextView cleanLevelText;
    private ProgressBar cleanLevelProgress;
    private SeekBar cleanLevelSeekBar;
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

        cleanLevelText = findViewById(R.id.text_clean_level);
        cleanLevelProgress = findViewById(R.id.progressBar_clean_level);
        cleanLevelSeekBar = findViewById(R.id.seekBar_clean_level);

        cleanLevelProgress.setMax(3);
        cleanLevelSeekBar.setMax(2);

        // SEEKBAR COM NO MÁXIMO 2 PONTOS DE PROGRESSO E
        // PROGRESSBAR COM NO MÁXIMO VALOR 3
        cleanLevelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // SEEKBAR E PROGRESSBAR COMEÇANDO EM 1 (PRIMEIRO NÍVEL)
                progress = progress + 1;
                cleanLevelProgress.setProgress(progress);
                cleanLevelText.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // BOTÃO PARA ADICIONAR FOTOS
    public void openCameraButton(View view){
        Intent intent = new Intent(this, CameraPage.class);
        startActivity(intent);
    }

    // BOTÃO PARA ENVIAR PEDIDO
    public void sendOrderButton(View view){
        Intent intent = new Intent(this, OrderSent.class);
        startActivity(intent);
    }

    // POP UP DE INFORMAÇÕES
    public void popUpInforButton(View view){
        Intent intent = new Intent(this, PopUpInfo.class);
        startActivity(intent);
    }

    // BOTÃO PARA VOLTAR PARA TELA DE MENU
    public void menuPageButton(View view){
        Intent intent = new Intent(this, StartMenu.class);
        startActivity(intent);
    }
}