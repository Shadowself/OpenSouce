package com.androidzhang.myapp.hospital;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.collected;
import com.androidzhang.myapp.order.order_choose;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class doc_hos_choose extends Activity{

	private ImageView neike_result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doc_hos_choose);

	}
	
	
	public void back_before(View v){
		this.finish();
	}
	

	public void changecolor(View v){
		neike_result = (ImageView)findViewById(R.id.neike_result);
		neike_result.setBackgroundResource(R.drawable.sesion_05_02);
	}
	
	public void tochoose_result(View v){
		
		Intent intent = new Intent(doc_hos_choose.this,choose_result.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}
	
    public void toorder_choose(View v){
		
		Intent intent = new Intent(doc_hos_choose.this,order_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}
    
    public void todoc_hos(View v){
		
		Intent intent = new Intent(doc_hos_choose.this,doc_hos.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}
    
    public void tocollected(View v){
		
		Intent intent = new Intent(doc_hos_choose.this,collected.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
	}

}
