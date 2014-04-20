package com.androidzhang.myapp;

import com.androidzhang.myapp.daobao.Persondao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity{

	private Button button;
	private EditText user,passwd;
	Persondao dao = new Persondao(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		user = (EditText)findViewById(R.id.user);
		passwd = (EditText)findViewById(R.id.passwd);
		
		button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new buttonListener());
	}
	
	public class buttonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String u = user.getText().toString().trim();
			String p = passwd.getText().toString().trim();
			String b = dao.find(u);
			String user = dao.finduser(u);
			
			if(u.equals("") || p.equals("")){
				Toast.makeText(login.this, "用户名或密码不能为空！", Toast.LENGTH_LONG).show();
			}
			else if(b == null){
				Toast.makeText(login.this, "用户名不存在！", Toast.LENGTH_LONG).show();
			}else{
				if(b.equals(p)){

					Toast.makeText(login.this, "登录成功！", Toast.LENGTH_LONG).show();			
					((dataGet)getApplication()).setIsLogin("true");
					((dataGet)getApplication()).setUphone(u);
					((dataGet)getApplication()).setUsername(user);
					Intent intent = new Intent();
					intent.setClass(login.this,personal.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
				else{
					Toast.makeText(login.this, "用户名或密码不正确！", Toast.LENGTH_LONG).show();
					
				}	
			}
			
		}
		
	}
	
	public void back_main(View v){
		this.finish();
	}

}
