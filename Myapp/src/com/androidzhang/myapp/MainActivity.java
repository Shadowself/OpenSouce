package com.androidzhang.myapp;

import com.androidzhang.myapp.hospital.doc_hos;
import com.androidzhang.myapp.order.order_choose;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		MyDbOpenHelper helper = new MyDbOpenHelper(this);
		helper.getReadableDatabase();
		helper.close();

	}

	public void person_view(View v){
		Intent intent = new Intent();
		intent.setClass(this, personal.class);
		startActivity(intent);
	}
	
	public void Healthy(View v){
		Intent intent = new Intent();
		intent.setClass(this, healthy.class);
		startActivity(intent);
	}
	
	public void toCollected(View v){
		Intent intent = new Intent();
		intent.setClass(this, collected.class);
		startActivity(intent);
	}
	
	public void todoc_hos(View v){
		Intent intent = new Intent();
		intent.setClass(this, doc_hos.class);
		startActivity(intent);
	}
	
	
	
	public void toorder_choose(View v){
		Intent intent = new Intent();
		intent.setClass(this, order_choose.class);
		startActivity(intent);
	}
	

}
