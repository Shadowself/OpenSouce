# OpenSouce
Android 小知识点
1、下拉刷新组件：SwipeRefreshLayout  
参考：http://stormzhang.github.io/android/2014/03/29/android-swiperefreshlayout/
2、AsyncHttpClient和Universal-Image-Loader网络连接
3、Volley  网络连接
4、== 号 比较是否指向同一个堆地址，equals比较对象的值是否相等。
5、101180101.shtml  101180101   郑州天气代号
6、java文件中设置背景颜色
layout.setBackgroundColor(Color.parseColor("#9F9B9A"));
7、android换Imageview的背景图片：
SetBackgroundRe、、、();

Drawable d = mContext.getResources().getDrawable(R.drawable.avatar_my);
holder.userImage.setBackgroundDrawable(d);


Bitmap bit = BitmapFactory.decodeFile(userPhoto);
Drawable drawable = new BitmapDrawable(mContext.getResources(), bit);
imgAvatar_My.setImageDrawable(drawable);	

在代码中设置TextView与图片相对位置时，常用到如下方法：
setCompoundDrawables(left, top, right, bottom)；
setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
意思是设置Drawable显示在text的左、上、右、下位置。（Textview、Button都可以）但是两者有些区别：
8、activity设置透明
<item name="android:windowIsTranslucent">true</item>

9、TextView 水平滚动：
android:scrollHorizontally = "true"
android:focuseable= "true"
android:focuseableInTouch = "true"

String Text = "<U>Hello word<U>";
tv.setText.setText(Html.fromHtml(Text));

10、android通信机制：
1.使用handler来进行通信
2.Notifation通知栏信息
3.广播的发送与接收
4.Activity与Activity之间的转跳

11、读写文件：
   InputStream in = new FileInputStream(“”);
   InputStreamReader isr = new InputStreamReader(in);
   BufferReader br = new BufferReader(isr);

   outputStream os = new FileoutputStream(“”);
   outputStreamWriter osw = new OutputStreamWriter(os);
   BufferWriter bw = new BufferWriter(osw);

流的操作规律：
1、 明确源和目的
   源：InputStream Reader     目的：OutputStream   Writer
2、 明确数据是否为纯文本数据：
  源： 是、Reader  否：InputStream
  目的: 是 、Writer  否：outputStream
明确具体设备。
   源设备：  
　　　　硬盘：File
　　　　键盘：System.in
　　　　内存：数组
　　　　网络：socket流
   目的设备：
　　　　硬盘：File
　　　　控制台：System.out
　　　　内存：数组
　　　　网络：Socket流

双击触法事件：
if(System.currentTimeMillis() - exitTime > 2000){
	showToastMessageForShort(MSG_KEY_AGAIN);
	exitTime = System.currentTimeMillis();
} else {
}
11、去掉标题栏：
this.requestWindowFeature(Window.FEATURE_NO_TITLE);
显示软键盘：
InputMethodManager inputManager =  
               (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);  
             inputManager.showSoftInput(editText, 0);
12、	// 分享照片
	public void SharePhoto(String photoUri,final Activity activity) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		File file = new File(photoUri);
		shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		shareIntent.setType("image/jpeg");
		StartActivity(Intent.createChooser(shareIntent, activity.getTitle()));
	}


