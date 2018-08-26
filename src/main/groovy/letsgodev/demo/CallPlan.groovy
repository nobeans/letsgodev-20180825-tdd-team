package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum CallPlan implements Rateable {

    BASIC_THE_NEXT("レッツゴーデベロッパー THE NEXT プラン"){
        @Override
        BigDecimal rate(CustomerContract customerContract, TrafficStats trafficStats) {
            4500
        }
    },

    BASIC_HENSHIN("レッツゴーデベロッパー 変真 プラン"){
        @Override
        BigDecimal rate(CustomerContract customerContract, TrafficStats trafficStats) {
            3500
        }
    },

    BASIC_X("レッツゴーデベロッパー X プラン"){
        @Override
        BigDecimal rate(CustomerContract customerContract, TrafficStats trafficStats) {
            2500
        }
    }

    String name

    @Override
    String toString() {
        name
    }

    @Override
    abstract BigDecimal rate(CustomerContract customerContract, TrafficStats trafficStats)
}
