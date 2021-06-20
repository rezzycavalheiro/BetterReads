package com.example.betterreads.dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.betterreads.model.UserDatabase;
import com.example.betterreads.model.UserInfo;

import java.util.ArrayList;

import static com.example.betterreads.model.UserDatabase.COL_EMAIL;
import static com.example.betterreads.model.UserDatabase.COL_ID;
import static com.example.betterreads.model.UserDatabase.COL_NAME;
import static com.example.betterreads.model.UserDatabase.COL_PASSWORD;
import static com.example.betterreads.model.UserDatabase.COL_PHONE;
import static com.example.betterreads.model.UserDatabase.DB_TABLE;

public class DaoUser {

    private final UserDatabase conexaoSQLite;

    public DaoUser(UserDatabase conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }


    public long createUserInDB(UserInfo user) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_NAME, user.getNome());
            values.put(COL_EMAIL, user.getEmail());
            values.put(COL_PASSWORD, user.getPassword());
            long id = db.insert(DB_TABLE, null, values);
            db.insert(DB_TABLE, null, values);
            db.close();
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
            db.close();
        }
        return 0;
    }

    // RETRIEVE - O USUÁRIO LOGADO DO BD
    public UserInfo getUser(String email) {
        String[] columns = {COL_NAME, COL_EMAIL, COL_PHONE, COL_PASSWORD};
        SQLiteDatabase db = conexaoSQLite.getReadableDatabase();

        String selection = COL_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(DB_TABLE, columns, selection,
                selectionArgs, null, null, null, null);

        UserInfo c = null;

        try {
            if (cursor.moveToFirst()) {
                String nome = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String emailUser = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD));

                c = new UserInfo(nome, emailUser, password);
            }
            db.close();
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
            db.close();
        }
        return c;
    }

    // UPDATE - ALTERAR DADOS NO BD
    public int updateUserInDB(String email) {
        SQLiteDatabase db = conexaoSQLite.getReadableDatabase();
        int count = 0;

        try {
            ContentValues values = new ContentValues();
            count = db.update(DB_TABLE, values, COL_EMAIL + "= ?", new String[]{email});
            db.close();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            db.close();
        }
        return count;
    }


    //DELETE - DELETAR DADOS DO BD
    public void deleteUserInDB(String email) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        try {
            db.delete(DB_TABLE, COL_EMAIL + " = ?", new String[]{email});
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
    }


    // MÉTODOS PARA LOGIN

    // CHECAR SE EXISTE USUÁRIO + EMAIL PARA LOGIN
    public boolean checkUser(String email, String password) {
        String[] columns = {COL_ID};
        SQLiteDatabase db = conexaoSQLite.getReadableDatabase();
        try {
            String selection = COL_EMAIL + " = ?" + " AND " + COL_PASSWORD + " = ?";
            String[] selectionArgs = {email, password};
            Cursor cursor = db.query(DB_TABLE, columns, selection,
                    selectionArgs, null, null, null);
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();
            if (cursorCount > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            db.close();
        }
        return false;
    }
}
