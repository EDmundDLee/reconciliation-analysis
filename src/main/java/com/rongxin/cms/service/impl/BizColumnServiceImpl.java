package com.rongxin.cms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.cms.domain.ColSelect;
import com.rongxin.common.core.domain.TreeSelect;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.StringUtils;
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

    /**
     * 发布内容界面获取栏目树
     * @return
     */
    @Override
    public List<BizColumn> selectColList() {
        return bizColumnMapper.selectColList();
    }


    /**
     * 构建前端所需要下拉树结构
     *
     * @param cols 栏目列表
     * @return 下拉树结构列表
     */
    @Override
    public List<ColSelect> buildColTreeSelect(List<BizColumn> cols)
    {
        List<BizColumn> colsTrees = buildDeptTree(cols);
        return colsTrees.stream().map(ColSelect::new).collect(Collectors.toList());
    }
    /**
     * 构建前端所需要树结构
     *
     * @param cols 栏目列表
     * @return 树结构列表
     */
    public List<BizColumn> buildDeptTree(List<BizColumn> cols)
    {
        List<BizColumn> returnList = new ArrayList<BizColumn>();
        List<Long> tempList = new ArrayList<Long>();
        for (BizColumn col : cols)
        {
            tempList.add(col.getId());
        }
        for (BizColumn col : cols)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(col.getParentId()))
            {
                recursionFn(cols, col);
                returnList.add(col);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = cols;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<BizColumn> list, BizColumn t)
    {
        // 得到子节点列表
        List<BizColumn> childList = getChildList(list, t);
        t.setChildren(childList);
        for (BizColumn tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<BizColumn> getChildList(List<BizColumn> list, BizColumn t)
    {
        List<BizColumn> tlist = new ArrayList<BizColumn>();
        Iterator<BizColumn> it = list.iterator();
        while (it.hasNext())
        {
            BizColumn n = (BizColumn) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<BizColumn> list, BizColumn t)
    {
        return getChildList(list, t).size() > 0;
    }
}
