package com.rongxin.mobile.tencent;

import com.rongxin.common.core.controller.BaseController;
import com.rongxin.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 腾讯云接口Controller
 * 
 * @author rx
 * @date 2022-11-22
 */
@Api(tags = "[腾讯云]")
@RestController
@RequestMapping("/mobilev/tencent")
public class TencentController extends BaseController
{

    /**
     * 获取视频点播签名
     */
    @ApiOperation("获取视频点播签名")
    @GetMapping("/getVideoSignature")
    public AjaxResult getVideoSignature() throws Exception {
        return AjaxResult.success(VideoSignature.getUploadSignature());
    }

}
