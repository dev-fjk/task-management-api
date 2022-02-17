package com.gradle.example.application

import spock.lang.Specification

class SampleSpec extends Specification {

    Sample sut

    def "足し算テスト"() {
        given:
        sut = new Sample()
        def input1 = a
        def input2 = b

        when:
        def result = sut.sum(input1, input2)

        then:
        result == expected

        where:
        a | b | expected
        1 | 1 | 2
        2 | 2 | 4
    }
}
