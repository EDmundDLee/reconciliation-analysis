package com.rongxin.web.framework.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


/**
 * ElasticSearch 客户端配置
 *
 * @author rx
 * 2022/9/7
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    /** elasticsearch   url */
    @Value("${spring.data.elasticsearch.rest.uris}")
    private String uris;
    /** elasticsearch   username */
    @Value("${spring.data.elasticsearch.rest.username}")
    private String username;
    /** elasticsearch   password */
    @Value("${spring.data.elasticsearch.rest.password}")
    private String password;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(uris)
//                .withBasicAuth(username, password)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}