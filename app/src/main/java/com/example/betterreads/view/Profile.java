package com.example.betterreads.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betterreads.R;
import com.example.betterreads.controller.UserCtrl;
import com.example.betterreads.model.UserDatabase;
import com.example.betterreads.model.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;


public class Profile extends AppCompatActivity {

    private EditText pwdUser;
    private TextView emailUser;
    private Button updateButton, deleteButton;
    DatabaseReference dbReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String firebaseUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pwdUser = findViewById(R.id.editTextPwdProfile);
        emailUser = findViewById(R.id.editTextEmailRegister);
        updateButton = findViewById(R.id.update_button);
        deleteButton = findViewById(R.id.delete_button);
        dbReference = FirebaseDatabase.getInstance().getReference("Info");
        firebaseAuth = FirebaseAuth.getInstance();

        }

    protected void onStart() {
        super.onStart();

        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            firebaseUserEmail = firebaseUser.getEmail();
            emailUser.setText(firebaseUserEmail);
        } else {
            Toast.makeText(getApplicationContext(),"Erro ao carregar usuário:", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteUser(View view) {

        String getPassword = pwdUser.getText().toString().trim();
        Intent startPage = new Intent(this, MainActivity.class);

        if (TextUtils.isEmpty(getPassword)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
            builder.setMessage("Digite sua senha para excluir o perfil.");
            builder.create().show();
        } else {
            AuthCredential credential = EmailAuthProvider.getCredential(firebaseUser.getEmail(), getPassword);
            firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Usuário excluído com sucesso.", Toast.LENGTH_LONG).show();
                                    startActivity(startPage);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Erro ao deletar usuário.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Senha incorreta.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void changePwd (View view) {
        firebaseAuth.sendPasswordResetEmail(firebaseUserEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"E-mail para redefinição de senha enviado.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Erro ao enviar e-mail.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void signOutButton(View view){
        firebaseAuth.signOut();
        Toast.makeText(getApplicationContext(),"Usuário saiu.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
