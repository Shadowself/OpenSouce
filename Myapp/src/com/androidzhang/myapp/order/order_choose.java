package com.androidzhang.myapp.order;

import java.util.Calendar;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.collected;
import com.androidzhang.myapp.dataGet;
import com.androidzhang.myapp.personal;
import com.androidzhang.myapp.hospital.doc_hos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class order_choose extends Activity{

	dataGet data;
	private TextView place;
	private TextView hospital;
	private TextView sesion;
	private TextView orderdata;
	private TextView wel_user;
	String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_choose);
		
		place = (TextView)findViewById(R.id.place);
		hospital = (TextView)findViewById(R.id.hospital);
		sesion = (TextView)findViewById(R.id.sesion);
		orderdata = (TextView)findViewById(R.id.orderdata);
		wel_user = (TextView)findViewById(R.id.wel_user);
		
		data= (dataGet)getApplication();
		
		String check_place = data.getPlace_checked();
		String check_hospital = data.getHos_checked();
		String check_sesion = data.getSesion_checked();
		String order_data = data.getOrder_date();
		username = data.getUsername();
		if(username.equals("false")){
			wel_user.setText("您好:您还未登陆");
		}else{
			wel_user.setText("您好:"+username);
		}

		if(check_place.equals("true")){
			place.setText("河南-郑州");
		}
		
		if(check_hospital.equals("true")){
			hospital.setText("郑大第一附属医院");
		}
		
		if(!check_sesion.equals("false")){
			sesion.setText(check_sesion);
		}
		
		if(!order_data.equals("false")){
			orderdata.setText(order_data);
		}
		
	}
	
	public void back_before(View v){
		((dataGet)getApplication()).setPlace_checked("false");
		((dataGet)getApplication()).setHos_checked("false");
		((dataGet)getApplication()).setSesion_checked("false");
		this.finish();
	}

	public void toplace_infor(View v){
		Intent intent = new Intent(order_choose.this,place_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	
	public void tohosital_choose(View v){
		Intent intent = new Intent(order_choose.this,hospital_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void tosesion_choose(View v){
		Intent intent = new Intent(order_choose.this,order_sesion_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	
	public void togetdata(View v){
		Calendar c = Calendar.getInstance();
		new DatePickerDialog(order_choose.this,new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				int month = monthOfYear + 1;
				((dataGet)getApplication()).setOrder_date(year+"-"+month+"-"+dayOfMonth);
				orderdata.setText(year+"-"+month+"-"+dayOfMonth);
			}
		}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) ).show();
		
	}
	
	
	public void tomyorder(View v){
		Intent intent = new Intent(order_choose.this,myorder.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void toclasses(View v){
		if(username.equals("false")){
			Toast.makeText(this, "您还未登陆，请先登录", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(order_choose.this,personal.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}else{
			Intent intent = new Intent(order_choose.this,order_classes.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}

	}
	
	
	public void todoc_hos(View v){
		Intent intent = new Intent();
		intent.setClass(order_choose.this,doc_hos.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void tocollected(View v){
		Intent intent = new Intent();
		intent.setClass(order_choose.this,collected.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
}
