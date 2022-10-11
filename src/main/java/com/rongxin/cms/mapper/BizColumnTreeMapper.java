package com.rongxin.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongxin.cms.domain.BizColumnTree;

import java.util.List;

/**
 * 栏目类别Mapper接口
 * 
 * @author rx
 * @date 2022-10-10
 */
public interface BizColumnTreeMapper extends BaseMapper<BizColumnTree>
{

    /**
     * 获取发布栏目树
     * @return
     */
    List<BizColumnTree> selectColList();
}
