package com.bootspark;

/**
 * Created by Jens Eriksberger on 2017-05-15.
 */

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


@Component
public class CountThemWords {

    @Autowired
    private JavaSparkContext javaSparkContext;

    @Value("${input.file}")
    private String inputFile;

    public void count() {

        JavaRDD<String> tokenize = javaSparkContext.textFile(inputFile).flatMap((s1) -> Arrays.asList(s1.split(" ")));

        //occurrence of each word
        JavaPairRDD<String, Integer> counts = tokenize.mapToPair(s -> new Tuple2<>(s, 1)).reduceByKey((i1, i2) -> i1 + i2);

        // filter out words with less than x occurrences
        JavaPairRDD<String, Integer> filtered = counts.filter(
                tup -> tup._2() >= 2
        );

        // count chars
        JavaPairRDD<Character, Integer> charCounts = filtered.flatMap(
                s -> {
                    Collection<Character> chars = new ArrayList<>(s._1().length());
                    for (char c : s._1().toCharArray()) {
                        chars.add(c);
                    }
                    return chars;
                }
        ).mapToPair(c -> new Tuple2<>(c, 1)).reduceByKey((i1, i2) -> i1 + i2);

        System.out.println(charCounts.collect());
    }
}
