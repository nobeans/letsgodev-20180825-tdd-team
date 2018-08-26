package letsgodev.demo

import java.time.LocalDate

class RateUtils {

    static Integer round(BigDecimal calculatedRate) {
        calculatedRate.setScale(0, BigDecimal.ROUND_HALF_UP)
    }

    static BigDecimal prorateDaily(BigDecimal fullRate, LocalDate contractDate, LocalDate cutoffDate) {
        if (DateUtils.isSameMonth(cutoffDate, contractDate)) {
            def ratePerDay = fullRate / cutoffDate.lengthOfMonth()
            return round(ratePerDay * (contractDate.lengthOfMonth() - contractDate.dayOfMonth + 1))
        }
        fullRate
    }
}
