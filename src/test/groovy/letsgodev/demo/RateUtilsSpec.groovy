package letsgodev.demo

import spock.lang.Specification

import java.time.LocalDate

class RateUtilsSpec extends Specification {

    void "金額に端数がある場合は小数点第一位で四捨五入する"() {
        expect:
        RateUtils.round(123.45) == 123

        where:
        original | result
        0        | 0
        0.1      | 0
        0.4      | 0
        0.499999 | 0
        0.5      | 1
        0.9      | 1
        123.0    | 123
        123.1    | 123
        123.4    | 123
        123.4999 | 123
        123.5    | 124
        123.9    | 124
    }

    void "日割り計算する"() {
        expect:
        RateUtils.prorateDaily(cutofDate, contractDate, fullRate) == proratedRate

        where:
        cutofDate                 | contractDate              | fullRate | proratedRate
        LocalDate.of(2018, 8, 31) | LocalDate.of(2018, 8, 1)  | 31.0     | 31.0
        LocalDate.of(2018, 8, 31) | LocalDate.of(2018, 8, 2)  | 31.0     | 30.0
        LocalDate.of(2018, 8, 31) | LocalDate.of(2018, 8, 30) | 31.0     | 2.0
        LocalDate.of(2018, 8, 31) | LocalDate.of(2018, 8, 31) | 31.0     | 1.0
        LocalDate.of(2018, 6, 30) | LocalDate.of(2018, 6, 1)  | 30.0     | 30.0
        LocalDate.of(2018, 6, 30) | LocalDate.of(2018, 6, 2)  | 30.0     | 29.0
        LocalDate.of(2018, 6, 30) | LocalDate.of(2018, 6, 29) | 30.0     | 2.0
        LocalDate.of(2018, 6, 30) | LocalDate.of(2018, 6, 30) | 30.0     | 1.0
        LocalDate.of(2018, 6, 30) | LocalDate.of(2018, 6, 15) | 100.0    | 53.0 // 端数は小数点第一位で四捨五入されること
    }
}

