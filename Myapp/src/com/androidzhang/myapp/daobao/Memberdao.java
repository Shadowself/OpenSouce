package com.androidzhang.myapp.daobao;

import java.util.ArrayList;
import java.util.List;

import com.androidzhang.myapp.MyDbOpenHelper;
import com.androidzhang.myapp.domain.Member;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Memberdao {

	private Context context;
	
	MyDbOpenHelper helper;
	public Memberdao(Context context){
		this.context = context;
		helper = new MyDbOpenHelper(context);
	}
	
		public void add(String mname,String msex,String mphone,String mid,String mdate,String fship,String user){
			SQLiteDatabase db = helper.getWritableDatabase();
			
			if(db.isOpen()){
				ContentValues value = new ContentValues();
				value.put("mname", mname);
				value.put("msex", msex);
				value.put("mphone", mphone);
				value.put("mid", mid);
				value.put("mdate", mdate);
				value.put("fship", fship);
				value.put("user", user);
				db.insert("member", null, value);
				db.close();
			}
		}
		
		
		//查询所有快递信息；
		public List<Member> findAll(){
			SQLiteDatabase db = helper.getWritableDatabase();
			List<Member> members = new ArrayList<Member>();
			
			if(db.isOpen()){
				Cursor cursor = db.query("Member", null, null, null, null, null, null);
			
				while(cursor.moveToNext()){
					Member member = new Member();				
					
					String mname = cursor.getString(cursor.getColumnIndex("mname"));
					member.setMname(mname);
					String msex = cursor.getString(cursor.getColumnIndex("msex"));
					member.setMsex(msex);
					String mphone = cursor.getString(cursor.getColumnIndex("mphone"));
					member.setMphone(mphone);
					String mid = cursor.getString(cursor.getColumnIndex("mid"));
					member.setMid(mid);
					String mdate = cursor.getString(cursor.getColumnIndex("mdate"));
					member.setMdate(mdate);
					String fship = cursor.getString(cursor.getColumnIndex("fship"));
					member.setFship(fship);
					String user = cursor.getString(cursor.getColumnIndex("user"));
					member.setUser(user);
					members.add(member);
				}
				cursor.close();
				db.close();
			}
				
			return members;	
		}
	
}
