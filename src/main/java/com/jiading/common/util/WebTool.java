package com.jiading.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.*;
import java.util.Map;

@SuppressWarnings("all")
public class WebTool {

    /**
     * 发送HttpPost请求
     *
     * @param strURL 服务地址
     * @param params json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
     * @return 成功:返回json字符串<br/>
     */
    public static String post(String strURL, String params) {
        BufferedReader reader = null;
        try {
            URL url = new URL(strURL);// 创建连接
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            //application/x-www-form-urlencoded;application/json
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuffer html = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                html.append(line);
            }
            reader.close();
            return new String(html.toString().getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "{}"; // 自定义错误信息
    }


    /**
     * 发送post请求
     */
    public static String post(String quurl, Map<String, String> params) {
        StringBuffer response = new StringBuffer("");//可拼接，线程安全
        BufferedReader reader = null;
        try {
            StringBuilder builder = new StringBuilder();//可拼接，线程不安全
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (builder.length() > 0) {
                    builder.append('&');
                }
                builder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                builder.append('=');
                builder.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] bytes = builder.toString().getBytes("UTF-8");

            URL url = new URL(quurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(bytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(bytes);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response.toString();
    }



    /**
     * post方式提交json数据
     *
     * @param url
     * @param json
     * @return
     */
    static public String callHtmlPost_json(String url, String json, Map<String, String> hm) {
        String re = "";
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            StringEntity stringentity = new StringEntity(json,
                    ContentType.create("application/json", "UTF-8"));
            if (hm != null) {
                for (String key : hm.keySet()) {
                    httppost.setHeader(key, hm.get(key));
                }
            }
            httppost.setEntity(stringentity);
            httpresponse = httpclient.execute(httppost);
            re = EntityUtils
                    .toString(httpresponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            re = "";
        }
        return re;
    }

    static public String callHtmlGet2(String url) {
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            GetMethod get = new GetMethod(url);
            httpClient.executeMethod(get);
            InputStream resInputStream = null;
            resInputStream = get.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resInputStream, "UTF-8"));
            String tempBf = null;
            StringBuffer html = new StringBuffer();
            while ((tempBf = reader.readLine()) != null) {

                html.append(tempBf);
            }
            return html.toString();
        } catch (Exception e) {
            return null;
        }
    }





}
