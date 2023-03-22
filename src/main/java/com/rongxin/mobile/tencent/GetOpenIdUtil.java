package com.rongxin.mobile.tencent;


import com.alibaba.fastjson.JSONObject;
import com.rongxin.mobile.tencent.TencentConstantValue;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @description: 微信登陆工具类
 * @author RX
 * @ClassName:GetOpenIdUtil
 * @date:2023/1/18 001816:43
 */
public class GetOpenIdUtil {
    private static final Logger log = LoggerFactory.getLogger(GetOpenIdUtil.class);

    public static void main(String[] args) throws Exception {
        //System.out.println(getOpenId("033FY9000nBDEP1HyI100wpj180FY90H"));
    }


    /**
     * 获取openId  SessionKeyID
     * @param code
     * @return
     */
    public static String getOpenId(String wxhost,String code) {
        String secret = TencentConstantValue.wxSecret;
        String appid = TencentConstantValue.wxAppId;
        //appid和secret是开发者分别是小程序ID和小程序密钥，开发者通过微信公众平台-》设置-》开发设置就可以直接获取，
        //connect_redirect=1  如果 频繁出现40029 或 code被使用的错误 在请求加上这个参数
        String url = wxhost +"/sns/jscode2session?appid="
                +appid+"&secret="+secret+"&js_code="+code+"&connect_redirect=1&grant_type=authorization_code";
        return urlCon(url);
    }
    /**
     * 获取微信token数据信息
     * @return
     */
    public static String getOpenToken(String wxhost) {
        String secret = TencentConstantValue.wxSecret;
        String appid = TencentConstantValue.wxAppId;
        //appid和secret是开发者分别是小程序ID和小程序密钥，开发者通过微信公众平台-》设置-》开发设置就可以直接获取，
        String url = wxhost + "/cgi-bin/token?grant_type=client_credential&appid="
                +appid+"&secret="+secret;
        return urlCon(url);
    }
    /**
     * 获取微信手机号信息
     * @param access_token
     * @param code
     * @return
     */
    public static String getOpenPhone(String wxhost ,String access_token, String code){
        String url = wxhost + "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + access_token;
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
     * 请求链接路径
     * @param url
     * @return
     */
    public static String urlCon(String url){
        BufferedReader in = null;
        try{
            URL weChatUrl = new URL(url);
            // 打开和URL之间的连接  跳过验证
//            HttpsURLConnection huconn=(HttpsURLConnection) weChatUrl.openConnection();
//            huconn.setHostnameVerifier(new CustomizedHostnameVerifier());
//            //连接服务器
//            huconn.connect();
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
     * 参数以raw的方式做post请求
     *
     * @param url
     * @param stringJson
     * @param headers
     * @param encode
     * @return
     */
    public static String httpPostRaw(String url, String stringJson, Map<String, String> headers, String encode) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        String str = "";
        if (encode == null) {
            encode = "utf-8";
        }
        // HttpClients.createDefault()等价于 HttpClientBuilder.create().build();
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        closeableHttpClient = HttpClients.custom()
//                .setSSLSocketFactory(new SSLConnectionSocketFactory(
//                        SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build()))
//                .build();
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
