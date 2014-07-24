package com.example.studentinfor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.dao.Studentdao;

public class changeSign extends Activity {

	private EditText Ssign;
	private TextView tv;
	String oldsign;
	String new_sign;
	int num = 30;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changesign);

		Ssign = (EditText) findViewById(R.id.newsign);
		tv = (TextView) findViewById(R.id.tv);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		oldsign = bundle.getString("sign");

		Ssign.setText(oldsign);
		tv.setText("" + Ssign.length() + "/" + "30");
		Ssign.setSelection(Ssign.length());
		// ͳ������
		Ssign.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			@Override
			public void onTextChanged(CharSequence s, int start, int count,
					int end) {
				// TODO Auto-generated method stub
				temp = s;

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int end) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				tv.setText("" + s.length() + "/" + "30");
				selectionStart = Ssign.getSelectionStart();
				selectionEnd = Ssign.getSelectionEnd();
				if (temp.length() > num) {
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					Ssign.setText(s);
					Ssign.setSelection(tempSelection);// ���ù�������
				}
			}
		});
	}

	// ����
	public void updatesign(View v) {
		new_sign = Ssign.getText().toString().trim();
		if (new_sign.equals("")) {
			Toast.makeText(this, "����Ϊ�գ�", Toast.LENGTH_LONG).show();
		} else {
			Studentdao dao = new Studentdao(this);
			dao.update(null, null, null, null, oldsign, new_sign);
			Toast.makeText(this, "����ɹ���", Toast.LENGTH_LONG).show();
			this.finish();
		}

	}

	public void back_before(View v) {
		this.finish();
	}

}
