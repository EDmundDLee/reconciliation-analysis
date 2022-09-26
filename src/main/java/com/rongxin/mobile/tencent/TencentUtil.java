package com.rongxin.mobile.tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.*;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;

public class TencentUtil
{
    private final static String SecretId = "AKIDeBVbT7YkiaRAhz7CC783qPkrkOozvd7N";
    private final static String SecretKey = "4wazA6oIbgX9WfZEepmVzEE0XpshIgqm";

    /**
     * 根据姓名 、身份证号码以及人脸验证的图片进行确认是否本人
     * @return
     */
    public static String GetDetectInfo(String biztoken) {
        String resultStr = "";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SecretId, SecretKey);
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

    /**
     * 人脸识别跳转至第三方服务接口H5界面
     * @return
     */
    public static String DetectAuth(String name,String idCard) {
        String resultStr = "";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SecretId, SecretKey);
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
            req.setIdCard(idCard);
            req.setName(name);
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

    /**
     * 实名认证
     * @param
     */
    public static String IdCardVerification(String idCard,String name) {
        String returnStr = "";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SecretId, SecretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            IdCardVerificationRequest req = new IdCardVerificationRequest();
            req.setIdCard(idCard);
            req.setName(name);
            // 返回的resp是一个IdCardVerificationResponse的实例，与请求对象对应
            IdCardVerificationResponse resp = client.IdCardVerification(req);
            // 输出json格式的字符串回包
            returnStr = IdCardVerificationResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            returnStr = e.toString();
        }
        return returnStr;
    }

    /**
     * 本接口用于校验姓名和身份证号的真实性和一致性，您可以通过输入姓名和身份证号或传入身份证人像面照片提供所需验证信息。
     * 如果只是传入身份证base64  则识别身份证信息 仅身份证正面
     * @param idCard
     * @param name
     * @param imageBase64
     * @return
     */
    public static String IdCardOCRVerification(String idCard,String name,String imageBase64 ) {
        String returnStr = "";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SecretId, SecretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            FaceidClient client = new FaceidClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            IdCardOCRVerificationRequest req = new IdCardOCRVerificationRequest();
            req.setIdCard(idCard);
            req.setName(name);
            req.setImageBase64(imageBase64);
            // 返回的resp是一个IdCardOCRVerificationResponse的实例，与请求对象对应
            IdCardOCRVerificationResponse resp = client.IdCardOCRVerification(req);
            // 输出json格式的字符串回包
            returnStr = IdCardVerificationResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            returnStr = e.toString();
        }
        return returnStr;
    }
    /**
     *
     * 身份证OCR识别
     * @param
     */
    public static String IDCardOCR(String CardSide,String ImageBase64) {
        String returnStr = "";
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SecretId, SecretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            IDCardOCRRequest req = new IDCardOCRRequest();
            req.setImageBase64(ImageBase64);
            req.setCardSide(CardSide);
            // 返回的resp是一个IDCardOCRResponse的实例，与请求对象对应
            IDCardOCRResponse resp = client.IDCardOCR(req);
            // 输出json格式的字符串回包
            returnStr = IdCardVerificationResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            returnStr = e.toString();
        }
        return returnStr;
    }
}