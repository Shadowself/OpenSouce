package com.androidzhang.myapp.hospital;

import com.androidzhang.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class fthree extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fthree);
	}

	public void todocinfor(View v){
		Intent intent = new Intent();
		intent.setClass(fthree.this,docinfor.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
}
