package com.example.betterreads.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class AddBookInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editTextBookTitle, editTextBookAuthor;
    private Button saveItem;
    Spinner spinner;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_info);

        editTextBookTitle = findViewById(R.id.editTextBookTitle);
        editTextBookAuthor = findViewById(R.id.editTextBookAuthor);
        saveItem = findViewById(R.id.buttonSaveItem);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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

        Intent intent = new Intent(this, StartMenu.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}