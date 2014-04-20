package com.androidzhang.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class personal extends Activity {

	private TextView t1,t2;
	dataGet data ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal);
		
		t1 = (TextView)findViewById(R.id.t1); 
		t2 = (TextView)findViewById(R.id.t2);
		data= (dataGet)getApplication();
		String is_login = data.getIsLogin();
		String user = data.getUsername();
		if(is_login.equals("true")){
			t1.setText("");
			t2.setText("您好："+user);
		}
			
	}
	
	public void back_main(View v){
		this.finish();
	}
	
	public void notify(View v){
		Toast.makeText(this, "该功能暂时尚未开发，敬请期待！", Toast.LENGTH_LONG).show();
	}
	
	public void toPost(View v){
		Intent intent = new Intent();
		intent.setClass(this, post.class);
		startActivity(intent);
	}
	
	public void toLogin(View v){
		data= (dataGet)getApplication();
		String is_login = data.getIsLogin();
		if(is_login.equals("true")){
			Toast.makeText(this, "您已登录", Toast.LENGTH_LONG).show();
		}
		else{
			Intent intent = new Intent();
			intent.setClass(this, login.class);
			startActivity(intent);
		}
		
	}
	
	public void to_addmem(View v){
		
		data= (dataGet)getApplication();
		String is_login = data.getIsLogin();
		if(is_login.equals("true")){
			
			Intent intent = new Intent();
			intent.setClass(this, managemb.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);

		}
		else{
			Toast.makeText(this, "您还没有登录，请先登录！", Toast.LENGTH_LONG).show();
		}
	}
	
	public void tologout(View v){
		data= (dataGet)getApplication();
		String is_login = data.getIsLogin();
		if(is_login.equals("false")){
			Toast.makeText(this, "您还未登录", Toast.LENGTH_LONG).show();
		}
		else{
			data.setIsLogin("false");
			Toast.makeText(this, "您已注销，请重新登陆！", Toast.LENGTH_LONG).show();
			Intent intent = new Intent();
			intent.setClass(this, login.class);
			startActivity(intent);
		}
		
	}
	
}
