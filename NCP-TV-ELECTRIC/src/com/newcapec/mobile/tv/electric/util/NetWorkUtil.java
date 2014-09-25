
package com.newcapec.mobile.tv.electric.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/** 
 * <p>Title: NetWorkUtil</p>  
 * <p>Description: </p>  
 * <p>Copyright: 新开普电子股份有限公司 Copyright (c) 2012</p>  
 * @author  朱祝元
 * @version
 * @date 创建日期：2014-7-21
 * 修改日期： 修改人： 复审人：
 */
public class NetWorkUtil {
	/**
	 * 
	 * @Title: networkIsAvailable
	 * @Description: 判断网络是否正常
	 * @category  判断网络是否正常
	 * @param context
	 * @return boolean    返回类型
	 * @throws
	 * @author 朱祝元
	 * @date 创建日期：2014-7-21
	 */
	public static boolean networkIsAvailable(Context context) {
		ConnectivityManager cManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		if (info == null) {
			return false;
		}
		if (info.isConnected()) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @Title: isWifiAvailable
	 * @Description: 判断wifi是否可用
	 * @category 判断wifi是否可用
	 * @return boolean    返回类型
	 * @throws
	 * @author 朱祝元
	 * @date 创建日期：2014-7-21
	 * 修改日期： 修改人： 复审人：
	 */
	public static boolean isWifiAvailable(Context context) {
		boolean isWifiAvailable = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			isWifiAvailable = true;
		}
		return isWifiAvailable;
	}
}
