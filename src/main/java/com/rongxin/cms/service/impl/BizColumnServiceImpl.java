package com.rongxin.cms.service.impl;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.cms.mapper.BizColumnMapper;
import com.rongxin.cms.domain.BizColumn;
import com.rongxin.cms.service.IBizColumnService;

/**
 * 栏目类别Service业务层处理
 * 
 * @author rx
 * @date 2022-10-10
 */
@Service
public class BizColumnServiceImpl extends ServiceImpl<BizColumnMapper, BizColumn> implements IBizColumnService
{
    @Autowired
    private BizColumnMapper bizColumnMapper;

    /**
     * 查询栏目类别
     * 
     * @param id 栏目类别主键
     * @return 栏目类别
     */
    @Override
    public BizColumn selectBizColumnById(Long id)
    {
        return bizColumnMapper.selectBizColumnById(id);
    }

    /**
     * 查询栏目类别列表
     * 
     * @param bizColumn 栏目类别
     * @return 栏目类别
     */
    @Override
    public List<BizColumn> selectBizColumnList(BizColumn bizColumn)
    {
        return bizColumnMapper.selectBizColumnList(bizColumn);
    }

    /**
     * 新增栏目类别
     * 
     * @param bizColumn 栏目类别
     * @return 结果
     */
    @Override
    public int insertBizColumn(BizColumn bizColumn)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        bizColumn.setColDelete(0);
        bizColumn.setCreateId(user.getUserId());
        bizColumn.setCreateName(user.getUserName());
        bizColumn.setCreateDate(new Date());
        return bizColumnMapper.insertBizColumn(bizColumn);
    }

    /**
     * 修改栏目类别
     * 
     * @param bizColumn 栏目类别
     * @return 结果
     */
    @Override
    public int updateBizColumn(BizColumn bizColumn)
    {
        return bizColumnMapper.updateBizColumn(bizColumn);
    }

    /**
     * 批量删除栏目类别
     * 
     * @param ids 需要删除的栏目类别主键
     * @return 结果
     */
    @Override
    public int deleteBizColumnByIds(Long[] ids)
    {
        return bizColumnMapper.deleteBizColumnByIds(ids);
    }

    /**
     * 删除栏目类别信息
     * 
     * @param id 栏目类别主键
     * @return 结果
     */
    @Override
    public int deleteBizColumnById(Long id)
    {
        return bizColumnMapper.deleteBizColumnById(id);
    }
}
