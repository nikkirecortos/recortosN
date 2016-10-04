package com.mlabs.bbm.firstandroidapp_morningclass;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import java.util.HashMap;
import android.database.Cursor;



public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final String TAG= DatabaseAdapter.class.getSimpleName();
    private static final String DATABASE_NAME= "user.db";
    private static final int DATABASE_VERSION= 1;
    private static final String TABLE_USER= "user";
    private static final String KEY_ID= "id";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_PASSWORD= "password";
    private static final String KEY_CREATED_AT= "created at";

    static final String DATABASE_CREATE = "create table" + "LOGIN" + "(" + "ID" + "integer primary key autoincrement," + "Email text, password text);";

    public SQLiteDatabase db;
    private final Context context;
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context _context){
        super(_context,DATABASE_NAME,null, DATABASE_VERSION);
        context= _context;
        dbHelper = new DatabaseHelper (_context, DATABASE_NAME,null, DATABASE_VERSION);

    }

    public DatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }
    public SQLiteDatabase getDb(){
        return db;
    }

   @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE= "CREATE TABLE" + TABLE_USER + "("
                + KEY_ID + "INTEGER PRIMARY KEY"
                + KEY_EMAIL + "TEXT UNIQUE"
                + KEY_PASSWORD + "TEXT," + KEY_CREATED_AT + "TEXT" + ")";
       db.execSQL(CREATE_USER_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);
        onCreate(db);
    }

    public  void registerUser(String email, String password, String created_at){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_EMAIL,email);
        values.put(KEY_PASSWORD,password);
        values.put(KEY_CREATED_AT,created_at);

        Long id = db.insert(TABLE_USER,null, values);
        db.close();

        Log.d(TAG, "Successfully added user: " + id);

    }

    public boolean validateUser(String userName, String password){
        HashMap<String,String> user= new HashMap<String,String>();
        String selectQuery = "SELECT email, password FROM" + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + userName+ "\" AND + KEY_PASSWORD + |=\""+password+ "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = db.rawQuery(selectQuery,null);
        boolean result = false;
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            user.put(KEY_EMAIL, cursor.getString(1));
            user.put(KEY_PASSWORD, cursor.getString(2));
            //user.put("created_at", cursor.getString(3));
           result= true;
        }
        else{
            result =  false;
        }
        cursor.close();
        db.close();


        Log.d(TAG, "Fetching user for SQLite" + user.toString());
        if(password.equals(user.get(password))){
            Log.d(TAG, "Password was validated!");
            return true;
        }
        else{
            Log.d(TAG, "Password was incorrect");
            return false;
        }
    }


}
