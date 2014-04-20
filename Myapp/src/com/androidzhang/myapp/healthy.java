package com.androidzhang.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class healthy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.healthy);
	}
	
	public void back_main(View v){
		this.finish();
	}
}
