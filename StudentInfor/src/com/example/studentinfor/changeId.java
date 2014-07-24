package com.example.studentinfor;

import com.example.database.dao.Studentdao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class changeId extends Activity {

	private EditText new_id;
	String newid;
	String oldid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changeid);
		new_id = (EditText) findViewById(R.id.new_id);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		oldid = bundle.getString("sid");

		new_id.setText(oldid);

	}

	public void updateid(View v) {
		newid = new_id.getText().toString().trim();

		if (newid.equals("")) {
			Toast.makeText(this, "身份证不能为空", Toast.LENGTH_LONG).show();
		} else {
			Studentdao dao = new Studentdao(this);
			dao.update(null, null, oldid, null, null, newid);
			this.finish();
		}

	}

	public void back_before(View v) {
		this.finish();
	}
}
