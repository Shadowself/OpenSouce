package com.androidzhang.myapp.daobao;

import java.util.ArrayList;
import java.util.List;

import com.androidzhang.myapp.MyDbOpenHelper;
import com.androidzhang.myapp.domain.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Persondao {
	
	private Context context;
	
	MyDbOpenHelper helper;
	public Persondao(Context context){
		this.context = context;
		helper = new MyDbOpenHelper(context);
	}
	//添加人员；
	public void add(String name,String uphone,String userid,String password){
		SQLiteDatabase db = helper.getWritableDatabase();
		if(db.isOpen()){

			ContentValues value = new ContentValues();

			value.put("uname", name);
			value.put("uphone", uphone);
			value.put("uid", userid);
			value.put("pswd", password);
			db.insert("person", null, value);
			
			db.close();
		}
	}
	
	//通过phone查找密码
	public String find(String uphone){
		SQLiteDatabase db = helper.getWritableDatabase();
		if(db.isOpen()){

			Cursor cursor = db.query("person", null, "uphone = ?", new String[]{uphone}, null, null, null);
			
			if(cursor.moveToFirst()){
				int index = cursor.getColumnIndex("pswd");
				String password = cursor.getString(index);
				cursor.close();		
				db.close();
				return password;
			}		
			cursor.close();		
			db.close();		
		}	
		return null;
	}
	
	//通过phone查找用户名
	public String finduser(String uphone){
		SQLiteDatabase db = helper.getWritableDatabase();
		if(db.isOpen()){

			Cursor cursor = db.query("person", null, "uphone = ?", new String[]{uphone}, null, null, null);
			
			if(cursor.moveToFirst()){
				int index = cursor.getColumnIndex("uname");
				String username = cursor.getString(index);
				cursor.close();		
				db.close();
				return username;
			}		
			cursor.close();		
			db.close();		
		}	
		return null;
	}
	
	//查询所有信息；
	public List<Person> findAll(){
		SQLiteDatabase db = helper.getWritableDatabase();
		List<Person> persons = new ArrayList<Person>();	
		if(db.isOpen()){
			Cursor cursor = db.query("person", null, null, null, null, null, null);
		
			while(cursor.moveToNext()){
				Person person = new Person();
				
				String name = cursor.getString(cursor.getColumnIndex("uname"));
				person.setUname(name);
				
				String userid = cursor.getString(cursor.getColumnIndex("uid"));
				person.setUid(userid);
				
				String password = cursor.getString(cursor.getColumnIndex("pswd"));
				person.setPswd(password);
				
				String uphone = cursor.getString(cursor.getColumnIndex("uphone"));
				person.setUphone(uphone);
				
				persons.add(person);
			}
			cursor.close();
			db.close();
		}
			
		return persons;	
	}
	

}
