package com.cookie.http_01;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class RegistThread extends Thread {

    String url;
    String name;
    String age;

    public RegistThread(String url, String name, String age){
        this.url = url;
        this.name = name;
        this.age = age;
    }

    private void doGet(){
        try {
            url = url + "?name=" + URLEncoder.encode(name, "utf-8") + "&age=" + age;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;

            StringBuffer sb = new StringBuffer();
            while((str = reader.readLine()) != null){
                sb.append(str);
            }

            Log.e("RegistThread", "result:"+sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doPost(){
        try {
            Properties property = System.getProperties();
            property.list(System.out);

            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();

            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);

            OutputStream out = conn.getOutputStream();//通过output发送，将数据转换成字节发送
            String content = "name="+ name + "&age=" + age;
            out.write(content.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;

            while((str = reader.readLine()) != null){
                sb.append(str);
            }
            Log.e("RegistThread", "result:"+sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run(){
//        doGet();
        doPost();
    }
}
