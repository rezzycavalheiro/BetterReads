package com.example.betterreads.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.betterreads.R;
import com.example.betterreads.model.BookModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class StartMenu extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    ItemArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ItemArrayAdapter();
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(StartMenu.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void addBookInfoButton(View view){
        Intent intent = new Intent(this, AddBookInfo.class);
        startActivity(intent);
    }

    public void addBookPicButton(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    public void myProfileButton(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}