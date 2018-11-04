package com.xxy.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xxy.elasticsearch.indexes.People;
import com.xxy.elasticsearch.service.elasticsearchService;
import org.assertj.core.util.DateUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;


@Service
public class elasticsearchServiceImpl implements elasticsearchService {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(elasticsearchServiceImpl.class);
    @Autowired
    private RestHighLevelClient rhlClient;
    private static final String index="people";
    private static final String type="man";

    /**
     * 单笔添加
     * @param source
     */
    @Override
    public void add(String source){
        IndexRequest  indexRequest = generateNewsRequest(source);
        try {
            rhlClient.index(indexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量添加
     */
    @Override
    public void addBatch(List<IndexRequest> requests){
        BulkRequest bulkRequest = new BulkRequest();
        for (IndexRequest indexRequest : requests) {
            bulkRequest.add(indexRequest);
        }
        try {
            rhlClient.bulk(bulkRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IndexRequest generateNewsRequest(String source){
        IndexRequest indexRequest = new IndexRequest(index, type);
        indexRequest.source(source, XContentType.JSON);
        return indexRequest;
    }

    public static String toJson(People people){
        return  JSON.toJSONString(people);
    }

    /**
     * 使用DOC方式修改
     * @param id
     */
    @Override
    public void updateDoc(String id ,String json){
        UpdateRequest updateRequest = new UpdateRequest(index, type, id);
        updateRequest.doc(json,XContentType.JSON);
        try {
            rhlClient.update(updateRequest);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void delete(String id){
        DeleteRequest deleteRequest = new DeleteRequest(index,type,id);
        DeleteResponse response = null;
        try {
            response = rhlClient.delete(deleteRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                rhlClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(response);
    }

    @Override
    public void deleteBatch(List<String> docIds){
        BulkRequest bulkRequest = new BulkRequest();
        for (String id : docIds) {
            DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
            bulkRequest.add(deleteRequest);
        }
        try {
            rhlClient.bulk(bulkRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void  query(){
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(10);

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "xxy");

        MatchPhraseQueryBuilder matchPhraseQueryBuilder =QueryBuilders.matchPhraseQuery("name","xxy");
        //精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("country", "中国");
        //范围查询
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("date");
        rangeQueryBuilder.gte("2018-01-26");
        rangeQueryBuilder.lte("2018-06-26");
        //bool类型复合查询
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(matchQueryBuilder);
        boolBuilder.must(termQueryBuilder);
        boolBuilder.must(rangeQueryBuilder);

        sourceBuilder.query(boolBuilder);
        SearchRequest searchRequest = new SearchRequest(index);
        // 排序
        FieldSortBuilder fsb = SortBuilders.fieldSort("date");
        fsb.order(SortOrder.DESC);
        sourceBuilder.sort(fsb);

        searchRequest.types(type);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = rhlClient.search(searchRequest);
            SearchHits hits= response.getHits();
            int totalRecordNum= (int) hits.getTotalHits();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
                    .create();
            for (SearchHit searchHit : hits) {
                Map<String, Object> source = searchHit.getSourceAsMap();
                //Object entity =gson.fromJson(gson.toJson(source),People.class);
                log.info("========================================================================="+source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
