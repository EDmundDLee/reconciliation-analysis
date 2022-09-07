package com.rongxin.demo.service.impl;

import java.io.IOException;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.rongxin.common.utils.uuid.IdUtils;
import com.rongxin.web.framework.web.service.ElasticSearchService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rongxin.demo.domain.RxStudent;
import com.rongxin.demo.service.IRxStudentService;

/**
 * 全文检索Service业务层处理
 * 
 * @author rx
 * @date 2022-09-06
 */
@Service
public class RxStudentServiceImpl  implements IRxStudentService
{

    @Autowired
    private ElasticSearchService easticSearchService;
    /**
     * 查询全文检索学生
     * 
     * @param id 学生主键
     * @return 学生对象
     */
    @Override
    public RxStudent selectRxStudentById(String id) throws IOException {
        return (RxStudent) easticSearchService.selectById(id,"students",RxStudent.class);
    }

    /**
     * 查询学生列表
     * 
     * @param rxStudent 学生
     * @return 学生
     */
    @Override
    public List<RxStudent> selectRxStudentList(RxStudent rxStudent) throws IOException {
        // 构建检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页采用简单的from + size分页，适用数据量小的，了解更多分页方式可自行查阅资料
        //        searchSourceBuilder.from((page - 1) * rows);
        //        searchSourceBuilder.size(rows);
        // 查询所有
        //        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        // 根据字段匹配
        QueryBuilder queryBuilder = null ;
        if(rxStudent.getName()!=null){
            queryBuilder = QueryBuilders.matchQuery("name",rxStudent.getName());
        }
        if(rxStudent.getBirthdate()!=null){
            queryBuilder = QueryBuilders.matchQuery("birthdate",String.valueOf(rxStudent.getBirthdate().getTime()));//时间查询
        }
        if(rxStudent.getIntro()!=null){
            queryBuilder = QueryBuilders.fuzzyQuery("intro",rxStudent.getIntro());//模糊查询
        }
        if(rxStudent.getAddress()!=null){
            queryBuilder = QueryBuilders.fuzzyQuery("address",rxStudent.getAddress());//模糊查询
        }
        searchSourceBuilder.query(queryBuilder);
        return (List<RxStudent>) easticSearchService.selectList("students",RxStudent.class,searchSourceBuilder);
    }

    /**
     * 新增学生
     * 
     * @param rxStudent 学生
     * @return 结果
     */
    @Override
    public  String insertRxStudent(RxStudent rxStudent) throws IOException {
        String idStr = IdUtils.simpleUUID();
        rxStudent.setId(idStr);
        return easticSearchService.insertContent(idStr,"students",JSON.toJSONString(rxStudent));
    }

    /**
     * 修改allcontent
     * 
     * @param rxStudent 学生
     * @return 结果
     */
    @Override
    public String updateRxStudent(RxStudent rxStudent) throws IOException {

        return easticSearchService.updateContent(rxStudent.getId(),"students",  JSON.toJSONString(rxStudent));
    }

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteRxStudentByIds(String[] ids) throws IOException {
        return easticSearchService.deleteContentByIds(ids,"students");
    }

    /**
     * 删除学生信息
     * 
     * @param id 学生t主键
     * @return 结果
     */
    @Override
    public String deleteRxStudentById(String id) throws IOException {
        return easticSearchService.deleteContentById(id,"students");
    }

}
