package com.example.hamirlir.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "foodapp.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;

    public DBHelper(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
        ctx = context;
    }

    public void CopyDataBaseFromAsset() throws IOException {
        InputStream myInput = ctx.getAssets().open(DBNAME);
// Path to the just created empty db
        String outFileName = getDatabasePath();

// if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

// Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

// transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
// Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DBNAME;
    }
    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DBNAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
//        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
//        MyDB.execSQL("create Table categories(_id Integer PRIMARY KEY, name TEXT, urlImg TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
//        MyDB.execSQL("drop Table if exists users");
//        MyDB.execSQL("drop Table if exists categories");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long results = MyDB.insert("users", null, contentValues);

        if(results == -1) return false;
        else
            return true;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username=?", new String[] {username});
        if (cursor.getCount() > 0)
            return true;
        else
            return  false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String [] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    /////////////     To get data from table food_kategori       ///////////////////////
    public Cursor viewData() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query = "Select kat_id,kategory_name,imgUrl from food_category";
        Cursor cursor = MyDB.rawQuery(query,null);
        return cursor;
    }

    /////////////     To get data from table dishes       ///////////////////////
    public Cursor viewDataDish() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query = "Select dishName,dishImgUrl,price,is_preferred from dishes where is_preferred=1";
        Cursor cursor = MyDB.rawQuery(query, null);
        return cursor;
    }

    public Cursor viewDataPreferred(){
        int preferred = 1;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query = "Select dishName,dishImgUrl,price,is_preferred from dishes";
        Cursor cursor = MyDB.rawQuery(query, null);
        return cursor;
    }
}
