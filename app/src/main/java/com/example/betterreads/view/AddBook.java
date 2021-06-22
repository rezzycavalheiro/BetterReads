package com.example.betterreads.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.betterreads.R;
import com.example.betterreads.model.BookInfo;
import com.example.betterreads.model.BookModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AddBook extends Fragment implements AdapterView.OnItemSelectedListener{

    EditText editTextBookTitle, editTextBookAuthor;
    private Button saveItem;
    Spinner spinner;
    DatabaseReference dbReference;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book,container,false);
        editTextBookTitle = view.findViewById(R.id.editTextBookTitle);
        editTextBookAuthor = view.findViewById(R.id.editTextBookAuthor);
        saveItem = view.findViewById(R.id.buttonSaveItem);

        spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        saveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSaveItemOnClick(v);
            }
        });

        return view;
    }

    public void buttonSaveItemOnClick(View view) {
        String itemTitle = editTextBookTitle.getText().toString();
        String itemAuthor = editTextBookAuthor.getText().toString();
        String itemStatus = spinner.getSelectedItem().toString();

        dbReference = FirebaseDatabase.getInstance().getReference().child("Livro").push();
        Map<String, Object> values = new HashMap<>();
        values.put("Título", itemTitle);
        values.put("Autor", itemAuthor);
        values.put("Status", itemStatus);
        dbReference.setValue(values);

        BookModel.getInstance().bookArray.add(new BookInfo(itemTitle, itemAuthor, itemStatus));

        Intent intent = new Intent(getActivity().getApplicationContext(), StartMenu.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}