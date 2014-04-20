package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.dataGet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class hospital_choose extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_choose);
		
	}
	
	public void back_before(View v){
		this.finish();
	}
	
	public void toorder_zdinfor(View v){
		Intent intent = new Intent(hospital_choose.this,order_zdinfor.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
