package letsgodev.demo

import java.time.LocalDate

class DateUtils {

    static boolean isSameMonth(LocalDate date1, LocalDate date2) {
        if (!date1 || !date2) {
            return false
        }
        date1.year == date2.year && date1.monthValue == date2.monthValue
    }
}
