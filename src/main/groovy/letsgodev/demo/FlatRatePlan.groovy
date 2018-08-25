package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum FlatRatePlan {

    FLAT_LL("[定額]仮面データパックLL"),

    FLAT_L("仮面データパックL"),

    FLAT_M("仮面データパックM")

    String name
}
