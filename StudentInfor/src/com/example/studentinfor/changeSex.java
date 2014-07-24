package com.example.studentinfor;

import com.example.database.dao.Studentdao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class changeSex extends Activity {

	private ImageView sex_boy;
	private ImageView sex_girl;

	String sex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changesex);

		sex_boy = (ImageView) findViewById(R.id.sex_boy);
		sex_girl = (ImageView) findViewById(R.id.sex_girl);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		sex = bundle.getString("sex");

		if (sex.equals("ÄÐ")) {
			sex_boy.setVisibility(View.VISIBLE);
			sex_girl.setVisibility(View.GONE);

		} else {
			sex_boy.setVisibility(View.GONE);
			sex_girl.setVisibility(View.VISIBLE);
		}

	}

	public void changetoboy(View v) {

		sex_boy.setVisibility(View.VISIBLE);
		sex_girl.setVisibility(View.GONE);

		Studentdao dao = new Studentdao(this);
		dao.update(null, sex, null, null, null, "ÄÐ");
		this.finish();
	}

	public void changetogirl(View v) {

		sex_boy.setVisibility(View.GONE);
		sex_girl.setVisibility(View.VISIBLE);

		Studentdao dao = new Studentdao(this);
		dao.update(null, sex, null, null, null, "Å®");
		this.finish();
	}

	public void back_before(View v) {
		this.finish();
	}

}
