package com.example.draggridview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.jar.Attributes;

/**
 * Created by wei on 2016/9/19.
 */
public class MyDataDB extends SQLiteOpenHelper {

    private final static String DATABASE_NAME="sec_db";
    private final static int DATABASE_VERSION=1;
    private final static String TABLE_NAME="sec_pwd";
    public final static String FIELD_ID="_id";
    public final static String FIELD_DATA="sec_Data";
    public final static String FIELD_NAME="sec_Name";
    ContentValues cv=new ContentValues();
    String[] webxml;

    public MyDataDB(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立應用程式需要的表格
        // 待會再回來完成它

        String sql="Create table "+TABLE_NAME+
                "(" +FIELD_ID+" integer primary key autoincrement,"
                +FIELD_DATA+" TEXT, "
                +FIELD_NAME+" TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql=" DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

    public void insert(String Title,String Name)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        cv.put(FIELD_DATA, Title);
        cv.put(FIELD_NAME,Name);
        db.insert(TABLE_NAME, null, cv);
        //Log.d("ADD", row+"");


    }

    public Cursor select()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null,"_id");

        cursor.moveToFirst();
        String str;
        do {
            str = "id" + cursor.getString(0) + "\n";
            str+= "data" + cursor.getString(1) + "\n";
            str+= "name" + cursor.getString(2) + "\n";

            Log.d("1211",str);

        }while (cursor.moveToNext());

        return cursor;
    }

    public boolean del(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Log.d("3333",name);
        return  db.delete(TABLE_NAME, FIELD_NAME+"='"+ name +"';", null) > 0;
       // return db.delete(DATABASE_TABLE, KEY_NAME + "='" + name +"' ;", null) > 0;

    }

    public Cursor getAll()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.query(TABLE_NAME,new String[]{FIELD_ID,FIELD_DATA,FIELD_NAME},null,null,null,
                null,null,null);

    }


}