package com.android.project;

import java.util.List;

import com.android.project.daobao.Fastmaildao;
import com.android.project.daobao.Persondao;
import com.android.project.domain.Fastmail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class singleMailInfor extends Activity{

	private TextView text_id;
	private ListView infor;
	private LayoutInflater inflater;
	
	String text;
	String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singlemailinfor);
		
		text_id = (TextView)findViewById(R.id.text_id);
		infor = (ListView)findViewById(R.id.singleinfor);
		
		Intent intent=getIntent();         
        Bundle bundle=intent.getExtras();  
        id = bundle.getString("mailid");
        text = "快递单号：" + id;
        
        text_id.setText(text);
        
        infor.setAdapter(new MyAdapter());
		
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
	
		
	}
	
	
	class MyAdapter extends BaseAdapter{
		Fastmaildao dao = new Fastmaildao(singleMailInfor.this);
		List<Fastmail> Fastmails = dao.findByid(id);
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Fastmails.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return Fastmails.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup parent) {
			// TODO Auto-generated method stub
/*			TextView tv = new TextView(loginsuccess.this);
			Person person = Persons.get(position);
			tv.setText(person.getUsername()+":"+person.getPswd());
*/
			View view = inflater.inflate(R.layout.singlelist, null);
			TextView logtime = (TextView)view.findViewById(R.id.logtime);
			TextView log = (TextView)view.findViewById(R.id.log);
	
			Fastmail mail = Fastmails.get(position);
			Persondao dao = new Persondao(singleMailInfor.this);
			String oper =  mail.getOperator();
			
			logtime.setText(mail.getFstime());
			log.setText(mail.getZt()+"  操作员：" + oper + "(" + dao.find_name(oper) + ")");
			
			return view;
		}
		
	}

	
	public void backclick(View v){
		this.finish();
	}
}
