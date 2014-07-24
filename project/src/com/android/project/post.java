package com.android.project;

import com.android.project.daobao.Persondao;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class post extends Activity{
	
	private Button ps;
	private EditText userid,password,password2;
	private EditText oper_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		
		userid = (EditText)findViewById(R.id.userid);
		password = (EditText)findViewById(R.id.password);
		password2 = (EditText)findViewById(R.id.password2);
		oper_name = (EditText)findViewById(R.id.oper_name);
		
		ps = (Button)findViewById(R.id.ps);
		
//		MyDbOpenHelper helper = new MyDbOpenHelper(this);
//		helper.getReadableDatabase();
		
		ps.setOnClickListener(new psListener());
		
	}
	
	public void fanhui(View v){
		this.finish();
	}
	
	class psListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String id = userid.getText().toString().trim();
			String name = oper_name.getText().toString().trim();
			String pswd = password.getText().toString().trim();
			String pswd2 = password2.getText().toString().trim();
			
			
			Persondao dao = new Persondao(post.this);
			if(dao.find(id) != null){
				Toast.makeText(post.this, "warning：注册失败，工号已经注册过！", Toast.LENGTH_LONG).show();
			}
			else{
				if(pswd.equals(pswd2) && !id.equals("")&& !name.equals("") && !pswd.equals("") && !pswd2.equals("")){
					dao.add(id,name,"活跃", pswd);
					if(dao.find(id) != null){
						Toast.makeText(post.this, "注册成功", Toast.LENGTH_LONG).show();
						post.this.finish();
					}
					}
				else{
					Toast.makeText(post.this, "输入的两次密码不一致或填写不完整", Toast.LENGTH_LONG).show();
				}
			}
		}
		
	}

}
