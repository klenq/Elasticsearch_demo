package com.klen.es.test.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @Description:
 * @Author: klenq
 * @CreateTime: 1/6/2022
 */
public class DeleteBatch {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("1001"));
        request.add(new IndexRequest().index("user").id("1001"));
        request.add(new IndexRequest().index("user").id("1001"));
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);


        //close ES
        client.close();

    }
}
