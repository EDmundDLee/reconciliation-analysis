package com.rongxin.mobile.tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.*;

public class TencentUtil
{
    public static String GetDetectInfo(String biztoken) {
        String resultStr = "";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential("AKIDeBVbT7YkiaRAhz7CC783qPkrkOozvd7N", "4wazA6oIbgX9WfZEepmVzEE0XpshIgqm");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            GetDetectInfoEnhancedRequest req = new GetDetectInfoEnhancedRequest();
            req.setBizToken(biztoken);
            req.setRuleId("1");
            // 返回的resp是一个GetDetectInfoEnhancedResponse的实例，与请求对象对应
            GetDetectInfoEnhancedResponse resp = client.GetDetectInfoEnhanced(req);
            // 输出json格式的字符串回包
            System.out.println(GetDetectInfoEnhancedResponse.toJsonString(resp));
            resultStr = GetDetectInfoEnhancedResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            resultStr = e.toString();
        }
        return resultStr;
    }


    public static String DetectAuth() {
        String resultStr = "";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential("AKIDeBVbT7YkiaRAhz7CC783qPkrkOozvd7N", "4wazA6oIbgX9WfZEepmVzEE0XpshIgqm");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DetectAuthRequest req = new DetectAuthRequest();
            req.setIdCard("230506198802030215");
            req.setName("高玉坤");
            req.setRuleId("1");
            req.setRedirectUrl("https://dev-jlrx-backend-basic.cacfintech.com/twxredirect.html");
            // 返回的resp是一个DetectAuthResponse的实例，与请求对象对应
            DetectAuthResponse resp = client.DetectAuth(req);
            // 输出json格式的字符串回包
            System.out.println(DetectAuthResponse.toJsonString(resp));
            resultStr = DetectAuthResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            resultStr =  e.toString();
        }
        return resultStr;
    }
}