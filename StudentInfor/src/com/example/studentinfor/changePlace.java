package com.example.studentinfor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class changePlace extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changplace);
	}

	public void change_shengfen(View v) {
		Intent intent = new Intent(this, changeShengfen.class);
		startActivity(intent);
	}

	public void back_before(View v) {
		this.finish();
	}
}
