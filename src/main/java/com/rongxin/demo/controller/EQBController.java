package com.rongxin.demo.controller;

import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.eqb.req.CreateFileUploadurlReqForm;
import com.rongxin.eqb.res.EQianBaoBaseResForm;
import com.rongxin.eqb.res.GetUploadFileUrlResForm;
import com.rongxin.eqb.service.IEQBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

/**
 * 测试e签宝
 *
 * @Date 2022-06-14
 */
@RestController
@RequestMapping(value = "/api/v1.0/wuquan/test")
@Api(tags = "测试用不参与联调")
public class EQBController {

    /**
     * e签宝Service
     */
    @Resource
    IEQBService ieQianBaoService;

    /**
     * @param path
     * @return
     */
    @PostMapping(value = "/generateTemplateNo")
    @ApiOperation("后台人员手动上传文件绝对路径生成模板编号")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "鉴权token", name = "authorization", paramType = "header", dataType = "String", required = true),
            @ApiImplicitParam(value = "文件路径", name = "path", paramType = "header", dataType = "String", required = true),
    })
    public AjaxResult generateTemplateNo(@RequestHeader("path") String path) {

        // 获取文件直传地址
        File file = new File(path);
        CreateFileUploadurlReqForm bo = new CreateFileUploadurlReqForm();
        bo.setFileName("咨询报告单.pdf");
        bo.setFileSize(file.length());
        bo.setContentType("application/pdf");
        // 计算文件MD5
        String md5 = null;
        try {
            md5 = Base64.getEncoder().encodeToString(MessageDigest.getInstance("md5").digest(getBytesByFile(path)));
            System.out.println(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        bo.setContentMd5(md5);
        EQianBaoBaseResForm form = ieQianBaoService.createFileUploadurl(bo);
        Map<String, String> map = (Map<String, String>) form.getData();

        // 上传文件
        GetUploadFileUrlResForm resForm = new GetUploadFileUrlResForm();
        resForm.setFileKey(map.get("fileKey"));
        resForm.setFileMD5Value(md5);
        resForm.setUploadUrl(map.get("uploadUrl"));

        FileInputStream resourceAsStream = null;
        try {
            resourceAsStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ieQianBaoService.uploadFileByFileUrl(resForm, resourceAsStream);
        // 创建模板生成模板编号
        return AjaxResult.success(ieQianBaoService.createContractTemplateByFileKey(map.get("fileKey")));
    }

    public static byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


