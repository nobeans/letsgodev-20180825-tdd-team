package letsgodev.demo

import java.time.LocalDate

class InternetConnection implements Rateable {

    @Override
    BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        300
    }
}
