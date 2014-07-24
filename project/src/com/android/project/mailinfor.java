package com.android.project;

import java.util.List;

import com.android.project.daobao.Fastmaildao;
import com.android.project.domain.Fastmail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class mailinfor extends Activity{

	private TextView gonghao2;
	private ListView infor;
	private LayoutInflater inflater;
	private static final int ITEM1 = Menu.FIRST;
	String id;
	String str;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mailinfor);
		
		gonghao2 = (TextView)findViewById(R.id.gonghao2);
		
		str = ((dataGet)getApplication()).getPresentid();
		gonghao2.setText("���ţ�"+str);
        
		infor = (ListView)findViewById(R.id.infor);
		
		infor.setAdapter(new MyAdapter());
		
		inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
	
		registerForContextMenu(infor);
		
		
		//�����¼�
		infor.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Fastmaildao dao = new Fastmaildao(mailinfor.this);
				List<Fastmail> Fastmails = dao.findFast_All(str);
				Fastmail mail = Fastmails.get(position);
				String mailid = mail.getMailid();
				Intent intent = new Intent();
				intent.setClass(mailinfor.this,singleMailInfor.class);
				intent.putExtra("mailid", mailid);
				startActivity(intent);
				
			}
			
		});
	}
	
	 //�����Ĳ˵���������ͨ��������Ŀ���������Ĳ˵�
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, 
            ContextMenuInfo menuInfo) {
        //��Ӳ˵���
        menu.add(0, ITEM1, 0, "ɾ��������¼��");

    }
    
    //�˵�������Ӧ
    @Override
    public boolean onContextItemSelected(MenuItem item){
        //��ȡ��ǰ��ѡ��Ĳ˵������Ϣ
        //AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
        //Log.i("braincol",String.valueOf(info.id)); 
    	//�ҵ�position��
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int position = (int)info.position;
        switch(item.getItemId()){
        case ITEM1:
        	Fastmaildao dao = new Fastmaildao(mailinfor.this);
        	List<Fastmail> Fastmails = dao.findFast_All(str);
        	id = Fastmails.get(position).getMailid();
        	
        	Fastmaildao mail = new Fastmaildao(mailinfor.this);
			mail.deleteMail(id);
			Toast.makeText(mailinfor.this, "ɾ���ɹ���"+id, Toast.LENGTH_SHORT).show();
		
			infor.setAdapter(new MyAdapter());
            break;
        }
        return true;
    }

	public void backclick(View v){
		this.finish();
	}
	
	class MyAdapter extends BaseAdapter{
		
		Fastmaildao dao = new Fastmaildao(mailinfor.this);
		List<Fastmail> Fastmails = dao.findFast_All(str);
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Fastmails.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return Fastmails.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup parent) {
			// TODO Auto-generated method stub
/*			TextView tv = new TextView(loginsuccess.this);
			Person person = Persons.get(position);
			tv.setText(person.getUsername()+":"+person.getPswd());
*/
			View view = inflater.inflate(R.layout.listlayout, null);
			TextView umailid = (TextView)view.findViewById(R.id.umailid);

			Fastmail mail = Fastmails.get(position);
			umailid.setText("��ݺţ�"+ mail.getMailid());
			
			return view;
		}
		
	}
}
