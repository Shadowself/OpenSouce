package com.androidzhang.baidudemo;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfigeration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.MyLocationConfigeration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;

public class DistanceDemo extends Activity implements
		OnGetGeoCoderResultListener {

	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;

	MapView mMapView;
	BaiduMap mBaiduMap;
	GeoCoder mresearch = null;

	// UI相关
	boolean isFirstLoc = true;// 是否首次定位
	private EditText start, end;
	private TextView distance_id;
	private Button requestLocButton;

	// 初始化全局 bitmap 信息，不用时及时 recycle
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_st);

	private Marker mMarkerA;

	String auto_loc;
	String choose_loc;
	LatLng auto_latlng;
	LatLng choose_latlng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.distancedemo);

		start = (EditText) findViewById(R.id.start);
		end = (EditText) findViewById(R.id.end);
		distance_id = (TextView) findViewById(R.id.distance_id);
		requestLocButton = (Button) findViewById(R.id.butt1);
		requestLocButton.setText("普通");
		OnClickListener btnClickListener = new OnClickListener() {
			public void onClick(View v) {
				switch (mCurrentMode) {
				case NORMAL:
					requestLocButton.setText("跟随");
					mCurrentMode = LocationMode.FOLLOWING;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfigeration(
									mCurrentMode, true, mCurrentMarker));

					break;
				case COMPASS:
					requestLocButton.setText("普通");
					mCurrentMode = LocationMode.NORMAL;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfigeration(
									mCurrentMode, true, mCurrentMarker));
					break;
				case FOLLOWING:
					requestLocButton.setText("罗盘");
					mCurrentMode = LocationMode.COMPASS;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfigeration(
									mCurrentMode, true, mCurrentMarker));
					break;
				}
			}
		};
		requestLocButton.setOnClickListener(btnClickListener);

		mCurrentMode = LocationMode.NORMAL;

		// 地图初始化
		mMapView = (MapView) findViewById(R.id.map);
		mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(2000); // 设置发起定位请求的间隔时间为2000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy);//设置定位模式
		option.setProdName(getPackageName());
		mLocClient.setLocOption(option);
		mLocClient.start();

		// 初始化搜索模块，注册事件监听
		mresearch = GeoCoder.newInstance();
		mresearch.setOnGetGeoCodeResultListener(this);

		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			/**
			 * 地图单击事件回调函数
			 * 
			 * @param point
			 *            点击的地理坐标
			 */
			public void onMapClick(LatLng point) {
				choose_loc = "纬度： " + point.latitude + "经度 " + point.longitude;
				choose_latlng = point;
				mresearch.reverseGeoCode(new ReverseGeoCodeOption()
						.location(choose_latlng));
			}

			/**
			 * 地图内 Poi 单击事件回调函数 点击的 poi 信息
			 */
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);

			LatLng p = new LatLng(location.getLatitude(),
					location.getLongitude());
			auto_latlng = p;
			// if(location.hasRadius()){
			// Toast.makeText(getApplicationContext(),
			// "定位精度半径："+location.getRadius()+"米", Toast.LENGTH_LONG).show();
			// }
			auto_loc = location.getAddrStr();

			OverlayOptions ooA = new MarkerOptions().position(auto_latlng)
					.icon(bdA).zIndex(9);
			mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

			if (auto_loc != null) {
				start.setText(auto_loc);
			} else {
				Toast.makeText(getApplicationContext(), "定位失败",
						Toast.LENGTH_LONG).show();
			}

			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		mresearch.destroy();
		super.onDestroy();
	}

	public void distance_count(View v) {
		double m = DistanceUtil.getDistance(auto_latlng, choose_latlng);

		if (m > 1000) {
			DecimalFormat df = new DecimalFormat("#0.00");

			String kmitre = df.format(m / 1000);
			distance_id.setText(kmitre + "千米");
		} else {
			DecimalFormat df = new DecimalFormat("#0.00");

			String mitre = df.format(m);
			distance_id.setText(mitre + "米");
		}

	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		// TODO Auto-generated method stub

		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(DistanceDemo.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
					.show();
		}

		mBaiduMap.clear();
		mBaiduMap
				.addOverlay(new MarkerOptions().position(result.getLocation())
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.icon_en)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		Toast.makeText(DistanceDemo.this, result.getAddress()+"\n"+choose_loc,
				Toast.LENGTH_LONG).show();
		String address = result.getAddress();

		end.setText(address);
	}

}
