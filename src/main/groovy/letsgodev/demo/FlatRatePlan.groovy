package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum FlatRatePlan {

    FLAT_LL("[定額]仮面データパックLL"),

    FLAT_L("仮面データパックL")

    String name
}
