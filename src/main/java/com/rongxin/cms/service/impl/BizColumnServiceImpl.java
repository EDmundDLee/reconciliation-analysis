package com.rongxin.cms.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.cms.domain.*;
import com.rongxin.cms.mapper.*;
import com.rongxin.cms.service.IBizColumnRuleService;
import com.rongxin.common.core.domain.entity.SysUser;
import com.rongxin.common.utils.SecurityUtils;
import com.rongxin.common.utils.StringUtils;
import com.rongxin.web.domain.CommonTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private BizColumnTreeMapper bizColumnTreeMapper;
    @Autowired
    private BizRuleMapper bizRuleMapper;
    @Autowired
    private BizAttributeMapper bizAttributeMapper;
    @Autowired
    private IBizColumnRuleService bizColumnRuleService;
    @Autowired
    private BizColumnRuleMapper bizColumnRuleMapper;

    @Autowired
    private BizAttributeValueMapper bizAttributeValueMapper;
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
    public List<BizColumnTree> selectColList() {
        return bizColumnTreeMapper.selectColList();
    }


    /**
     * 构建前端所需要下拉树结构
     *
     * @param cols 栏目列表
     * @return 下拉树结构列表
     */
    @Override
    public List<CommonTree> buildColTreeSelect(List<BizColumnTree> cols)
    {
        List<BizColumnTree> colsTrees = buildDeptTree(cols);
        return colsTrees.stream().map(CommonTree::new).collect(Collectors.toList());
    }
    /**
     * 构建前端所需要树结构
     *
     * @param cols 栏目列表
     * @return 树结构列表
     */
    public List<BizColumnTree> buildDeptTree(List<BizColumnTree> cols)
    {
        List<BizColumnTree> returnList = new ArrayList<BizColumnTree>();
        List<Long> tempList = new ArrayList<Long>();
        for (BizColumnTree col : cols)
        {
            tempList.add(col.getId());
        }
        for (BizColumnTree col : cols)
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
    private void recursionFn(List<BizColumnTree> list, BizColumnTree t)
    {
        // 得到子节点列表
        List<BizColumnTree> childList = getChildList(list, t);
        t.setChildren(childList);
        for (BizColumnTree tChild : childList)
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
    private List<BizColumnTree> getChildList(List<BizColumnTree> list, BizColumnTree t)
    {
        List<BizColumnTree> tlist = new ArrayList<BizColumnTree>();
        Iterator<BizColumnTree> it = list.iterator();
        while (it.hasNext())
        {
            BizColumnTree n = (BizColumnTree) it.next();
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
    private boolean hasChild(List<BizColumnTree> list, BizColumnTree t)
    {
        return getChildList(list, t).size() > 0;
    }


    @Override
    public Map<String, Object> getRuleAttr() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list =  new ArrayList<>();
        List<BizRule> ruleList = bizRuleMapper.selectBizRuleList(null);
        if(ruleList!= null && ruleList.size()>0){
            map.put("ruleList",ruleList);
            BizAttribute bizAttribute = new BizAttribute();
            bizAttribute.setRuleId(ruleList.get(0).getId());
            List<BizAttribute> attrList = bizAttributeMapper.selectBizAttributeList(bizAttribute);
            map.put("attributeList",attrList);
        }
        return map;
    }

    @Override
    public int bindRule(Map<String,Object> map) {
        String ruleId =  String.valueOf(map.get("ruleId"));
        List<String> idsStr = (ArrayList) map.get("ids");
        bizColumnRuleMapper.deleteBizArticleRuleByArticleIds(idsStr);
        bizAttributeValueMapper.deleteBizArticleRuleValueByArticleIds(idsStr);
        BizColumnRule bizColumnRule =  new BizColumnRule();
        for(int i = 0 ;i<idsStr.size();i++){
            bizColumnRule =  new BizColumnRule();
            bizColumnRule.setRuleId(Long.valueOf(ruleId));
            bizColumnRule.setColumnId(Long.valueOf(String.valueOf(idsStr.get(i))));
            bizColumnRuleService.insertBizColumnRule(bizColumnRule);
        }
        return 0;
    }
}
