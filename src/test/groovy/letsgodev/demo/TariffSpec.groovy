package letsgodev.demo

import spock.lang.Specification

class TariffSpec extends Specification {

    void "基本プランの料金を計算する"() {
        given:
        def tariff = new Tariff()

        and:
        def plan = "レッツゴーデベロッパー THE NEXT プラン"

        expect:
        tariff.calculateMonthlyPayment(plan) == 4500
    }
}
