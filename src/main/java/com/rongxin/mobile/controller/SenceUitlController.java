package com.rongxin.mobile.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.common.utils.file.FileUploadUtils;
import com.rongxin.mobile.senceverification.httpSenceClientPost;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.SignatureException;
import java.util.Map;

/**
 * 商汤科技微信小程序接口
 *
 * @author rx
 * @since 2022/8/13
 */
@RestController
@RequestMapping("/sence/util")
public class SenceUitlController extends BaseController {

    /**
     * 上传身份证照片（正面 front  反面 back）
     */
    @Log(title = "上传图片", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadImage")
    @ResponseBody
    public AjaxResult uploadImage(@RequestParam(value="files" , required = false)MultipartFile[] files,
                                  HttpServletRequest request,
                                  @RequestParam Map<String, Object> map) throws IOException, SignatureException {
        //此处根据实际情况采用云上传方式
        String path = FileUploadUtils.upload("C:\\Users\\Administrator\\Desktop", files[0]);
        path = path.replace("/profile/","C:\\Users\\Administrator\\Desktop").replace("/","\\");
        return AjaxResult.success(httpSenceClientPost.getInfoFromOcrIdCard((String) map.get("mvl"),path));
    }

    @Log(title = "人脸识别跳转至第三方服务接口H5界面", businessType = BusinessType.UPDATE)
    @PostMapping({"reallyverify"})
    @ResponseBody
    public AjaxResult reallyVerify() throws IOException, SignatureException {
        return  AjaxResult.success( httpSenceClientPost.getHTbiztoken());
    }

    @Log(title = "验证姓名与身份证号码是否匹配", businessType = BusinessType.UPDATE)
    @PostMapping({"checkNameIdNo"})
    @ResponseBody
    public AjaxResult checkNameIdNo(String name,String idNumber) throws IOException, SignatureException {
        return  AjaxResult.success( httpSenceClientPost.checkNameAndIdcard(name,idNumber));
    }
    @Log(title = "获取人脸验证结果信息", businessType = BusinessType.UPDATE)
    @PostMapping({"checkNameIdNoAndFace"})
    @ResponseBody
    public AjaxResult checkNameIdNoAndFace(String biztoken,String result_id,String name,String idNumber) throws IOException, SignatureException, URISyntaxException {
        //现获取结果
         String resultOfSence = httpSenceClientPost.getDetectionResult(biztoken,result_id);
         //将结果的图片  信息获取
         JSONObject jsonObject = JSON.parseObject(resultOfSence);
         String ImageBase64 = "";
         if(jsonObject.get("base64_liveness_image")!= null){
             ImageBase64 = String.valueOf(jsonObject.get("base64_liveness_image"));
        }
         //根据姓名 、身份证号码以及人脸验证的图片进行确认是否本人
        return  AjaxResult.success( httpSenceClientPost.nameAndIdNoPhotoCheckByImage(name,idNumber, ImageBase64));
    }
}
