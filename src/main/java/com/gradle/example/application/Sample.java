package com.gradle.example.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sample {

    /**
     * 足し算を行う
     *
     * @param a : 第一引数
     * @param b : 第二引数
     * @return aにbを加算した値
     */
    public int sum(int a, int b) {
        log.info("sum Start a : {}. b : {}", a, b);
        int result = a + b;
        log.info("result : {}", result);
        return result;
    }
}
