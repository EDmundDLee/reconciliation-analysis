package com.rongxin.demo.controller;

import com.rongxin.common.annotation.Log;
import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.enums.BusinessType;
import com.rongxin.eqb.req.CreateFileUploadurlReqForm;
import com.rongxin.eqb.res.EQianBaoBaseResForm;
import com.rongxin.eqb.res.GetUploadFileUrlResForm;
import com.rongxin.eqb.service.IEQBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
/**
 * 测试e签宝
 *
 * @Date 2022-06-14
 */
@RestController
@RequestMapping(value = "/eqb/eqbTest")
@Api(tags = "测试用不参与联调")
public class EQBController {

    /**
     * e签宝Service
     */
//    @Resource
//    IEQBService ieQianBaoService;
    //测试环境接入地址
    @Value("${eqianbao.api.url.prefix}")
    private String eqianbaoApiUrlPrefix;
    //应用标识 id
    @Value("${eqianbao.api.app.id}")
    private String eqianbaoApiAppId;
    //秘钥
    @Value("${eqianbao.api.app.secret}")
    private String eqianbaoApiAppSecret;

    /**
     * 创建合同模板
     */
    @Value("${eqianbao.api.contract.template.create.url.suffix}")
    private String contractTemplateCreateUrlSuffix;
    //合同模板创建合同
    @Value("${eqianbao.api.contract.create.url.suffix}")
    private String contractCreateUrlSuffix;

    /**
     * 文件生成路径
     */
    @Value("${eqianbao.api.file.upload.url.suffix}")
    private String fileUploadUrlSuffix;
    //创建合同签署流程
    @Value("${eqianbao.api.sign.contract.process.create.url.suffix}")
    private String signContractProcessCreateUrlSuffix;
    //发起用户自动签署
    @Value("${eqianbao.api.user.auto.sign.contract.url.suffix}")
    private String userAutoSignContractUrlSuffix;
    //签署文件下载
    @Value("${eqianbao.api.contract.download.url.suffix}")
    private String contractDownloadUrlSuffix;
    //归档流程
    @Value("${eqianbao.api.contract.contract.url.suffix}")
    private String contractContractUrlSuffix;


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
//        EQianBaoBaseResForm createFileUploadurl(CreateFileUploadurlReqForm reqform, String eqianbaoApiUrlPrefix, String eqianbaoApiAppId, String eqianbaoApiAppSecret);
        IEQBService ieQianBaoService = new IEQBService();
        EQianBaoBaseResForm form = ieQianBaoService.createFileUploadurl(bo, eqianbaoApiUrlPrefix, eqianbaoApiAppId, eqianbaoApiAppSecret, fileUploadUrlSuffix);
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
        ieQianBaoService.uploadFileByFileUrl(resForm, resourceAsStream, eqianbaoApiAppId, eqianbaoApiAppSecret);
        // 创建模板生成模板编号
        return AjaxResult.success(ieQianBaoService.createContractTemplateByFileKey(map.get("fileKey"),eqianbaoApiUrlPrefix, eqianbaoApiAppId, eqianbaoApiAppSecret, contractTemplateCreateUrlSuffix));
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

    /**
     * @param flowId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('eqb:eqbTest:signContractDownload')")
    @GetMapping(value = "/signContractDownload")
    @ApiOperation("测试下载文件接口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "鉴权token", name = "authorization", paramType = "header", dataType = "String", required = true),
            @ApiImplicitParam(value = "文件编码", name = "flowId", paramType = "header", dataType = "String", required = true),
    })
    public AjaxResult signContractDownload(String flowId) {
        System.out.println(flowId);
        IEQBService ieQianBaoService = new IEQBService();
        return AjaxResult.success(ieQianBaoService.signContractDownload("1c1009bed17c48ce94421e5112041673", eqianbaoApiUrlPrefix, eqianbaoApiAppId, eqianbaoApiAppSecret, contractDownloadUrlSuffix));
    }
}


