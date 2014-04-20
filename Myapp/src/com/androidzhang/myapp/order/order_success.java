package com.androidzhang.myapp.order;

import com.androidzhang.myapp.MainActivity;
import com.androidzhang.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class order_success extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_success);
	}
	
	public void back_before(View v){
		this.finish();
	}
	
	public void tofirst_page(View v){
		Intent intent = new Intent(order_success.this,MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void tomyorder(View v){
		Intent intent = new Intent(order_success.this,myorder.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
