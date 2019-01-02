package com.cookie.http_01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageHttpThread extends Thread{

	private String url;

	private ImageView imageView;

	private Handler handler;

	public ImageHttpThread(String url, ImageView imageView, Handler handler){
		this.url = url;
		this.imageView = imageView;
		this.handler = handler;
	}
	
	@Override
	public void run(){
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);

			InputStream in = conn.getInputStream();
			FileOutputStream out = null;
			File downloadFile = null;
			String fileName = String.valueOf(System.currentTimeMillis());
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File parent = Environment.getExternalStorageDirectory();
				downloadFile = new File(parent, fileName);

				out = new FileOutputStream(downloadFile);
			}

			byte[] b = new byte[2*1024];
			int len;

			if(out != null){
				while((len = in.read(b)) != -1){
					out.write(b, 0, len);
				}
			}

			final Bitmap bitmap = BitmapFactory.decodeFile(downloadFile.getAbsolutePath());

			handler.post(new Runnable() {
				@Override
				public void run() {
					imageView.setImageBitmap(bitmap);
				}
			});

		} catch (MalformedURLException e) {//�ܷ����URL���쳣
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
