package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity implements View.OnClickListener {

    protected EditText editText;
    protected TextView textView;
    private final static String SP_INFO = "Author";
    private final static String NAME = "name";

    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_home);
        sp =  getSharedPreferences("Author", Context.MODE_PRIVATE);
        editor = sp.edit();
        editText = (EditText) findViewById(R.id.author_name);
        textView = (TextView) findViewById(R.id.author);
        textView.setText("本日记作者："+sp.getString(NAME,"本日记作者：佚名"));
        Button button_submit = (Button) findViewById(R.id.Sumbit_author);
        button_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Sumbit_author:
                String name = editText.getText().toString();
                editor.putString(NAME,name);
                editor.commit();
                textView.setText("本日记作者："+sp.getString(NAME,"佚名"));
        }
    }
}