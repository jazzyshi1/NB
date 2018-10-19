package com.jz.bigdata.myspark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.List;

/**
 * Created by jazzyshi on 2018/8/21.
 */
public class TestKafkaSpark {

    public static void main(String ...args){
        System.setProperty("hadoop.home.dir", "C:\\Users\\jazzyshi\\Desktop\\bigdata\\hadoop-2.8.2");
        SparkSession spark = SparkSession
                .builder()
                .appName("JavaStructuredStreming")
                .getOrCreate();


        Dataset<Row> df = spark
                .read()
                .format("kafka")
                .option("kafka.bootstrap.servers", "192.168.0.88:9092")
                .option("subscribe", "FirstTopic")
                .option("maxOffsetPerTrigger",100)
                .load();

        df.selectExpr("CAST(key AS STRING)","CAST(value AS STRING)");


    }
}
