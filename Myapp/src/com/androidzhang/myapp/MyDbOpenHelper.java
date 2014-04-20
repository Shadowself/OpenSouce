package com.androidzhang.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbOpenHelper extends SQLiteOpenHelper {

	public MyDbOpenHelper(Context context) {
		super(context, "database.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE IF NOT EXISTS person (uname varchar(20),uphone varchar(20) primary key,uid varchar(20), pswd varchar(20))");
	    db.execSQL("CREATE TABLE IF NOT EXISTS member(mname varchar(20), msex varchar(10), mphone varchar(20),mid varchar(20) primary key,mdate varchar(20),fship varchar(20),user varchar(20) )");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS person");
		db.execSQL("DROP TABLE IF EXISTS member");
		
		onCreate(db);
	}

}
