package com.example.pimt;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);

    }

    public void view(View view) {
        //入力番号の取得
        EditText editText_Number = findViewById(R.id.editText_numberView);
        String inputNumber = editText_Number.getText().toString();

        //もし、入力されていたら。
        //空白でないかどうかで判断
        if (inputNumber != null) {
            System.out.println("空白ではありません！: " + inputNumber);
        } else {
            System.out.println("空白です！: " + inputNumber);
        }

        //データベースヘルパー（SQLiteOpenHelper）のインスタンスを作成
        MyDBHelper myDBHelper = new MyDBHelper(this, "pimt_db", null, 1);
        //getWritableDatabaseメソッドでSQLiteDatabase型の変数に代入
        SQLiteDatabase sqliteDatabase = myDBHelper.getWritableDatabase();

        //sql文
        String sql = "select * from user";

        Cursor c = sqliteDatabase.rawQuery(sql, null);

        while(c.moveToNext()){
            int index_id = c.getColumnIndex("id");
            int index_name = c.getColumnIndex("name");
            int id = c.getInt(index_id);
            String name = c.getString(index_name);
            String result = "ID: " + id + " 名前:" +  name + "\n";
            System.out.println(result);
        }


    }

    public void returnButton(View view) {
        finish();
    }

}
