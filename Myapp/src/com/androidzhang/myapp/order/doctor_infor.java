package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class doctor_infor extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_infor);
	}
	
	public void back_before(View v){
		this.finish();
	}
	
	public void tomake_sure(View v){
		Intent intent = new Intent(doctor_infor.this,make_sure.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
