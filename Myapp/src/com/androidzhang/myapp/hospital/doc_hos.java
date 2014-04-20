package com.androidzhang.myapp.hospital;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.collected;
import com.androidzhang.myapp.order.order_choose;

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class doc_hos extends ActivityGroup {

	private LinearLayout bodyView;
	private LinearLayout one, two, three;
	private int flag = 0; // 通过标记跳转不同的页面，显示不同的菜单项
	private TextView butt_choose1;
	private TextView butt_choose2;
	private TextView butt_choose3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doc_hos);

		initMainView();
		// 显示标记页面
		showView(flag);
		one.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag = 0;
				showView(flag);
			}
		});
		two.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag = 1;
				showView(flag);
			}
		});
		three.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag = 2;
				showView(flag);
			}
		});

	}

	/*
	 * 初始化主界面
	 */
	public void initMainView() {
		bodyView = (LinearLayout) findViewById(R.id.fbody);
		one = (LinearLayout) findViewById(R.id.fone);
		two = (LinearLayout) findViewById(R.id.ftwo);
		three = (LinearLayout) findViewById(R.id.fthree);

		butt_choose1 = (TextView)findViewById(R.id.butt_choose1);
		butt_choose2 = (TextView)findViewById(R.id.butt_choose2);
		butt_choose3 = (TextView)findViewById(R.id.butt_choose3);
	}

	// 在主界面中显示其他界面
	public void showView(int flag) {
		switch (flag) {
		case 0:
			bodyView.removeAllViews();
			View v = getLocalActivityManager().startActivity("one",
					new Intent(doc_hos.this, fone.class)).getDecorView();

			butt_choose1.setBackgroundColor(Color.parseColor("#20B2AA"));
			butt_choose2.setBackgroundColor(Color.parseColor("#87CEFF"));
			butt_choose3.setBackgroundColor(Color.parseColor("#87CEFF"));
		
			bodyView.addView(v);
			break;
		case 1:
			bodyView.removeAllViews();
			bodyView.addView(getLocalActivityManager().startActivity("two",
					new Intent(doc_hos.this, ftwo.class)).getDecorView());

			butt_choose1.setBackgroundColor(Color.parseColor("#87CEFF"));
			butt_choose2.setBackgroundColor(Color.parseColor("#20B2AA"));
			butt_choose3.setBackgroundColor(Color.parseColor("#87CEFF"));
		
			break;
		case 2:
			bodyView.removeAllViews();
			bodyView.addView(getLocalActivityManager().startActivity("three",
					new Intent(doc_hos.this, fthree.class)).getDecorView());
			
			butt_choose1.setBackgroundColor(Color.parseColor("#87CEFF"));
			butt_choose2.setBackgroundColor(Color.parseColor("#87CEFF"));
			butt_choose3.setBackgroundColor(Color.parseColor("#20B2AA"));
			break;

		default:
			break;
		}
	}

	public void back_main(View v) {
		this.finish();
	}

//	public void tohos_choose(View v) {
//		butt_choose1.setBackgroundColor(Color.parseColor("#20B2AA"));
//		butt_choose2.setBackgroundColor(Color.parseColor("#87CEFF"));
//		butt_choose3.setBackgroundColor(Color.parseColor("#87CEFF"));
//
//		Intent intent = new Intent(doc_hos.this, hos_choose.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
//	}
	
	public void tohos_choose(View v) {
		butt_choose1.setBackgroundColor(Color.parseColor("#20B2AA"));
		butt_choose2.setBackgroundColor(Color.parseColor("#87CEFF"));
		butt_choose3.setBackgroundColor(Color.parseColor("#87CEFF"));

		Intent intent = new Intent(doc_hos.this, doc_hos_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	public void to_order(View v) {
		Intent intent = new Intent(doc_hos.this, order_choose.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	public void to_collected(View v) {
		
		butt_choose1.setBackgroundColor(Color.parseColor("#20B2AA"));
		butt_choose2.setBackgroundColor(Color.parseColor("#87CEFF"));
		butt_choose3.setBackgroundColor(Color.parseColor("#87CEFF"));

		Intent intent = new Intent(doc_hos.this, collected.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

}
