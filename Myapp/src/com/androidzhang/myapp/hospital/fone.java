package com.androidzhang.myapp.hospital;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.addmember;
import com.androidzhang.myapp.managemb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class fone extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fone);
	}
	
	public void tozdinfor(View v){
		Intent intent = new Intent();
		intent.setClass(fone.this,zdinfor.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
