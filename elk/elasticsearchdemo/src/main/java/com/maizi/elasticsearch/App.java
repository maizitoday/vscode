package com.maizi.elasticsearch;
/*
 * @Description  : 请输入....
 * @Author       : 麦子
 * @Date         : 2020-05-15 11:11:32
 * @FilePath     : /elk/elasticsearchdemo/src/main/java/com/maizi/elasticsearch/App.java
 * @LastEditTime : 2020-05-18 17:16:46
 * @LastEditors  : Do not edit
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class App {

    private static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("127.0.0.1", 9200)));

    public static void main(String[] args) throws IOException {

        allSearch(client);



    }

    /**
     * @Description: 方法说明....模糊查询 相当于SQL语句中的like查询。
     * @Date: 2020-05-18 17:16:35
     * @param {type}
     * @return:
     * @LastEditors: Do not edit
     */
    private static void likeSearch() throws IOException {
        String type = "_doc";
        String index = "test1";
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        /**
         * SELECT * FROM p_test where message like '%xu%';
         */
        boolQueryBuilder.must(QueryBuilders.wildcardQuery("message", "*xu*"));
        searchSourceBuilder.query(boolQueryBuilder);
        System.out.println("模糊查询语句:" + searchSourceBuilder.toString());
        searchRequest.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(documentFields -> {
            System.out.println("模糊查询结果:" + documentFields.getSourceAsMap());
        });
        System.out.println("\n=================\n");
    }

    /**
     * @Description: 方法说明.... 或查询 其实这个或查询也是bool查询中的一种，
     * @Date: 2020-05-18 17:13:43
     * @param {type}
     * @return:
     * @LastEditors: Do not edit
     */
    private static void orSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("test1");
        searchRequest.types("_doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        BoolQueryBuilder boolQueryBuilder2 = new BoolQueryBuilder();

        /**
         * SELECT * FROM test1 where (uid = 1234 or uid =12345) and phone = 12345678909
         */
        boolQueryBuilder2.should(QueryBuilders.termQuery("uid", 1234));
        boolQueryBuilder2.should(QueryBuilders.termQuery("uid", 12345));
        boolQueryBuilder.must(boolQueryBuilder2);
        boolQueryBuilder.must(QueryBuilders.termQuery("phone", "12345678909"));
        searchSourceBuilder.query(boolQueryBuilder);
        System.out.println("或查询语句:" + searchSourceBuilder.toString());
        searchRequest.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        searchResponse.getHits().forEach(documentFields -> {

            System.out.println("查询结果:" + documentFields.getSourceAsMap());
        });

    }

    /**
     * @Description: 一般查询代码示例 其实就是等值查询，只不过在里面加入了分页、排序、超时、路由等等设置，并且在查询结果里面增加了一些处理。
     * @Date: 2020-05-18 17:09:15
     * @param {type}
     * @return:
     * @LastEditors: Do not edit
     */
    private static void genSearch(RestHighLevelClient client) throws IOException {
        String type = "_doc";
        String index = "test1";
        // 查询指定的索引库
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 设置查询条件
        sourceBuilder.query(QueryBuilders.termQuery("uid", "1234"));
        // 设置起止和结束
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 设置路由
        // searchRequest.routing("routing");
        // 设置索引库表达式
        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        // 查询选择本地分片，默认是集群分片
        searchRequest.preference("_local");

        // 排序
        // 根据默认值进行降序排序
        // sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        // 根据字段进行升序排序
        // sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));

        // 关闭suorce查询
        // sourceBuilder.fetchSource(false);

        String[] includeFields = new String[] { "title", "user", "innerObject.*" };
        String[] excludeFields = new String[] { "_type" };
        // 包含或排除字段
        // sourceBuilder.fetchSource(includeFields, excludeFields);

        searchRequest.source(sourceBuilder);
        System.out.println("普通查询的DSL语句:" + sourceBuilder.toString());
        // 同步查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // HTTP状态代码、执行时间或请求是否提前终止或超时
        RestStatus status = searchResponse.status();
        TimeValue took = searchResponse.getTook();
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        boolean timedOut = searchResponse.isTimedOut();

        // 供关于受搜索影响的切分总数的统计信息，以及成功和失败的切分
        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();
        // 失败的原因
        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
            // failures should be handled here
        }
        // 结果
        searchResponse.getHits().forEach(hit -> {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println("普通查询的结果:" + map);
        });
        System.out.println("\n=================\n");
    }

    /**
     * @Description: 查询所有代码示例
     * @Date: 2020-05-18 17:09:34
     * @param {type}
     * @return:
     * @LastEditors: Do not edit
     */
    private static void allSearch(RestHighLevelClient client) throws IOException {
        SearchRequest searchRequestAll = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequestAll.source(searchSourceBuilder);
        // 同步查询
        SearchResponse searchResponseAll = client.search(searchRequestAll, RequestOptions.DEFAULT);
        System.out.println("所有查询总数:" + searchResponseAll.getHits().getTotalHits());
    }

    /**
     * @Description: ES根据ID删除代码示例
     * @Date: 2020-05-18 17:10:13
     * @param {type}
     * @return:
     * @LastEditors: Do not edit
     */
    private static void delete(RestHighLevelClient client) throws IOException {
        String type = "_doc";
        String index = "test1";
        // 唯一编号
        String id = "1";
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.id(id);
        deleteRequest.index(index);
        deleteRequest.type(type);
        // 设置超时时间
        deleteRequest.timeout(TimeValue.timeValueMinutes(2));
        // 设置刷新策略"wait_for"
        // 保持此请求打开，直到刷新使此请求的内容可以搜索为止。此刷新策略与高索引和搜索吞吐量兼容，但它会导致请求等待响应，直到发生刷新
        deleteRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        // 同步删除
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * @Description: 方法说明....ES修改的代码示例
     * @Date: 2020-05-18 17:11:17
     * @param {type}
     * @return:
     * @LastEditors: Do not edit
     */
    private static void update(RestHighLevelClient client) throws IOException {
        String type = "_doc";
        String index = "test1";
        // 唯一编号
        String id = "1";
        UpdateRequest upateRequest = new UpdateRequest();
        upateRequest.id(id);
        upateRequest.index(index);
        upateRequest.type(type);

        // 依旧可以使用Map这种集合作为更新条件
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("uid", 12345);
        jsonMap.put("phone", 123456789019L);
        jsonMap.put("msgcode", 2);
        jsonMap.put("sendtime", "2019-03-14 01:57:04");
        jsonMap.put("message", "xuwujing study Elasticsearch");
        upateRequest.doc(jsonMap);
        // upsert 方法表示如果数据不存在，那么就新增一条
        upateRequest.docAsUpsert(true);
        client.update(upateRequest, RequestOptions.DEFAULT);
        System.out.println("更新成功！");

    }

    /**
     * @Description: 方法说明....新增索引库的代码示例:
     * @Date: 2020-05-18 17:11:38
     * @param {type}
     * @return:
     * @LastEditors: Do not edit
     */
    private static void createIndex(RestHighLevelClient client) throws IOException {
        String type = "_doc";
        String index = "maizi";
        // setting 的值
        Map<String, Object> setmapping = new HashMap<>();
        // 分区数、副本数、缓存刷新时间
        setmapping.put("number_of_shards", 10);
        setmapping.put("number_of_replicas", 1);
        setmapping.put("refresh_interval", "5s");
        Map<String, Object> keyword = new HashMap<>();
        // 设置类型
        keyword.put("type", "keyword");
        Map<String, Object> lon = new HashMap<>();
        // 设置类型
        lon.put("type", "long");
        Map<String, Object> date = new HashMap<>();
        // 设置类型
        date.put("type", "date");
        date.put("format", "yyyy-MM-dd HH:mm:ss");

        Map<String, Object> jsonMap2 = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        // 设置字段message信息
        properties.put("uid", lon);
        properties.put("phone", lon);
        properties.put("msgcode", lon);
        properties.put("message", keyword);
        properties.put("sendtime", date);
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        jsonMap2.put(type, mapping);

        GetIndexRequest getRequest = new GetIndexRequest();
        getRequest.indices(index);
        getRequest.local(false);
        getRequest.humanReadable(true);
        boolean exists2 = client.indices().exists(getRequest, RequestOptions.DEFAULT);
        // 如果存在就不创建了
        if (exists2) {
            System.out.println(index + "索引库已经存在!");
            return;
        }
        // 开始创建库
        CreateIndexRequest request = new CreateIndexRequest(index);
        try {
            // 加载数据类型
            request.settings(setmapping);
            // 设置mapping参数
            request.mapping(type, jsonMap2);
            // 设置别名
            request.alias(new Alias("pancm_alias"));
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            boolean falg = createIndexResponse.isAcknowledged();
            if (falg) {
                System.out.println("创建索引库:" + index + "成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description: 方法说明....通过map创建,会自动转换成json的数据
     * @Date: 2020-05-18 17:11:53
     * @param {type}
     * @return:
     * @LastEditors: Do not edit
     */

    private static void add(RestHighLevelClient client) throws IOException {
        String index = "test1";
        String type = "_doc";
        // 唯一编号
        String id = "2";
        IndexRequest request = new IndexRequest(index, type, id);
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("uid", 1234);
        jsonMap.put("phone", 12345678909L);
        jsonMap.put("msgcode", 1);
        jsonMap.put("sendtime", "2019-03-14 01:57:04");
        jsonMap.put("message", "xuwujing study Elasticsearch");
        request.source(jsonMap);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
    }

}
