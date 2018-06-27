package com.xxy.elasticsearch.service;

import org.elasticsearch.action.index.IndexRequest;

import java.util.List;

public interface elasticsearchService {

    void add(String source);

    void addBatch(List<IndexRequest> requests);

    void updateDoc(String id);

    void delete(String id);

    void deleteBatch(List<String> docIds);

    void  query();
}
