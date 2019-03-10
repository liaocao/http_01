package com.cookie.http_01.HttpClient;

import android.provider.Settings;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class HttpClientThread extends Thread{

    private String url;
    private String name;
    private String age;

    public HttpClientThread(String url){
        this.url = url;
    }

    public HttpClientThread(String url, String name, String age){
        this.url = url;
        this.name = name;
        this.age = age;
    }

    private void doHttpClientPost(){
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        //通过NameValuePair去存储数据
        ArrayList<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name", name));
        list.add(new BasicNameValuePair("age", age));
        try {
            //设置要发送的数据
            post.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = client.execute(post);

            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String content = EntityUtils.toString(response.getEntity());

                System.out.println("content------->"+content);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dohttpClientGet(){
        //创建HttpGet对象
        HttpGet httpGet = new HttpGet(url);
        //创建HttpClient对象
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        try {
            //发送请求
            response = client.execute(httpGet);
            //判断类型
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //取出服务器返回的数据
                String content = EntityUtils.toString(response.getEntity());

                System.out.println("content------->"+content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
//        dohttpClientGet();
        doHttpClientPost();
    }
}
