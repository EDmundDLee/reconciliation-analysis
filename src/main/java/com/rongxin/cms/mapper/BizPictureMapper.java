package com.rongxin.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rongxin.cms.domain.BizPicture;

import java.util.List;

/**
 * 文章内容Mapper接口
 * 
 * @author rx
 * @date 2022-10-09
 */
public interface BizPictureMapper extends BaseMapper<BizPicture>
{
    /**
     * 查询列表
     *
     * @param bizPicture
     * @return 集合
     */
    public List<BizPicture> selectBizPictureList(BizPicture bizPicture);
}
