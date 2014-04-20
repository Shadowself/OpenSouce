package com.androidzhang.myapp;

import java.util.List;

import com.androidzhang.myapp.daobao.Memberdao;
import com.androidzhang.myapp.domain.Member;
import com.androidzhang.myapp.hospital.doc_hos;
import com.androidzhang.myapp.order.order_choose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class managemb extends Activity{

	private ListView mbinfor;
	private LayoutInflater inflater;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managemb);
		
		mbinfor = (ListView)findViewById(R.id.mbinfor);
		
		mbinfor.setAdapter(new MyAdapter());
		
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
	
	}

	public void back_before(View v){
		this.finish();
	}
	
	public void add_mb(View v){
		Intent intent = new Intent();
		intent.setClass(managemb.this,addmember.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public class MyAdapter extends BaseAdapter{

		Memberdao dao = new Memberdao(managemb.this);
		List<Member> Members = dao.findAll();
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Members.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return Members.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			Member m = Members.get(position);
			
			View view = inflater.inflate(R.layout.showmb, null);
			TextView text1 = (TextView)view.findViewById(R.id.showtext1);
			TextView text2 = (TextView)view.findViewById(R.id.showtext2);
			TextView text3 = (TextView)view.findViewById(R.id.showtext3);
			TextView text4 = (TextView)view.findViewById(R.id.showtext4);
			
			text1.setText(""+m.getMname());
			text2.setText(""+m.getMphone());
			text3.setText(""+m.getFship());
			text4.setText(""+m.getMdate());
			
			
			return view;
		}
		
	}
	
	public void to_order(View v){
		Intent intent = new Intent();
		intent.setClass(managemb.this,order_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void todoc_hos(View v){
		Intent intent = new Intent();
		intent.setClass(managemb.this,doc_hos.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void tocollected(View v){
		Intent intent = new Intent();
		intent.setClass(managemb.this,collected.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
}
