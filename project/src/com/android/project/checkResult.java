package com.android.project;

import com.android.project.daobao.Fastmaildao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class checkResult extends Activity{

	private Button sure,wrong;
	private TextView danhao;
	private TextView gonghao1,textView1;
	String str;
	String text;
	String num;
	String fstime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkresult);
		
		sure = (Button)findViewById(R.id.sure);
		wrong = (Button)findViewById(R.id.wrong);
		
		danhao = (TextView)findViewById(R.id.danhao);
		gonghao1 = (TextView)findViewById(R.id.gonghao1);
		textView1 = (TextView)findViewById(R.id.textView1);
		
		str = ((dataGet)getApplication()).getPresentid();
        gonghao1.setText("工号："+str); 
        
        text = ((dataGet)getApplication()).getRos();
        textView1.setText(text+"扫描结果单号");
		
  		Intent intent=getIntent();         //getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent  
        Bundle bundle=intent.getExtras();  //.getExtras()得到intent所附带的额外数据  
        num = bundle.getString("num");     //getString()返回指定key的值 
        
        danhao.setText(num);
		
 //       MyDbOpenHelper helper = new MyDbOpenHelper(this);
//		helper.getReadableDatabase();
        
        sure.setOnClickListener(new sureListener());
	}
	
	public void back_surface(View v){
		this.finish();
	}

	public class sureListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		    Time time = new Time();       
	        time.setToNow();      
	        int year = time.year;      
	        int month = time.month+1;      
	        int day = time.monthDay;      
	        int minute = time.minute;      
	        int hour = time.hour;      
	        int sec = time.second;      
	        fstime = year+"-"+month+"-"+day+" "+hour+":"+minute + ":"+sec; 
			
	        Fastmaildao fast = new Fastmaildao(checkResult.this);
//	        if(fast.find(num) != null){
//	        	fast.changemail(num,text,fstime,str);
//	        }
//	        else{
		        
//		        fast.add(num,text,fstime,str);
//	        }

	        fast.add(num,text,fstime,str);
	        
	        if(fast.find(num) == fstime){
	        	Toast.makeText(checkResult.this, "add 失败！", Toast.LENGTH_LONG).show();
	        }else{
	        	Toast.makeText(checkResult.this, "add 成功！", Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.setClass(checkResult.this,mailinfor.class);
				startActivity(intent);
				checkResult.this.finish();
	        }
		}
		
	}

	
}
