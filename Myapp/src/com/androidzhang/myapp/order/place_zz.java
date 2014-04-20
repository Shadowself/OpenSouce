package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.dataGet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class place_zz extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_zz);
	}

	public void back_before(View v){
		this.finish();
	}
	
	public void toorder_choose(View v){
		((dataGet)getApplication()).setPlace_checked("true");
		Intent intent = new Intent(place_zz.this,order_choose.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
	}
}
