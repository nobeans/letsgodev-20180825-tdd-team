package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum CallPlan implements Rateable {

    BASIC_THE_NEXT("レッツゴーデベロッパー THE NEXT プラン", 4500),

    BASIC_HENSHIN("レッツゴーデベロッパー 変真 プラン", 3500),

    BASIC_X("レッツゴーデベロッパー X プラン", 2500)

    String name

    Integer rate

    @Override
    String toString() {
        "${name}"
    }

    @Override
    Integer getRate(CustomerContract customerContract, TrafficStats trafficStats) {
        rate
    }
}
