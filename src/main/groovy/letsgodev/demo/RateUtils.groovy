package letsgodev.demo

class RateUtils {

    static Integer round(BigDecimal calculatedRate) {
        calculatedRate.setScale(0, BigDecimal.ROUND_HALF_UP)
    }
}
