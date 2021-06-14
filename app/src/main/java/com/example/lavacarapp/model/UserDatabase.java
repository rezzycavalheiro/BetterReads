package com.example.lavacarapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper {

    private static UserDatabase INSTANCE_CONNECTION;

    private static final String DB_NAME = "user.sqlite";
    private static final int DB_VERSION = 1;
    public static final String DB_TABLE = "User";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "nome";
    public static final String COL_PHONE = "telefone";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";

    /*
    CREATE
    RETRIEVE
    UPDATE
    DELETE
    CRUD
     */

    public UserDatabase (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static UserDatabase getInstance(Context context) {
        if (INSTANCE_CONNECTION == null) {
            INSTANCE_CONNECTION = new UserDatabase(context);
        }
        return INSTANCE_CONNECTION;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = String.format("CREATE TABLE IF NOT EXISTS %s("+
                " %s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                " %s TEXT, " +
                " %s TEXT, " +
                " %s TEXT, " +
                " %s TEXT);",DB_TABLE,COL_ID,COL_NAME,COL_PHONE,COL_EMAIL,COL_PASSWORD);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query;
        query = String.format("DROP TABLE IF EXISTS " + DB_TABLE);
        db.execSQL(query);
        onCreate(db);
    }
}
