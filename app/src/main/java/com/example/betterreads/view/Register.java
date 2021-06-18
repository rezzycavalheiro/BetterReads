package com.example.betterreads.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.betterreads.R;
import com.example.betterreads.controller.UserCtrl;
import com.example.betterreads.model.UserDatabase;
import com.example.betterreads.model.UserInfo;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private EditText nomeUser, phoneUser, email, password, confirmPassword;
    private Button register;
    DatabaseReference dbReference;
    String emailValid = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nomeUser = findViewById(R.id.nome_user);
        phoneUser = findViewById(R.id.telefone_user);
        email = findViewById(R.id.editTextTextEmailAddress3);
        password = findViewById(R.id.editTextTextPassword3);
        confirmPassword = findViewById(R.id.editTextTextPassword4);
        register = findViewById(R.id.update_button);
        firebaseAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference().child("Info");

        register.setOnClickListener(v -> {
            UserInfo user = new UserInfo();
            user.setNome(nomeUser.getText().toString());
            user.setTelefone(phoneUser.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());

            UserCtrl userCtrl = new UserCtrl(UserDatabase.getInstance(this));
            long newUser = userCtrl.salvarUser(user);

            if(nomeUser.getText().toString().isEmpty() || phoneUser.getText().toString().isEmpty()
                    || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()
                    || confirmPassword.getText().toString().isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setMessage("Preencher todos os dados para o cadastro.");
                builder.create().show();
            }
            else if(!email.getText().toString().trim().matches(emailValid)){
                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setMessage("E-mail inválido!");
                builder.create().show();
            }
            else if (newUser > 0){
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                Intent registered = new Intent(this, MainActivity.class);
                startActivity(registered);
            } else {
                Toast.makeText(this, "Falha ao cadastrar usuário.", Toast.LENGTH_LONG).show();
            }
        });
    }
}