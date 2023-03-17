package com.rongxin.mobile.controller;

import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.mobile.tencent.ImUtil;
import com.rongxin.mobile.tencent.TLSSigAPIv2;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tencent/im")
public class TencentImController {


    @Value("${tencent.im.sdkappid}")
    private Long imsdkappid;
    @Value("${tencent.im.key}")
    private String imkey;

    /**
     * 获取IM或实时音视频的userSig
     */
    @ApiOperation("获取IM或实时音视频的userSig")
    @PostMapping("/genUserSig")
    @ResponseBody
    public AjaxResult genUserSig(String userId) throws Exception {
        Map<String,Object> map =  new HashMap<>();
        TLSSigAPIv2 tlsSigAPIv2 = new TLSSigAPIv2(imsdkappid,imkey);// TRTC 和 IM 服务中必须要使用的 UserSig
        ImUtil imUtil = new ImUtil(imsdkappid,imkey);//im 通用工具类

        //先获取管理员签名 管理员通过腾讯云控制台-账号管理-进行确认 和配置
        String adminUserSig =  tlsSigAPIv2.genUserSig("administrator",86400);
        //获取用户签名
        String commonUserSig = tlsSigAPIv2.genUserSig(userId,86400);
        //使用管理员导入用户信息，如果不导入后续获取用户信息失败 用户交互存在问题
        map.put("tencentToResult",imUtil.importTencentImUser(adminUserSig,userId));
        map.put("userSig",commonUserSig);
       return AjaxResult.success(map);
    }

}
