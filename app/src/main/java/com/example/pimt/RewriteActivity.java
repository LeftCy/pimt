package com.example.pimt;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RewriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewrite_layout);

        //現在時刻のセット
        TextView atView = findViewById(R.id.editText_atView);
        atView.setText(getNowDate());

    }

    public void rewrite(View view) {

        if (isEmpty()) {
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

            //ダイアログを表示
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("確認")
                    .setMessage("登録情報を変更しますか？")
                    .setPositiveButton("変更", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //変更時
                            rewriteDo();

                        }
                    })
                    .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //キャンセル時
                        }
                    });
            builder.show();
        }

    }

    public void rewriteDo() {
        MyDBHelper myDBHelper = new MyDBHelper(this, "pimt_db", null, 1);
        SQLiteDatabase sqliteDatabase = myDBHelper.getWritableDatabase();

        //変更時
        EditText numberView = findViewById(R.id.editText_numberView);
        TextView nameView = findViewById(R.id.editText_nameView);
        TextView addView = findViewById(R.id.editText_addView);
        TextView telView = findViewById(R.id.editText_telView);
        TextView regionView = findViewById(R.id.editText_regionView);
        TextView atView = findViewById(R.id.editText_atView);

        String inputNumber = numberView.getText().toString();
        String inputName = nameView.getText().toString();
        String inputAdd = addView.getText().toString();
        String inputTel = telView.getText().toString();
        String inputRegion = regionView.getText().toString();
        String inputAt = atView.getText().toString();

        String rewriteName = "update users set name = " + "'" + inputName +  "'" + " where id = " + inputNumber;
        String rewriteAdd = "update users set address = " + "'" + inputAdd +  "'" + " where id = " + inputNumber;
        String rewriteTel = "update users set tel = " + "'" + inputTel +  "'" + " where id = " + inputNumber;
        String rewriteRegion = "update users set customer_signature = " + "'" + inputRegion +  "'" + " where id = " + inputNumber;
        String rewriteAt = "update users set created_at = " + "'" + inputAt +  "'" + " where id = " + inputNumber;

        sqliteDatabase.execSQL(rewriteName);
        sqliteDatabase.execSQL(rewriteAdd);
        sqliteDatabase.execSQL(rewriteTel);
        sqliteDatabase.execSQL(rewriteRegion);
        sqliteDatabase.execSQL(rewriteAt);

        view();
    }

    public void view() {

        //入力番号の取得
        EditText editText_Number = findViewById(R.id.editText_numberView);
        String inputNumber = editText_Number.getText().toString();

        System.out.println("入力値: " + inputNumber);

        MyDBHelper myDBHelper = new MyDBHelper(this, "pimt_db", null, 1);
        SQLiteDatabase sqliteDatabase = myDBHelper.getWritableDatabase();

        //sql文
        String sql = "select * from users where id = ?";
        Cursor c = sqliteDatabase.rawQuery(sql, new String[] {inputNumber});

        if (c.moveToNext()) {

            //宣言
            TextView nameView = findViewById(R.id.editText_nameView);
            TextView addView = findViewById(R.id.editText_addView);
            TextView telView = findViewById(R.id.editText_telView);
            TextView regionView = findViewById(R.id.editText_regionView);
            TextView atView = findViewById(R.id.editText_atView);


            int index_id = c.getColumnIndex("id");
            int index_name = c.getColumnIndex("name");
            int index_add = c.getColumnIndex("address");
            int index_tel = c.getColumnIndex("tel");
            int index_region = c.getColumnIndex("customer_signature");
            int index_at = c.getColumnIndex("created_at");
            int id = c.getInt(index_id);
            String name = c.getString(index_name);
            String add = c.getString(index_add);
            String tel = c.getString(index_tel);
            String region = c.getString(index_region);
            String at = c.getString(index_at);
            String result = "ID: " + id + " 名前: " +  name + " 住所: " + add + " でんわ: " + tel + " 所属: " + region + " 登録日: " + at + "\n";

            System.out.println(result);

            nameView.setText(name);
            addView.setText(add);
            telView.setText(tel);
            regionView.setText(region);
            atView.setText(at);

        } else {

            TextView numberView = findViewById(R.id.editText_numberView);
            numberView.setText("");

            //ダイアログを表示
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("エラー")
                    .setMessage("正しい登録番号を入力してください")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //
                        }
                    });
            builder.show();

        }
    }

    public boolean isEmpty() {
        boolean empty = false;

        EditText editText_number = findViewById(R.id.editText_numberView);
        EditText editText_name = findViewById(R.id.editText_nameView);
        EditText editText_add = findViewById(R.id.editText_addView);
        EditText editText_tel = findViewById(R.id.editText_telView);
        EditText editText_region = findViewById(R.id.editText_regionView);
        EditText editText_at = findViewById(R.id.editText_atView);

        String rewriteNumber = editText_number.getText().toString();
        String rewriteName = editText_name.getText().toString();
        String rewriteAdd = editText_add.getText().toString();
        String rewriteTel = editText_tel.getText().toString();
        String rewriteRegion = editText_region.getText().toString();
        String rewriteAt = editText_at.getText().toString();

        //入力値が空だった場合
        if (
                rewriteNumber.toString().isEmpty() ||
                rewriteName.toString().isEmpty() ||
                rewriteAdd.toString().isEmpty() ||
                rewriteTel.toString().isEmpty() ||
                rewriteRegion.toString().isEmpty() ||
                rewriteAt.toString().isEmpty()
        ) {
            empty = true;
        }

        return empty;
    }

    public void returnButton(View view) {
        finish();
    }

    public static String getNowDate() {

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);

    }
}
