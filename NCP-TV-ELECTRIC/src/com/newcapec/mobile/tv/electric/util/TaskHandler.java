package com.newcapec.mobile.tv.electric.util;

import java.io.InputStream;
import java.util.List;

/**
 * when you call {@link Request#request(String, TaskHandler)} ,you should
 * implement this, override the {@link #onNetError()},
 * {@link #onSuccess(T result)} ,{@link #onFail()},
 * {@link #parseResult(InputStream result)}
 */
public abstract class TaskHandler<T> {
	
	/** ������� */
	public abstract void onFinish();
	
	/** network is break */
	public abstract void onNetError(String errorIP);

	/**
	 * have a successful response
	 * 
	 * @param result
	 */
	public abstract void onSuccess(T result,String ipflag);

	/** if the timeout,server error */
	public abstract void onFail(String msg,String ipflag);

	/**
	 * parse the InputStream,must be override this
	 * 
	 * @param result
	 */
	public abstract T parseResult(Object result);
	
	/**
	 * 调用失败时回调该方法，注意此方法会在后台线程中执行。
	 * @author shikeying
	 * @date 2014-6-12
	 */
	public void onError(){
		
	}
}
