package com.android.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.project.daobao.Persondao;

public class MainActivity extends Activity {

	private Button post, login;
	private EditText userid, pswd;
	private CheckBox check;

	String remind = null;
	Persondao dao = new Persondao(this);
	SharedPreferences shared;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		shared = getSharedPreferences("checkid", Context.MODE_PRIVATE);

		post = (Button) findViewById(R.id.post);
		login = (Button) findViewById(R.id.login);

		userid = (EditText) findViewById(R.id.userid);
		pswd = (EditText) findViewById(R.id.pswd);

		check = (CheckBox) findViewById(R.id.check);

		post.setOnClickListener(new postListener());
		login.setOnClickListener(new loginListener());

		MyDbOpenHelper helper = new MyDbOpenHelper(this);
		helper.getReadableDatabase();

		boolean issetup = shared.getBoolean("issetup", false);

		if (issetup) {
			remind = shared.getString("remindid", "");
			userid.setText(remind);
			pswd.setText(dao.find(remind));
		}
		helper.close();
	}

	class postListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, post.class);
			startActivity(intent);
		}

	}

	class loginListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String u = userid.getText().toString().trim();
			String p = pswd.getText().toString().trim();
			String b = dao.find(u);

			if (u.equals("") || p.equals("")) {
				Toast.makeText(MainActivity.this, "用户名或密码不能为空！",
						Toast.LENGTH_LONG).show();
			} else if (b == null) {
				Toast.makeText(MainActivity.this, "用户名不存在！", Toast.LENGTH_LONG)
						.show();

			} else {
				if (b.equals(p)) {
					if (check.isChecked()) {
						Editor editor = shared.edit();
						editor.putString("remindid", u);
						editor.putBoolean("issetup", true);
						editor.commit();
					}
					((dataGet) getApplication()).setPresentid(u);
					Toast.makeText(MainActivity.this, "登录成功！",
							Toast.LENGTH_LONG).show();
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, loginsuccess.class);
					// intent.putExtra("str", u);
					startActivity(intent);
				} else {
					Toast.makeText(MainActivity.this, "用户名或密码不正确！",
							Toast.LENGTH_LONG).show();

				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
