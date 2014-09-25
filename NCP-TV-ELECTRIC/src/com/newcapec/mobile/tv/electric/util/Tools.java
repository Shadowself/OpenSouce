package com.newcapec.mobile.tv.electric.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.newcapec.mobile.tv.electric.bean.Page;
import com.newcapec.mobile.tv.electric.bean.Room;

public class Tools {
	
	public static boolean isNumber(String num) {
		try {
			int testnumber = Integer.parseInt(num);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}
	public static boolean isRightIp(String ipAddress){
		String  ip="(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";   
		Pattern pattern = Pattern.compile(ip);   
		Matcher matcher = pattern.matcher(ipAddress);  
		return matcher.matches();   
	}
	
	public static List<Room> parse(InputStream is) throws Exception {
		List<Room> rooms = null;
		Room room = null;
		
//		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//		XmlPullParser parser = factory.newPullParser();
		
		XmlPullParser parser = Xml.newPullParser();	//由android.util.Xml创建一个XmlPullParser实例
    	parser.setInput(is, "UTF-8");				//设置输入流 并指明编码方式

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				rooms = new ArrayList<Room>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("room")) {
					room = new Room();
					room.setDesc(parser.getAttributeValue(1));
					room.setOdd(parser.getAttributeValue(0));
					eventType = parser.next();
					room.setRoomId(parser.getText());
				} 
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("room")) {
					rooms.add(room);
					room = null;	
				}
				break;
			}
			eventType = parser.next();
		}
		return rooms;
	}
	
	public static List<Room> getPagnationListRoom(Page page ,List<Room> list){
		List<Room> rooms = new ArrayList<Room>(); 
		for(int i =page.getBeginItemNum(); i<= page.getEndItemNum();i++){
			rooms.add(list.get(i));
		}
		return rooms;
	}
	private static List<Room> getAllRoomLists(List<Room> list1,List<Room> list2){
		return null;
	}
}
