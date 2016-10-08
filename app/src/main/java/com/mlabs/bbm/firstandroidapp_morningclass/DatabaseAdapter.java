package com.mlabs.bbm.firstandroidapp_morningclass;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import java.util.HashMap;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class DatabaseAdapter extends SQLiteOpenHelper{
    private static final String TAG = DatabaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FIRST = "first";
    private static final String KEY_SUR = "surname";
    private static final String KEY_USER = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CREATED_AT = "created_at";
    static final String DATABASE_CREATE = "create table " + "LOGIN" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "Email  text,Password text); ";
    public SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME,  null,    DATABASE_VERSION);
    }


    public DatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDb(){
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_FIRST + " TEXT, " +
                KEY_SUR + " TEXT, " +
                KEY_USER+ " TEXT UNIQUE, " +
                KEY_EMAIL + " TEXT UNIQUE, " +
                KEY_PASSWORD + " TEXT, " +
                KEY_CREATED_AT + " TEXT )";
        db.execSQL(CREATE_USER_TABLE);
        Log.d(TAG,"Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void registeruser(String first, String sur, String username, String email, String password, String created_at){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST,first);
        values.put(KEY_SUR,sur);
        values.put(KEY_USER,username);
        values.put(KEY_EMAIL,email);
        values.put(KEY_PASSWORD,password);
        values.put(KEY_CREATED_AT,created_at);

        long id  = db.insert(TABLE_USER,null, values);
        db.close();
        Log.d(TAG, "User added successfully!");
    }


    public boolean validateUser(String username/*,String email*/,String password) {

        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER + " = '" + username + "'  or " + KEY_EMAIL + " = '" + username + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        System.out.println(username + "from editext");
        System.out.println(password + "from edittext");

         cursor.moveToFirst();
        //if (cursor.moveToFirst()){
        //    System.out.println(cursor.getString(3));
        //}


        while (!cursor.isAfterLast()) {
            //System.out.println(cursor.getString(1));
            //System.out.println(cursor.getString(2));
           // System.out.println(cursor.getString(3));
           // System.out.println(cursor.getString(4));
            //System.out.println(cursor.getString(5));
            //System.out.println(cursor.getString(6));
            String passworddb= cursor.getString(5);
            cursor.moveToNext();
            if(password.equals(passworddb)){
                return true;
            }
            else{
                return false;
            }
        }
      cursor.close();

        return false;
    }
    public String getUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER + " = '" + username + "'  or " + KEY_EMAIL + " = '" + username + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 1) {
            cursor.close();
            return "NOT EXIST";
            }
            cursor.moveToFirst();
            String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
            return password;

            }


    public long insertData (String UsernameReg, String PasswordReg, String EmailReg)
    {
        ContentValues values = new ContentValues();
        values.put("USERNAME", UsernameReg);
        values.put("PASSWORD", PasswordReg);
        values.put("EMAIL", EmailReg);

        return db.insertWithOnConflict(DATABASE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

    }

    public String getData() {
        return null;
    }

    public boolean checkExist(String username, String email){
        String qry = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER +" = ' " +username+ "'  or "+ KEY_EMAIL + " = '" + email +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry,null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0){
            return true;
        }
        else{
            return false;
        }
    }


    public  void deleteUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user from sqlite");
    }
}


