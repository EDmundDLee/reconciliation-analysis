package com.rongxin.web.framework.web.service.impl;

import com.rongxin.common.utils.oss.OSSFactory;
import com.rongxin.web.framework.web.service.ISysOssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 参数配置 服务层实现
 * 
 * @author rx
 */
@Service
public class SysOssServiceImpl implements ISysOssService
{

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.FOLDER}")
    private String FOLDER;

    @Value("${oss.endpoint}")
    private String endpoint;

    public  String getImportPath() {
        return FOLDER + "import";
    }

    public  String getAvatarPath() {
        return FOLDER + "avatar";
    }

    public  String getDownloadPath() {
        return FOLDER + "download/";
    }

    public  String getUploadPath() {
        return FOLDER + "/upload";
    }
    @Override
    public String upload(MultipartFile file,String fileName,String opath) throws IOException {

        // oss中的文件夹名
        OSSFactory ossUtil = new OSSFactory();
        // 上传oss
        ossUtil.uploadFile2OSS(file.getInputStream(), endpoint, accessKeyId, accessKeySecret, bucketName, opath, fileName);
        //获取文件的URl地址
        String fileUrl = ossUtil.getImgUrl(fileName, endpoint, accessKeyId, accessKeySecret, bucketName, opath);

        return fileUrl;
    }

}
