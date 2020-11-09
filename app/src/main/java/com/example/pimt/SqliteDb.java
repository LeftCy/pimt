package com.example.pimt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteDb extends SQLiteOpenHelper {

    static final String DB_NAME = "pimtDB.db";
    static final int DB_VERSION = 1;

    static final String DB_FILENAME= "pimt.db";

    public SqliteDb(Context context) {
        super(context,DB_FILENAME,null,DB_VERSION);
    }

    @Override //親クラスを子クラスで上書きした場合の処理
    public void onCreate(SQLiteDatabase db) {
        //1番目の引数にタグを表す文字列を指定し、2番目の引数にログとして出力する文字列を指定
        Log.d("SqliteDb", "onCreate");

        //DBの作成
        db.execSQL("CREATE TABLE Users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "address TEXT, " +
                "tel TEXT, " +
                "customer_signature TEXT, " +
                "created_at timestamp )");

        //テストデーター
        db.execSQL("insert into Users (" +
                "name, " +
                "address, " +
                "tel, " +
                "customer_signature, " +
                "created_at )" +
                "values (" +
                "1, " +
                "'平井　嵩大'," +
                "'ひじり野南３－１－９－１'," +
                "'090-3333-3333'," +
                "'ユニバーサルコンピューター')" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
