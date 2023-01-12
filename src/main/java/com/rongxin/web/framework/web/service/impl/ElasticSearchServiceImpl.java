package com.rongxin.web.framework.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.rongxin.web.framework.web.service.ElasticSearchService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 全文检索Service接口
 * 
 * @author rx
 * @date 2022-09-07
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService
{

    @Autowired
    @Qualifier("elasticsearchClient")
    private RestHighLevelClient client;

    /**
     * 根据索引、ID获取对象
     * @param id  索引内的ID值
     * @param indexValue  索引
     * @param entity 需要返回的实体对象
     * @return
     * @throws IOException
     */
    @Override
    public Object selectById(String id, String indexValue,Class entity) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexValue);
        // 构建检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("id",id);
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        // 查询ES
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询结果：" + searchResponse.toString());
        SearchHits hits = searchResponse.getHits();
        // 遍历封装列表对象
        List<Object> list = new ArrayList<>();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            list.add(JSON.parseObject(searchHit.getSourceAsString(), entity));
        }

        return list.get(0);
    }

    /**
     * 根据 索引  实体对象 以及 构建的查询元进行查询并返回list集合
     * @param indexValue 索引
     * @param entity 返回的实体对象
     * @param searchSourceBuilder  构建的查询元对象
     * @return
     * @throws IOException
     */
    @Override
    public List<Object> selectList( String indexValue, Class entity,SearchSourceBuilder searchSourceBuilder) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexValue);
        //注入查询
        searchRequest.source(searchSourceBuilder);
        // 查询ES
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("查询结果：" + searchResponse.toString());
        SearchHits hits = searchResponse.getHits();
        // 遍历封装列表对象
        List<Object> list = new ArrayList<>();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            list.add(JSON.parseObject(searchHit.getSourceAsString(), entity));
        }
        return list;
    }

    /**
     * 根据索引 ID 插入文档source
     * @param id id
     * @param indexValue 索引
     * @param source 文档内容
     * @return
     * @throws IOException
     */
    @Override
    public String insertContent(String id, String indexValue, String source) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexValue);
        indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//等待分片与备份刷新完成后再返回。
        indexRequest.id(id).source(source, XContentType.JSON);
        // 操作ES
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.getId();
    }
    /**
     * 根据索引 ID 插入文档source
     * @param indexValue 索引
     * @param list  需要新增的 id 及其对应的内容
     * @return
     * @throws IOException
     */
    @Override
    public int insertBatchContent(String indexValue,List<Map<String,String>> list) throws IOException {
        BulkRequest requests = new BulkRequest();
        BulkResponse response = null;
        IndexRequest indexRequest = null;
        for (Map<String,String> map:list) {
            indexRequest = new IndexRequest(indexValue);
            indexRequest.id(map.get("id")).source(map.get("source"), XContentType.JSON);
            requests.add(indexRequest);
        }
        requests.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//等待分片与备份刷新完成后再返回。
        // 操作ES
        response  = client.bulk(requests, RequestOptions.DEFAULT);
        return response.getItems().length;
    }
    /**
     * 根据索引、id更改文档source
     * @param id 索引内的ID值
     * @param indexValue 索引
     * @param source 文档内容
     * @return
     * @throws IOException
     */
    @Override
    public String updateContent(String id, String indexValue, String source) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(indexValue, id);
        updateRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//等待分片与备份刷新完成后再返回。
        updateRequest.doc(source, XContentType.JSON);
        // 操作ES
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        return updateResponse.getId();
    }
    /**
     * 根据索引、id集合更改文档source
     * @param list 索引内的ID值与批量变更的内容集合
     * @param indexValue 索引
     * @return
     * @throws IOException
     */
    @Override
    public int updateBatchContent(List<Map<String,String>> list, String indexValue) throws IOException {
        BulkRequest requests = new BulkRequest();
        BulkResponse response = null;
        UpdateRequest updateRequest = null;
        for (Map<String,String> map:list) {
            updateRequest = new UpdateRequest(indexValue, map.get("id"));
            updateRequest.doc(map.get("source"), XContentType.JSON);
            requests.add(updateRequest);
        }
        requests.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//等待分片与备份刷新完成后再返回。
        // 操作ES
        response  = client.bulk(requests, RequestOptions.DEFAULT);
        return response.getItems().length;
    }

    /**
     * 根据索引、id集合 批量删除
     * @param ids  索引下的id集合
     * @param indexValue  索引
     * @return
     * @throws IOException
     */
    @Override
    public int deleteContentByIds(String[] ids, String indexValue) throws IOException {
        //批量删除数据
        BulkRequest request = new BulkRequest();
        DeleteRequest source = null;
        BulkResponse response = null;
        for (String id:ids) {
            source =  new DeleteRequest(indexValue, id);
            request.add(source);
        }
        if(source!=null){
            request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//等待分片与备份刷新完成后再返回。
            response = client.bulk(request, RequestOptions.DEFAULT);
        }
        if(response!=null){
            return response.getItems().length;
        }else{
            return 0;
        }
    }

    /**
     * 根据索引、id进行删除对应内容
     * @param id 索引下的id值
     * @param indexValue 索引
     * @return
     * @throws IOException
     */
    @Override
    public String deleteContentById(String id, String indexValue) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(indexValue, id);
        deleteRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//等待分片与备份刷新完成后再返回。
        // 操作ES
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return deleteResponse.getId();
    }

    @Override
    public SearchResponse search(SearchRequest searchRequest, RequestOptions options) throws IOException {
        return client.search(searchRequest,options);
    }

    @Override
    public IndexResponse index(IndexRequest request, RequestOptions aDefault) throws IOException {
        return client.index(request,aDefault);
    }

    @Override
    public BulkByScrollResponse deleteByQuery(DeleteByQueryRequest request, RequestOptions aDefault) throws IOException {
        return client.deleteByQuery(request,aDefault);
    }


}
