package com.rongxin.web.framework.web.service;

import com.rongxin.web.domain.SysConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


/**
 * oss 文件上传 服务层
 * 
 * @author rx
 */
public interface ISysOssService
{
    String getImportPath();

    String getAvatarPath();

    String getDownloadPath();

    String getUploadPath();

    String upload(MultipartFile file, String fileName, String opath) throws IOException;

    boolean deleteFile(String fileName, String endpoint, String accessKeyId, String accessKeySecret, String bucketName);

    List<String> deleteFileAll(List<String> keys, String endpoint, String accessKeyId, String accessKeySecret, String bucketName);

    void convertWordtoPdf(String fileName) throws FileNotFoundException;
}
