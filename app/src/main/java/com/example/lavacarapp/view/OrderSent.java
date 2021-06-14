package com.example.lavacarapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lavacarapp.R;

public class OrderSent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sent);
    }

    public void orderPageButton(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }
}