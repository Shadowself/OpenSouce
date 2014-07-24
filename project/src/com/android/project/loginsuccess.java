package com.android.project;

import com.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class loginsuccess extends Activity{

	private Button btn_back;
	private Button resv;
	private Button send;
	private TextView gonghao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginsuccess);
		
		btn_back = (Button)findViewById(R.id.btn_back);
		resv = (Button)findViewById(R.id.resv);
		send = (Button)findViewById(R.id.send);
		gonghao = (TextView)findViewById(R.id.gonghao);
		
		send.setOnClickListener(new sendListener());
		resv.setOnClickListener(new resvListener());
		
/*		Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent  
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据  
        String str=bundle.getString("str");//getString()返回指定key的值 
        
         */
		String str = ((dataGet)getApplication()).getPresentid();
        gonghao.setText("工号："+str);  
	}
	
	public class sendListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//调用开源框架；
			((dataGet)getApplication()).setRos("已送达郑州分公司");
			((dataGet)getApplication()).setIn_way("get");
			Intent intent = new Intent();
			intent.setClass(loginsuccess.this,CaptureActivity.class);
			startActivity(intent);
		}
		
	}
	
	public class resvListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//调用开源框架；
			((dataGet)getApplication()).setRos("已收件");
			((dataGet)getApplication()).setIn_way("get");
			Intent intent = new Intent();
			intent.setClass(loginsuccess.this,CaptureActivity.class);
			startActivity(intent);
		}
		
	}
	
	public void back_main(View v){
		this.finish();
	}
	
	public void findmail(View v){
		Intent intent = new Intent();
		intent.setClass(loginsuccess.this,chooseFindWay.class);
		startActivity(intent);
	}
	
	public void changepswd(View v){
		Intent intent = new Intent();
		intent.setClass(loginsuccess.this,changePw.class);
		startActivity(intent);
	}
	
/*	public void resv_Button(View v){
		
		//调用开源框架；
		Intent openCameraIntent = new Intent(this,CaptureActivity.class);
		startActivityForResult(openCameraIntent, 0);
	}
*/
}
