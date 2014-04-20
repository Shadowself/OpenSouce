package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.dataGet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class myorder extends Activity{

	private TextView order_count;
	private TextView success_count;
	dataGet data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myorder);
		
		order_count = (TextView)findViewById(R.id.order_count);
		success_count = (TextView)findViewById(R.id.success_count);
		data= (dataGet)getApplication();
		 
		int count = data.getCount();
		
		order_count.setText("订单总数："+count);
		success_count.setText("成功预约："+count);
	}
	
	public void back_before(View v){
		this.finish();
	}

}
