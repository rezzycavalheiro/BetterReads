package com.example.betterreads.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betterreads.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Profile extends Fragment {
    private EditText pwdUser;
    private TextView emailUser;
    private Button updateButton, deleteButton;
    DatabaseReference dbReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String firebaseUserEmail;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        pwdUser = view.findViewById(R.id.PwdProfile_editText);
        emailUser = view.findViewById(R.id.EmailRegister_editText);
        updateButton = view.findViewById(R.id.update_button);
        deleteButton = view.findViewById(R.id.delete_button);
        dbReference = FirebaseDatabase.getInstance().getReference("Info");
        firebaseAuth = FirebaseAuth.getInstance();

        Button buttonDelete = view.findViewById(R.id.delete_button);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(v);
            }
        });

        Button buttonChange = view.findViewById(R.id.update_button);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePwd(v);
            }
        });

        Button buttonExit = view.findViewById(R.id.logOut_button);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutButton(v);
            }
        });
        return view;
    }

    public void onStart() {
        super.onStart();

        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            firebaseUserEmail = firebaseUser.getEmail();
            emailUser.setText(firebaseUserEmail);
        } else {
            Toast.makeText(getActivity().getApplicationContext(),"Erro ao carregar usuário:", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteUser(View view) {

        String getPassword = pwdUser.getText().toString().trim();
        Intent startPage = new Intent(getActivity().getApplicationContext(), MainActivity.class);

        if (TextUtils.isEmpty(getPassword)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
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
                                    Toast.makeText(getActivity().getApplicationContext(), "Usuário excluído com sucesso.", Toast.LENGTH_LONG).show();
                                    startActivity(startPage);
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Erro ao deletar usuário.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Senha incorreta.", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getActivity().getApplicationContext(),"E-mail para redefinição de senha enviado.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),"Erro ao enviar e-mail.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void signOutButton(View view){
        firebaseAuth.signOut();
        Toast.makeText(getActivity().getApplicationContext(),"Usuário saiu.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}