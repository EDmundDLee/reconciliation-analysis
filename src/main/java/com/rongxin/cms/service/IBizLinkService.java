package com.rongxin.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizLink;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service接口
 * 
 * @author rx
 * @date 2022-10-09
 */
public interface IBizLinkService extends IService<BizLink>
{
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    public BizLink selectBizLinkById(Long id);

    /**
     * 查询列表
     *
     * @param bizLink
     * @return 集合
     */
    public List<BizLink> selectBizLinkList(BizLink bizLink);

    /**
     * 新增
     *
     * @param bizLink
     * @return 结果
     */
    public int insertBizLink(MultipartFile file, BizLink bizLink) throws IOException;

    /**
     * 修改
     *
     * @param bizLink
     * @return 结果
     */
    public int updateBizLink(MultipartFile file,BizLink bizLink) throws IOException;

    /**
     * 批量删除
     *
     * @param ids 需要删除的主键集合
     * @return 结果
     */
    public int deleteBizLinkByIds(Long[] ids);

    /**
     * 删除信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deleteBizLinkById(Long id);
}
