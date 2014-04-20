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
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class make_sure extends Activity {

	private TextView sesion;
	private TextView orderdata;
	private TextView payway;
	private TextView choose_name;
	dataGet data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.make_sure);

		sesion = (TextView) findViewById(R.id.sesion);
		orderdata = (TextView) findViewById(R.id.orderdata);

		data = (dataGet) getApplication();

		String check_sesion = data.getSesion_checked();
		String order_data = data.getOrder_date();

		sesion.setText(check_sesion);
		orderdata.setText(order_data);
	}

	public void back_before(View v) {
		this.finish();
	}

	public void toorder_success(View v) {
		int c = data.getCount();
		((dataGet) getApplication()).setCount(c + 1);
		((dataGet) getApplication()).setHos_checked("false");
		((dataGet) getApplication()).setPlace_checked("false");
		((dataGet) getApplication()).setSesion_checked("false");
		Intent intent = new Intent(make_sure.this, order_success.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	public void tochoose_payway(View v) {
		dialog1();
	}

	public void tochoose_person(View v) {
		dialog();
	}

	protected void dialog1() {

		// View v = inflater.inflate(R.layout.sesion_dialog,null);
		AlertDialog.Builder builder = new Builder(make_sure.this);

		// builder.setView(v);
		final String[] items = { "到院支付", "网上支付" };
		builder.setSingleChoiceItems(items, 0, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				payway = (TextView) findViewById(R.id.payway);
				if (which == 0) {
					payway.setText("到院支付");
					payway.setTextColor(Color.parseColor("#1874CD"));
				} else {
					payway.setText("网上支付");
					payway.setTextColor(Color.parseColor("#1874CD"));
				}
				dialog.dismiss();
			}

		});

		builder.create().show();
	}
	
	protected void dialog() {

		// View v = inflater.inflate(R.layout.sesion_dialog,null);
		AlertDialog.Builder builder = new Builder(make_sure.this);

		// builder.setView(v);
		final String[] items = { "彭丹", "彭华" };
		builder.setSingleChoiceItems(items, 0, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				choose_name = (TextView) findViewById(R.id.choose_name);
				if (which == 0) {
					choose_name.setText("彭丹");
					choose_name.setTextColor(Color.parseColor("#1874CD"));
				} else {
					choose_name.setText("彭华");
					choose_name.setTextColor(Color.parseColor("#1874CD"));
				}
				dialog.dismiss();
			}

		});

		builder.create().show();
	}

	public void toorder_choose(View v){
		Intent intent = new Intent();
		intent.setClass(make_sure.this,order_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void todoc_hos(View v){
		Intent intent = new Intent();
		intent.setClass(make_sure.this,doc_hos.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void tocollected(View v){
		Intent intent = new Intent();
		intent.setClass(make_sure.this,collected.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
