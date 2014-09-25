package com.newcapec.mobile.tv.electric.bean;

import java.io.Serializable;

public class Building implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5545505744311898140L;
	private String ip;
	private String name;
	private boolean netState = true;//网络状态
	private boolean running = false;//是否正在运行
	private boolean isLoaded = false;
	private boolean isIlleage = false;//ip没有或者不正确此为false
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNetState() {
		return netState;
	}
	public void setNetState(boolean netState) {
		this.netState = netState;
	}
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public boolean isLoaded() {
		return isLoaded;
	}
	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	public boolean isIlleage() {
		return isIlleage;
	}
	public void setIlleage(boolean isIlleage) {
		this.isIlleage = isIlleage;
	}
	
}
