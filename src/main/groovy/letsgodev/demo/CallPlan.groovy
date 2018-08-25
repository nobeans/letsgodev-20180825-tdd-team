package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum CallPlan {

    BASIC_THE_NEXT("レッツゴーデベロッパー THE NEXT プラン"),

    BASIC_HENSHIN("レッツゴーデベロッパー 変真 プラン"),

    BASIC_X("レッツゴーデベロッパー X プラン")

    String name
}
