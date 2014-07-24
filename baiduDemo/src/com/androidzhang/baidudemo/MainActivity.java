package com.androidzhang.baidudemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void show_baseMap(View v){
		Intent intent = new Intent(this,BaseMapDemo.class);
		startActivity(intent);
	}
	
	public void show_LocationDemo(View v){
		Intent intent = new Intent(this,LocationDemo.class);
		startActivity(intent);
	}
	
	public void to_distance(View v){
		Intent intent = new Intent(this,DistanceDemo.class);
		startActivity(intent);
	}
	
	
	public void to_buslinedemo(View v){
		Intent intent = new Intent(this,BusLineDemo.class);
		startActivity(intent);
	}

}
