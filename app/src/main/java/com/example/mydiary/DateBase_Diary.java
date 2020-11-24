package com.example.mydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DateBase_Diary extends SQLiteOpenHelper {
    public static final String CREATE_DIARY = "create table Dirary ("
            + "id integer primary key autoincrement,"
            +"Title text,"
            +"MainContent text,"
            +"Image BLOB,"
            +"Time text)";
    private Context mContext;
    private static final String DATABASE_NAME = "Dirary.db";  //数据库名
    private static final int DATABASE_VERSION = 5;               //数据库版本号
    public DateBase_Diary(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
        Toast.makeText(mContext,"Success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Dirary");
        onCreate(db);
    }
}
