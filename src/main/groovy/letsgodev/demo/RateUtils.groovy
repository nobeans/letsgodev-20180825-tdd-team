package letsgodev.demo

import java.time.LocalDate

class RateUtils {

    static Integer round(BigDecimal calculatedRate) {
        calculatedRate.setScale(0, BigDecimal.ROUND_HALF_UP)
    }

    static BigDecimal prorateDaily(LocalDate cutoffDate, LocalDate contractDate, LocalDate cancelDate, BigDecimal fullRate) {
        assert cutoffDate : "締め日は必須"
        assert contractDate : "契約日は必須"
        if (DateUtils.isSameMonth(cutoffDate, contractDate) && DateUtils.isSameMonth(cutoffDate, cancelDate)) {
            assert contractDate <= cancelDate : "契約日よりも解約日が後のはず"
            def ratePerDay = fullRate / cutoffDate.lengthOfMonth()
            return round(ratePerDay * (cancelDate.dayOfMonth - contractDate.dayOfMonth + 1))
        }
        if (DateUtils.isSameMonth(cutoffDate, contractDate)) {
            def ratePerDay = fullRate / cutoffDate.lengthOfMonth()
            return round(ratePerDay * (cutoffDate.lengthOfMonth() - contractDate.dayOfMonth + 1))
        }
        if (DateUtils.isSameMonth(cutoffDate, cancelDate)) {
            def ratePerDay = fullRate / cutoffDate.lengthOfMonth()
            return round(ratePerDay * cancelDate.dayOfMonth)
        }
        fullRate
    }
}
