package com.example.studentinfor;

import java.io.File;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.database.dao.Studentdao;
import com.example.database.domain.Student;

public class StudentInforActivity extends Activity {

	private TextView S_name;
	private TextView S_sex;
	private TextView S_id;
	private TextView S_place;
	private TextView S_sign;
	private ImageView touxiang;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_infor);

		S_name = (TextView) findViewById(R.id.S_name);
		S_sex = (TextView) findViewById(R.id.S_sex);
		S_id = (TextView) findViewById(R.id.S_id);
		S_place = (TextView) findViewById(R.id.S_place);
		S_sign = (TextView) findViewById(R.id.S_sign);

		touxiang = (ImageView) findViewById(R.id.touxiang);
		MyOpenHelper helper = new MyOpenHelper(this);
		helper.getWritableDatabase();

		helper.close();

	}

	@SuppressLint("SdCardPath")
	public void init() {
		Studentdao dao = new Studentdao(this);
		if (!dao.isanyone()) {
			dao.addStudent("无名", "男", "001", "河南", "我有我个性！");
		}

		List<Student> students = dao.findAll();

		Student s = students.get(0);

		S_name.setText(s.getSname());
		S_sex.setText(s.getSsex());
		S_id.setText(s.getSid());
		S_place.setText(s.getSplace());
		S_sign.setText(s.getSsign());
 
		File file = new File("/sdcard/image/temp.jpg");
		if (file.exists()) {
			Uri uri = Uri.fromFile(file);
			touxiang.setImageURI(uri);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		init();

	}

	@SuppressLint("SdCardPath")
	public void changetouxiang(View v) {
		Intent intent = new Intent(this, chooseChangetxWay.class);
		startActivity(intent);

	}

	public void show_big_touxiang(View v) {
		Intent intent = new Intent(this, showBigtx.class);
		startActivity(intent);
	}

	public void change_name(View v) {
		String name = S_name.getText().toString().trim();
		Intent intent = new Intent(this, changeName.class);
		intent.putExtra("name", name);
		startActivity(intent);
	}

	public void changesex(View v) {
		String sex = S_sex.getText().toString().trim();
		Intent intent = new Intent(this, changeSex.class);
		intent.putExtra("sex", sex);
		startActivity(intent);
	}

	public void changeid(View v) {
		String sid = S_id.getText().toString().trim();
		Intent intent = new Intent(this, changeId.class);
		intent.putExtra("sid", sid);
		startActivity(intent);
	}

	public void changeplace(View v) {
		String Splace = S_place.getText().toString().trim();
		Myapplication app = (Myapplication) getApplication();
		app.setPlace(Splace);
		Intent intent = new Intent(this, changePlace.class);
		startActivity(intent);
	}

	public void changesign(View v) {
		String sign = S_sign.getText().toString().trim();
		Intent intent = new Intent(this, changeSign.class);
		intent.putExtra("sign", sign);
		startActivity(intent);
	}

}
