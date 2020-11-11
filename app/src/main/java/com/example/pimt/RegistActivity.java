package com.example.pimt;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistActivity extends AppCompatActivity {

    //DBの宣言
    SQLiteDatabase db;

    //自作したクラス
    //MyDBHelper myDBHelper;

    //EditTextから入力された文字、数字を受け取るために変数を宣言
    EditText number;
    EditText name;
    EditText add;
    EditText tel;
    EditText region;
    EditText created_at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_layout);

        /*
        //変数へ代入
        number = (EditText)findViewById(R.id.editText_number);
        name = (EditText)findViewById(R.id.editText_name);
        add = (EditText)findViewById(R.id.editText_add);
        tel = (EditText)findViewById(R.id.editText_tel);
        region = (EditText)findViewById(R.id.editText_region);
        created_at = (EditText)findViewById(R.id.editText_at);

        //データベースヘルパーの生成
        //２番目の引数はデータベースの名前
        myDBHelper = new MyDBHelper(this, "pimt_db", null, 1);
        //データベースの生成
        sqLiteDatabase = myDBHelper.getWritableDatabase();

        //登録ボタンの処理
        /*
        Button registButton = findViewById(R.id.regist_button);
        registButton.setOnClickListener(v -> {

        });

         */


        //戻るボタンの処理
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(v -> finish());

    }

    public void write() {

        //EditTextから入力されたデータの読み取り
        //String getNumber = number.getText().toString();
        String getName = name.getText().toString();
        String getAdd = add.getText().toString();
        String getTel = tel.getText().toString();
        String getRegion = region.getText().toString();
        String getCreatedAt = created_at.getText().toString();

        /*
        //SQL文
        String sql =
                "insert into pimt_db"
                + "(name, address, tel, customer_signature, created_at)"
                + "values('" + getName + "',"
                + getAdd
                + getTel
                + getRegion
                + getCreatedAt
                + ")";
        db.execSQL(sql);

         */

        System.out.println(getName + getAdd + getTel + getRegion + getCreatedAt);

        //挿入完了を通知するためのトースト
        Toast.makeText(this, "登録しました！", Toast.LENGTH_SHORT).show();

        finish();
    }
}
