package letsgodev.demo

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

import static letsgodev.demo.AdditionalService.*
import static letsgodev.demo.CallPlan.*
import static letsgodev.demo.DataPlan.*

class TariffSpec extends Specification {

    Tariff tariff = new Tariff()

    LocalDate cutoffDate = LocalDate.of(2018, 8, 31)

    @Unroll
    void "基本プランが#callPlanのとき、月額の基本料金は#rate円になる"() {
        given:
        def customerContract = new CustomerContract(callPlan: callPlan)

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfCallPlan(cutoffDate, customerContract, trafficStats) == rate

        where:
        callPlan       | rate
        BASIC_THE_NEXT | 4500
        BASIC_HENSHIN  | 3500
        BASIC_X        | 2500
    }

    @Unroll
    void "データ定額プランが#dataPlanのとき、データ通信量が#totalDataBytesバイトの場合、月額料金は#rate円になる"() {
        given:
        def customerContract = new CustomerContract(dataPlan: dataPlan)

        and:
        def trafficStats = new TrafficStats(totalDataBytes: totalDataBytes)

        expect:
        tariff.getRateOfDataPlan(cutoffDate, customerContract, trafficStats) == rate

        where:
        dataPlan   | totalDataBytes | rate
        FLAT_LL    | 0              | 7000
        FLAT_L     | 0              | 6000
        FLAT_M     | 0              | 4500
        STEPWISE_S | 0              | 2900
        STEPWISE_S | 0              | 2900
        STEPWISE_S | 1_000_000_000  | 2900
        STEPWISE_S | 1_000_000_001  | 4000
        STEPWISE_S | 3_000_000_000  | 4000
        STEPWISE_S | 3_000_000_001  | 5000
        STEPWISE_S | 5_000_000_000  | 5000
        STEPWISE_S | 5_000_000_001  | 7000
        STEPWISE_S | 20_000_000_000 | 7000
        STEPWISE_S | 20_000_000_001 | 7000
        STEPWISE_S | Long.MAX_VALUE | 7000
    }

    @Unroll
    void "インターネット接続料金を計算する"() {
        given:
        def customerContract = new CustomerContract()

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfInternetConnection(cutoffDate, customerContract, trafficStats) == 300
    }

    @Unroll
    void "オプションとして#additionalServicesを契約しているとき、オプション料金が#rate円になる"() {
        given:
        def customerContract = new CustomerContract(
            additionalServiceContracts: additionalServices.collect { AdditionalService additionalService ->
                new AdditionalServiceContract(
                    additionalService: additionalService,
                    contractDate: LocalDate.of(2018, 4, 1),
                    cancelDate: null
                )
            }
        )

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfAdditionalServices(cutoffDate, customerContract, trafficStats) == rate

        where:
        additionalServices                                                          | rate
        []                                                                          | 0
        [SAFE_COMPENSATION_SUPPORT]                                                 | 330
        [SAFE_REMOTE_SUPPORT]                                                       | 400
        [SAFE_NET_SECURITY_SUPPORT]                                                 | 500
        [SAFE_COMPENSATION_SUPPORT, SAFE_REMOTE_SUPPORT, SAFE_NET_SECURITY_SUPPORT] | 330 + 400 + 500
    }

    void "月ごとの合計料金(税抜き)を計算する"() {
        given:
        def customerContract = new CustomerContract(callPlan: callPlan, dataPlan: dataPlan)

        and:
        def trafficStats = new TrafficStats(totalDataBytes: totalDataBytes)

        expect:
        tariff.getTotalRate(cutoffDate, customerContract, trafficStats) == rate

        where:
        callPlan       | dataPlan | totalDataBytes | rate
        BASIC_THE_NEXT | FLAT_LL  | 0              | 4500 + 7000 + 300
        BASIC_HENSHIN  | FLAT_LL  | 0              | 3500 + 7000 + 300
        BASIC_X        | FLAT_LL  | 0              | 2500 + 7000 + 300
        // TODO 組み合わせ書く
    }
}
