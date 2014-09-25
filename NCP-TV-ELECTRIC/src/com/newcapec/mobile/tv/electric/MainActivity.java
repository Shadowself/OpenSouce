package com.newcapec.mobile.tv.electric;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newcapec.mobile.tv.BaseActivity;
import com.newcapec.mobile.tv.electric.adapter.RoomInfoListAdapter;
import com.newcapec.mobile.tv.electric.bean.Page;
import com.newcapec.mobile.tv.electric.bean.Room;
import com.newcapec.mobile.tv.electric.util.TaskHandler;
import com.newcapec.mobile.tv.electric.util.Tools;

public class MainActivity extends BaseActivity {

	private ListView desc;
	private RoomInfoListAdapter adapter;
	private Page page = new Page();
	private List<Room> roomList;

	private TextView pageNum;
	private TextView NetWorkFalse; // 显示网络异常

	public static int RUNNING = 0;
	public static int STOPPED = 1;
	public static int SUSPEND = 2;
	private int paginationThreadCanRunningState;// 运行状态0，本次运行结束1，停止状态2

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		desc = (ListView) findViewById(R.id.list);
		adapter = new RoomInfoListAdapter(mContext);
		desc.setAdapter(adapter);
		pageNum = (TextView) findViewById(R.id.pageNum);
		NetWorkFalse = (TextView) findViewById(R.id.NetWorkFalse);

		getRequestFlag();
	}

	private void getRequestFlag() {
		if (building1.isIlleage()) {
			hm.request("http://" + building1.getIp() + ":8080/lowelec.xml",
					new myHander(), "1");
		} else if (building2.isIlleage()) {
			hm.request("http://" + building2.getIp() + ":8080/lowelec.xml",
					new myHander(), "2");
		} else {
			// 1号楼和2号楼的IP都不合法就会打开IP设置的界面
			onServerInitCreateDialog(handlerInitListRoom);
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			paginationThreadCanRunningState = SUSPEND;
			onServerInitCreateDialog(handlerInitListRoom);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	class myHander extends TaskHandler {

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onNetError(String errorIp) {
			// TODO Auto-generated method stub
			Looper.prepare();

			Message msg = new Message();
			if (errorIp.equals("1")) {
				Toast.makeText(mContext, "集中器1网络异常", Toast.LENGTH_LONG).show();
				building1.setNetState(false);// 网络断开
				building1.setLoaded(false);// 未加载
				msg.obj = "1";
				if (paginationThreadCanRunningState == SUSPEND) {
					return;
				} else {
					handlerNetWork.sendMessage(msg);
				}
			}

			if (errorIp.equals("2")) {
				Toast.makeText(mContext, "集中器2网络异常", Toast.LENGTH_LONG).show();
				building2.setNetState(false);// 网络断开
				building2.setLoaded(false);// 未加载
				msg.obj = "2";
				if (paginationThreadCanRunningState == SUSPEND) {
					return;
				} else {
					handlerNetWork.sendMessage(msg);
				}
			}
			Looper.loop();
		}

		@Override
		public void onSuccess(Object result, String ipflag) {
			// TODO Auto-generated method stub
			if (paginationThreadCanRunningState == SUSPEND) {
				return;
			}
			paginationThreadCanRunningState = RUNNING;
			String netWork = NetWorkFalse.getText().toString().trim();

			if (ipflag.equals("1")) {
				building1.setRunning(true);
				building2.setRunning(false);
				if ("集中器1网络异常".equals(netWork) || !building2.isIlleage()) {
					NetWorkFalse.setText("");
				}
				// isIp1Work = true;
				// NetWorkFalse.setText(building1.getName()+"号楼");
				adapter.setBuildingName(building1.getName());
			}

			if (ipflag.equals("2")) {
				building2.setRunning(true);
				building1.setRunning(false);
				if ("集中器2网络异常".equals(netWork)) {
					NetWorkFalse.setText("");
				}
				// isIp2Work = true;
				// NetWorkFalse.setText(building2.getName()+"号楼");
				adapter.setBuildingName(building2.getName());
			}

			// NetWorkFalse.setText("");
			if (roomList != null)
				roomList.clear();
			roomList = (List<Room>) result;
			page.setCurrentPage(1);
			page.setTotalNums(roomList.size());
			page.initPagnationParam();

			List<Room> list = Tools.getPagnationListRoom(page, roomList);
			pageNum.setText(page.getCurrentPage() + "/" + page.getTotoalPages()
					+ "页");
			adapter.clearAndAppendData(list);
			new Thread(new MyThread()).start();
		}

		@Override
		public void onFail(String msg, String ipflag) {
			// TODO Auto-generated method stub
			Looper.prepare();

			Message msgHandler = new Message();
			if (ipflag.equals("1")) {
				Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
				building1.setNetState(false);// 网络断开
				building1.setLoaded(false);// 未加载
				msgHandler.obj = "1";
				if (paginationThreadCanRunningState == SUSPEND) {
					return;
				} else {
					handlerNetWork.sendMessage(msgHandler);
				}
			}

			if (ipflag.equals("2")) {
				Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
				building2.setNetState(false);// 网络断开
				building2.setLoaded(false);// 未加载
				msgHandler.obj = "2";
				if (paginationThreadCanRunningState == SUSPEND) {
					return;
				} else {
					handlerNetWork.sendMessage(msgHandler);
				}
			}
			Looper.loop();

		}

		@SuppressWarnings("unchecked")
		@Override
		public Object parseResult(Object result) {
			return null;
		}

	}

	public class MyThread implements Runnable { // thread
		@Override
		public void run() {

			int i = page.getTotoalPages() - 1;
			// int i = 5;
			for (; i >= 0; i--) {
				try {
					Thread.sleep(5000); // sleep 5000ms
					Message message = new Message();
					message.obj = i;
					handlerPagination.sendMessage(message);
				} catch (Exception e) {
				}
				if (paginationThreadCanRunningState != RUNNING) {
					i = -1;
				}
			}
			if (paginationThreadCanRunningState == RUNNING) {
				paginationThreadCanRunningState = STOPPED;
				handlerNextBuilding.sendMessage(new Message());
			}
		}
	}

	final Handler handlerNextBuilding = new Handler() {
		public void handleMessage(Message msg) {
			if (building1.isRunning()) {
				if (building2.isIlleage()) {
					hm.request("http://" + building2.getIp()
							+ ":8080/lowelec.xml", new myHander(), "2");
				} else {
					// try {
					// Thread.sleep(6000);
					// } catch (InterruptedException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					hm.request("http://" + building1.getIp()
							+ ":8080/lowelec.xml", new myHander(), "1");
				}
			} else if (building2.isRunning()) {
				if (building1.isIlleage()) {
					hm.request("http://" + building1.getIp()
							+ ":8080/lowelec.xml", new myHander(), "1");
				} else {
					// try {
					// Thread.sleep(6000);
					// } catch (InterruptedException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					hm.request("http://" + building2.getIp()
							+ ":8080/lowelec.xml", new myHander(), "2");
				}
			}
		}
	};
	final Handler handlerPagination = new Handler() {
		public void handleMessage(Message msg) {

			page.setCurrentPage(page.getCurrentPage() + 1);

			page.initPagnationParam();
			List<Room> list = Tools.getPagnationListRoom(page, roomList);
			if (page.getCurrentPage() <= page.getTotoalPages()) {
				pageNum.setText(page.getCurrentPage() + "/"
						+ page.getTotoalPages() + "页");
				adapter.clearAndAppendData(list);
			}

			super.handleMessage(msg);
		}
	};
	final Handler handlerInitListRoom = new Handler() {
		public void handleMessage(Message msg) {
			
			paginationThreadCanRunningState = RUNNING;
			// 因为设置了IP1必须存在所以每一次点击IP设置完成后都要先请求IP1
			hm.request("http://" + building1.getIp() + ":8080/lowelec.xml",
					new myHander(), "1");

			super.handleMessage(msg);
		}
	};

	// 检测网络和服务器状态
	final Handler handlerNetWork = new Handler() {
		public void handleMessage(Message msg) {
			if ("1".equals(msg.obj.toString())) {
				if (building2.isIlleage()) {
					Toast.makeText(mContext, "正在切换到楼号2", Toast.LENGTH_LONG)
							.show();
					NetWorkFalse.setText("集中器1网络异常");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					hm.request("http://" + building2.getIp()
							+ ":8080/lowelec.xml", new myHander(), "2");

				} else {
					NetWorkFalse.setText("集中器1网络异常");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Log.i("ip1加载", "加载、、、");
					hm.request("http://" + building1.getIp()
							+ ":8080/lowelec.xml", new myHander(), "1");

				}
			}
			if ("2".equals(msg.obj.toString())) {
				if (building1.isIlleage()) {
					Toast.makeText(mContext, "正在切换到楼号1", Toast.LENGTH_LONG)
							.show();
					NetWorkFalse.setText("集中器2网络异常");
					if (paginationThreadCanRunningState != SUSPEND) {
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						hm.request("http://" + building1.getIp()
								+ ":8080/lowelec.xml", new myHander(), "1");
					}
				}
			}
			super.handleMessage(msg);
		}

	};
}
