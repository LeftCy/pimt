package com.example.pimt;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    SQLiteDatabase sqliteDatabase;

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

    public void write(View view) {

        if (isEmpty()) {
            System.out.println("入力値が空白であった場合のダイアログを表示する処理を書くこと");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("エラー")
                    .setMessage("入力していない箇所があります！")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //
                        }
                    });
            builder.show();
        } else {
            System.out.println("DBへの書き込み処理");
            //EditTextから入力された文字、数字を受け取るために変数を宣言
            EditText editText_number = findViewById(R.id.editText_number);
            EditText editText_name = findViewById(R.id.editText_name);
            EditText editText_add = findViewById(R.id.editText_add);
            EditText editText_tel = findViewById(R.id.editText_tel);
            EditText editText_region = findViewById(R.id.editText_region);
            EditText editText_at = findViewById(R.id.editText_at);
            //EditTextから入力されたデータの読み取り
            String registNumber = editText_number.getText().toString();
            String registName = editText_name.getText().toString();
            String registAdd = editText_add.getText().toString();
            String registTel = editText_tel.getText().toString();
            String registRegion = editText_region.getText().toString();
            String registAt = editText_at.getText().toString();

            //for Debug
            String inputs[] = {registNumber, registName, registAdd, registTel, registRegion, registAt};
            for (int i = 0; i < inputs.length; i ++) {
                System.out.println("入力値： " + inputs[i]);
            }

            //Insert to DB
            //この動作に戻り値はない為、execSQL()が使用可能
            //変数を生で叩き込まないこと(''で囲まないとエラーの原因になる)
            String sql =
                    "insert into users (" +
                            "id," +
                            "name, " +
                            "address, " +
                            "tel, " +
                            "customer_signature, "+
                            "created_at) " +
                            "values (" +
                            registNumber + ", " +
                            "'" + registName + "'," +
                            "'" + registAdd + "'," +
                            "'" + registTel + "'," +
                            "'" + registRegion + "'," +
                            "'" + registAt + "'" +
                            ")";

            sqliteDatabase.execSQL(sql);

            finish();

            //挿入完了を通知するためのトースト
            Toast.makeText(this, "登録しました！", Toast.LENGTH_SHORT).show();
        }

    }

    //入力値が空でないか？
    public boolean isEmpty() {
        boolean empty = false;

        EditText editText_name = findViewById(R.id.editText_name);
        EditText editText_add = findViewById(R.id.editText_add);
        EditText editText_tel = findViewById(R.id.editText_tel);
        EditText editText_region = findViewById(R.id.editText_region);
        EditText editText_at = findViewById(R.id.editText_at);

        String registName = editText_name.getText().toString();
        String registAdd = editText_add.getText().toString();
        String registTel = editText_tel.getText().toString();
        String registRegion = editText_region.getText().toString();
        String registAt = editText_at.getText().toString();

        //入力値が空だった場合
        if (
                registName.toString().isEmpty() ||
                registAdd.toString().isEmpty() ||
                registTel.toString().isEmpty() ||
                registRegion.toString().isEmpty() ||
                registAt.toString().isEmpty()
        ) {
            empty = true;
        }

        return empty;
    }

    //現在時刻をString型で返すメソッド
    public static String getNowDate() {

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);

    }

    public void returnButton(View view) {
        finish();
    }
}
