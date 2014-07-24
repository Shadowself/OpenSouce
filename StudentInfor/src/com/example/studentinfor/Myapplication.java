package com.example.studentinfor;

import android.app.Application;

public class Myapplication extends Application {

	private String place;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
