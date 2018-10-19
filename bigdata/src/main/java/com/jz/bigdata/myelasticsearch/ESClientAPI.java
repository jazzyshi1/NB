package com.jz.bigdata.myelasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Created by jazzyshi on 2018/8/29.
 */
public class ESClientAPI {

    public static void main(String[] args){
        ESClientAPI();
        scrollTest();

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scrollTest(){
        final Scroll scroll = new Scroll(TimeValue.timeValueMillis(1L));
        SearchRequest searchRequest = new SearchRequest("winlogbeat-6.2.4-2018.05.13");
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(10000);
        searchSourceBuilder.fetchSource(new String[]{"log_name","record_number"},new String[]{});
        searchSourceBuilder.query();
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        while (searchHits != null && searchHits.length > 0) {

            System.out.println("***************第一次***********");

            for(SearchHit searchHit : searchHits){
                System.out.println(searchHit.getSourceAsMap());
            }

            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            try {
                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();

        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = null;
        try {
            clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean succeeded = clearScrollResponse.isSucceeded();
    }

    public static void suggestionsTest(){
        SearchRequest searchRequest = new SearchRequest("winlogbeat-6.2.4-2018.05.13").types("doc");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermSuggestionBuilder termSuggestionBuilder = SuggestBuilders.termSuggestion("log_name").text("System");
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("suggest_log_name",termSuggestionBuilder);
        searchSourceBuilder.suggest(suggestBuilder);
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            Suggest suggest = searchResponse.getSuggest();
            TermSuggestion termSuggestion = suggest.getSuggestion("suggest_log_name");

            for(TermSuggestion.Entry entry : termSuggestion.getEntries()){
                for(TermSuggestion.Entry.Option option : entry){
                    System.out.println(option.getText().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void aggregationTest() {
        SearchRequest searchRequest = new SearchRequest("winlogbeat-6.2.4-2018.05.13").types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(0);
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("by_log_name").field("log_name");
        aggregationBuilder.subAggregation(AggregationBuilders.avg("average_process_id").field("process_id"));
        searchSourceBuilder.aggregation(aggregationBuilder);

        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            Aggregations aggregationts = searchResponse.getAggregations();
            Terms byNameAggregation = aggregationts.get("by_log_name");

            for(Terms.Bucket bucket : byNameAggregation.getBuckets()){
                Avg avg = bucket.getAggregations().get("average_process_id");
                System.out.println(bucket.getKey()+"="+avg.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  void ESClientAPI(){
        if(client == null){
             client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("192.168.0.88", 9201, "http"),
                            new HttpHost("192.168.0.88", 9202, "http"),
                            new HttpHost("192.168.0.88", 9203, "http")));
        }
    }

    static RestHighLevelClient client;
}
