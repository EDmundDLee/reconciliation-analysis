package com.rongxin.mobile.controller;

import com.alibaba.fastjson.JSONObject;
import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.core.redis.RedisCache;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.mobile.tencent.TLSSigAPIv2;
import com.rongxin.mobile.tencent.TencentUtil;
import com.rongxin.mobile.tencent.VideoSignature;
import com.rongxin.web.util.Base64Util;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tencent/util")
public class TencentUtilController {


    @Value("${tencent.im.sdkappid}")
    private Long imsdkappid;
    @Value("${tencent.im.key}")
    private String imkey;

    @Autowired
    private RedisCache redisCache;
    //   接下来这段是腾讯云普通版 H5    -------------------------------------开始
    @Log(title = "人脸识别跳转至第三方服务接口H5界面", businessType = BusinessType.UPDATE)
    @PostMapping({"getFaceResultTen"})
    @ResponseBody
    public AjaxResult getFaceResultTen(String idCard,String name){
        String result =  TencentUtil.DetectAuth(name,idCard);
        JSONObject jsonObject = JSONObject.parseObject(result);
        //此处实际应用的时候需要进行业务逻辑表支撑
        redisCache.setCacheObject("tencentBizToken", jsonObject.get("BizToken"));
        return  AjaxResult.success(result);
    }
    @Log(title = "获取人脸验证结果信息", businessType = BusinessType.UPDATE)
    @PostMapping({"checkNameIdNoAndFaceTen"})
    @ResponseBody
    public AjaxResult checkNameIdNoAndFaceTen()  {

        String tencentBizToken = redisCache.getCacheObject("tencentBizToken");
        //根据姓名 、身份证号码以及人脸验证的图片进行确认是否本人
        return  AjaxResult.success( TencentUtil.GetDetectInfo(tencentBizToken) );
    }
    @Log(title = "实名认证", businessType = BusinessType.UPDATE)
    @PostMapping({"IdCardVerification"})
    @ResponseBody
    public AjaxResult IdCardVerification(String idCard,String name)  {
        return  AjaxResult.success( TencentUtil.IdCardVerification(idCard,name) );
    }


    /**
     * 上传身份证照片（正面 FRONT  反面 BACK）
     */
    @Log(title = "身份证OCR识别", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadImageIDCardOCR")
    @ResponseBody
    public AjaxResult uploadImageIDCardOCR(@RequestParam(value="files" , required = false) MultipartFile[] files,
                                  HttpServletRequest request,
                                  @RequestParam Map<String, Object> map) throws IOException {
        //此处根据实际情况采用云上传方式
       String CardSide = map.get("CardSide").toString();
       MultipartFile file =  files[0];
        if(file!= null){
            return AjaxResult.success( TencentUtil.IDCardOCR(CardSide, Base64Util.encode(files[0].getBytes())) );
        }
        else{
            return AjaxResult.error("文件上传异常");
        }
    }

    /**
     * 获取视频云点播签名
     */
    @ApiOperation("获取视频云点播签名")
    @GetMapping("/getVideoSignature")
    public AjaxResult getVideoSignature() throws Exception {
        return AjaxResult.success(VideoSignature.getUploadSignature());
    }

}
