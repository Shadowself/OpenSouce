package com.newcapec.mobile.tv.electric.application;

import android.app.Application;

import com.newcapec.mobile.tv.electric.bean.Building;
import com.newcapec.mobile.tv.electric.util.PreferencesUtil;

public class SystemApplication extends Application{
	public static PreferencesUtil mPreferUtil;
	private static Building b1 = new Building();
	private static Building b2 = new Building();
	private static SystemApplication mInstance = null;
	@Override
	public void onCreate(){
		super.onCreate();
		mPreferUtil = PreferencesUtil.getPreferencesUtilInstance();
		mPreferUtil.setContext(getApplicationContext());
		mInstance = (SystemApplication)getApplicationContext();
	}
	public PreferencesUtil getPreferencesUtil(){
		return mPreferUtil;
	}
	
	public Building getFirstBuilding(){
		return b1;
	}
	public Building getSecondBuilding(){
		return b2;
	}
	
	public static SystemApplication getInstance() {
		
		return mInstance;
	}
}
