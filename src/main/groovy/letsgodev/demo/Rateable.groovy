package letsgodev.demo

import java.time.LocalDate

interface Rateable {

    BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats)
}
