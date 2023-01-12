package com.rongxin.cms.service.impl;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizCopyrightMapper;
import com.rongxin.cms.domain.BizCopyright;
import com.rongxin.cms.service.IBizCopyrightService;

/**
 * cms版权信息Service业务层处理
 * 
 * @author rx
 * @date 2022-10-12
 */
@Service
public class BizCopyrightServiceImpl extends ServiceImpl<BizCopyrightMapper, BizCopyright> implements IBizCopyrightService
{
    @Autowired
    private BizCopyrightMapper bizCopyrightMapper;

    /**
     * 查询cms版权信息
     * 
     * @param id cms版权信息主键
     * @return cms版权信息
     */
    @Override
    public BizCopyright selectBizCopyrightById(Long id)
    {
        return bizCopyrightMapper.selectBizCopyrightById(id);
    }

    /**
     * 查询cms版权信息列表
     * 
     * @param bizCopyright cms版权信息
     * @return cms版权信息
     */
    @Override
    public List<BizCopyright> selectBizCopyrightList(BizCopyright bizCopyright)
    {
        return bizCopyrightMapper.selectBizCopyrightList(bizCopyright);
    }

    /**
     * 新增cms版权信息
     * 
     * @param bizCopyright cms版权信息
     * @return 结果
     */
    @Override
    public int insertBizCopyright(BizCopyright bizCopyright)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        bizCopyright.setCreateDate(new Date());
        bizCopyright.setCreateId(user.getUserId());
        bizCopyright.setCreateName(user.getUserName());
        bizCopyright.setIsdel(new Long(0));
        return bizCopyrightMapper.insertBizCopyright(bizCopyright);
    }

    /**
     * 修改cms版权信息
     * 
     * @param bizCopyright cms版权信息
     * @return 结果
     */
    @Override
    public int updateBizCopyright(BizCopyright bizCopyright)
    {
        return bizCopyrightMapper.updateBizCopyright(bizCopyright);
    }

    /**
     * 批量删除cms版权信息
     * 
     * @param ids 需要删除的cms版权信息主键
     * @return 结果
     */
    @Override
    public int deleteBizCopyrightByIds(Long[] ids)
    {
        return bizCopyrightMapper.deleteBizCopyrightByIds(ids);
    }

    /**
     * 删除cms版权信息信息
     * 
     * @param id cms版权信息主键
     * @return 结果
     */
    @Override
    public int deleteBizCopyrightById(Long id)
    {
        return bizCopyrightMapper.deleteBizCopyrightById(id);
    }
}
