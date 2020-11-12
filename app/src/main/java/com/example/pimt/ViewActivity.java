package com.example.pimt;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    private EditText inputNumber;
    private TextView textView;

    //DBの宣言
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);



    }

    public void view(View view) {
        //入力番号の取得

        //もし、入力されていたら。
    }

    public void returnButton(View view) {
        finish();
    }
}
