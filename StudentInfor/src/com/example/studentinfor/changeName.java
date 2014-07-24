package com.example.studentinfor;

import com.example.database.dao.Studentdao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class changeName extends Activity {

	private EditText newname;
	String new_name;
	String oldname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changename);

		newname = (EditText) findViewById(R.id.newname);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		oldname = bundle.getString("name");

		newname.setText(oldname);

	}

	public void updatename(View v) {
		new_name = newname.getText().toString().trim();

		if (new_name.equals("")) {
			Toast.makeText(this, "姓名不能为空", Toast.LENGTH_LONG).show();
		} else {
			Studentdao dao = new Studentdao(this);
			dao.update(oldname, null, null, null, null, new_name);
			this.finish();
		}

	}

	public void back_before(View v) {
		this.finish();
	}

}
