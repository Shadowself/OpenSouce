package com.example.camera_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("SdCardPath")
public class CameraActivity extends Activity {

	private ImageView tupian;
	Bitmap newBitmap;

	private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";// temp
																				// file
	Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);// The Uri to store the big
													// bitmap

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);

		tupian = (ImageView) findViewById(R.id.tupian);

		File file = new File("/sdcard/image.jpg");
		if (file.exists()) {
			Uri uri = Uri.fromFile(file);
			tupian.setImageURI(uri);
		}
	}

	//显示大图像
	public void showBigpic(View v) {
		Intent intent = new Intent(this, bigMap.class);
		startActivity(intent);
	}

	// 调用相机
	public void camera_way(View v) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// action is
																	// capture
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, 3);// or TAKE_SMALL_PICTURE
	}

	// 对相机返回的相片进行裁减
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

	// 调用相册并裁减
	public void ablum_way(View v) {
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
		startActivityForResult(intent, 2);

	}

	//保存到sd卡
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


	//对返回的结果进行处理
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			switch (requestCode) {

			case 2:

				if (data != null) {
					Bitmap bp = data.getParcelableExtra("data");
					tupian.setImageBitmap(bp);
					savePhotoToSDCard("sdcard/image", "image.jpg", bp);
				} else {
					Log.e("shibai", "CHOOSE_SMALL_PICTURE: data = " + data);
				}
				break;

			case 3:
				Log.i("cg", "TAKE_SMALL_PICTURE: data = " + data);
				// TODO sent to crop
				cropImageUri(imageUri, 400, 400, 4);

				break;
			case 4:
				if (imageUri != null) {
					Bitmap bmp = decodeUriAsBitmap(imageUri);
					tupian.setImageBitmap(bmp);
					savePhotoToSDCard("sdcard/image", "image.jpg", bmp);
				} else {
					Log.e("cg", "CROP_SMALL_PICTURE: data = " + data);
				}
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
