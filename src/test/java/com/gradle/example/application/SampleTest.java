package com.gradle.example.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleTest {

    @Autowired
    private Sample target;

    @Test
    public void sumTest() {
        assertEquals(3, target.sum(1, 2));
    }

}