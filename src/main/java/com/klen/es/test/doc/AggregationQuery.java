package com.klen.es.test.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.InternalOrder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

/**
 * @Description:
 * @Author: klenq
 * @CreateTime: 1/6/2022
 */
public class AggregationQuery {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        SearchRequest request = new SearchRequest();

        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        //聚合查询
        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
        //分组查询
        //AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");


        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);


        SearchHits hits = response.getHits();

        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit: hits){
            System.out.println(hit.getSourceAsString());
        }

        //close ES
        client.close();

    }
}
