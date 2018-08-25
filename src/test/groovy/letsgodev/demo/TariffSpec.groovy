package letsgodev.demo

import spock.lang.Specification
import spock.lang.Unroll

class TariffSpec extends Specification {

    Tariff tariff = new Tariff()

    @Unroll
    void "基本プランの料金が#plan.nameのとき月額料金は#priceになる"() {
        expect:
        tariff.getPriceOfBasicPlan(plan) == price

        where:
        plan               | price
        BasicPlan.THE_NEXT | 4500
        BasicPlan.HENSHIN  | 3500
        BasicPlan.X        | 2500
    }

    @Unroll
    void "インターネット接続料金を計算する"() {
        expect:
        tariff.priceOfInternetFee == 300
    }
}
