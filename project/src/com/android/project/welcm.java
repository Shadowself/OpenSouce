package com.android.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class welcm extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcm);
        Thread T = new newthread();
        T.start();
    }
    
    class newthread extends Thread{

		@Override
		public void run() {
			try {
                Thread.sleep(2500);
        
			} catch (InterruptedException e) {
                e.printStackTrace();
        
			}
        
			Intent intent = new Intent();
        
			intent.setClass(welcm.this, MainActivity.class);
        
			startActivity(intent);
        
			finish();	
		}
    }
}
