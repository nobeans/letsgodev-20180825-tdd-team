package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum DataPlan implements Rateable {

    FLAT_LL("[定額]仮面データパックLL"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            7000
        }
    },

    FLAT_L("仮面データパックL"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            6000
        }
    },

    FLAT_M("仮面データパックM"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            4500
        }
    },

    STEPWISE_S("仮面データパックS"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            if (trafficStats.totalDataBytes <= 1_000_000_000) {
                return 2900
            } else if (trafficStats.totalDataBytes <= 3_000_000_000) {
                return 4000
            } else if (trafficStats.totalDataBytes <= 5_000_000_000) {
                return 5000
            } else {
                return 7000
            }
        }
    }

    String name

    @Override
    abstract Integer rate(CustomerContract customerContract, TrafficStats trafficStats)

    @Override
    String toString() {
        name
    }
}
