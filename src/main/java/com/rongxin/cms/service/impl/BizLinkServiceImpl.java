package com.rongxin.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.cms.domain.BizLink;
import com.rongxin.cms.mapper.BizLinkMapper;
import com.rongxin.cms.service.IBizLinkService;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.oss.OSSFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Service业务层处理
 * 
 * @author rx
 * @date 2022-10-09
 */
@Service
public class BizLinkServiceImpl extends ServiceImpl<BizLinkMapper, BizLink> implements IBizLinkService
{
    @Autowired
    private BizLinkMapper bizLinkMapper;

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
     * 查询
     * 
     * @param id 主键
     * @return 
     */
    @Override
    public BizLink selectBizLinkById(Long id)
    {
        return bizLinkMapper.selectBizLinkById(id);
    }

    /**
     * 查询列表
     * 
     * @param bizLink 
     * @return 
     */
    @Override
    public List<BizLink> selectBizLinkList(BizLink bizLink)
    {
        return bizLinkMapper.selectBizLinkList(bizLink);
    }

    /**
     * 新增
     * 
     * @param bizLink 
     * @return 结果
     */
    @Override
    public int insertBizLink(MultipartFile file,BizLink bizLink) throws IOException
    {
        String fileUrl = "";
        if(file != null){
            String fileName = file.getOriginalFilename();
            // oss中的文件夹名
            OSSFactory ossUtil = new OSSFactory();
            // 上传oss
            ossUtil.uploadFile2OSS(file.getInputStream(), endpoint, accessKeyId, accessKeySecret, bucketName, FOLDER, fileName);
            //获取文件的URl地址
            fileUrl = ossUtil.getImgUrl(fileName, endpoint, accessKeyId, accessKeySecret, bucketName, FOLDER);
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        bizLink.setIsDel(new Long("0"));
        bizLink.setCreateDate(new Date());
        bizLink.setImgUrl(fileUrl);
        bizLink.setCreateId(Long.parseLong(user.getCreateBy()));
        bizLink.setCreateName(user.getUserName());
        return bizLinkMapper.insertBizLink(bizLink);
    }

    /**
     * 修改
     * 
     * @param bizLink 
     * @return 结果
     */
    @Override
    public int updateBizLink(MultipartFile file, BizLink bizLink) throws IOException
    {
        String fileUrl = "";
        if(file != null){
            String fileName = file.getOriginalFilename();
            // oss中的文件夹名
            OSSFactory ossUtil = new OSSFactory();
            // 上传oss
            ossUtil.uploadFile2OSS(file.getInputStream(), endpoint, accessKeyId, accessKeySecret, bucketName, FOLDER, fileName);
            //获取文件的URl地址
            fileUrl = ossUtil.getImgUrl(fileName, endpoint, accessKeyId, accessKeySecret, bucketName, FOLDER);
        }
        bizLink.setImgUrl(fileUrl);
        return bizLinkMapper.updateBizLink(bizLink);
    }

    /**
     * 批量删除
     * 
     * @param ids 需要删除的主键
     * @return 结果
     */
    @Override
    public int deleteBizLinkByIds(Long[] ids)
    {
        return bizLinkMapper.deleteBizLinkByIds(ids);
    }

    /**
     * 删除信息
     * 
     * @param id 主键
     * @return 结果
     */
    @Override
    public int deleteBizLinkById(Long id)
    {
        return bizLinkMapper.updateIsDel(id);
//        return bizLinkMapper.deleteBizLinkById(id);
    }
}
