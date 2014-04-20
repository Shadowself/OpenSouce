package com.androidzhang.myapp;

import com.androidzhang.myapp.daobao.Memberdao;
import com.androidzhang.myapp.daobao.Persondao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class post extends Activity{

	private EditText uname;
	private EditText uphone;
	private EditText uid;
	private EditText password;
	private EditText password2;
	private TextView yzm;
	private Button ps;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		
		uname = (EditText)findViewById(R.id.uname);
		uphone = (EditText)findViewById(R.id.uphone);
		uid = (EditText)findViewById(R.id.uid);
		password = (EditText)findViewById(R.id.password);
		password2 = (EditText)findViewById(R.id.password2);
		yzm = (TextView)findViewById(R.id.yzm);
		
		ps = (Button)findViewById(R.id.ps);
		
		ps.setOnClickListener(new psListener());
		
	}
	
	public class psListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String phone = uphone.getText().toString().trim();
			String pswd = password.getText().toString().trim();
			String pswd2 = password2.getText().toString().trim();
			String name = uname.getText().toString().trim();
			String id = uid.getText().toString().trim();
			
			Persondao dao = new Persondao(post.this);
			Memberdao mdao = new Memberdao(post.this);
			if(dao.find(phone) != null){
				Toast.makeText(post.this, "warning：注册失败，手机号已经注册过！", Toast.LENGTH_LONG).show();
			}
			else{
				if(pswd.equals(pswd2) && !phone.equals("") && !pswd.equals("") && !pswd2.equals("")){
					dao.add(name,phone,id, pswd);
					mdao.add(name, "女", phone, id, "1992-03-11", "本人", phone);
					if(dao.find(phone) != null){
						Toast.makeText(post.this,"注册成功", Toast.LENGTH_LONG).show();
						post.this.finish();
					}
				}
				else{
					Toast.makeText(post.this, "输入的两次密码不一致或填写不完整", Toast.LENGTH_LONG).show();
				}
			}
		}
		
	}
	
	public void back_before(View v){
		this.finish();
	}
	
	public void get_code(View v){
		yzm.setText("58771");
	}

}
