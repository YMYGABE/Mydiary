package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    WriteDirary writeDirary = new WriteDirary();
    public List<Dirary> list = new ArrayList<Dirary>();
    public int ID;
    GetDirary getDirary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.mainlayout);
        getDirary = new GetDirary(MainActivity.this);
        initDirary();
        DiraryAdapter adapter = new DiraryAdapter(MainActivity.this,R.layout.dirary_item,list);
        /* 获取控件*/
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dirary dirary = list.get(position);
                ChangeDirary changeDirary = new ChangeDirary();
//                Log.d("ID111",String.valueOf(dirary.getId()));
                Intent intent = null;
                intent = new Intent(MainActivity.this, ChangeDirary.class);
                intent.putExtra("ID",String.valueOf(dirary.getId()));
                startActivityForResult(intent,1);
            }
        });
        Button button_1 = (Button)findViewById(R.id.add_button);
        Button button_home = (Button) findViewById(R.id.home);
        button_home.setOnClickListener(this);
        button_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
                Intent intent = new Intent(MainActivity.this, WriteDirary.class);
                startActivityForResult(intent,1);break;
            case R.id.home:
                intent = new Intent(MainActivity.this,Home.class);
                startActivityForResult(intent,1);break;
            default:break;

        }
    }
    private void initDirary(){
        list = getDirary.inquireData("Dirary",null,null,null,null,null,null,null);
    }
}