package letsgodev.demo

class InternetConnection implements Rateable {

    @Override
    BigDecimal rate(CustomerContract customerContract, TrafficStats trafficStats) {
        300
    }
}
