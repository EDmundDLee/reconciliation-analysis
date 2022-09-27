package com.rongxin.web.framework.web.service;

import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ElasticSearchService {

    Object selectById(String id,String indexValue,Class entity) throws IOException;

    Object selectList(String indexValue, Class entity, SearchSourceBuilder searchSourceBuilder) throws IOException;

    String insertContent(String id,String indexValue,String source) throws IOException;

    int insertBatchContent(String indexValue,List<Map<String,String>> list) throws IOException;

    String updateContent(String id,String indexValue,String source) throws IOException;

    int updateBatchContent(List<Map<String,String>> list, String indexValue)throws IOException;

    int deleteContentByIds(String[] ids,String indexValue) throws IOException;

    String deleteContentById(String id,String indexValue) throws IOException;
}
