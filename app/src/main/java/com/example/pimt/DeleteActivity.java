package com.example.pimt;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_layout);
    }

    public void delete(View view) {
        view();

        //ダイアログを表示
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
            .setTitle("警告")
            .setMessage("本当に削除を実行しますか？")
            .setPositiveButton("削除", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    deleteDo();

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
            initialize();

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

    public void initialize() {
        //EditTextの初期化
        TextView nameView = findViewById(R.id.editText_nameView);
        TextView addView = findViewById(R.id.editText_addView);
        TextView telView = findViewById(R.id.editText_telView);
        TextView regionView = findViewById(R.id.editText_regionView);
        TextView atView = findViewById(R.id.editText_atView);

        nameView.setText("");
        addView.setText("");
        telView.setText("");
        regionView.setText("");
        atView.setText("");
    }

    public void deleteDo() {
        MyDBHelper myDBHelper = new MyDBHelper(this, "pimt_db", null, 1);
        SQLiteDatabase sqliteDatabase = myDBHelper.getWritableDatabase();

        //削除時
        EditText editText_Number = findViewById(R.id.editText_numberView);
        String inputNumber = editText_Number.getText().toString();

        sqliteDatabase.delete("users", "id = ?", new String[] {inputNumber});

        initialize();
        TextView numberView = findViewById(R.id.editText_nameView);
        numberView.setText("");
        //Toast.makeText(this, "削除が完了しました", Toast.LENGTH_LONG).show();

    }

    public void returnButton(View view) {
        finish();
    }
}
