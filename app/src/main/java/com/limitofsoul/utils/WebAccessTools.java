package com.limitofsoul.utils;

import android.content.Context;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * 访问网络工具类
 */
public class WebAccessTools {

    private Context context;

    public WebAccessTools(Context context) {
        this.context = context;
    }

    public String getWebContent(String url) {
        HttpGet request = new HttpGet(url);
        HttpParams params = new BasicHttpParams();
        // HttpConnectionParams.setConnectionTimeout(params, 3000);
        // HttpConnectionParams.setSoTimeout(params, 5000);
        HttpClient httpClient = new DefaultHttpClient(params);
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            } else {
                Toast.makeText(context, "There is no Internet!",
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return null;
    }

    public String postWebContent(String url, Object object) throws Exception {
        HttpPost request = new HttpPost(url);
        HttpParams params = new BasicHttpParams();
        // HttpConnectionParams.setConnectionTimeout(params, 3000);
        // HttpConnectionParams.setSoTimeout(params, 5000);
        HttpClient httpClient = new DefaultHttpClient(params);
        request.setHeader("Content-type", "application/json; charset=utf-8");
        StringEntity entity = new StringEntity(JsonUtil.objectToJson(object));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        request.setEntity(entity);
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            } else {
                Toast.makeText(context, "There is no Internet!",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return null;
    }
}