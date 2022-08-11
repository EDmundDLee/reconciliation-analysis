package com.rongxin.demo.service.impl;

import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.oss.OSSFactory;
import com.rongxin.common.utils.uuid.IdUtils;
import com.rongxin.demo.domain.FileUploadShare;
import com.rongxin.demo.mapper.FileUploadShareMapper;
import com.rongxin.demo.service.IFileUploadShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * 文件上传明细Service业务层处理
 *
 * @author rx
 * @date 2022-08-08
 */
@Service
public class FileUploadShareServiceImpl implements IFileUploadShareService
{
    @Autowired
    private FileUploadShareMapper fileUploadShareMapper;

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

    /**
     * 查询文件上传明细
     *
     * @param id 文件上传明细主键
     * @return 文件上传明细
     */
    @Override
    public FileUploadShare selectFileUploadShareById(String id)
    {
        return fileUploadShareMapper.selectFileUploadShareById(id);
    }

    /**
     * 查询文件上传明细列表
     *
     * @param fileUploadShare 文件上传明细
     * @return 文件上传明细
     */
    @Override
    public List<FileUploadShare> selectFileUploadShareList(FileUploadShare fileUploadShare)
    {
        return fileUploadShareMapper.selectFileUploadShareList(fileUploadShare);
    }

    /**
     * 新增文件上传明细
     *
     * @param files 文件列表
     * @return 结果
     */
    @Override
    public int insertFileUploadShare(MultipartFile[] files) throws IOException
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        int a = 0;
        for(int i = 0;i < files.length; i++){
            MultipartFile file = files[i];
            String fileName = file.getOriginalFilename();
            long fileSize = file.getSize();
            int begin = fileName.indexOf(".");
            String fileType = fileName.substring(begin);
            // oss中的文件夹名
            OSSFactory ossUtil = new OSSFactory();
            // 上传oss
            ossUtil.uploadFile2OSS(file.getInputStream(), endpoint, accessKeyId, accessKeySecret, bucketName, FOLDER, fileName);
            //获取文件的URl地址
            String fileUrl = ossUtil.getImgUrl(fileName, endpoint, accessKeyId, accessKeySecret, bucketName, FOLDER);

            FileUploadShare fileUploadShare = new FileUploadShare();
            fileUploadShare.setCreatedate(new Date());
            fileUploadShare.setCreateuser(user.getUserId().toString());
            fileUploadShare.setDeptcode(user.getDeptId().toString());
            fileUploadShare.setId(IdUtils.simpleUUID());
            fileUploadShare.setFileurl(fileUrl);
            fileUploadShare.setFilename(fileName);
            fileUploadShare.setFileType(fileType);
            fileUploadShare.setFilesize(fileSize+"");
            a = fileUploadShareMapper.insertFileUploadShare(fileUploadShare);
        }
        return a;
    }

    /**
     * 修改文件上传明细
     *
     * @param fileUploadShare 文件上传明细
     * @return 结果
     */
    @Override
    public int updateFileUploadShare(FileUploadShare fileUploadShare)
    {
        return fileUploadShareMapper.updateFileUploadShare(fileUploadShare);
    }

    /**
     * 批量删除文件上传明细
     *
     * @param ids 需要删除的文件上传明细主键
     * @return 结果
     */
    @Override
    public int deleteFileUploadShareByIds(String[] ids)
    {
        return fileUploadShareMapper.deleteFileUploadShareByIds(ids);
    }

    /**
     * 删除文件上传明细信息
     *
     * @param id 文件上传明细主键
     * @return 结果
     */
    @Override
    public int deleteFileUploadShareById(String id)
    {
        return fileUploadShareMapper.deleteFileUploadShareById(id);
    }

}
