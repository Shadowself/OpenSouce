package com.example.studentinfor;

import com.example.database.dao.Studentdao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class changeShengfen extends Activity {

	private ListView shengfen;
	private LayoutInflater inflater;

	String[] name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changeshengfen);

		shengfen = (ListView) findViewById(R.id.shengfen);

		inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);

		shengfen.setAdapter(new MyAdapter());

		shengfen.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String[] provincename = { "北京", "天津", "重庆", "浙江", "江苏", "广东",
						"福建", "湖南", "湖北", "辽宁", "吉林", "黑龙江", "河北", "河南", "安徽",
						"江西", "山东" };

				String str = provincename[position];
				Myapplication app = (Myapplication) getApplication();
				String oldplace = app.getPlace();
				Studentdao dao = new Studentdao(changeShengfen.this);
				dao.update(null, null, null, oldplace, null, "中国  " + str);

				Intent intent = new Intent(changeShengfen.this,
						StudentInforActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

			}
		});
	}

	class MyAdapter extends BaseAdapter {

		String[] provincename = { "北京", "天津", "重庆", "浙江", "江苏", "广东", "福建",
				"湖南", "湖北", "辽宁", "吉林", "黑龙江", "河北", "河南", "安徽", "江西", "山东" };

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return provincename.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup parent) {
			// TODO Auto-generated method stub

			View view = inflater.inflate(R.layout.list_shenfen, null);
			TextView text = (TextView) view.findViewById(R.id.province);
			text.setText(provincename[position]);
			return view;
		}

	}

	public void back_before(View v) {
		this.finish();
	}
}
