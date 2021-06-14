package com.example.lavacarapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.lavacarapp.R;
import com.example.lavacarapp.controller.UserCtrl;
import com.example.lavacarapp.model.UserDatabase;
import com.example.lavacarapp.model.UserInfo;

public class Login extends AppCompatActivity {

    private EditText editTextTextEmailAddress;
    private EditText editTextTextPassword;
    private UserCtrl userCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        userCtrl = new UserCtrl(UserDatabase.getInstance(this));
    }

    public void onClickLogin(View view){ checkLogin(); }

    public void checkLogin() {

        UserDatabase.getInstance(this);

        UserInfo user = new UserInfo();

        String userEmail = editTextTextEmailAddress.getText().toString().trim();
        String userPwd = editTextTextPassword.getText().toString().trim();

        user.setEmail(userEmail);
        user.setPassword(userPwd);

        if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPwd)){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("E-mail ou senha vazios.");
            builder.create().show();
        }
        else if (userCtrl.checkUser(user)) {
            Intent loggedIn = new Intent(this, StartMenu.class);
            loggedIn.putExtra("EMAIL", editTextTextEmailAddress.getText().toString().trim());
            cleanInput();
            startActivity(loggedIn);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("E-mail ou senha incorretos.");
            builder.create().show();
        }
    }

    private void cleanInput() {
        editTextTextEmailAddress.setText("");
        editTextTextPassword.setText("");
    }
}