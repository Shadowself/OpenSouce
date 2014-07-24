package com.example.camera_test;

import java.io.File;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

public class bigMap extends Activity{

	private ImageView big_map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_big);
		
		big_map = (ImageView)findViewById(R.id.big_map);
		
		File file = new File(Environment
				.getExternalStorageDirectory() + "/image/image.jpg");
		Uri uri = Uri.fromFile(file);
		big_map.setImageURI(uri);
	}
	
	public void cancelthis(View v){
		this.finish();
	}

}
