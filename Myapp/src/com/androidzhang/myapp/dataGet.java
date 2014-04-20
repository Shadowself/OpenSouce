package com.androidzhang.myapp;

import android.app.Application;

public class dataGet extends Application{

	private String uphone;
	private String isLogin;
	private String username;
	private String place_checked;
	private String hos_checked;
	private String sesion_checked;
	private String order_date;
	private int count;
	private static final String s = "false";
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		setIsLogin(s);
		setUphone(s);
		setUsername(s);
		setPlace_checked(s);
		setHos_checked(s);
		setSesion_checked(s);
		setOrder_date(s);
		setCount(0);
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlace_checked() {
		return place_checked;
	}

	public void setPlace_checked(String place_checked) {
		this.place_checked = place_checked;
	}

	public String getHos_checked() {
		return hos_checked;
	}

	public void setHos_checked(String hos_checked) {
		this.hos_checked = hos_checked;
	}

	public String getSesion_checked() {
		return sesion_checked;
	}

	public void setSesion_checked(String sesion_checked) {
		this.sesion_checked = sesion_checked;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
