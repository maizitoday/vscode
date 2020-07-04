package com.mongodb.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Hello world!
 *
 */
public class App {

    private static com.mongodb.client.MongoClient mongoClient;

    /**
     * 关于MongoDB Client的初始化和关闭
     * 
     * 
     */
    public static MongoClient connection() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            // System.out.println(mongoClient);
            // System.out.println("连接成功");
        }
        return mongoClient;
    }

    /**
     * client连接到一个 Replica Set：
     */
    public static void initByReplicaSet() {
        mongoClient = MongoClients.create("mongodb://host1:27017,host2:27017,host3:27017");
        MongoClients.create("mongodb://host1:27017,host2:27017,host3:27017/?replicaSet=myReplicaSet");
        mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress("host1", 27017),
                        new ServerAddress("host2", 27017), new ServerAddress("host3", 27017))))
                .build());
    }

    /**
     * 连接关闭
     */
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

    /**
     * 
     * 获取database，如果没有会自动创建
     * 
     * @param databaseName
     * @return
     */
    public static MongoDatabase getDatabase(String databaseName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        System.out.println("获取数据库成功   " + database);
        return database;
    }

    /**
     * 创建Collection
     * 
     * @param db
     * @param collectionName
     * @return
     */
    public static MongoCollection<Document> getCollection(MongoDatabase db, String collectionName) {
        MongoCollection<Document> collection = db.getCollection(collectionName);
        // db.createCollection(collectionName);
        System.out.println("创建collection成功   " + collection);
        return collection;
    }

    /*
     * ------------------------新增----------------------------------------
     */

    /**
     * 新增一条或者多条
     * 
     * @param collection
     */
    public static void insertDocument(MongoCollection<Document> collection) {
        Document document = new Document();
        document.append("java", "长沙");
        document.append("age", "8个月");
        // 新增一条
        collection.insertOne(document);

        // 新增多条
        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < 5; i++) {
            Document newDocument = new Document();
            newDocument.append("java", "长沙-" + i);
            newDocument.append("age", "时间-" + i);
            documents.add(newDocument);
        }
        collection.insertMany(documents);
    }

    /**
     * MongoDB 的bulkWrite操作 （批量写入），对于数据很多时，效率很高
     * 
     * @param dataBaseName
     * @param collectionName
     * @param listData
     * @return
     */
    public BulkWriteResult bulkWrite(String dataBaseName, String collectionName,
            List<? extends WriteModel<? extends Document>> listData) {
        return getCollectionByName(dataBaseName, collectionName).bulkWrite(listData);
    }

    /*
     * ------------------------查询----------------------------------------
     */

    /**
     * 查询数据库中有多少的collection
     * 
     * @param dataBaseName
     * @return
     */
    public static List<String> listCollectionNames(String dataBaseName) {
        List<String> stringList = new ArrayList<String>();
        mongoClient.getDatabase(dataBaseName).listCollectionNames().forEach((Consumer<? super String>) t -> {
            stringList.add(t);
        });
        return stringList;
    }

    /**
     * 查询数据库中的 对应的 collection
     * 
     * @param dataBaseName
     * @param collectionName
     * @return
     */
    public static MongoCollection<Document> getCollectionByName(String dataBaseName, String collectionName) {
        return getDatabase(dataBaseName).getCollection(collectionName);
    }

    /**
     * 精确查询，模糊查询，范围查询， 排序，返回条数
     * 
     * @param dataBaseName
     * @param collectionName
     * @param id
     * @return
     */
    public static FindIterable<Document> findMongoDbDocById(String dataBaseName, String collectionName, String id) {
        BasicDBObject searchDoc = new BasicDBObject();
        // 通过id（objectid）精确查询
        // searchDoc.put("_id", new ObjectId(id));

        // 模糊查询
        searchDoc.put("java", new BasicDBObject("$regex", "长沙"));

        // searchDoc.put("java", new BasicDBObject("$gte", startId).append("$lte",
        // endId));

        //
        return getCollectionByName(dataBaseName, collectionName).find(searchDoc).limit(10)
                .sort(new Document().append("age", 1));
    }

    /*
     * ------------------------修改---------------------------------------
     */

    /**
     * 
     * 更新一条， 批量更新
     * 
     */
    public static void update() {
        MongoDatabase database = App.getDatabase("maiziDB");
        MongoCollection<Document> workCollection = database.getCollection("work");
        Bson eqBson = Filters.eq("java", "长沙");
        // 要修改的内容
        Document updateBson = new Document("$set", new Document("age", 25).append("school", "一度教育"));
        UpdateResult updateMany = workCollection.updateMany(eqBson, updateBson);
        System.out.println(updateMany.toString());

        // workCollection.updateMany(filter, update)
    }

    /*
     * ------------------------删除---------------------------------------
     */

    /**
     * 
     * 根据多个条件删除单个或者是多个
     * 
     */
    public static void remove() {
        MongoDatabase database = App.getDatabase("maiziDB");
        MongoCollection<Document> workCollection = database.getCollection("work");
        // 单一条件删除
        Bson delBson = Filters.eq("age", "时间-0");
        DeleteResult deleteMany = workCollection.deleteMany(delBson);
        System.out.println(deleteMany.toString());

        // 多个条件合并，删除多个
        Bson delBson1 = Filters.gte("age", 20);
        // 构造删除条件（age<=30）
        Bson delBson2 = Filters.lte("age", 30);
        // 合并删除条件
        Bson delBsonCount = Filters.and(delBson1, delBson2);
        deleteMany = workCollection.deleteMany(delBsonCount);
        System.out.println(deleteMany.toString());
    }

    /*
     * ------------------------替换操作---------------------------------------
     */

    /**
     * 存在就替换，不存在的话就插入
     * 
     * @param dataBaseName
     * @param collectionName
     * @param var1
     * @param var2
     * @param var3
     * @return
     */
    public UpdateResult replaceDoc(String dataBaseName, String collectionName, Bson var1, Document var2,
            ReplaceOptions var3) {
        return getCollectionByName(dataBaseName, collectionName).replaceOne(var1, var2, var3);
    }

    /**
     * 设置存在就替换，不存在的话就插入
     * 
     * @param dataBaseName
     * @param collectionName
     * @param var1
     * @param var2
     * @return
     */
    public UpdateResult replaceDoc(String dataBaseName, String collectionName, Bson var1, Document var2) {
        Document documentQuery = new Document("_id", new ObjectId("id"));
        Document document = new Document("_id", new ObjectId("id"));
        ReplaceOptions replaceOptions = new ReplaceOptions();
        replaceOptions.upsert(true);
        return replaceDoc(dataBaseName, collectionName, documentQuery, document, replaceOptions);
    }

    public static void main(final String[] args) {
        App.connection();

        App.remove();

        // App.update();
        // MongoDatabase database = App.getDatabase("maiziDB");

        // FindIterable<Document> documentIterables = App.findMongoDbDocById("maiziDB",
        // "work",
        // "5eb7c480f76e442ccb1a0eaf");
        // MongoCursor<Document> iterator = documentIterables.iterator();
        // while (iterator.hasNext()) {
        // Document document = iterator.next();
        // System.out.println("json---->" + document.toJson());
        // }

        // MongoCollection<Document> collection = App.getCollection(database, "work");
        // App.insertDocument(collection);

        // List<String> listCollectionNames = App.listCollectionNames("maiziDB");
        // System.out.println(listCollectionNames);
    }

}
