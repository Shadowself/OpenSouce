package com.android.project.daobao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.project.MyDbOpenHelper;
import com.android.project.domain.Fastmail;

public class Fastmaildao {

	private Context context;
	
	MyDbOpenHelper helper;
	public Fastmaildao(Context context){
		this.context = context;
		helper = new MyDbOpenHelper(context);
	}
	
	//��ӿ����Ϣ��
		public void add(String mailid,String zt,String fstime,String operator){
			SQLiteDatabase db = helper.getWritableDatabase();
			
			if(db.isOpen()){
				ContentValues value = new ContentValues();
				value.put("mailid", mailid);
				value.put("zt", zt);
				value.put("fstime", fstime);
				value.put("operator", operator);
				db.insert("fastmail", null, value);
				db.close();
			}
		}
		
		//ͨ��id���ҿ��
		public String find(String mailid){
			SQLiteDatabase db = helper.getWritableDatabase();
			if(db.isOpen()){

				Cursor cursor = db.query("fastmail", null, "mailid = ?", new String[]{mailid}, null, null, null);
				
				if(cursor.moveToFirst()){
					int index = cursor.getColumnIndex("fstime");
					String fstime = cursor.getString(index);
					cursor.close();		
					db.close();	
					return fstime;
				}		
				cursor.close();		
				db.close();		
			}	
			return null;
		}
		
		//�޸Ŀ����Ϣ��
		public void changemail(String mailid,String newzt,String newfstime,String newoperator){

			SQLiteDatabase db = helper.getWritableDatabase();
			
			if(db.isOpen()){
				ContentValues value = new ContentValues();
				value.put("zt", newzt);
				value.put("fstime", newfstime);
				value.put("operator", newoperator);
				db.update("fastmail", value, "mailid=?", new String[]{mailid});
				db.close();
			}
		}
		
		//��ѯ���п����Ϣ��
		public List<Fastmail> findAll(){
			SQLiteDatabase db = helper.getWritableDatabase();
			List<Fastmail> fastmails = new ArrayList<Fastmail>();

			
			if(db.isOpen()){
				Cursor cursor = db.query("fastmail", null, null, null, null, null, null);
			
				while(cursor.moveToNext()){
					Fastmail fastmail = new Fastmail();
					String mailid = cursor.getString(cursor.getColumnIndex("mailid"));
					fastmail.setMailid(mailid);
					String zt = cursor.getString(cursor.getColumnIndex("zt"));
					fastmail.setZt(zt);
					String fstime = cursor.getString(cursor.getColumnIndex("fstime"));
					fastmail.setFstime(fstime);
					String operator = cursor.getString(cursor.getColumnIndex("operator"));
					fastmail.setOperator(operator);
					fastmails.add(fastmail);
				}
				cursor.close();
				db.close();
			}
				
			return fastmails;	
		}
		
		//��ѯָ�����Ա������Ŀ����Ϣ��
			public List<Fastmail> findFast_All(String operator){
				SQLiteDatabase db = helper.getWritableDatabase();
				List<Fastmail> fastmails = new ArrayList<Fastmail>();

				
				if(db.isOpen()){
//					Cursor cursor = db.query("fastmail", null, "operator = ?", new String[]{operator}, null, null, null);
					Cursor cursor = db.rawQuery("select distinct mailid from fastmail where operator = ?",new String[]{operator});
					while(cursor.moveToNext()){
						Fastmail fastmail = new Fastmail();
						String mailid = cursor.getString(cursor.getColumnIndex("mailid"));
						fastmail.setMailid(mailid);
						
						fastmails.add(fastmail);
					}
					cursor.close();
					db.close();
				}
					
				return fastmails;	
			}
	
			
			//��ѯָ�����ŵ�ȫ�������Ϣ��
			public List<Fastmail> findByid(String id){
				SQLiteDatabase db = helper.getWritableDatabase();
				List<Fastmail> fastmails = new ArrayList<Fastmail>();

				
				if(db.isOpen()){
					Cursor cursor = db.query("fastmail", null, "mailid = ?", new String[]{id}, null, null, null);
					
					while(cursor.moveToNext()){
						Fastmail fastmail = new Fastmail();
						String mailid = cursor.getString(cursor.getColumnIndex("mailid"));
						fastmail.setMailid(mailid);
						String zt = cursor.getString(cursor.getColumnIndex("zt"));
						fastmail.setZt(zt);
						String fstime = cursor.getString(cursor.getColumnIndex("fstime"));
						fastmail.setFstime(fstime);
						String foperator = cursor.getString(cursor.getColumnIndex("operator"));
						fastmail.setOperator(foperator);
						fastmails.add(fastmail);
					}
					cursor.close();
					db.close();
				}
					
				return fastmails;	
			}
				
				
		//ͨ��id���ҿ��Ȼ��ɾ��
		public void deleteMail(String mailid){
			SQLiteDatabase db = helper.getWritableDatabase();
			if(db.isOpen()){
//				String str = "delete from fastmail where mailid = ' " + mailid +" '";
//				db.execSQL(str);
				try{
					db.delete("fastmail", "mailid = ?", new String[]{mailid});
					Log.i("ɾ��","�ɹ�ɾ����¼");
				}catch(Exception e)
				{
					Log.i("ɾ��","ɾ����¼ʧ��");
				}
				
				db.close();		
			}
		}
	
}
