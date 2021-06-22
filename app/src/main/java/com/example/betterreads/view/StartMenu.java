package com.example.betterreads.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.betterreads.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class StartMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        BottomNavigationView btm_Nav = findViewById(R.id.bottomNavigationView);
        btm_Nav.setOnNavigationItemSelectedListener(navListner);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.addBook:
                    selectedFragment = new AddBook();
                    break;
                case R.id.addCover:
                    selectedFragment = new AddCover();
                    break;
                case R.id.perfil:
                    selectedFragment = new Profile();
                    break;
                case R.id.home:
                    selectedFragment = new MainMenu();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selectedFragment).commit();
            return true;
        }
    };
}