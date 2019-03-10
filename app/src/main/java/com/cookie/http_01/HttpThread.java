package com.cookie.http_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Handler;
import android.webkit.WebView;

public class HttpThread extends Thread{

	private String url;
	
	private WebView webView;
	
	private Handler handler;
	
	public HttpThread(String url, WebView webView, Handler handler){
		this.url = url;
		this.webView = webView;
		this.handler = handler;
	}
	
	@Override
	public void run(){
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			
			final StringBuffer sb = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			//getInputStream拿到读入流，也就是网址的内容
			
			String str;
			while((str = reader.readLine()) != null){
				sb.append(str);
			}
			
			handler.post(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					webView.loadData(sb.toString(), "text/html;charset=utf-8", null);
				}
				
			});
		} catch (MalformedURLException e) {//能否解析URL的异常
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
