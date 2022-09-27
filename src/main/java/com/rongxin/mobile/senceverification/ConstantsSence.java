package com.rongxin.mobile.senceverification;


/**
 * 通用常量信息
 * 
 * @author rx
 */
public class ConstantsSence
{


    /**
     * AUTHORIZATION替换为根据API_KEY和API_SECRET得到的签名认证串
     */
    public static final String AUTHORIZATION = "a4700c30cfa640f5adf269a88d1fde63";
    public static final String SECRET = "d9fb61ef7d1c4a89be59d2a530d42648";
    /**
     * 上传一些配置参数并获取当次交互活体检测H5的biztoken。
     */
    public static final String POST_URL = "https://v2-auth-api.visioncloudapi.com/v2/h5/liveness/auth_token";
    /**
     * 用姓名、身份证号到第三方核查后台查询预留照片，然后与客户通过接口上传的活体数据进行比对，来判断是否为同一个人。
     */
    public static final String POST_URLA = "https://v2-auth-api.visioncloudapi.com/identity/liveness_idnumber_verification/stateless";
    /**
     * 用于验证身份证上的姓名与身份证号是否匹配。
     */
    public static final String POST_URLB = "https://v2-auth-api.visioncloudapi.com/validity/idnumber_verification";
    /**
     * 用于识别通过接口上传的银行卡图片上的卡号和相关信息。
     */
    public static final String POST_URLC = "https://v2-auth-api.visioncloudapi.com/ocr/bankcard/stateless";
    /**
     * 用于识别通过接口上传的静态身份证图片上的文字信息。
     */
    public static final String POST_URLD = "https://v2-auth-api.visioncloudapi.com/ocr/idcard/stateless";
    /**
     * 用于识别行驶证图片上的文字信息。
     */
    public static final String POST_URLE = "https://v2-auth-api.visioncloudapi.com/ocr/vehicle_license/stateless";
    /**
     * 用于识别驾驶证图片上的文字信息。
     */
    public static final String POST_URLF =  "https://v2-auth-api.visioncloudapi.com/ocr/driving_license/stateless";
    /**
     * 用于识别营业执照上的文字信息。
     */
    public static final String POST_URLG =  "https://v2-auth-api.visioncloudapi.com/ocr/business_license/stateless";
    /**
     * 用于识别组织机构代码上的文字信息
     */
    public static final String POST_URLH =  "https://v2-auth-api.visioncloudapi.com/ocr/organization_code/stateless";
    /**
     * 用于识别图片的文字信息
     */
    public static final String POST_URLI =  "https://v2-auth-api.visioncloudapi.com/ocr/general/stateless";
    /**
     * 用姓名、身份证号到第三方核查后台查询预留照片，然后与客户通过接口上传的活体数据进行比对，来判断是否为同一个人。
     */
    public static final String POST_URLJ =  "https://v2-auth-api.visioncloudapi.com/identity/idnumber_verification/stateless";
    /**
     * 获取当次交互活体检测H5的结果。
     */
    public static final String GET_URL = "https://v2-auth-api.visioncloudapi.com/v2/h5/liveness/detection_result";

}
