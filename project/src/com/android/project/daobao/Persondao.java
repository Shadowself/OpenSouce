package com.android.project.daobao;

import java.util.ArrayList;
import java.util.List;

import com.android.project.MyDbOpenHelper;
import com.android.project.domain.Person;

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
	//�����Ա��
	public void add(String userid,String name,String state,String password){
		SQLiteDatabase db = helper.getWritableDatabase();
		if(db.isOpen()){

//			db.execSQL("insert into person(username,pswd) values (?,?)", new String[]{name,password});
	
			ContentValues value = new ContentValues();
			value.put("userid", userid);
			value.put("username", name);
			value.put("state", state);
			value.put("pswd", password);
			db.insert("person", null, value);
			
			db.close();
		}
	}
	
	//�޸����룻
	public void change_pw(String userid,String pswd){

		SQLiteDatabase db = helper.getWritableDatabase();
		
		if(db.isOpen()){
			ContentValues value = new ContentValues();
			value.put("pswd", pswd);
			db.update("person", value, "userid=?", new String[]{userid});
			db.close();
		}
	}
	
	//ͨ��id������Ա
	public String find(String userid){
		SQLiteDatabase db = helper.getWritableDatabase();
		if(db.isOpen()){

			Cursor cursor = db.query("person", null, "userid = ?", new String[]{userid}, null, null, null);
			
			if(cursor.moveToFirst()){
				int index = cursor.getColumnIndex("pswd");
				String pswd = cursor.getString(index);
				cursor.close();		
				db.close();
				return pswd;
			}		
			cursor.close();		
			db.close();		
		}	
		return null;
	}
	
	//ͨ��id������Ա����
		public String find_name(String userid){
			SQLiteDatabase db = helper.getWritableDatabase();
			if(db.isOpen()){

				Cursor cursor = db.query("person", null, "userid = ?", new String[]{userid}, null, null, null);
				
				if(cursor.moveToFirst()){
					int index = cursor.getColumnIndex("username");
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
		
	//��ѯ������Ϣ��
	public List<Person> findAll(){
		SQLiteDatabase db = helper.getWritableDatabase();
		List<Person> persons = new ArrayList<Person>();	
		if(db.isOpen()){
			Cursor cursor = db.query("person", null, null, null, null, null, null);
		
			while(cursor.moveToNext()){
				Person person = new Person();
				String userid = cursor.getString(cursor.getColumnIndex("userid"));
				person.setUserid(userid);
				String name = cursor.getString(cursor.getColumnIndex("username"));
				person.setUsername(name);
				String password = cursor.getString(cursor.getColumnIndex("pswd"));
				person.setPswd(password);
				String state = cursor.getString(cursor.getColumnIndex("state"));
				person.setState(state);
				persons.add(person);
			}
			cursor.close();
			db.close();
		}
			
		return persons;	
	}
	

}
