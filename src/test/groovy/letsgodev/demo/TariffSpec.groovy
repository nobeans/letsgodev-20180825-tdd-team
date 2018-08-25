package letsgodev.demo

import spock.lang.Specification
import spock.lang.Unroll

class TariffSpec extends Specification {

    @Unroll
    void "基本プランの料金が#plan.nameのとき月額料金は#priceになる"() {
        given:
        def tariff = new Tariff()

        expect:
        tariff.calculateMonthlyPayment(plan) == price

        where:
        plan               | price
        BasicPlan.THE_NEXT | 4500
        BasicPlan.HENSHIN  | 3500
        BasicPlan.X        | 2500
    }
}
