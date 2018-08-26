package letsgodev.demo

import spock.lang.Specification

class RateUtilsSpec extends Specification {

    void "金額に端数がある場合は小数第一位で四捨五入する"() {
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
}
