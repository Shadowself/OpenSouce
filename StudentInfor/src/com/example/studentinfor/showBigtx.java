package com.example.studentinfor;

import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

public class showBigtx extends Activity {

	private ImageView big_tx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showbigtx);

		big_tx = (ImageView) findViewById(R.id.big_tx);

		File file = new File(Environment.getExternalStorageDirectory()
				+ "/image/temp.jpg");
		Uri uri = Uri.fromFile(file);
		big_tx.setImageURI(uri);
	}

	public void cancelthis(View v) {
		this.finish();
	}

}
