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

	// ��λ���
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;

	MapView mMapView;
	BaiduMap mBaiduMap;
	GeoCoder mresearch = null;

	// UI���
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ
	private EditText start, end;
	private TextView distance_id;
	private Button requestLocButton;

	// ��ʼ��ȫ�� bitmap ��Ϣ������ʱ��ʱ recycle
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
		requestLocButton.setText("��ͨ");
		OnClickListener btnClickListener = new OnClickListener() {
			public void onClick(View v) {
				switch (mCurrentMode) {
				case NORMAL:
					requestLocButton.setText("����");
					mCurrentMode = LocationMode.FOLLOWING;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfigeration(
									mCurrentMode, true, mCurrentMarker));

					break;
				case COMPASS:
					requestLocButton.setText("��ͨ");
					mCurrentMode = LocationMode.NORMAL;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfigeration(
									mCurrentMode, true, mCurrentMarker));
					break;
				case FOLLOWING:
					requestLocButton.setText("����");
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

		// ��ͼ��ʼ��
		mMapView = (MapView) findViewById(R.id.map);
		mBaiduMap = mMapView.getMap();
		// ������λͼ��
		mBaiduMap.setMyLocationEnabled(true);
		// ��λ��ʼ��
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// ��gps
		option.setCoorType("bd09ll"); // ������������
		option.setScanSpan(2000); // ���÷���λ����ļ��ʱ��Ϊ2000ms
		option.setIsNeedAddress(true);// ���صĶ�λ���������ַ��Ϣ
		option.setNeedDeviceDirect(true);// ���صĶ�λ��������ֻ���ͷ�ķ���
		option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy);//���ö�λģʽ
		option.setProdName(getPackageName());
		mLocClient.setLocOption(option);
		mLocClient.start();

		// ��ʼ������ģ�飬ע���¼�����
		mresearch = GeoCoder.newInstance();
		mresearch.setOnGetGeoCodeResultListener(this);

		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			/**
			 * ��ͼ�����¼��ص�����
			 * 
			 * @param point
			 *            ����ĵ�������
			 */
			public void onMapClick(LatLng point) {
				choose_loc = "γ�ȣ� " + point.latitude + "���� " + point.longitude;
				choose_latlng = point;
				mresearch.reverseGeoCode(new ReverseGeoCodeOption()
						.location(choose_latlng));
			}

			/**
			 * ��ͼ�� Poi �����¼��ص����� ����� poi ��Ϣ
			 */
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);

			LatLng p = new LatLng(location.getLatitude(),
					location.getLongitude());
			auto_latlng = p;
			// if(location.hasRadius()){
			// Toast.makeText(getApplicationContext(),
			// "��λ���Ȱ뾶��"+location.getRadius()+"��", Toast.LENGTH_LONG).show();
			// }
			auto_loc = location.getAddrStr();

			OverlayOptions ooA = new MarkerOptions().position(auto_latlng)
					.icon(bdA).zIndex(9);
			mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

			if (auto_loc != null) {
				start.setText(auto_loc);
			} else {
				Toast.makeText(getApplicationContext(), "��λʧ��",
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
		// �˳�ʱ���ٶ�λ
		mLocClient.stop();
		// �رն�λͼ��
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
			distance_id.setText(kmitre + "ǧ��");
		} else {
			DecimalFormat df = new DecimalFormat("#0.00");

			String mitre = df.format(m);
			distance_id.setText(mitre + "��");
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
			Toast.makeText(DistanceDemo.this, "��Ǹ��δ���ҵ����", Toast.LENGTH_LONG)
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
