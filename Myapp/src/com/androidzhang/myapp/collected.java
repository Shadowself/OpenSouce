package com.androidzhang.myapp;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

@SuppressWarnings("deprecation")
public class collected extends ActivityGroup{

	private LinearLayout bodyView;
	private LinearLayout one, two, three;
	private int flag = 0; // ͨ�������ת��ͬ��ҳ�棬��ʾ��ͬ�Ĳ˵���
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collected);
		
		initMainView();
		// ��ʾ���ҳ��
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
	 * ��ʼ��������
	 */
	public void initMainView() {
		bodyView = (LinearLayout) findViewById(R.id.body);
		one = (LinearLayout) findViewById(R.id.one);
		two = (LinearLayout) findViewById(R.id.two);
		three = (LinearLayout) findViewById(R.id.three);

	}

	// ������������ʾ��������
	public void showView(int flag) {
		switch (flag) {
		case 0:
			bodyView.removeAllViews();
			View v = getLocalActivityManager().startActivity("one",
					new Intent(collected.this, OneView.class)).getDecorView();


			bodyView.addView(v);
			break;
		case 1:
			bodyView.removeAllViews();
			bodyView.addView(getLocalActivityManager().startActivity("two",
					new Intent(collected.this, TwoView.class)).getDecorView());
			

			break;
		case 2:
			bodyView.removeAllViews();
			bodyView.addView(getLocalActivityManager().startActivity("three",
					new Intent(collected.this, ThreeView.class)).getDecorView());
			break;

		default:
			break;
		}
	}

	public void back_main(View v){
		this.finish();
	}
}
