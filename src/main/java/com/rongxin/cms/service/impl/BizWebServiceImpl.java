package com.rongxin.cms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rongxin.cms.state.EsIndexConfig;
import com.rongxin.cms.domain.BizArticle;
import com.rongxin.cms.service.IBizArticleService;
import com.rongxin.cms.service.IBizWebService;
import com.rongxin.web.framework.web.service.ElasticSearchService;
import com.rongxin.web.util.Utils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 文网站接口集合
 *
 * @author rx
 * @date 2022-10-11
 */
@Service
public class BizWebServiceImpl   implements IBizWebService
{

    @Autowired
    private IBizArticleService bizArticleService;
    @Autowired
    private ElasticSearchService elasticSearchService;
    @Override
    public List<BizArticle> search(Map<String, Object> map) throws Exception {
        // 创建查询请求
        SearchRequest searchRequest = new SearchRequest(EsIndexConfig.index);
        // 创建查询构造
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        /* es多条件搜索  详情请：https://blog.csdn.net/Leige_Smart/article/details/80901059 */
        MatchQueryBuilder m1 = QueryBuilders.matchQuery("title",map.get("name"));
        MatchQueryBuilder m2 = QueryBuilders.matchQuery("content",map.get("name"));
        QueryBuilder qb2 = QueryBuilders.boolQuery()
                .should(m1)
                .should(m2);
        searchSourceBuilder.query(qb2);

        //设置高亮
        String preTags = "<font color=\"red\">";
        String postTags = "</font>";
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags(preTags);
        highlightBuilder.postTags(postTags);
        //设置高亮字段
        highlightBuilder.field("content");
        highlightBuilder.field("title");
        //设置高亮信息
        searchSourceBuilder.highlighter(highlightBuilder);
        // TODO  分页
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(100);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = elasticSearchService.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new Exception("搜索遇到未知错误");
        }
        SearchHit[] hits = response.getHits().getHits();
        List<BizArticle> articleList = new LinkedList<>();
        for(SearchHit hit: hits){
            BizArticle article = JSONObject.parseObject(hit.getSourceAsString(),BizArticle.class);
            Map<String, HighlightField> hitMap = hit.getHighlightFields();
            HighlightField highlightField = hitMap.get("content");
            HighlightField highlightTitle = hitMap.get("title");
            if (highlightField != null) {
                Text[] text = highlightField.getFragments();
                article.setContent(text[0].toString());
            }else{
                article.setContent("...");
                Text[] text = highlightTitle.getFragments();
                article.setTitle(text[0].toString());
            }
            articleList.add(article);
        }
        return articleList;
    }


    @Override
    public void synchronizationArticle() throws Exception {
        try {
            importAll();//elasticSearch 同一索引下支持覆盖更新
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    /* 删除全部es中的文章数据  */
    private void deleteAllArticle() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest(EsIndexConfig.index);
        BulkByScrollResponse response = elasticSearchService.deleteByQuery(request,RequestOptions.DEFAULT);
    }
    /* 从数据库中查询数据后 批量导入到es中 */
    private void importAll() throws IOException {
        List<BizArticle> list = bizArticleService.selectBizArticleList(new BizArticle());
        for (BizArticle article : list){
            article.setCreateDate(null);
            addEsArticle(article);
        }
    }
    /* 插入es一条文章 */
    private void addEsArticle(BizArticle article) throws IOException {
        IndexRequest request = new IndexRequest(EsIndexConfig.index).id(article.getId().toString()).source(Utils.beanToMap(article));
        IndexResponse response = elasticSearchService.index(request, RequestOptions.DEFAULT);
    }
}
