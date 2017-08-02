package com.example.gd001.sqlciphertest;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;


/**
 * Created by gd001 on 17-8-1.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test.db";
    private static final String TABLE_NAME = "person";
    private static final String PASSWORD = "password";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase.loadLibs(context); //first init the db libraries with the context
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("hello", "onCreate");
        this.db = sqLiteDatabase;
        db.execSQL("CREATE TABLE IF NOT EXISTS person" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, sex TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("hello", "onUpgrade");
        System.out.println("update Database");
    }

    //插入方法
    public void insert(ContentValues values){
        Log.d("hello", "insert");
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase(PASSWORD);
        //插入数据库中
        db.insert(TABLE_NAME, null, values);
    }

    //查询方法
    public Cursor query(){
        Log.d("hello", "query");
        SQLiteDatabase db = getReadableDatabase(PASSWORD);
        //获取Cursor
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        return c;
    }

    //根据唯一标识_id  来删除数据
    public void delete(int id){
        Log.d("hello", "delete");
        SQLiteDatabase db = getWritableDatabase(PASSWORD);
        db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});
    }

    //更新数据库的内容
    public void update(ContentValues values, String whereClause, String[]whereArgs){
        Log.d("hello", "update");
        SQLiteDatabase db = getWritableDatabase(PASSWORD);
        db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    //关闭数据库
    public void close(){
        Log.d("hello", "close");
        if(db != null){
            db.close();
        }
    }
}
