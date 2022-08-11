package com.rongxin.demo.service;

import com.rongxin.demo.domain.FileUploadShare;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件上传明细Service接口
 * 
 * @author rx
 * @date 2022-08-08
 */
public interface IFileUploadShareService 
{
    /**
     * 查询文件上传明细
     * 
     * @param id 文件上传明细主键
     * @return 文件上传明细
     */
    public FileUploadShare selectFileUploadShareById(String id);

    /**
     * 查询文件上传明细列表
     * 
     * @param fileUploadShare 文件上传明细
     * @return 文件上传明细集合
     */
    public List<FileUploadShare> selectFileUploadShareList(FileUploadShare fileUploadShare);

    /**
     * 新增文件上传明细
     * 
     * @param files 文件列表
     * @return 结果
     */
    public int insertFileUploadShare(MultipartFile[] files) throws IOException;

    /**
     * 修改文件上传明细
     * 
     * @param fileUploadShare 文件上传明细
     * @return 结果
     */
    public int updateFileUploadShare(FileUploadShare fileUploadShare);

    /**
     * 批量删除文件上传明细
     * 
     * @param ids 需要删除的文件上传明细主键集合
     * @return 结果
     */
    public int deleteFileUploadShareByIds(String[] ids);

    /**
     * 删除文件上传明细信息
     * 
     * @param id 文件上传明细主键
     * @return 结果
     */
    public int deleteFileUploadShareById(String id);
}
