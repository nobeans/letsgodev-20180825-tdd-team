package letsgodev.demo

class InternetConnection implements Rateable {

    @Override
    Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
        300
    }
}
