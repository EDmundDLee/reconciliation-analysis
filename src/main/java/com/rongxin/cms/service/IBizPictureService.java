package com.rongxin.cms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rongxin.cms.domain.BizPicture;

import java.util.List;

/**
 * 图片Service接口
 * 
 * @author rx
 * @date 2022-10-09
 */
public interface IBizPictureService extends IService<BizPicture>
{
    /**
     * 查询列表
     *
     * @param bizPicture
     * @return 集合
     */
    public List<BizPicture> selectBizPictureList(BizPicture bizPicture);
}
