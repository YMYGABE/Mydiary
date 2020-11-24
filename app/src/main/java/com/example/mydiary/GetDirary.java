package com.example.mydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GetDirary {
    private DateBase_Diary du;
    private SQLiteDatabase db;

    public GetDirary(Context context){
        du = new DateBase_Diary(context);
        db = du.getWritableDatabase();
    }
    /**
    * 添加数据
    * */
    public void addData(String tableName,String[] key,String[] values,byte[] image){
        ContentValues contentValues = new ContentValues();
        for(int i = 0; i < key.length; i ++){
            contentValues.put(key[i],values[i]);
        }
        contentValues.put("Image",image);
        db.insert(tableName,null,contentValues);
        contentValues.clear();
    }

    /**
     * 删除数据
     * */
    public int delData(String where,String[] values){
        int del_data;
        del_data = db.delete("Dirary",where,values);
        return del_data;
    }

    /**
     * 修改数据
     * */
    public void update(String ID,String[] values){
        db.execSQL("update Dirary set Title=?,MainContent=?,Time=? where id="+ID,values);
    }

    /**
     * 查询数据
     * */
    public List<Dirary> inquireData(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy,String limit){
        List<Dirary> list = new ArrayList<Dirary>();
        Cursor cursor = db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy,limit);
        if(cursor.moveToFirst()){
            list.clear();
            do{
                int ID = cursor.getInt(cursor.getColumnIndex("id"));
                String titl = cursor.getString(cursor.getColumnIndex("Title"));
                String context = cursor.getString(cursor.getColumnIndex("MainContent"));
                byte[] in = cursor.getBlob(cursor.getColumnIndex("Image"));
                String time = cursor.getString(cursor.getColumnIndex("Time"));
                Dirary dirary = new Dirary(ID,titl,context,in,time);
                list.add(dirary);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 关闭数据库连接
     * */
    public void getClose(){
        if(db != null){
            db.close();
        }
    }
}
