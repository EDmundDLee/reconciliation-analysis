package com.rongxin.web.service.impl;

import java.util.List;

import com.rongxin.common.core.domain.AjaxResult;
import com.rongxin.common.utils.DateUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rongxin.web.domain.BusinessDict;
import com.rongxin.web.mapper.BusinessDictMapper;
import com.rongxin.web.service.IBusinessDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务字典Service业务层处理
 * 
 * @author rx
 * @date 2023-03-14
 */
@Service
public class BusinessDictServiceImpl extends ServiceImpl<BusinessDictMapper, BusinessDict> implements IBusinessDictService
{
    @Autowired
    private BusinessDictMapper businessDictMapper;

    /**
     * 查询业务字典
     * 
     * @param id 业务字典主键
     * @return 业务字典
     */
    @Override
    public BusinessDict selectBusinessDictById(Long id)
    {
        return businessDictMapper.selectBusinessDictById(id);
    }

    /**
     * 查询业务字典列表
     * 
     * @param businessDict 业务字典
     * @return 业务字典
     */
    @Override
    public List<BusinessDict> selectBusinessDictList(BusinessDict businessDict)
    {
        return businessDictMapper.selectBusinessDictList(businessDict);
    }

    /**
     * 新增业务字典
     * 
     * @param businessDict 业务字典
     * @return 结果
     */
    @Override
    public int insertBusinessDict(BusinessDict businessDict)
    {
        businessDict.setCreateTime(DateUtils.getNowDate());
        return businessDictMapper.insertBusinessDict(businessDict);
    }

    /**
     * 修改业务字典
     * 
     * @param businessDict 业务字典
     * @return 结果
     */
    @Override
    public int updateBusinessDict(BusinessDict businessDict)
    {
        businessDict.setUpdateTime(DateUtils.getNowDate());
        return businessDictMapper.updateBusinessDict(businessDict);
    }

    /**
     * 批量删除业务字典
     * 
     * @param ids 需要删除的业务字典主键
     * @return 结果
     */
    @Override
    public int deleteBusinessDictByIds(Long[] ids)
    {
        return businessDictMapper.deleteBusinessDictByIds(ids);
    }

    /**
     * 删除业务字典信息
     * 
     * @param id 业务字典主键
     * @return 结果
     */
    @Override
    public AjaxResult deleteBusinessDictById(Long id)
    {
        List<BusinessDict> list = businessDictMapper.selectBusinessDictListOfAllSon(id);
        if(list.size()>0){
            Long[] ids = new Long[list.size()];
            for(int i = 0;i<list.size();i++){
                ids[i]= list.get(i).getId();
            }
            if(businessDictMapper.deleteBusinessDictByIds(ids)>0){
                return AjaxResult.success("当前字典数据及其子节点数据已删除！");
            }else{
                return AjaxResult.error("当前字典数据删除失败！");
            }
        }else{
            return AjaxResult.success("当前字典数据获取失败！");
        }
    }


    /**
     * 根据字典id当前字典下所有子数据
     *
     * @param id 业务字典主键
     * @return 业务字典
     */
    @Override
    public List<BusinessDict> selectBusinessDictListOfAllSon(Long id){
        return businessDictMapper.selectBusinessDictListOfAllSon(id);
    }

    /**
     * 根据字典id当前字典下所有父节点数据
     *
     * @param id 业务字典主键
     * @return 业务字典
     */
    @Override
    public List<BusinessDict> selectBusinessDictListOfAllParent(Long id){
        return businessDictMapper.selectBusinessDictListOfAllParent(id);
    }
}
