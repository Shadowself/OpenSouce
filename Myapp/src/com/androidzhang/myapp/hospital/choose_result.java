package com.androidzhang.myapp.hospital;

import com.androidzhang.myapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class choose_result extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_result);
	}
	
	public void back_before(View v){
		this.finish();
	}
	
	

}
