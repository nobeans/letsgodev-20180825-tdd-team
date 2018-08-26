package letsgodev.demo

import spock.lang.Specification
import spock.lang.Unroll

class TariffSpec extends Specification {

    Tariff tariff = new Tariff()

    @Unroll
    void "基本プランが#callPlanのとき、月額の基本料金は#price円になる"() {
        given:
        def contract = new CustomerContract(callPlan: callPlan)

        expect:
        tariff.getPriceOfCallPlan(contract) == price

        where:
        callPlan                | price
        CallPlan.BASIC_THE_NEXT | 4500
        CallPlan.BASIC_HENSHIN  | 3500
        CallPlan.BASIC_X        | 2500
    }

    @Unroll
    void "データ定額プランが#dataPlanのとき、データ通信量が#dataTrafficBytesバイトの場合、月額料金は#price円になる"() {
        given:
        def contract = new CustomerContract(dataPlan: dataPlan)

        expect:
        tariff.getPriceOfDataPlan(contract, dataTrafficBytes) == price

        where:
        dataPlan            | dataTrafficBytes | price
        DataPlan.FLAT_LL    | 0                | 7000
        DataPlan.FLAT_L     | 0                | 6000
        DataPlan.FLAT_M     | 0                | 4500
        DataPlan.STEPWISE_S | 0                | 2900
        DataPlan.STEPWISE_S | 0                | 2900
        DataPlan.STEPWISE_S | 1_000_000_000    | 2900
        DataPlan.STEPWISE_S | 1_000_000_001    | 4000
        DataPlan.STEPWISE_S | 3_000_000_000    | 4000
        DataPlan.STEPWISE_S | 3_000_000_001    | 5000
        DataPlan.STEPWISE_S | 5_000_000_000    | 5000
        DataPlan.STEPWISE_S | 5_000_000_001    | 7000
        DataPlan.STEPWISE_S | 20_000_000_000   | 7000
        DataPlan.STEPWISE_S | 20_000_000_001   | 7000
        DataPlan.STEPWISE_S | Long.MAX_VALUE   | 7000
    }

    @Unroll
    void "インターネット接続料金を計算する"() {
        expect:
        tariff.priceOfInternetFee == 300
    }

    @Unroll
    void "オプションとして#additionalServicesを契約しているとき、オプション料金が#price円になる"() {
        given:
        def contract = new CustomerContract(additionalServices: additionalServices)

        expect:
        tariff.getPriceOfAdditionalService(contract) == price

        where:
        additionalServices                                                                                                                | price
        []                                                                                                                                | 0
        [AdditionalService.SAFE_COMPENSATION_SUPPORT]                                                                                     | 330
        [AdditionalService.SAFE_REMOTE_SUPPORT]                                                                                           | 400
        [AdditionalService.SAFE_NET_SECURITY_SUPPORT]                                                                                     | 500
        [AdditionalService.SAFE_COMPENSATION_SUPPORT, AdditionalService.SAFE_REMOTE_SUPPORT, AdditionalService.SAFE_NET_SECURITY_SUPPORT] | 330 + 400 + 500
    }

    void "月ごとの合計料金(税抜き)を計算する"() {
        given:
        def contract = new CustomerContract(callPlan: callPlan, dataPlan: dataPlan)

        expect:
        tariff.getTotalPrice(contract, dataTrafficBytes) == price

        where:
        callPlan                | dataPlan         | dataTrafficBytes | price
        CallPlan.BASIC_THE_NEXT | DataPlan.FLAT_LL | 0                | 4500 + 7000 + 300
        CallPlan.BASIC_HENSHIN  | DataPlan.FLAT_LL | 0                | 3500 + 7000 + 300
        CallPlan.BASIC_X        | DataPlan.FLAT_LL | 0                | 2500 + 7000 + 300
        // TODO 組み合わせ書く
    }
}
