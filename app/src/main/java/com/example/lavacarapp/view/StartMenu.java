package com.example.lavacarapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lavacarapp.R;

public class StartMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);


    }

    public void orderButton(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    public void myProfileButton(View view){
        String userEmail = getIntent().getStringExtra("EMAIL");
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("EMAIL", userEmail);
        startActivity(intent);
    }
}