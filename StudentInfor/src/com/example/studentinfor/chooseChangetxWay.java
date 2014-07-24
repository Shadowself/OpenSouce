package com.example.studentinfor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

@SuppressLint("SdCardPath")
public class chooseChangetxWay extends Activity {

	private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg"; // Tempt
																					// file
	Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);// The Uri to store the big
													// bitmap

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_changetx_way);
	}

	// 拍照设置头像
	public void pickfromcamera(View v) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// action is
																	// capture
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, 1);// or TAKE_SMALL_PICTURE
	}

	// 从相册取头像，并裁减
	public void pickfromalbum(View v) {

		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 200);
		intent.putExtra("scale", true);
		intent.putExtra("return-data", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		startActivityForResult(intent, 0);
	}

	// 裁减图片
	private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("return-data", false);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		startActivityForResult(intent, requestCode);
	}

	// 保存到SD卡
	public static void savePhotoToSDCard(String path, String photoName,
			Bitmap photoBitmap) {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File photoFile = new File(path, photoName); // 在指定路径下创建文件
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null) {
					if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100,
							fileOutputStream)) {
						fileOutputStream.flush();
					}
				}
			} catch (FileNotFoundException e) {
				photoFile.delete();
				e.printStackTrace();
			} catch (IOException e) {
				photoFile.delete();
				e.printStackTrace();
			} finally {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 0:

				if (data != null) {
					Bitmap bp = data.getParcelableExtra("data");
					savePhotoToSDCard("sdcard/image", "temp.jpg", bp);
				} else {
					Log.e("shibai", "CHOOSE_SMALL_PICTURE: data = " + data);
				}
				Intent intent = new Intent(chooseChangetxWay.this,
						StudentInforActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;

			case 1:
				// TODO sent to crop
				cropImageUri(imageUri, 400, 400, 2);
				break;
			case 2:
				if (imageUri != null) {
					Bitmap bmp = decodeUriAsBitmap(imageUri);
					savePhotoToSDCard("sdcard/image", "temp.jpg", bmp);
				} else {
					Log.e("cg1", "CROP_SMALL_PICTURE: data = " + data);
				}
				Intent in = new Intent(chooseChangetxWay.this,
						StudentInforActivity.class);
				in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(in);
				break;
			default:
				break;
			}
		}
	}

	private Bitmap decodeUriAsBitmap(Uri uri) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

}
