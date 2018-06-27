package com.xxy.elasticsearch.config;


import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


/**
 * @author xxy
 * @version V1.0
 * @Package com.example.demo
 * @Description: TODO
 * @date 2018/5/20 10:10
 */
@Configuration
public class ElasticsearchConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchConfig.class);

    /**
     * 连接协议
     */
    @Value("${elasticsearch.schema}")
    private String schema;

    /**
     * 连接ip
     */
    @Value("${elasticsearch.host}")
    private String host;

    /**
     * 连接端口
     */
    @Value("${elasticsearch.port}")
    private int port;

    /**
     * 连接超时时间
     */
    @Value("${elasticsearch.connectTimeOut}")
    private int connectTimeOut;

    /**
     *
     */
    @Value("${elasticsearch.socketTimeOut}")
    private int socketTimeOut;

    /**
     *
     */
    @Value("${elasticsearch.connectionRequestTimeOut}")
    private int connectionRequestTimeOut;

    /**
     * 最大连接数
     */
    @Value("${elasticsearch.maxConnectNum}")
    private int maxConnectNum;

    @Value("${elasticsearch.maxConnectPerRoute}")
    private int maxConnectPerRoute;

    boolean uniqueConnectTimeConfig = true;
    boolean uniqueConnectNumConfig = true;
    private RestClientBuilder builder;
    private RestHighLevelClient client;


    @Bean
    public RestHighLevelClient client() {
        HttpHost httpHost = new HttpHost(host, port, schema);
        //一个RestHighLevelClient实例需要构建一个REST低级客户端构建器
        //高级客户端将在内部创建用于基于提供的构建器执行请求的低级客户端，并管理其生命周期。
        builder = RestClient.builder(httpHost);
        if (uniqueConnectTimeConfig) {
            setConnectTimeOutConfig();
        }
        if (uniqueConnectNumConfig) {
            setMutiConnectConfig();
        }
        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(HttpHost host) {
                LOGGER.error("connect failed.the host:" + host.getHostName());
            }
        });
        client=new RestHighLevelClient(builder);
        return client;
    }

    // 主要关于异步httpclient的连接延时配置
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(connectTimeOut);
                requestConfigBuilder.setSocketTimeout(socketTimeOut);
                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return requestConfigBuilder;
            }
        });
    }

    // 主要关于异步httpclient的连接数配置
    public void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                httpAsyncClientBuilder.setMaxConnTotal(maxConnectNum);
                httpAsyncClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpAsyncClientBuilder;
            }
        });
    }

    public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
