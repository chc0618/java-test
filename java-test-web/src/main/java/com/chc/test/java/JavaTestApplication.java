package com.chc.test.java;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JavaTestApplication {
    public static void main(String[] args) {
        // 设置 druid 使用 slf4j 记录日志
        long startTime=System.currentTimeMillis();
        log.info("HollyApplication main start ......");


        System.setProperty("druid.logType", "slf4j");
        SpringApplication.run(JavaTestApplication.class, args);

        long costTime=System.currentTimeMillis()-startTime;
        log.info("HollyApplication main start ok ...... costTime:{} ms", costTime);
    }

}
