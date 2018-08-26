package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum DataPlan {

    FLAT_LL("[定額]仮面データパックLL"),

    FLAT_L("仮面データパックL"),

    FLAT_M("仮面データパックM"),

    STEPWISE_S("仮面データパックS")

    String name

    @Override
    String toString() {
        name
    }
}
