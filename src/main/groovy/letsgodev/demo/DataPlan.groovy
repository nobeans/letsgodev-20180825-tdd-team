package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum DataPlan {

    FLAT_LL("[定額]仮面データパックLL"){
        @Override
        Integer getRate(CustomerContract customerContract, long dataTrafficBytes) {
            7000
        }
    },

    FLAT_L("仮面データパックL"){
        @Override
        Integer getRate(CustomerContract customerContract, long dataTrafficBytes) {
            6000
        }
    },

    FLAT_M("仮面データパックM"){
        @Override
        Integer getRate(CustomerContract customerContract, long dataTrafficBytes) {
            4500
        }
    },

    STEPWISE_S("仮面データパックS"){
        @Override
        Integer getRate(CustomerContract customerContract, long dataTrafficBytes) {
            if (dataTrafficBytes <= 1_000_000_000) {
                return 2900
            } else if (dataTrafficBytes <= 3_000_000_000) {
                return 4000
            } else if (dataTrafficBytes <= 5_000_000_000) {
                return 5000
            } else {
                return 7000
            }
        }
    }

    String name

    abstract Integer getRate(CustomerContract customerContract, long dataTrafficBytes)

    @Override
    String toString() {
        name
    }
}
