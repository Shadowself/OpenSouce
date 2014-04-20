package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.collected;
import com.androidzhang.myapp.dataGet;
import com.androidzhang.myapp.hospital.doc_hos;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class order_sesion_choose extends Activity {

	private ImageButton neike_result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_sesion_choose);
		neike_result = (ImageButton)findViewById(R.id.neike_result);

	}
	
	public void choose_neike(View v){
		neike_result.setBackgroundResource(R.drawable.sesion_05_02);
		dialog();
	}
	
protected void dialog() {
		
//		View v = inflater.inflate(R.layout.sesion_dialog,null);
	     AlertDialog.Builder builder = new Builder(order_sesion_choose.this);
	     builder.setTitle("                    内科");
	     
//	     builder.setView(v);
	     final String[] items = {"心血管内科","消化内科","内分泌内科","血液内科",
	    		 "神经内科","呼吸内科","肾病内科","风湿免疫科"};
		builder.setSingleChoiceItems(items, 0, new OnClickListener(){

			@Override 
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				((dataGet)getApplication()).setSesion_checked(items[which]);
			}
	    	 
	     });
	     
	     builder.setPositiveButton("预约", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(order_sesion_choose.this,order_choose.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
	
	     });
	     
	     builder.setNegativeButton("取消", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					((dataGet)getApplication()).setSesion_checked("false");
				}
		
		     });
	  
	     builder.create().show();
	}

	public void back_before(View v) {
		this.finish();
	}

	public void toorder_choose(View v) {

		Intent intent = new Intent(order_sesion_choose.this, order_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

	public void todoc_hos(View v) {

		Intent intent = new Intent(order_sesion_choose.this, doc_hos.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

	public void tocollected(View v) {

		Intent intent = new Intent(order_sesion_choose.this, collected.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

}
