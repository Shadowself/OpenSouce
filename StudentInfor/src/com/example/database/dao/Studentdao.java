package com.example.database.dao;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.database.domain.Student;
import com.example.studentinfor.MyOpenHelper;

public class Studentdao {

	private Context context;

	MyOpenHelper helper;

	public Studentdao(Context context) {
		this.context = context;
		helper = new MyOpenHelper(context);
	}

	//添加学生信息
	public void addStudent(String sname, String ssex, String sid,
			String splace, String ssign) {
		SQLiteDatabase db = helper.getWritableDatabase();

		if (db.isOpen()) {

			ContentValues value = new ContentValues();
			value.put("sname", sname);
			value.put("ssex", ssex);
			value.put("sid", sid);
			value.put("splace", splace);
			value.put("ssign", ssign);
			db.insert("student", null, value);

			db.close();
		}

	}
	
	//更新学生信息
	public void update(String sname, String ssex, String sid,
			String splace, String ssign,String newdata){
		SQLiteDatabase db = helper.getWritableDatabase();
		
		if(db.isOpen()){
			
			if(null != sname){
				ContentValues value = new ContentValues();
				value.put("sname", newdata);
				db.update("student", value, "sname=?", new String[]{sname});
				db.close();
			}
			if(null != ssex){
				ContentValues value = new ContentValues();
				value.put("ssex", newdata);
				db.update("student", value, "ssex=?", new String[]{ssex});
				db.close();
			}
			if(null != sid){
				ContentValues value = new ContentValues();
				value.put("sid", newdata);
				db.update("student", value, "sid=?", new String[]{sid});
				db.close();
			}
			if(null != splace){
				ContentValues value = new ContentValues();
				value.put("splace", newdata);
				db.update("student", value, "splace=?", new String[]{splace});
				db.close();
			}
			if(null != ssign){
				ContentValues value = new ContentValues();
				value.put("ssign", newdata);
				db.update("student", value, "ssign=?", new String[]{ssign});
				db.close();
			}
		}
		
	}

	//判断是否存在学生信息
	public boolean isanyone() {
		SQLiteDatabase db = helper.getWritableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.query("student", null, null, null, null, null,
					null);

			if (cursor.moveToFirst()) {
				return true;
			}
			cursor.close();
			db.close();
		}

		return false;
	}

	//查找所有学生信息
	public List<Student> findAll() {
		SQLiteDatabase db = helper.getWritableDatabase();
		List<Student> students = new ArrayList<Student>();
		if (db.isOpen()) {
			Cursor cursor = db.query("student", null, null, null, null, null,
					null);

			while (cursor.moveToNext()) {

				Student student = new Student();
				String sname = cursor.getString(cursor.getColumnIndex("sname"));
				student.setSname(sname);
				String ssex = cursor.getString(cursor.getColumnIndex("ssex"));
				student.setSsex(ssex);
				String sid = cursor.getString(cursor.getColumnIndex("sid"));
				student.setSid(sid);
				String splace = cursor.getString(cursor
						.getColumnIndex("splace"));
				student.setSplace(splace);
				String ssign = cursor.getString(cursor.getColumnIndex("ssign"));
				student.setSsign(ssign);
				students.add(student);
			}
			cursor.close();
			db.close();
		}

		return students;
	}

}
