package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class WriteDirary extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar = Calendar.getInstance();
    public DateBase_Diary dateBase_diary;
    public SQLiteDatabase db;
    public List<Dirary> DiraryList = new ArrayList<Dirary>();
    private EditText editText_title;
    private EditText editText_main;
    private int TheDay;
    private String SetDay;
    private String T_text;
    private String M_text;
    private Date date;
    private String time;
    private ImageView image;
    private GetDirary dirary;
    private SharedPreferences sp;
    private Bitmap bit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        /*控件获取*/
        TextView textview_time =(TextView) findViewById(R.id.SetTime);
        TextView textView_author = (TextView) findViewById(R.id.author);
        sp = getSharedPreferences("Author",Context.MODE_PRIVATE);
        textView_author.setText("\t\t\t\t作者："+sp.getString("name","佚名"));
        editText_title = (EditText) findViewById(R.id.title);
        editText_main = (EditText) findViewById(R.id.mainContext);
        image = (ImageView) findViewById(R.id.select_image);
        Button button_save = (Button) findViewById(R.id.save);
        Button button_addImage = (Button) findViewById(R.id.image);
//        Button button_camera = (Button) findViewById(R.id.camera);
        button_addImage.setOnClickListener(this);
        button_save.setOnClickListener(this);
//        button_camera.setOnClickListener(this);
        /*数据库*/
        dirary = new GetDirary(WriteDirary.this);
        /*时间获取*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        date = new Date(System.currentTimeMillis());
        time = simpleDateFormat.format(date);
        TheDay = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        switch (TheDay){
            case Calendar.SUNDAY:
                SetDay = "周日";break;
            case Calendar.MONDAY:
                SetDay = "周一";break;
            case Calendar.TUESDAY:
                SetDay = "周二";break;
            case Calendar.WEDNESDAY:
                SetDay = "周三";break;
            case Calendar.THURSDAY:
                SetDay = "周四";break;
            case Calendar.FRIDAY:
                SetDay = "周五";break;
            case Calendar.SATURDAY:
                SetDay = "周六";break;
        }
        textview_time.setText(simpleDateFormat.format(date) + "\t"+ "\t"+ "\t"+ "\t"+SetDay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                final ByteArrayOutputStream os = new ByteArrayOutputStream();
                bit.compress(Bitmap.CompressFormat.PNG, 100, os);

                dirary.addData("Dirary", new String[]{"Title", "MainContent", "Time"},
                        new String[]{editText_title.getText().toString(),editText_main.getText().toString(),time},os.toByteArray());
                Intent intent = new Intent(WriteDirary.this, MainActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.image:
                intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, 0x1);
                break;
            default:break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0x1) {
            if(data == null){
                return;
            }
            Uri u = data.getData();

            ContentResolver cr = WriteDirary.this.getContentResolver();
            try {
                bit = BitmapFactory.decodeStream(cr.openInputStream(u));
                image.setImageBitmap(bit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}