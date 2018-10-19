package com.jz.bigdata.myspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;
import scala.tools.nsc.backend.icode.Members;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by jazzyshi on 2018/8/19.
 */
public class TestRDD {
    public  static void main(String ...args){
        System.setProperty("hadoop.home.dir", "C:\\Users\\jazzyshi\\Desktop\\bigdata\\hadoop-2.8.2");
        SparkConf conf = new SparkConf().setAppName("TestRDD").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

//        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
//        JavaRDD<Integer> distData = sc.parallelize(data);
//        distData.reduce((a,b) -> a+ b);

//        JavaRDD<String> distFile = sc.textFile("C:\\Users\\jazzyshi\\Desktop\\bigdata\\遇到问题1.txt");
//        distFile.map(s -> s.length()).reduce((a, b) -> a + b);
//
//        JavaRDD<String> lines = sc.textFile("C:\\\\Users\\\\jazzyshi\\\\Desktop\\\\bigdata\\\\遇到问题1.txt");
//        JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
//        int totalLength = lineLengths.reduce((a, b) -> a + b);
//        System.out.println("数量="+totalLength);
//        lineLengths.foreach(tt -> {
//            System.out.print("foreach是="+tt);
//        });

//        JavaRDD<String> lines = sc.textFile("C:\\\\Users\\\\jazzyshi\\\\Desktop\\\\bigdata\\\\遇到问题1.txt");
//        JavaPairRDD<String, Integer> pairs = lines.mapToPair(s -> new Tuple2(s, 1));
//        JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);
//        counts.sortByKey();
//        counts.foreach(tt -> {
//            System.out.println("foreach是="+tt._1()+","+tt._2());
//        });

//        Broadcast<int[]> broadcastVar = sc.broadcast(new int[] {1, 2, 3});
//        broadcastVar.value();

    }
}
