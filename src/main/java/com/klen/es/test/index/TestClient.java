package com.klen.es.test.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Description:
 * @Author: klenq
 * @CreateTime: 1/6/2022
 */
public class TestClient {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))


        );

        //close ES
        restHighLevelClient.close();

    }
}
