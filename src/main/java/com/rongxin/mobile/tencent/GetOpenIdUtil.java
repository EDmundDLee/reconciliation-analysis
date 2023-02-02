package com.rongxin.mobile.tencent;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @description: 微信登陆工具类
 * @author jianfei.ma
 * @ClassName:GetOpenIdUtil
 * @date:2020/2/18 001816:43
 */
public class GetOpenIdUtil {
    public static void main(String[] args){


        System.out.println("结果"+getOpenToken());
        //System.out.println("结果"+getOpenId("023YtQ000iwQmP1EBe000KYHY24YtQ0R"));
    }

    /**
     * 获取openId  SessionKeyID
     * @param code
     * @return
     */
    public static String getOpenId(String code) {
        String secret = TencentConstantValue.wxSecret;
        String appid = TencentConstantValue.wxAppId;
        BufferedReader in = null;
        //appid和secret是开发者分别是小程序ID和小程序密钥，开发者通过微信公众平台-》设置-》开发设置就可以直接获取，
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="
                +appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        return urlCon(url);

    }

    /**
     * 获取微信token数据信息
     * @return
     */
    public static String getOpenToken() {
        String secret = TencentConstantValue.wxSecret;
        String appid = TencentConstantValue.wxAppId;
        //appid和secret是开发者分别是小程序ID和小程序密钥，开发者通过微信公众平台-》设置-》开发设置就可以直接获取，
        String url=" https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                +appid+"&secret="+secret;
       return urlCon(url);
    }

    /**
     * 请求链接路径
     * @param url
     * @return
     */
    public static String urlCon(String url){
        BufferedReader in = null;
        try{
            URL weChatUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = weChatUrl.openConnection();
            // 设置通用的请求属性
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 获取微信手机号信息
     * @param access_token
     * @param code
     * @return
     */
    public static String getOpenPhone(String access_token, String code){
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + access_token;
        JSONObject body = new JSONObject();
        body.put("code", code);
        String ret = "";
        try {
              ret = httpPostRaw(url, body.toString(), null, null);
            if(ret != null && !"".equals(ret)) {
                return ret;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * 参数以raw的方式做post请求
     *
     * @param url
     * @param stringJson
     * @param headers
     * @param encode
     * @return
     */
    public static String httpPostRaw(String url, String stringJson, Map<String, String> headers, String encode) {
        String str = "";
        if (encode == null) {
            encode = "utf-8";
        }
        // HttpClients.createDefault()等价于 HttpClientBuilder.create().build();
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);

        // 设置header
        httpost.setHeader("Content-type", "application/json");
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 组织请求参数
        StringEntity stringEntity = new StringEntity(stringJson, encode);
        httpost.setEntity(stringEntity);
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            // 响应信息
            httpResponse = closeableHttpClient.execute(httpost);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            System.out.println(content);
            str = content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try { // 关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
