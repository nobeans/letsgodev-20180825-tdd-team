package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum BasicPlan {

    THE_NEXT("レッツゴーデベロッパー THE NEXT プラン"),

    HENSHIN("レッツゴーデベロッパー 変真 プラン")

    String name
}
