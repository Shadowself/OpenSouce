package com.android.project;

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

		db.execSQL("CREATE TABLE IF NOT EXISTS person (userid varchar(20) primary key, username varchar(20),state varchar(20), pswd varchar(20))");
	    db.execSQL("CREATE TABLE IF NOT EXISTS fastmail(mailid varchar(20), zt varchar(10), fstime varchar(30),operator varchar(20),state varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS person");
		db.execSQL("DROP TABLE IF EXISTS fastmail");
		
		onCreate(db);
	}

}
