package com.example.betterreads.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.betterreads.R;
import com.example.betterreads.model.Pictures;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddCover extends Fragment {
    List<Pictures> carPaths;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book,container,false);
        recyclerView = view.findViewById(R.id.rv_CoverBooks);
        adapter = new RecyclerViewAdapter(view.getContext(), carPaths);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext().getApplicationContext(), 2));
        recyclerView.setAdapter(adapter);

        Button button = view.findViewById(R.id.add_pic_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraButton(v);
            }
        });

        return view;
    }

    // BOTÃO PARA ADICIONAR FOTOS
    public void openCameraButton(View view){
        Intent intent = new Intent(getActivity().getApplicationContext(), CameraPage.class);
        startActivity(intent);
    }
}