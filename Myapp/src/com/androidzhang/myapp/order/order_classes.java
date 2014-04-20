package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class order_classes extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_classes);
		
	}
	
	public void back_before(View v){
		this.finish();
	}
	
	public void todoc_infor(View v){
		Intent intent = new Intent(order_classes.this,doctor_infor.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
