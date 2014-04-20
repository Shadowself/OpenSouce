package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.dataGet;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class sesion_choose extends Activity{

	private ListView sesion_information;
	private LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sesion_choose);

		sesion_information = (ListView)findViewById(R.id.sesion_information);
		
		sesion_information.setAdapter(new sesionListener());
		
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		sesion_information.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				

				if(1 == position){
					dialog();
					}		
			
			}
			
		});
	}
	
	public class sesionListener extends BaseAdapter{

		String[] name = {"牙科  可预约人数 （159）","内科  可预约人数 （236）","外科  可预约人数 （267）","骨科  可预约人数 （156）",
				"妇产科  可预约人数 （254）","儿科  可预约人数 （165）","眼科  可预约人数 （196）","皮肤科  可预约人数 （132）","耳鼻喉科  可预约人数 （276）",
				"中医科  可预约人数 （185）","针灸科  可预约人数 （183）","老年病科  可预约人数 （286）","感染科  可预约人数 （196）"};
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return name.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup arg2) {
			// TODO Auto-generated method stub
			
			View view = inflater.inflate(R.layout.sesion_infor, null);
			TextView text = (TextView)view.findViewById(R.id.order_sesion);
			text.setText(name[position]);
			return view;
		}
		
	}
	
	protected void dialog() {
		
//		View v = inflater.inflate(R.layout.sesion_dialog,null);
	     AlertDialog.Builder builder = new Builder(sesion_choose.this);
	     builder.setTitle("                    内科");
	     
//	     builder.setView(v);
	     final String[] items = {"心血管内科","消化内科","内分泌内科","血液内科",
	    		 "神经内科","呼吸内科","肾病内科","风湿免疫科"};
		builder.setSingleChoiceItems(items, 0, new OnClickListener(){

			@Override 
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				((dataGet)getApplication()).setSesion_checked(items[which]);
			}
	    	 
	     });
	     
	     builder.setPositiveButton("预约", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(sesion_choose.this,order_choose.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
	
	     });
	     
	     builder.setNegativeButton("取消", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					((dataGet)getApplication()).setSesion_checked("false");
				}
		
		     });
	  
	     builder.create().show();
	}
	
	
	public void back_before(View v){
		this.finish();
	}

}
