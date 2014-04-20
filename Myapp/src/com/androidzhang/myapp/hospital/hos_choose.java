package com.androidzhang.myapp.hospital;

import com.androidzhang.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class hos_choose extends Activity{

	private ListView sesion;
	private LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hos_choose);
		sesion = (ListView)findViewById(R.id.sesion);
		
		sesion.setAdapter(new sesionAdapter());
		
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
	}
	
	public class sesionAdapter extends BaseAdapter{

		String[] name = {"全部","内科","外科","肿瘤科","妇产科","儿科","五官科","男科","传染科","皮肤科","皮肤性病","精神心理科","老年科","急诊","生殖健康"};
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return name.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup arg2) {
			// TODO Auto-generated method stub
			
			View view = inflater.inflate(R.layout.sesioninfor, null);
			TextView text = (TextView)view.findViewById(R.id.sesionname);
			text.setText(name[position]);
			return view;
		}
		
	}
	
	public void back_before(View v){
		this.finish();
	}
	
	
	
	public void tochoose_result(View v){
		
		Intent intent = new Intent(hos_choose.this,choose_result.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}

}
