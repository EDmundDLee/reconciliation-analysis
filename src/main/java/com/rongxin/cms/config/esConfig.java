package com.rongxin.cms.config;


import com.rongxin.cms.service.impl.BizWebServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/* 用于服务器启动同步到es的文章数据 */
@Component
public class esConfig implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger("搜索引擎同步数据:");
    private final
    BizWebServiceImpl articleService;
    @Value("${cms.open}")
    private Boolean cmsOpen;
    @Autowired
    public esConfig(BizWebServiceImpl articleService) {
        this.articleService = articleService;
    }
//    @Autowired
//    private BloomFilterUtils bloomFilterUtils;

    @Override
    public void run(String... args) throws Exception {
        if(cmsOpen){
            LOGGER.info("开始同步搜索引擎请耐心等待");
            articleService.synchronizationArticle();
            LOGGER.info("搜索引擎同步完成!");
        }
       // LOGGER.info("布隆过滤器同步中!");
       // bloomFilterUtils.importDate();
       // LOGGER.info("布隆过滤器同步完毕");
    }
}
