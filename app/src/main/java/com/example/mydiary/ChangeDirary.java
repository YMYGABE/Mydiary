package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChangeDirary extends AppCompatActivity implements View.OnClickListener {

    GetDirary dirary;
    MainActivity mainActivity;
    private String ID;
    private String title;
    private String main_context;
    private EditText Title;
    private EditText MainContext;
    private Date date;
    private String time;
    private  Intent intent;
    private SharedPreferences sp;
    private ImageView image;

    private Bitmap bit;
    private List<Dirary> list = new ArrayList<Dirary>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_change_dirary);
        ID = getIntent().getExtras().getString("ID");
        dirary = new GetDirary(ChangeDirary.this);
        Title = (EditText) findViewById(R.id.title);

        MainContext = (EditText) findViewById(R.id.mainContext);
        Button bt_change = (Button) findViewById(R.id.change);
        Button bt_delet = (Button) findViewById(R.id.delet);
        TextView textView = (TextView) findViewById(R.id.author);
        sp = getSharedPreferences("Author",Context.MODE_PRIVATE);
        textView.setText("作者："+sp.getString("name","佚名"));
        bt_change.setOnClickListener(this);
        bt_delet.setOnClickListener(this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        date = new Date(System.currentTimeMillis());
        time = simpleDateFormat.format(date);
        setDirary();
        Title.setText(title);
        MainContext.setText(main_context);
    }
    private void setDirary(){
        list = dirary.inquireData("Dirary",null,"id = ?",new String[]{ID},null,null,null,null);

//        Cursor cursor = db.query("Dirary",null,"id = ?",new String[]{ID},null,null,null,null);
//        cursor.moveToFirst();

        title = list.get(0).getTitle();
        main_context = list.get(0).getMainContext();

//        Log.d("Title",title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change:
                dirary.update(ID,new String[]{Title.getText().toString(),MainContext.getText().toString(),time});
                Toast.makeText(ChangeDirary.this,"修改成功",Toast.LENGTH_SHORT).show();
                intent = new Intent(ChangeDirary.this, MainActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.delet:
                dirary.delData("id = ?",new String[]{ID});
                Toast.makeText(ChangeDirary.this,"删除成功",Toast.LENGTH_SHORT).show();
                intent = new Intent(ChangeDirary.this, MainActivity.class);
                startActivityForResult(intent,1);
                break;
            default:break;
        }
    }
}