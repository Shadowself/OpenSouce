package com.androidzhang.baidudemo;

import com.baidu.mapapi.map.MapView;

import android.app.Activity;
import android.os.Bundle;

public class BaseMapDemo extends Activity{

	private MapView mMapView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basemapdemo);
		
		// 获取地图控件引用
				mMapView = (MapView) findViewById(R.id.bmapView);
				
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}


}
