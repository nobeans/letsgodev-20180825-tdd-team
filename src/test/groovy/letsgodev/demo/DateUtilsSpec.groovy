package letsgodev.demo

import spock.lang.Specification

import java.time.LocalDate

class DateUtilsSpec extends Specification {

    void "同じ月かどうか"() {
        expect:
        DateUtils.isSameMonth(date1, date2) == isSame

        where:
        date1                     | date2                     | isSame
        LocalDate.of(2018, 8, 25) | LocalDate.of(2018, 8, 1)  | true
        LocalDate.of(2018, 8, 25) | LocalDate.of(2018, 8, 25) | true
        LocalDate.of(2018, 8, 25) | LocalDate.of(2018, 8, 31) | true
        LocalDate.of(2018, 8, 25) | LocalDate.of(2018, 9, 1)  | false
        null                      | LocalDate.of(2018, 8, 25) | false
        LocalDate.of(2018, 8, 25) | null                      | false
        null                      | null                      | false
    }
}
