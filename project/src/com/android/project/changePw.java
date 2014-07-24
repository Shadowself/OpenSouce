package com.android.project;

import com.android.project.daobao.Persondao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class changePw extends Activity{

	private EditText old_pw,new_pw,new_pw1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changepw);
		
		old_pw = (EditText)findViewById(R.id.old_pw);
		new_pw = (EditText)findViewById(R.id.new_pw);
		new_pw1 = (EditText)findViewById(R.id.new_pw1);
		
	}
	
	public void fanhui(View v){
		this.finish();
	}
	
	public void change_pswd(View v){
		String userid = ((dataGet)getApplication()).getPresentid();
		String oldps = old_pw.getText().toString().trim();
		String newps = new_pw.getText().toString().trim();
		String newps1 = new_pw1.getText().toString().trim();
		
		Persondao dao = new Persondao(changePw.this);
//		if(dao.find(userid) != oldps){
//			Toast.makeText(changePw.this, "warning：注册失败，工号已经注册过！", Toast.LENGTH_LONG).show();
//		}
//		else{
		if(newps.equals(newps1) && !oldps.equals("") && !new_pw.equals("") && !new_pw1.equals("")){
			if(!(dao.find(userid)).equals(oldps)){
				Toast.makeText(changePw.this, "密码不正确！", Toast.LENGTH_LONG).show();
			}else{
				dao.change_pw(userid, newps);
				if(newps.equals(dao.find(userid))){
					Toast.makeText(changePw.this, "修改成功！", Toast.LENGTH_LONG).show();
					Intent intent = new Intent();
					intent.setClass(changePw.this,MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					changePw.this.finish();
				}else{
					Toast.makeText(changePw.this, "修改失败！", Toast.LENGTH_LONG).show();
				}
			}
		}else{
			Toast.makeText(changePw.this, "输入的两次密码不一致或填写不完整", Toast.LENGTH_LONG).show();
		}
//		}
		
	}

}
