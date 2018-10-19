package com.jz.bigdata.myspark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * Created by jazzyshi on 2018/8/19.
 */
public class SimpleAp {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "C:\\Users\\jazzyshi\\Desktop\\bigdata\\hadoop-2.8.2");
        String logFile = "/home/spark/README.md"; // Should be some file on your system
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile);

        long numAs = logData.filter(s -> s.contains("a")).count();
        long numBs = logData.filter(s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
    }
}
