package com.androidzhang.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class welcome_02 extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_02);
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
        
			intent.setClass(welcome_02.this, MainActivity.class);
        
			startActivity(intent);
        
			finish();	
		}
    }
}
