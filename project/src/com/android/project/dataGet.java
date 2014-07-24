package com.android.project;

import android.app.Application;

public class dataGet extends Application{

	private String presentid;
	private String ros;
	private String In_way = "get";

	public String getPresentid() {
		return presentid;
	}

	public void setPresentid(String presentid) {
		this.presentid = presentid;
	}

	public String getRos() {
		return ros;
	}

	public void setRos(String ros) {
		this.ros = ros;
	}

	public String getIn_way() {
		return In_way;
	}

	public void setIn_way(String in_way) {
		In_way = in_way;
	}
	
}
