package com.rongxin.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.util.StringUtil;
import com.rongxin.cms.domain.BizLink;
import com.rongxin.cms.mapper.BizLinkMapper;
import com.rongxin.cms.service.IBizLinkService;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.web.framework.web.service.ISysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


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

    @Autowired
    private ISysOssService sysOssService;

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
        SysUser user = SecurityUtils.getLoginUser().getUser();
        bizLink.setIsDel(new Long("0"));
        bizLink.setCreateDate(new Date());
        if(file != null){
            bizLink.setImgUrl(uploadPicture(file));
            bizLink.setImgName(file.getOriginalFilename());
        }
        bizLink.setCreateId(user.getUserId());
        bizLink.setCreateName(user.getUserName());
        return bizLinkMapper.insertBizLink(bizLink);
    }

    /**
     * 修改
     * 
     * @param bizLink 
     * @return 结果-
     */
    @Override
    public int updateBizLink(MultipartFile file, BizLink bizLink) throws IOException
    {
         if(file == null && StringUtil.isEmpty(bizLink.getImgUrl())){
            bizLink.setImgUrl("");
            bizLink.setImgName("");
        }else if(file != null && StringUtil.isEmpty(bizLink.getImgUrl())){
            bizLink.setImgUrl(uploadPicture(file));
            bizLink.setImgName(file.getOriginalFilename());
        }
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
    }
    private String uploadPicture(MultipartFile file) throws IOException {
        String fName = file.getOriginalFilename();
        // 上传文件路径
        String filePath = sysOssService.getUploadPath();
        // 上传并返回新文件名称
        return sysOssService.upload(file, fName,filePath);
    }
}
