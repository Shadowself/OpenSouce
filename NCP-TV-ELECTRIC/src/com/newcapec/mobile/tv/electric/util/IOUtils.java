package com.newcapec.mobile.tv.electric.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
* @ClassName: IOUtils 
* @Description: IO帮助类 
* @author 朱祝元
* @date 2014-7-21 下午2:10:44 
*
 */
public class IOUtils {
	/**
	 * 
	 * @Title: stream2StringUTF8
	 * @Description: IO处理
	 * @category 
	 * @return String    返回类型
	 * @throws
	 * @author 朱祝元
	 * @date 创建日期：2014-7-21
	 */
	public static String stream2StringUTF8(final InputStream instream)
			throws IOException {
		final StringBuilder sb = new StringBuilder();
		try {
			final BufferedReader reader = new BufferedReader(
					new InputStreamReader(instream, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} finally {
			closeStream(instream);
		}
		return sb.toString();
	}
	
	/**Stream reverse to String*/
	public static byte[] stream2Bytes(final InputStream instream)
			throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = instream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	/**close Stream*/
	public static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				android.util.Log.e("IOUtils", "Could not close stream", e);
			}
		}
	}
}
