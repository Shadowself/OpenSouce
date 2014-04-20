package com.androidzhang.myapp;

import com.androidzhang.myapp.daobao.Memberdao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class addmember extends Activity {

	private EditText mname;
	private EditText mphone;
	private EditText mid;
	private EditText fship;
	private EditText mdate;
	private RadioButton checkboy;
	private RadioGroup rg;
	String sex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmember);

		mname = (EditText) findViewById(R.id.mname);
		mphone = (EditText) findViewById(R.id.mphone);
		mid = (EditText) findViewById(R.id.mid);
		fship = (EditText) findViewById(R.id.fship);
		mdate = (EditText) findViewById(R.id.date);
		checkboy = (RadioButton) findViewById(R.id.checkboy);
		rg = (RadioGroup) findViewById(R.id.rg);

		rg.setOnCheckedChangeListener(new rgChangeListener());

	}

	public class rgChangeListener implements
			android.widget.RadioGroup.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup rg, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == checkboy.getId()) {
				sex = "��";
			} else {
				sex = "Ů";
			}
		}

	}

	public void back_before(View v) {
		this.finish();
	}

	public void add_sure(View v) {
		String name = mname.getText().toString().trim();
		String phone = mphone.getText().toString().trim();
		String id = mid.getText().toString().trim();
		String ship = fship.getText().toString().trim();
		String date = mdate.getText().toString().trim();
		dataGet data = (dataGet) getApplication();
		String user = data.getUphone();
		
		if(!name.equals("") && !id.equals("") && !phone.equals("") && !ship.equals("")
				&& !date.equals("")){
			Memberdao dao = new Memberdao(addmember.this);
			dao.add(name, sex, phone, id, date, ship, user);
			dialog();
		}else{
			Toast.makeText(addmember.this, "��Ϣ��д��������", Toast.LENGTH_LONG).show();
		}

	}

	protected void dialog() {
	     AlertDialog.Builder builder = new Builder(addmember.this);
	
	     builder.setMessage("                  ��ӳɹ�"); 
	     builder.setTitle("��ܰ��ʾ");
	     builder.setNeutralButton("�ر�", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
	    		Intent intent = new Intent(addmember.this,managemb.class);
	    		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    		startActivity(intent);
			}
	
	     });
	  
	     builder.create().show();
	}
}
