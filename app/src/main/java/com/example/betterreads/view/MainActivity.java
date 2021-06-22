package com.example.betterreads.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.betterreads.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TESTE DE CRASH PARA O CRASHLYTICS
        // COMENTAR APÓS APRESENTAÇÃO DE CRASH
//        Button crashButton = new Button(this);
//        crashButton.setText("Crash!");
//        crashButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                throw new RuntimeException("Test Crash"); // Force a crash
//            }
//        });
//
//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
        //
    }

    public void loginButton(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void registerButton(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}