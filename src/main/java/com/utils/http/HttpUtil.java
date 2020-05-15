package com.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.utils.pojo.HttpSender;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * httputil类
 *
 * @author wanghongshen
 * @date 2020-03-01
 */
@SuppressWarnings("all")
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录

    /**
     * post请求传输json参数
     *
     * @param httpSender http请求配置
     * @param url        目标url
     * @param jsonParam  json参数
     * @return
     */
    public static JSONObject httpJsonPost(HttpSender httpSender, String url, JSONObject jsonParam) {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        httpPost.setConfig(httpSender.getConfig());
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }

            jsonResult = sendMessage(httpClient, url, httpPost);

        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * post请求传输String参数 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     *
     * @param httpSender http请求配置
     * @param url        url地址
     * @param strParam   参数
     * @return
     */
    public static JSONObject httpFormPost(HttpSender httpSender, String url, String strParam) {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(httpSender.getConfig());
        try {
            if (null != strParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(strParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            jsonResult = sendMessage(httpClient, url, httpPost);
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * 发送get请求
     *
     * @param httpSender http请求配置
     * @param url        路径
     * @return
     */
    public static JSONObject httpGet(HttpSender httpSender, String url) {
        // get请求返回结果
        JSONObject jsonResult = null;
        CloseableHttpClient client = HttpClients.createDefault();
        // 发送get请求
        HttpGet request = new HttpGet(url);
        request.setConfig(httpSender.getConfig());
        try {
            CloseableHttpResponse response = client.execute(request);

            // 请求发送成功，并得到响应
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                String strResult = EntityUtils.toString(entity, "utf-8");
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(strResult);
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        } finally {
            request.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * 发送post请求带header
     *
     * @param httpSender http请求配置
     * @param url        目标地址
     * @param headerMap  头参数
     * @param contentMap body参数
     * @return
     */
    public static JSONObject httpPostHeader(HttpSender httpSender, String url, Map<String, String> headerMap, Map<String, String> contentMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(httpSender.getConfig());
        List<NameValuePair> content = new ArrayList<NameValuePair>();
        Iterator iterator = contentMap.entrySet().iterator();           //将content生成entity
        while (iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
            content.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            Iterator headerIterator = headerMap.entrySet().iterator();          //循环增加header
            while (headerIterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) headerIterator.next();
                httpPost.addHeader(elem.getKey(), elem.getValue());
            }
            if (content.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, "UTF-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            return sendMessage(httpClient, url, httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * 文件上传
     *
     * @param url   目标地址
     * @param file  传输的文件
     * @param param 传输参数
     * @return
     */
    public static String HttpPostOfMultipartFormData(HttpSender httpSender, String url, File file, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(httpSender.getConfig());
            // 创建请求内容
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            if (file != null && file.exists() && file.isFile()) {
                multipartEntityBuilder.addBinaryBody("file", file);
            }
            if (param != null) {
                for (String key : param.keySet()) {
                    multipartEntityBuilder.addTextBody(key, param.get(key));
                }
            }
            httpPost.setEntity(multipartEntityBuilder.build());
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            logger.error("文件上传失败", e);

        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error("文件上传失败", e);
            }
        }

        return resultString;
    }

    /**
     * 发送信息
     *
     * @param httpClient
     * @param url
     * @param httpPost
     * @return
     * @throws IOException
     */
    private static JSONObject sendMessage(CloseableHttpClient httpClient, String url, HttpPost httpPost) throws IOException {
        CloseableHttpResponse result = httpClient.execute(httpPost);
        // 请求发送成功，并得到响应
        if (result != null && result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String str = "";
            // 读取服务器返回过来的json字符串数据
            str = EntityUtils.toString(result.getEntity(), "utf-8");
            // 把json字符串转换成json对象
            return JSONObject.parseObject(str);

        } else {
            logger.error("post请求提交失败:" + url);
            return null;
        }
    }

}

