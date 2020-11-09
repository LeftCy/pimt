package com.example.pimt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        //None
        //git Mac
        //git Win
    }
}
