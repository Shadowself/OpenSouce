package com.newcapec.mobile.tv;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newcapec.mobile.tv.electric.R;
import com.newcapec.mobile.tv.electric.application.SystemApplication;
import com.newcapec.mobile.tv.electric.bean.Building;
import com.newcapec.mobile.tv.electric.util.HttpManager;
import com.newcapec.mobile.tv.electric.util.PreferencesUtil;
import com.newcapec.mobile.tv.electric.util.SystemConstants;
import com.newcapec.mobile.tv.electric.util.Tools;

public abstract class BaseActivity extends Activity {
	protected Dialog mServerSetDialog;
	protected Context mContext = this;
	protected PreferencesUtil mPreferUtil;
	protected SystemApplication application;
	protected HttpManager hm;
	protected Building building1;
	protected Building building2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		initProperties();
		initBuildingInfo();
	}

	private void initProperties() {
		mPreferUtil = ((SystemApplication) this.getApplication())
				.getPreferencesUtil();
		application = SystemApplication.getInstance();
		hm = HttpManager.getInstanceHttpManager(mContext);
		building1 = application.getFirstBuilding();
		building2 = application.getSecondBuilding();
	}

	protected void initBuildingInfo() {
		String ip1 = mPreferUtil.getString(SystemConstants.IP1, "");
		if (Tools.isRightIp(ip1)) {
			building1.setIp(ip1);
			building1.setName(mPreferUtil.getString(
					SystemConstants.BUILDINGNAME1, ""));
			building1.setIlleage(true);// ip合法
		} else {
			building1.setIp(ip1);
			building1.setIlleage(false);// ip不合法
		}

		String ip2 = mPreferUtil.getString(SystemConstants.IP2, "");
		if (Tools.isRightIp(ip2)) {
			building2.setIp(ip2);
			building2.setName(mPreferUtil.getString(
					SystemConstants.BUILDINGNAME2, ""));
			building2.setIlleage(true);// ip合法
		} else {
			building2.setIp(ip2);
			building2.setIlleage(false);// ip不合法
		}
	}

	// 处理键盘事件

	public void onServerInitCreateDialog(final Handler handler) {
		mServerSetDialog = new Dialog(mContext, R.style.MyDialogStyleBottom);
		mServerSetDialog.setCanceledOnTouchOutside(false);
		LinearLayout dialogView = (LinearLayout) getLayoutInflater().inflate(
				R.layout.serversetting_dialog, null);
		mServerSetDialog.setContentView(dialogView);
		mServerSetDialog.show();
		final EditText etIP1 = (EditText) dialogView.findViewById(R.id.etIP1);
		final EditText etIP2 = (EditText) dialogView.findViewById(R.id.etIP2);
		final EditText etIP3 = (EditText) dialogView.findViewById(R.id.etIP3);
		final EditText etIP4 = (EditText) dialogView.findViewById(R.id.etIP4);
		final EditText etIP5 = (EditText) dialogView.findViewById(R.id.etIP5);
		final EditText etIP6 = (EditText) dialogView.findViewById(R.id.etIP6);
		final EditText etIP7 = (EditText) dialogView.findViewById(R.id.etIP7);
		final EditText etIP8 = (EditText) dialogView.findViewById(R.id.etIP8);
		final TextView remindMsg = (TextView) dialogView
				.findViewById(R.id.remindMsg);
		final EditText buildName1 = (EditText) dialogView
				.findViewById(R.id.buildName1);
		final EditText buildName2 = (EditText) dialogView
				.findViewById(R.id.buildName2);
		String ip_str1 = mPreferUtil.getString(SystemConstants.IP1, "");
		String ip_str2 = mPreferUtil.getString(SystemConstants.IP2, "");
		String building_str1 = mPreferUtil.getString(
				SystemConstants.BUILDINGNAME1, "");
		String building_str2 = mPreferUtil.getString(
				SystemConstants.BUILDINGNAME2, "");
		if (ip_str1 != null && !"".equals(ip_str1)) {
			String[] ip = ip_str1.split("\\.");
			etIP1.setText(ip[0]);
			etIP2.setText(ip[1]);
			etIP3.setText(ip[2]);
			etIP4.setText(ip[3]);
			buildName1.setText(building_str1);
		}
		if (ip_str2 != null && !"".equals(ip_str2)) {
			String[] ip = mPreferUtil.getString(SystemConstants.IP2, "").split(
					"\\.");
			etIP5.setText(ip[0]);
			etIP6.setText(ip[1]);
			etIP7.setText(ip[2]);
			etIP8.setText(ip[3]);
			buildName2.setText(building_str2);
		}
		Button close = (Button) dialogView.findViewById(R.id.btnBack);
		Button confirm = (Button) dialogView.findViewById(R.id.btnConfirm);
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String ip1 = etIP1.getText().toString().trim();
				String ip2 = etIP2.getText().toString().trim();
				String ip3 = etIP3.getText().toString().trim();
				String ip4 = etIP4.getText().toString().trim();
				String ip5 = etIP5.getText().toString().trim();
				String ip6 = etIP6.getText().toString().trim();
				String ip7 = etIP7.getText().toString().trim();
				String ip8 = etIP8.getText().toString().trim();
				String firstIp = new StringBuffer("").append(ip1).append(".")
						.append(ip2).append(".").append(ip3).append(".")
						.append(ip4).toString();
				String str_buildName1 = buildName1.getText().toString().trim();
				String str_buildName2 = buildName2.getText().toString().trim();

				int i = 0;
				int j = 0;
				if (Tools.isRightIp(firstIp)) {
					mPreferUtil.saveString(SystemConstants.IP1, firstIp);
					mPreferUtil.saveString(SystemConstants.BUILDINGNAME1,
							str_buildName1);
					building1.setIp(firstIp);
					building1.setName(str_buildName1);
					building1.setIlleage(true);// ip合法
				} else {
					building1.setIp(firstIp);
					building1.setIlleage(false);// ip不合法
					remindMsg.setText("集中器1的IP地址必须输入");
					new Thread(new RemindThread(remindMsg)).start();
					return;
				}
				String secondIp = new StringBuffer("").append(ip5).append(".")
						.append(ip6).append(".").append(ip7).append(".")
						.append(ip8).toString();
				if (Tools.isRightIp(secondIp)) {
					mPreferUtil.saveString(SystemConstants.IP2, secondIp);
					mPreferUtil.saveString(SystemConstants.BUILDINGNAME2,
							str_buildName2);
					building2.setIp(secondIp);
					building2.setName(str_buildName2);
					building2.setIlleage(true);// ip合法
				} else {
					building2.setIp(secondIp);
					building2.setIlleage(false);// ip不合法
				}

				if (ip5.equals("") || ip6.equals("") || ip7.equals("")
						|| ip8.equals("")) {
					mPreferUtil.saveString(SystemConstants.IP2, null);
				}

				Message msg = new Message();
				handler.sendMessage(msg);
				mServerSetDialog.dismiss();
			}
		});
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mServerSetDialog.dismiss();
				
				Message msg = new Message();
				handler.sendMessage(msg);
				mServerSetDialog.dismiss();

			}
		});
	}

	class RemindThread implements Runnable { // thread
		private TextView view;

		public RemindThread(TextView view) {
			this.view = view;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.setText("");
		}
	}

}
