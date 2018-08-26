package letsgodev.demo

import groovy.transform.TupleConstructor

import java.time.LocalDate

@TupleConstructor
enum DataPlan implements Rateable {

    FLAT_LL("仮面データパックLL"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            7000
        }
    },

    FLAT_L("仮面データパックL"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            6000
        }
    },

    FLAT_M("仮面データパックM"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            4500
        }
    },

    STEPWISE_S("仮面データパックS"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
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
    abstract BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats)

    @Override
    String toString() {
        name
    }
}
