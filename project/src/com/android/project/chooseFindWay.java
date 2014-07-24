package com.android.project;

import com.android.project.daobao.Fastmaildao;
import com.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class chooseFindWay extends Activity{

	private EditText fastId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choosefindway);
		fastId = (EditText)findViewById(R.id.FastId);
	}
	
	public void find_all(View v){
		Intent intent = new Intent();
		intent.setClass(chooseFindWay.this,mailinfor.class);
		startActivity(intent);
	}
	
	public void scan_id(View v){
		((dataGet)getApplication()).setIn_way("result");
		Intent intent = new Intent();
		intent.setClass(chooseFindWay.this,CaptureActivity.class);
		startActivityForResult(intent, 0);
	}
	

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	
		if(data != null){
			String num = data.getStringExtra("num");
			fastId.setText(num);
		}
	}

	public void find_fast(View v){
		String id = fastId.getText().toString().trim();
		if(id.equals("")){
			Toast.makeText(this, "请填写要查询的单号", Toast.LENGTH_LONG).show();
		}else{
			Fastmaildao fast = new Fastmaildao(chooseFindWay.this);
			if(fast.find(id) == null){
				Toast.makeText(this, "查询的单号不存在", Toast.LENGTH_LONG).show();
			}else{
				Intent intent = new Intent();
				intent.setClass(chooseFindWay.this,singleMailInfor.class);
				intent.putExtra("mailid", id);
				startActivity(intent);
			}
		}

	}
	
	public void backclick(View v){
		this.finish();
	}

}
