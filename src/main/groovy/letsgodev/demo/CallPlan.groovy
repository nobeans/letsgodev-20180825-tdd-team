package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum CallPlan implements Rateable {

    BASIC_THE_NEXT("レッツゴーデベロッパー THE NEXT プラン"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            4500
        }
    },

    BASIC_HENSHIN("レッツゴーデベロッパー 変真 プラン"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            3500
        }
    },

    BASIC_X("レッツゴーデベロッパー X プラン"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            2500
        }
    }

    String name

    @Override
    String toString() {
        name
    }

    @Override
    abstract Integer rate(CustomerContract customerContract, TrafficStats trafficStats)
}
