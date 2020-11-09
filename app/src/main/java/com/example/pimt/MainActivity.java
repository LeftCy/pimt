package com.example.pimt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //変数へ代入
        /*
        editText1 = (EditText)findViewById(R.id.)

         */

        System.out.println("Hello World!");

    }

    //登録ボタン
    public void regist(View view) {
        Intent intent = new Intent(getApplication(), RegistActivity.class);
        startActivity(intent);
    }

    //データ表示ボタン
    public void view(View view) {
        Intent intent = new Intent(getApplication(), ViewActivity.class);
        startActivity(intent);
    }

    //データ削除ボタン
    public void delete(View view) {
        Intent intent = new Intent(getApplication(), DeleteActivity.class);
        startActivity(intent);
    }

    //データ変更ボタン
    public void rewrite(View view) {
        //Toast.makeText(this, "まだこの機能は実装されていません", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplication(), RewriteActivity.class);
        startActivity(intent);
    }

    //終了ボタン
    public void exit(View view) {
        finish();
    }

}




















