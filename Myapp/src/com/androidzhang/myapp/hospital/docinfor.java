package com.androidzhang.myapp.hospital;

import com.androidzhang.myapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class docinfor extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.docinfor);
	}
	
	public void back_befor(View v){
		this.finish();
	}

}
