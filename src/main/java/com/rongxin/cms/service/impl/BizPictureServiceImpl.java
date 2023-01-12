package com.rongxin.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.cms.domain.BizPicture;
import com.rongxin.cms.mapper.BizPictureMapper;
import com.rongxin.cms.service.IBizPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章内容Service业务层处理
 * 
 * @author rx
 * @date 2022-10-09
 */
@Service
public class BizPictureServiceImpl extends ServiceImpl<BizPictureMapper, BizPicture> implements IBizPictureService
{

    @Autowired
    private BizPictureMapper bizPictureMapper;
    @Override
    public List<BizPicture> selectBizPictureList(BizPicture bizPicture) {
        return bizPictureMapper.selectBizPictureList(bizPicture);
    }
}
