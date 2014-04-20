package com.androidzhang.myapp.order;

import com.androidzhang.myapp.R;
import com.androidzhang.myapp.dataGet;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class sesion_choose extends Activity{

	private ListView sesion_information;
	private LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sesion_choose);

		sesion_information = (ListView)findViewById(R.id.sesion_information);
		
		sesion_information.setAdapter(new sesionListener());
		
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		sesion_information.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				

				if(1 == position){
					dialog();
					}		
			
			}
			
		});
	}
	
	public class sesionListener extends BaseAdapter{

		String[] name = {"����  ��ԤԼ���� ��159��","�ڿ�  ��ԤԼ���� ��236��","���  ��ԤԼ���� ��267��","�ǿ�  ��ԤԼ���� ��156��",
				"������  ��ԤԼ���� ��254��","����  ��ԤԼ���� ��165��","�ۿ�  ��ԤԼ���� ��196��","Ƥ����  ��ԤԼ���� ��132��","���Ǻ��  ��ԤԼ���� ��276��",
				"��ҽ��  ��ԤԼ���� ��185��","��Ŀ�  ��ԤԼ���� ��183��","���겡��  ��ԤԼ���� ��286��","��Ⱦ��  ��ԤԼ���� ��196��"};
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
			
			View view = inflater.inflate(R.layout.sesion_infor, null);
			TextView text = (TextView)view.findViewById(R.id.order_sesion);
			text.setText(name[position]);
			return view;
		}
		
	}
	
	protected void dialog() {
		
//		View v = inflater.inflate(R.layout.sesion_dialog,null);
	     AlertDialog.Builder builder = new Builder(sesion_choose.this);
	     builder.setTitle("                    �ڿ�");
	     
//	     builder.setView(v);
	     final String[] items = {"��Ѫ���ڿ�","�����ڿ�","�ڷ����ڿ�","ѪҺ�ڿ�",
	    		 "���ڿ�","�����ڿ�","�����ڿ�","��ʪ���߿�"};
		builder.setSingleChoiceItems(items, 0, new OnClickListener(){

			@Override 
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				((dataGet)getApplication()).setSesion_checked(items[which]);
			}
	    	 
	     });
	     
	     builder.setPositiveButton("ԤԼ", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(sesion_choose.this,order_choose.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
	
	     });
	     
	     builder.setNegativeButton("ȡ��", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					((dataGet)getApplication()).setSesion_checked("false");
				}
		
		     });
	  
	     builder.create().show();
	}
	
	
	public void back_before(View v){
		this.finish();
	}

}
