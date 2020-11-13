package com.example.pimt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//SQLiteOpenHelperを継承
public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //このメソッドの内容を編集
    @Override
    public void onCreate(SQLiteDatabase db) {
        //テーブルの作成
        String sql =
                "CREATE TABLE users (" +
                "id INTEGER not null," +
                "name TEXT," +
                "address TEXT, " +
                "tel TEXT, " +
                "customer_signature TEXT, " +
                "created_at timestamp )";
        db.execSQL(sql);

        //usersテーブルへの書き込み
        String sqlTestData =
                "insert into users (" +
                        "id," +
                        "name, " +
                        "address, " +
                        "tel, " +
                        "customer_signature, " +
                        "created_at" +
                        ") " +
                        "values (" +
                        "1, " +
                        "'平井　嵩大', " +
                        "'ひじり野南３－１－９－１', " +
                        "'090-3333-3333', " +
                        "'ユニバーサルコンピューター', " +
                        "'" + getNowDate() + "'" +
                        ")";
        db.execSQL(sqlTestData);



    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO 自動生成されたメソッド・スタブ
    }

    //現在時刻をString型で返すメソッド
    public static String getNowDate() {

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);

    }
}
