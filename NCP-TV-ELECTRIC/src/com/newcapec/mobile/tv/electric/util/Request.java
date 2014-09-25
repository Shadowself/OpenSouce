package com.newcapec.mobile.tv.electric.util;

import java.util.Map;

/*
 * 
 */
public interface Request {
	
	void request(String url, TaskHandler handler,String ipflag);

	void request(String url, Map<String, String> params, TaskHandler handler,String ipflag);
}
