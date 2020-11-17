package com.example.pimt;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_layout);

        //戻るボタンの処理
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(v -> finish());
    }

    public void delete(View view) {
        //入力番号の取得
        EditText editText_Number = findViewById(R.id.editText_numberView);
        String inputNumber = editText_Number.getText().toString();

        System.out.println("入力値: " + inputNumber);

        MyDBHelper myDBHelper = new MyDBHelper(this, "pimt_db", null, 1);
        SQLiteDatabase sqliteDatabase = myDBHelper.getWritableDatabase();

        //sql
        String sql = "";
    }
}
