package com.example.betterreads.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.betterreads.R;
import com.example.betterreads.model.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private EditText nomeUser, email, password, confirmPassword;
    DatabaseReference dbReference;
    String emailValid = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nomeUser = findViewById(R.id.editTextNameRegister);
        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTExtPwdRegister);
        confirmPassword = findViewById(R.id.editTextPwdRepeatRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference().child("Info");
    }

    public void onRegister(View view){ createUser(); }

    void createUser(){

        String getEmail = email.getText().toString().trim();
        String getPassword = password.getText().toString().trim();
        Intent loginScreen = new Intent(this, MainActivity.class);
        String getName = nomeUser.getText().toString();

        UserInfo user = new UserInfo(getName);

        dbReference.push().setValue(user);

        if(nomeUser.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setMessage("Preencher todos os dados para o cadastro.");
            builder.create().show();
        }
        else if(!email.getText().toString().trim().matches(emailValid)){
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setMessage("E-mail inválido!");
            builder.create().show();
        }
        else if(!password.getText().toString().equals(confirmPassword.getText().toString())){
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setMessage("As senha devem ser iguais!");
            builder.create().show();
        }
        else {
            firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener(Register.this,
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Usuário cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                        startActivity(loginScreen);
                    } else {
                        Toast.makeText(getApplicationContext(),"Erro ao criar usuário.", Toast.LENGTH_LONG).show();
                        Log.e("FIREBASE", "Creat error" + task.getException().toString());
                    }
                }
            });
        }
    }
}