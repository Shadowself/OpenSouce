package com.newcapec.mobile.tv.electric.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newcapec.mobile.tv.electric.R;
import com.newcapec.mobile.tv.electric.bean.Room;

public class RoomInfoListAdapter extends BaseListAdapter<Room> {
	private Context mContext;
	private String buildingName;

	public RoomInfoListAdapter(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView != null && convertView.getId() == R.id.list) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			try {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.list_item, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			holder = new ViewHolder();
			holder.building = (TextView) convertView
					.findViewById(R.id.building);
			holder.roomId = (TextView) convertView.findViewById(R.id.room_id);
			holder.odd = (TextView) convertView.findViewById(R.id.odd);
			holder.desc = (TextView) convertView.findViewById(R.id.desc);
			convertView.setTag(holder);
		}
		if (mDatas.size() > 0) {
			Room room = mDatas.get(position);
			holder.building.setText(buildingName);
			holder.roomId.setText(room.getRoomId());

			holder.odd.setText(room.getOdd());
			holder.desc.setText(room.getDesc());
			if (Double.valueOf(room.getOdd()) < 0) {
				holder.odd.setTextColor(Color.parseColor("#ff0000"));
				holder.desc.setTextColor(Color.parseColor("#ff0000"));
			}
		}
		return convertView;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	class ViewHolder {
		TextView roomId, odd, desc, building;
	}
}
