package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class place_choose extends Activity{

	private ListView place_infor;
	private LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_choose);
		
		place_infor = (ListView)findViewById(R.id.place_infor);
		
		place_infor.setAdapter(new piListener());
		
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		place_infor.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				

				if(1 == position){
					Intent intent = new Intent(place_choose.this,place_zz.class);
			        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			        startActivity(intent);
				}		
			
			}
			
		});
	}
	
	public class piListener extends BaseAdapter{

		String[] name = {"上海","河南","北京","广东","浙江","江苏","安徽","湖南","陕西","四川","山西","陕西","深圳","香港"};
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return name.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup arg2) {
			// TODO Auto-generated method stub
			
			View view = inflater.inflate(R.layout.place_infor, null);
			TextView text = (TextView)view.findViewById(R.id.sesionname);
			text.setText(name[position]);
			return view;
		}
		
	}
	
	public void back_before(View v){
		this.finish();
	}

}
