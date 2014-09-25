package com.newcapec.mobile.tv.electric.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.newcapec.mobile.tv.electric.bean.Room;
/**
 * 
* @ClassName: HttpManager 
* @Description: HTTP调用远程服务接口
* @author 朱祝元
* @date 2014-7-21 上午11:49:56 
*
 */
public class HttpManager implements Request {

	private Context context;
	private static final String TAG = HttpManager.class.getSimpleName();
	
	private static HttpManager hm =null ;
	private  HttpManager(Context context) {
		this.context = context;
	}
	public static HttpManager getInstanceHttpManager(Context context){
		if(hm == null){
			hm = new HttpManager(context);
		}
		return hm;
	}
	
	public void request(String url, TaskHandler handler,String ipflag) {
		if (context != null) {
			synchronized (this) {
				new HttpTask(context, url, 0, null, handler,ipflag).start();
			}

		}
	}

	public void request(String url, Map<String, String> params,
			TaskHandler handler,String ipflag) {
		if (context != null) {
			synchronized (this) {
				new HttpTask(context, url, 1, params, handler,ipflag).start();
			}

		}
	}

	/**task */
	private static class HttpTask extends Thread {

		Context context;
		String url;
		/** 0 is get,1 is post */
		int type = 0;
		TaskHandler handler;
		Map<String, String> params;
		String currentIp;
		
		public HttpTask(Context context, String url, int type,
				Map<String, String> params, TaskHandler handler,String ipflag) {
			this.context = context;
			this.url = url;
			this.type = type;
			this.handler = handler;
			this.params = params;
			this.currentIp = ipflag;
		}

		@Override
		public void run() {
			doInBackground();
		}

		private void onNetError(final String currentIp) {
			if (context instanceof Activity) {
				Activity act = (Activity) context;
				act.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						handler.onNetError(currentIp);
					}
				});
			} else
				handler.onFail(SystemConstants.REMAIND_MSG_SYSTEM_ERROR,this.currentIp);

		}

		protected void doInBackground() {
			// TODO Auto-generated method stub
			InputStream in = null;
			boolean flag = false;//网络异常控制变量
			if (!NetWorkUtil.networkIsAvailable(context)) {
				onNetError(this.currentIp);
				return;
			}
			if (NetWorkUtil.networkIsAvailable(context)) {//network is well
				if (type == 0) {// get
					HttpEntity entity = null;
					HttpGet get = new HttpGet(url);
					try {
						HttpResponse res = HttpFactory.execute(context, get);
						final int statusCode = res.getStatusLine()
								.getStatusCode();
						entity = res.getEntity();
						if (statusCode == HttpStatus.SC_OK && entity != null) {
							flag = true;
							in = entity.getContent();
							// System.out.println("####"
							// + EntityUtils.toString(entity));
							List<Room> list = Tools.parse(in);
							onPostExecute(list,this.currentIp);
						} else {
							get.abort();
						}
					} catch (Exception e) {
						get.abort();
					}
					finally{
						if(!flag){
							handler.onFail(SystemConstants.REMAIND_MSG_LOST_HOST,this.currentIp);
						}
					}
				} else {// post
					final HttpPost post = new HttpPost(url);
					HttpEntity entity = null;
					try {
						// add the url parameters
						List<NameValuePair> pair = new ArrayList<NameValuePair>();
						if (params != null && !params.isEmpty()) {
							for (Map.Entry<String, String> entry : params.entrySet()) {
								pair.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
							}
						}
						post.setEntity(new UrlEncodedFormEntity(pair, "UTF-8"));
						final HttpResponse response = HttpFactory.execute(
								context, post);
						final int statusCode = response.getStatusLine()
								.getStatusCode();
						if (statusCode == HttpStatus.SC_OK) {
							flag = true;
							entity = response.getEntity();
							if (entity != null) {
								in = entity.getContent();
								List<Room> list = Tools.parse(in);
								onPostExecute(list,this.currentIp);
							}
						} else {
							post.abort();
						}
					} catch (Exception e) {
						post.abort();
					}finally{
						if(!flag){
							handler.onFail(SystemConstants.REMAIND_MSG_LOST_HOST,this.currentIp);
						}
					}
				}
			}

		}

		/**handler the result*/
		protected void onPostExecute(final List<Room> list,final String currentIp) {
			if (context instanceof Activity) {
				Activity act = (Activity) context;
				act.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (list == null) {
							handler.onFail(SystemConstants.REMAIND_MSG_NO_RESULTS,currentIp);
						} else {
							handler.onSuccess(list,currentIp);
						}
						handler.onFinish();
					}
				});
			} else
				handler.onFail(SystemConstants.REMAIND_MSG_SYSTEM_ERROR,this.currentIp);
		}
	}
}
