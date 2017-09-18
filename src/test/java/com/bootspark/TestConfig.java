package com.bootspark;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jens Eriksberger on 2017-05-15.
 */

@Configuration
public class TestConfig {

    @Bean public SparkConf sparkConf(){
        return new SparkConf(false);
    }

}
