package letsgodev.demo

interface Rateable {

    BigDecimal rate(CustomerContract customerContract, TrafficStats trafficStats)
}
