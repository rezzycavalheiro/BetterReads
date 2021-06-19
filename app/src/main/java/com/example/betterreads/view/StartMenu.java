package com.example.betterreads.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.betterreads.R;
import com.google.firebase.auth.FirebaseAuth;

public class StartMenu extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        firebaseAuth = FirebaseAuth.getInstance();
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

    public void signOutButton(View view){
        firebaseAuth.signOut();
        Toast.makeText(getApplicationContext(),"Usuário saiu.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}