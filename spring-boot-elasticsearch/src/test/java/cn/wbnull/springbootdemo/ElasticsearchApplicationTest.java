package cn.wbnull.springbootdemo;

import cn.wbnull.springbootdemo.model.UserModel;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Time;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.IndexOperation;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ElasticsearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ElasticsearchApplicationTest {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void contextLoads() throws IOException {
        //创建索引
        CreateIndexRequest index = new CreateIndexRequest.Builder()
                .index("test_index")
                .build();

        //执行，获得响应
        CreateIndexResponse response = elasticsearchClient.indices().create(index);
        System.out.println(response.toString());
    }

    /**
     * 查询索引
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsGet() throws IOException {
        GetIndexRequest index = new GetIndexRequest.Builder()
                .index("test_index")
                .build();

        GetIndexResponse response = elasticsearchClient.indices().get(index);
        System.out.println(response.toString());
    }

    /**
     * 创建文档
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsAdd() throws IOException {
        UserModel userModel = new UserModel("张三", "zhangsan");

        //创建请求
        IndexRequest<UserModel> request = new IndexRequest.Builder<UserModel>()
                .index("test_index")
                .id("1")
                .timeout(new Time.Builder().time("1s").build())
                .document(userModel)
                .build();

        //发送请求，获取响应结果
        IndexResponse response = elasticsearchClient.index(request);
        System.out.println(response.toString());
    }

    /**
     * 查询文档
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsGetDoc() throws IOException {
        //检查文档是否存在
        ExistsRequest request = new ExistsRequest.Builder()
                .index("test_index")
                .id("1")
                .build();

        BooleanResponse exists = elasticsearchClient.exists(request);
        System.out.println("BooleanResponse: " + exists.value());

        if (!exists.value()) {
            return;
        }

        //若存在，则获取文档信息
        GetRequest requestGet = new GetRequest.Builder()
                .index("test_index")
                .id("1")
                .build();

        GetResponse<? extends UserModel> responseGet = elasticsearchClient.get(requestGet, UserModel.class);
        System.out.println(responseGet.toString());
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(responseGet.source()));
    }

    /**
     * 更新文档
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsUpdate() throws IOException {
        UserModel userModel = new UserModel("张三三", "zhangsansan");

        UpdateRequest<UserModel, UserModel> request = new UpdateRequest.Builder<UserModel, UserModel>()
                .index("test_index")
                .id("1")
                .timeout(new Time.Builder().time("1s").build())
                .doc(userModel)
                .build();

        UpdateResponse<UserModel> response = elasticsearchClient.update(request, UserModel.class);
        System.out.println(response.toString());
    }

    /**
     * 删除文档
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsDelete() throws IOException {
        DeleteRequest request = new DeleteRequest.Builder()
                .index("test_index")
                .id("1")
                .timeout(new Time.Builder().time("1s").build())
                .build();

        DeleteResponse response = elasticsearchClient.delete(request);
        System.out.println(response.toString());
    }

    /**
     * 批量插入
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsBulk() throws IOException {
        List<BulkOperation> operations = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            UserModel userModel = new UserModel("张三" + i, "zhangsan" + i);
            operations.add(new BulkOperation(new IndexOperation.Builder<UserModel>()
                    .id(String.valueOf(i + 1))
                    .document(userModel)
                    .build()));
        }

        BulkRequest request = new BulkRequest.Builder()
                .index("test_index")
                .timeout(new Time.Builder().time("10s").build())
                .operations(operations)
                .build();

        BulkResponse response = elasticsearchClient.bulk(request);
        System.out.println(response.toString());
    }

    /**
     * 分词查询
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsMatchQuery() throws IOException {
        //查询条件
        MatchQuery matchQuery = new MatchQuery.Builder()
                .field("username")
                .query("张三1")
                .build();

        //构建搜索条件
        SearchRequest request = new SearchRequest.Builder()
                .index("test_index")
                .timeout("10s")
                .query(new Query.Builder().match(matchQuery).build())
                .build();

        SearchResponse<UserModel> response = elasticsearchClient.search(request, UserModel.class);
        System.out.println(response.hits().hits().toString());
    }

    /**
     * 精确匹配
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsTermQuery() throws IOException {
        TermQuery termQuery = new TermQuery.Builder()
                .field("username.keyword")
                .value("张三1")
                .build();

        SearchRequest request = new SearchRequest.Builder()
                .index("test_index")
                .timeout("10s")
                .query(new Query.Builder().term(termQuery).build())
                .build();

        SearchResponse<UserModel> response = elasticsearchClient.search(request, UserModel.class);
        System.out.println(response.hits().hits().toString());
    }

    /**
     * 组合查询
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsBoolQuery() throws IOException {
        TermQuery termQuery1 = new TermQuery.Builder()
                .field("username.keyword")
                .value("张三1")
                .build();
        TermQuery termQuery2 = new TermQuery.Builder()
                .field("password")
                .value("zhangsan2")
                .build();

        //must  AND
        //mustNot NOT
        //should OR
        BoolQuery boolQuery = new BoolQuery.Builder()
                .should(new Query.Builder().term(termQuery1).build())
                .should(new Query.Builder().term(termQuery2).build())
                .build();

        SearchRequest request = new SearchRequest.Builder()
                .index("test_index")
                .timeout("10s")
                .query(new Query.Builder().bool(boolQuery).build())
                .build();
        SearchResponse<UserModel> response = elasticsearchClient.search(request, UserModel.class);
        System.out.println(response.hits().hits().toString());
    }

    /**
     * 模糊查询
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsFuzzyQuery() throws IOException {
        FuzzyQuery fuzzyQuery = new FuzzyQuery.Builder()
                .field("password")
                .value("zhangsan20")
                .build();

        SearchRequest request = new SearchRequest.Builder()
                .index("test_index")
                .timeout("10s")
                .query(new Query.Builder().fuzzy(fuzzyQuery).build())
                .build();

        SearchResponse<UserModel> response = elasticsearchClient.search(request, UserModel.class);
        System.out.println(response.hits().hits().toString());
    }

    /**
     * 根据id查询
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsIdsQuery() throws IOException {
        IdsQuery idsQuery = new IdsQuery.Builder()
                .values("1", "2", "3")
                .build();

        SearchRequest request = new SearchRequest.Builder()
                .index("test_index")
                .timeout("10s")
                .query(new Query.Builder().ids(idsQuery).build())
                .build();

        SearchResponse<UserModel> response = elasticsearchClient.search(request, UserModel.class);
        System.out.println(response.hits().hits().toString());
    }

    /**
     * 范围查询
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsRangeQuery() throws IOException {
        RangeQuery rangeQuery = new RangeQuery.Builder()
                .field("password")
                .gte(JsonData.of("zhangsan10"))
                .lte(JsonData.of("zhangsan15"))
                .build();

        SearchRequest request = new SearchRequest.Builder()
                .index("test_index")
                .timeout("10s")
                .query(new Query.Builder().range(rangeQuery).build())
                .build();

        SearchResponse<UserModel> response = elasticsearchClient.search(request, UserModel.class);
        System.out.println(response.hits().hits().toString());
    }

    /**
     * 通配符查询
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsWildcardQuery() throws IOException {
        WildcardQuery wildcardQuery = new WildcardQuery.Builder()
                .field("password")
                .value("zhang*9")
                .build();

        SearchRequest request = new SearchRequest.Builder()
                .index("test_index")
                .timeout("10s")
                .query(new Query.Builder().wildcard(wildcardQuery).build())
                .build();

        SearchResponse<UserModel> response = elasticsearchClient.search(request, UserModel.class);
        System.out.println(response.hits().hits().toString());
    }

    /**
     * 前缀匹配查询
     *
     * @throws IOException
     */
    @Test
    public void contextLoadsPrefixQuery() throws IOException {
        PrefixQuery prefixQuery = new PrefixQuery.Builder()
                .field("password")
                .value("zhangsan9")
                .build();

        SearchRequest request = new SearchRequest.Builder()
                .index("test_index")
                .timeout("10s")
                .query(new Query.Builder().prefix(prefixQuery).build())
                .build();

        SearchResponse<UserModel> response = elasticsearchClient.search(request, UserModel.class);
        System.out.println(response.hits().hits().toString());
    }
}
