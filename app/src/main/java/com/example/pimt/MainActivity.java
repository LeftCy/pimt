package com.example.pimt;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //データベースの宣言
    SQLiteDatabase sqliteDatabase;

    //自作したクラス（SQLiteOpenHelperを継承しているクラス)
    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "DB作成", Toast.LENGTH_SHORT).show();

        TextView number = findViewById(R.id.numbers);
        number.setText("現在の登録件数:" + read() + "件");
    }

    //登録数の読み込み
    public String read() {
        //このクラスで利用するため
        //データベースヘルパー（SQLiteOpenHelper）のインスタンスを作成
        MyDBHelper myDBHelper = new MyDBHelper(this, "pimt_db", null, 1);
        //getWritableDatabaseメソッドでSQLiteDatabase型の変数に代入
        SQLiteDatabase sqliteDatabase = myDBHelper.getWritableDatabase();

        //カーソルの宣言
        Cursor c = null;

        //データベースへの問い合わせ
        String sql = "select count(*) from users";

        c = sqliteDatabase.rawQuery(sql, null);

        c.moveToFirst();

        String data = c.getString(0);

        return data;

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




















