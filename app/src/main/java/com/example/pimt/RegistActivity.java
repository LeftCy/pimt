package com.example.pimt;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    SQLiteDatabase sqliteDatabase;


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

        //データベースヘルパー（SQLiteOpenHelper）のインスタンスを作成
        myDBHelper = new MyDBHelper(this, "pimt_db", null, 1);
        //getWritableDatabaseメソッドでSQLiteDatabase型の変数に代入
        sqliteDatabase = myDBHelper.getWritableDatabase();

        //カーソルの宣言
        Cursor c;


        //空き番号のセット
        String sql =
                "select min(id + 1) as id "
                        + "from users "
                        + "where (id + 1) not in (select id from users)";

        c = sqliteDatabase.rawQuery(sql, null);
        c.moveToFirst();
        //整数型で取得
        int number = c.getInt(0);

        //カーソルを閉じる
        c.close();

        TextView numberView = findViewById(R.id.editText_number);
        numberView.setText(String.valueOf(number));


        //現在時刻のセット
        TextView atView = findViewById(R.id.editText_at);
        atView.setText(getNowDate());




    }

    public void write() {

        //EditTextから入力されたデータの読み取り
        //String getNumber = number.getText().toString();
        String getName = name.getText().toString();
        String getAdd = add.getText().toString();
        String getTel = tel.getText().toString();
        String getRegion = region.getText().toString();
        String getCreatedAt = created_at.getText().toString();

        System.out.println(getName + getAdd + getTel + getRegion + getCreatedAt);

        //挿入完了を通知するためのトースト
        Toast.makeText(this, "登録しました！", Toast.LENGTH_SHORT).show();

        finish();
    }

    //現在時刻をString型で返すメソッド
    public static String getNowDate() {

        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);

    }

    public void returnButton(View view) {
        finish();
    }
}
