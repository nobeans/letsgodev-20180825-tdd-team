package letsgodev.demo

import spock.lang.Specification
import spock.lang.Unroll

class TariffSpec extends Specification {

    Tariff tariff = new Tariff()

    @Unroll
    void "基本プランの料金が#plan.nameのとき月額料金は#price円になる"() {
        expect:
        tariff.getPriceOfBasicPlan(plan) == price

        where:
        plan               | price
        BasicPlan.THE_NEXT | 4500
        BasicPlan.HENSHIN  | 3500
        BasicPlan.X        | 2500
    }

    @Unroll
    void "データ定額プランの料金が#plan.nameのとき、データ通信量が#dataTrafficBytesバイトの場合、月額料金は#price円になる"() {
        expect:
        tariff.getPriceOfFlatRatePlan(plan, dataTrafficBytes) == price

        where:
        plan                    | dataTrafficBytes | price
        FlatRatePlan.FLAT_LL    | 0                | 7000
        FlatRatePlan.FLAT_L     | 0                | 6000
        FlatRatePlan.FLAT_M     | 0                | 4500
        FlatRatePlan.STEPWISE_S | 0                | 2900
        FlatRatePlan.STEPWISE_S | 0                | 2900
        FlatRatePlan.STEPWISE_S | 1_000_000_000    | 2900
        FlatRatePlan.STEPWISE_S | 1_000_000_001    | 4000
        FlatRatePlan.STEPWISE_S | 3_000_000_000    | 4000
        FlatRatePlan.STEPWISE_S | 3_000_000_001    | 5000
        FlatRatePlan.STEPWISE_S | 5_000_000_000    | 5000
        FlatRatePlan.STEPWISE_S | 5_000_000_001    | 7000
        FlatRatePlan.STEPWISE_S | 20_000_000_000   | 7000
        FlatRatePlan.STEPWISE_S | 20_000_000_001   | 7000
        FlatRatePlan.STEPWISE_S | Long.MAX_VALUE   | 7000
    }

    @Unroll
    void "インターネット接続料金を計算する"() {
        expect:
        tariff.priceOfInternetFee == 300
    }
}
