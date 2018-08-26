package letsgodev.demo

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

import static letsgodev.demo.AdditionalService.*
import static letsgodev.demo.CallPlan.*
import static letsgodev.demo.DataPlan.*

class TariffSpec extends Specification {

    @Shared
    LocalDate cutoffDate_ = LocalDate.of(2018, 8, 31) // 月末締め(固定)とする

    Tariff tariff = new Tariff()

    @Unroll
    void "基本プランの#callPlanを#contractDateに新規契約し#cancelDescriptionたとき、#cutoffDateを締め日とした月額の基本料金は#proratedDescription#rate円になる"() {
        given:
        def customerContract = new CustomerContract(
            callPlanContract: new CallPlanContract(
                callPlan: callPlan,
                contractDate: dateOf(contractDate),
                cancelDate: dateOf(cancelDate),
            )
        )

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfCallPlan(dateOf(cutoffDate), customerContract, trafficStats) == rate

        where:
        callPlan       | cutoffDate   | contractDate | cancelDate   | rate
        // 日割りなし
        BASIC_THE_NEXT | "2018-08-31" | "2018-04-01" | null         | 4500
        BASIC_HENSHIN  | "2018-08-31" | "2018-04-01" | null         | 3500
        BASIC_X        | "2018-08-31" | "2018-04-01" | null         | 2500

        // 日割り有り
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-01" | null         | 4500
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-02" | null         | RateUtils.round(4500 / 31 * 30)
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-31" | null         | RateUtils.round(4500 / 31)
        BASIC_THE_NEXT | "2018-08-31" | "2018-04-01" | "2018-08-01" | RateUtils.round(4500 / 31)
        BASIC_THE_NEXT | "2018-08-31" | "2018-04-01" | "2018-08-02" | RateUtils.round(4500 / 31 * 2)
        BASIC_THE_NEXT | "2018-08-31" | "2018-04-01" | "2018-08-31" | 4500
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-15" | "2018-08-15" | RateUtils.round(4500 / 31)
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-15" | "2018-08-16" | RateUtils.round(4500 / 31 * 2)
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-01" | null         | 3500
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-02" | null         | RateUtils.round(3500 / 31 * 30)
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-31" | null         | RateUtils.round(3500 / 31)
        BASIC_HENSHIN  | "2018-08-31" | "2018-04-01" | "2018-08-01" | RateUtils.round(3500 / 31)
        BASIC_HENSHIN  | "2018-08-31" | "2018-04-01" | "2018-08-02" | RateUtils.round(3500 / 31 * 2)
        BASIC_HENSHIN  | "2018-08-31" | "2018-04-01" | "2018-08-31" | 3500
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-15" | "2018-08-15" | RateUtils.round(3500 / 31)
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-15" | "2018-08-16" | RateUtils.round(3500 / 31 * 2)
        BASIC_X        | "2018-08-31" | "2018-08-01" | null         | 2500
        BASIC_X        | "2018-08-31" | "2018-08-02" | null         | RateUtils.round(2500 / 31 * 30)
        BASIC_X        | "2018-08-31" | "2018-08-31" | null         | RateUtils.round(2500 / 31)
        BASIC_X        | "2018-08-31" | "2018-04-01" | "2018-08-01" | RateUtils.round(2500 / 31)
        BASIC_X        | "2018-08-31" | "2018-04-01" | "2018-08-02" | RateUtils.round(2500 / 31 * 2)
        BASIC_X        | "2018-08-31" | "2018-04-01" | "2018-08-31" | 2500
        BASIC_X        | "2018-08-31" | "2018-08-15" | "2018-08-15" | RateUtils.round(2500 / 31)
        BASIC_X        | "2018-08-31" | "2018-08-15" | "2018-08-16" | RateUtils.round(2500 / 31 * 2)

        cancelDescription = cancelDate ? "て${cancelDate}に解約し" : ""

        proratedDescription = DateUtils.isSameMonth(dateOf(cutoffDate), dateOf(contractDate)) || DateUtils.isSameMonth(dateOf(cutoffDate), dateOf(cancelDate)) ? "日割りされて" : ""
    }

    @Unroll
    void "基本プランの#callPlanを#contractDateに新規契約し#cancelDescriptionたとき、#cutoffDateを締め日としたインターネット接続料金は#proratedDescription#rate円になる"() {
        given:
        def customerContract = new CustomerContract(
            callPlanContract: new CallPlanContract(
                callPlan: callPlan,
                contractDate: dateOf(contractDate),
                cancelDate: dateOf(cancelDate),
            )
        )

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfInternetConnection(dateOf(cutoffDate), customerContract, trafficStats) == rate

        where:
        callPlan       | cutoffDate   | contractDate | cancelDate   | rate
        // 日割りなし
        BASIC_THE_NEXT | "2018-08-31" | "2018-04-01" | null         | 300
        BASIC_HENSHIN  | "2018-08-31" | "2018-04-01" | null         | 300
        BASIC_X        | "2018-08-31" | "2018-04-01" | null         | 300

        // 日割りあり
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-01" | null         | 300
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-02" | null         | RateUtils.round(300 / 31 * 30)
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-31" | null         | RateUtils.round(300 / 31)
        BASIC_THE_NEXT | "2018-08-31" | "2018-04-01" | "2018-08-01" | RateUtils.round(300 / 31)
        BASIC_THE_NEXT | "2018-08-31" | "2018-04-01" | "2018-08-02" | RateUtils.round(300 / 31 * 2)
        BASIC_THE_NEXT | "2018-08-31" | "2018-04-01" | "2018-08-31" | 300
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-15" | "2018-08-15" | RateUtils.round(300 / 31)
        BASIC_THE_NEXT | "2018-08-31" | "2018-08-15" | "2018-08-16" | RateUtils.round(300 / 31 * 2)
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-01" | null         | 300
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-02" | null         | RateUtils.round(300 / 31 * 30)
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-31" | null         | RateUtils.round(300 / 31)
        BASIC_HENSHIN  | "2018-08-31" | "2018-04-01" | "2018-08-01" | RateUtils.round(300 / 31)
        BASIC_HENSHIN  | "2018-08-31" | "2018-04-01" | "2018-08-02" | RateUtils.round(300 / 31 * 2)
        BASIC_HENSHIN  | "2018-08-31" | "2018-04-01" | "2018-08-31" | 300
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-15" | "2018-08-15" | RateUtils.round(300 / 31)
        BASIC_HENSHIN  | "2018-08-31" | "2018-08-15" | "2018-08-16" | RateUtils.round(300 / 31 * 2)
        BASIC_X        | "2018-08-31" | "2018-08-01" | null         | 300
        BASIC_X        | "2018-08-31" | "2018-08-02" | null         | RateUtils.round(300 / 31 * 30)
        BASIC_X        | "2018-08-31" | "2018-08-31" | null         | RateUtils.round(300 / 31)
        BASIC_X        | "2018-08-31" | "2018-04-01" | "2018-08-01" | RateUtils.round(300 / 31)
        BASIC_X        | "2018-08-31" | "2018-04-01" | "2018-08-02" | RateUtils.round(300 / 31 * 2)
        BASIC_X        | "2018-08-31" | "2018-04-01" | "2018-08-31" | 300
        BASIC_X        | "2018-08-31" | "2018-08-15" | "2018-08-15" | RateUtils.round(300 / 31)
        BASIC_X        | "2018-08-31" | "2018-08-15" | "2018-08-16" | RateUtils.round(300 / 31 * 2)

        cancelDescription = cancelDate ? "て${cancelDate}に解約し" : ""

        proratedDescription = DateUtils.isSameMonth(dateOf(cutoffDate), dateOf(contractDate)) || DateUtils.isSameMonth(dateOf(cutoffDate), dateOf(cancelDate)) ? "日割りされて" : ""
    }

    @Unroll
    void "データ定額プランの#dataPlanを#contractDateに新規契約し#cancelDescriptionたとき、データ通信量が#totalDataBytesバイトの場合、#cutoffDateを締め日とした月額の基本料金は#proratedDescription#rate円になる"() {
        given:
        def customerContract = new CustomerContract(
            dataPlanContract: new DataPlanContract(
                dataPlan: dataPlan,
                contractDate: dateOf(contractDate),
                cancelDate: dateOf(cancelDate),
            )
        )

        and:
        def trafficStats = new TrafficStats(totalDataBytes: totalDataBytes)

        expect:
        tariff.getRateOfDataPlan(dateOf(cutoffDate), customerContract, trafficStats) == rate

        where:
        dataPlan   | cutoffDate   | contractDate | cancelDate   | totalDataBytes | rate
        // 日割りなし
        FLAT_LL    | "2018-08-31" | "2018-04-01" | null         | 0              | 7000
        FLAT_L     | "2018-08-31" | "2018-04-01" | null         | 0              | 6000
        FLAT_M     | "2018-08-31" | "2018-04-01" | null         | 0              | 4500
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | 0              | 2900
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | 1_000_000_001  | 4000
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | 3_000_000_001  | 5000
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | 0              | 2900
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | 1_000_000_001  | 4000
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | 3_000_000_001  | 5000
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | 5_000_000_001  | 7000
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | 20_000_000_001 | 7000
        STEPWISE_S | "2018-08-31" | "2018-04-01" | null         | Long.MAX_VALUE | 7000

        // 日割りあり
        FLAT_LL    | "2018-08-31" | "2018-08-01" | null         | 0              | 7000
        FLAT_LL    | "2018-08-31" | "2018-08-02" | null         | 0              | RateUtils.round(7000 / 31 * 30)
        FLAT_LL    | "2018-08-31" | "2018-08-31" | null         | 0              | RateUtils.round(7000 / 31)
        FLAT_LL    | "2018-08-31" | "2018-04-01" | "2018-08-01" | 0              | RateUtils.round(7000 / 31)
        FLAT_LL    | "2018-08-31" | "2018-04-01" | "2018-08-02" | 0              | RateUtils.round(7000 / 31 * 2)
        FLAT_LL    | "2018-08-31" | "2018-04-01" | "2018-08-31" | 0              | 7000
        FLAT_LL    | "2018-08-31" | "2018-08-15" | "2018-08-15" | 0              | RateUtils.round(7000 / 31)
        FLAT_LL    | "2018-08-31" | "2018-08-15" | "2018-08-16" | 0              | RateUtils.round(7000 / 31 * 2)
        FLAT_L     | "2018-08-31" | "2018-08-01" | null         | 0              | 6000
        FLAT_L     | "2018-08-31" | "2018-08-02" | null         | 0              | RateUtils.round(6000 / 31 * 30)
        FLAT_L     | "2018-08-31" | "2018-08-31" | null         | 0              | RateUtils.round(6000 / 31)
        FLAT_L     | "2018-08-31" | "2018-04-01" | "2018-08-01" | 0              | RateUtils.round(6000 / 31)
        FLAT_L     | "2018-08-31" | "2018-04-01" | "2018-08-02" | 0              | RateUtils.round(6000 / 31 * 2)
        FLAT_L     | "2018-08-31" | "2018-04-01" | "2018-08-31" | 0              | 6000
        FLAT_L     | "2018-08-31" | "2018-08-15" | "2018-08-15" | 0              | RateUtils.round(6000 / 31)
        FLAT_L     | "2018-08-31" | "2018-08-15" | "2018-08-16" | 0              | RateUtils.round(6000 / 31 * 2)
        FLAT_M     | "2018-08-31" | "2018-08-01" | null         | 0              | 4500
        FLAT_M     | "2018-08-31" | "2018-08-02" | null         | 0              | RateUtils.round(4500 / 31 * 30)
        FLAT_M     | "2018-08-31" | "2018-08-31" | null         | 0              | RateUtils.round(4500 / 31)
        FLAT_M     | "2018-08-31" | "2018-04-01" | "2018-08-01" | 0              | RateUtils.round(4500 / 31)
        FLAT_M     | "2018-08-31" | "2018-04-01" | "2018-08-02" | 0              | RateUtils.round(4500 / 31 * 2)
        FLAT_M     | "2018-08-31" | "2018-04-01" | "2018-08-31" | 0              | 4500
        FLAT_M     | "2018-08-31" | "2018-08-15" | "2018-08-15" | 0              | RateUtils.round(4500 / 31)
        FLAT_M     | "2018-08-31" | "2018-08-15" | "2018-08-16" | 0              | RateUtils.round(4500 / 31 * 2)
        STEPWISE_S | "2018-08-31" | "2018-08-01" | null         | 0              | 2900
        STEPWISE_S | "2018-08-31" | "2018-08-02" | null         | 0              | RateUtils.round(2900 / 31 * 30)
        STEPWISE_S | "2018-08-31" | "2018-08-31" | null         | 0              | RateUtils.round(2900 / 31)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-01" | 0              | RateUtils.round(2900 / 31)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-02" | 0              | RateUtils.round(2900 / 31 * 2)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-31" | 0              | 2900
        STEPWISE_S | "2018-08-31" | "2018-08-15" | "2018-08-15" | 0              | RateUtils.round(2900 / 31)
        STEPWISE_S | "2018-08-31" | "2018-08-15" | "2018-08-16" | 0              | RateUtils.round(2900 / 31 * 2)
        STEPWISE_S | "2018-08-31" | "2018-08-01" | null         | 1_000_000_001  | 4000
        STEPWISE_S | "2018-08-31" | "2018-08-02" | null         | 1_000_000_001  | RateUtils.round(4000 / 31 * 30)
        STEPWISE_S | "2018-08-31" | "2018-08-31" | null         | 1_000_000_001  | RateUtils.round(4000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-01" | 1_000_000_001  | RateUtils.round(4000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-02" | 1_000_000_001  | RateUtils.round(4000 / 31 * 2)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-31" | 1_000_000_001  | 4000
        STEPWISE_S | "2018-08-31" | "2018-08-15" | "2018-08-15" | 1_000_000_001  | RateUtils.round(4000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-08-15" | "2018-08-16" | 1_000_000_001  | RateUtils.round(4000 / 31 * 2)
        STEPWISE_S | "2018-08-31" | "2018-08-01" | null         | 3_000_000_001  | 5000
        STEPWISE_S | "2018-08-31" | "2018-08-02" | null         | 3_000_000_001  | RateUtils.round(5000 / 31 * 30)
        STEPWISE_S | "2018-08-31" | "2018-08-31" | null         | 3_000_000_001  | RateUtils.round(5000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-01" | 3_000_000_001  | RateUtils.round(5000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-02" | 3_000_000_001  | RateUtils.round(5000 / 31 * 2)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-31" | 3_000_000_001  | 5000
        STEPWISE_S | "2018-08-31" | "2018-08-15" | "2018-08-15" | 3_000_000_001  | RateUtils.round(5000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-08-15" | "2018-08-16" | 3_000_000_001  | RateUtils.round(5000 / 31 * 2)
        STEPWISE_S | "2018-08-31" | "2018-08-01" | null         | 5_000_000_001  | 7000
        STEPWISE_S | "2018-08-31" | "2018-08-02" | null         | 5_000_000_001  | RateUtils.round(7000 / 31 * 30)
        STEPWISE_S | "2018-08-31" | "2018-08-31" | null         | 5_000_000_001  | RateUtils.round(7000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-01" | 5_000_000_001  | RateUtils.round(7000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-02" | 5_000_000_001  | RateUtils.round(7000 / 31 * 2)
        STEPWISE_S | "2018-08-31" | "2018-04-01" | "2018-08-31" | 5_000_000_001  | 7000
        STEPWISE_S | "2018-08-31" | "2018-08-15" | "2018-08-15" | 5_000_000_001  | RateUtils.round(7000 / 31)
        STEPWISE_S | "2018-08-31" | "2018-08-15" | "2018-08-16" | 5_000_000_001  | RateUtils.round(7000 / 31 * 2)

        cancelDescription = cancelDate ? "て${cancelDate}に解約し" : ""

        proratedDescription = DateUtils.isSameMonth(dateOf(cutoffDate), dateOf(contractDate)) || DateUtils.isSameMonth(dateOf(cutoffDate), dateOf(cancelDate)) ? "日割りされて" : ""
    }

    @Unroll
    void "オプションとして#additionalServicesを契約しているとき、オプション料金が#rate円になる"() {
        given:
        def additionalServiceContracts = []

        // 過去のキャンセルされたオプション契約は計算対象にならないこと
        def oldDate = LocalDate.of(1970, 1, 1)
        additionalServiceContracts.addAll(additionalServices.collect { AdditionalService additionalService ->
            new AdditionalServiceContract(additionalService: additionalService, contractDate: oldDate, cancelDate: oldDate)
        })

        // 計算対象となるオプション契約
        additionalServiceContracts.addAll(additionalServices.collect { AdditionalService additionalService ->
            new AdditionalServiceContract(
                additionalService: additionalService,
                contractDate: LocalDate.of(2018, 4, 1), // ここでは初回加入月の無料計算の対象外として計算する
                cancelDate: null,
            )
        })

        def customerContract = new CustomerContract(additionalServiceContracts: additionalServiceContracts)

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfAdditionalServices(dateOf(cutoffDate), customerContract, trafficStats) == rate

        where:
        additionalServices                                                  | rate
        []                                                                  | 0
        [SAFE_COMPENSATION_SERVICE]                                         | 330
        [SAFE_REMOTE_SUPPORT]                                               | 400
        [SAFE_NET_SECURITY]                                                 | 500
        [SAFE_COMPENSATION_SERVICE, SAFE_REMOTE_SUPPORT, SAFE_NET_SECURITY] | 330 + 400 + 500

        cutoffDate = "2018-08-31"
    }

    @Unroll
    void "オプションとして#additionalServiceを契約しているとき、#description"() {
        given:
        def additionalServiceContracts = []

        // 過去のオプション契約
        if (canceledOnce) {
            def oldDate = LocalDate.of(1970, 1, 1)
            additionalServiceContracts << new AdditionalServiceContract(additionalService: additionalService, contractDate: oldDate, cancelDate: oldDate)
        }

        // 計算対象となるオプション契約
        additionalServiceContracts << new AdditionalServiceContract(
            additionalService: additionalService,
            contractDate: dateOf(contractDate),
            cancelDate: null,
        )

        def customerContract = new CustomerContract(additionalServiceContracts: additionalServiceContracts)

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfAdditionalServices(dateOf(cutoffDate), customerContract, trafficStats) == rate

        where:
        additionalService         | canceledOnce | cutoffDate   | contractDate | rate | description
        SAFE_COMPENSATION_SERVICE | false        | "2018-08-31" | "2018-08-01" | 0    | "初回加入月は無料となる"
        SAFE_COMPENSATION_SERVICE | false        | "2018-08-31" | "2018-07-01" | 0    | "初回加入月の翌月は無料となる"
        SAFE_COMPENSATION_SERVICE | false        | "2018-08-31" | "2018-06-01" | 330  | "初回加入月の翌々月は有料となる"
        SAFE_COMPENSATION_SERVICE | false        | "2018-08-31" | "1970-01-01" | 330  | "初回加入月のかなり昔は有料となる"
        SAFE_COMPENSATION_SERVICE | true         | "2018-08-31" | "2018-08-01" | 330  | "加入月ではあるが初回ではない場合は有料となる"
        SAFE_REMOTE_SUPPORT       | false        | "2018-08-31" | "2018-08-01" | 0    | "初回加入月は無料となる"
        SAFE_REMOTE_SUPPORT       | false        | "2018-08-31" | "2018-07-01" | 0    | "初回加入月の翌月は無料となる"
        SAFE_REMOTE_SUPPORT       | false        | "2018-08-31" | "2018-06-01" | 400  | "初回加入月の翌々月は有料となる"
        SAFE_REMOTE_SUPPORT       | false        | "2018-08-31" | "1970-01-01" | 400  | "初回加入月のかなり昔は有料となる"
        SAFE_REMOTE_SUPPORT       | true         | "2018-08-31" | "2018-08-01" | 400  | "加入月ではあるが初回ではない場合は有料となる"
        SAFE_NET_SECURITY         | false        | "2018-08-31" | "2018-08-01" | 0    | "初回加入月は無料となる"
        SAFE_NET_SECURITY         | false        | "2018-08-31" | "2018-07-01" | 0    | "初回加入月の翌月は無料となる"
        SAFE_NET_SECURITY         | false        | "2018-08-31" | "2018-06-01" | 500  | "初回加入月の翌々月は有料となる"
        SAFE_NET_SECURITY         | false        | "2018-08-31" | "1970-01-01" | 500  | "初回加入月のかなり昔は有料となる"
        SAFE_NET_SECURITY         | true         | "2018-08-31" | "2018-08-01" | 500  | "加入月ではあるが初回ではない場合は有料となる"
    }

    @Unroll
    void "オプションとして#additionalServiceを月途中で新規契約した場合、日割り計算せずに満額請求となる"() {
        given:
        def additionalServiceContracts = []

        // 過去のオプション契約(これがない場合は、加入月＝無料となるため、※15の条件は適用外となる)
        def oldDate = LocalDate.of(1970, 1, 1)
        additionalServiceContracts << new AdditionalServiceContract(additionalService: additionalService, contractDate: oldDate, cancelDate: oldDate)

        // 計算対象となるオプション契約
        additionalServiceContracts << new AdditionalServiceContract(
            additionalService: additionalService,
            contractDate: contractDate,
            cancelDate: null,
        )

        def customerContract = new CustomerContract(additionalServiceContracts: additionalServiceContracts)

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfAdditionalServices(cutoffDate_, customerContract, trafficStats) == rate

        where:
        additionalService         | contractDate                                            | rate
        SAFE_COMPENSATION_SERVICE | cutoffDate_.withDayOfMonth(1)                           | 330
        SAFE_COMPENSATION_SERVICE | cutoffDate_.withDayOfMonth(cutoffDate_.lengthOfMonth()) | 330
        SAFE_REMOTE_SUPPORT       | cutoffDate_.withDayOfMonth(1)                           | 400
        SAFE_REMOTE_SUPPORT       | cutoffDate_.withDayOfMonth(cutoffDate_.lengthOfMonth()) | 400
        SAFE_NET_SECURITY         | cutoffDate_.withDayOfMonth(1)                           | 500
        SAFE_NET_SECURITY         | cutoffDate_.withDayOfMonth(cutoffDate_.lengthOfMonth()) | 500
    }

    @Unroll
    void "オプションとして#additionalServiceを月途中で解約した場合、日割り計算せずに満額請求となる"() {
        given:
        def additionalServiceContracts = []

        // 過去のオプション契約(これがない場合は、加入月＝無料となるため、※15の条件は適用外となる)
        // 計算対象となるオプション契約
        additionalServiceContracts << new AdditionalServiceContract(
            additionalService: additionalService,
            contractDate: LocalDate.of(1970, 1, 1),
            cancelDate: cancelDate,
        )

        def customerContract = new CustomerContract(additionalServiceContracts: additionalServiceContracts)

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfAdditionalServices(cutoffDate_, customerContract, trafficStats) == rate

        where:
        additionalService         | cancelDate                                              | rate
        SAFE_COMPENSATION_SERVICE | cutoffDate_.withDayOfMonth(1)                           | 330
        SAFE_COMPENSATION_SERVICE | cutoffDate_.withDayOfMonth(cutoffDate_.lengthOfMonth()) | 330
        SAFE_REMOTE_SUPPORT       | cutoffDate_.withDayOfMonth(1)                           | 400
        SAFE_REMOTE_SUPPORT       | cutoffDate_.withDayOfMonth(cutoffDate_.lengthOfMonth()) | 400
        SAFE_NET_SECURITY         | cutoffDate_.withDayOfMonth(1)                           | 500
        SAFE_NET_SECURITY         | cutoffDate_.withDayOfMonth(cutoffDate_.lengthOfMonth()) | 500
    }

    void "月ごとの合計料金(税抜き)を計算する"() {
        given:
        def customerContract = new CustomerContract(
            callPlanContract: new CallPlanContract(
                callPlan: callPlan,
                contractDate: LocalDate.of(2018, 4, 1),
                cancelDate: null,
            ),
            dataPlanContract: new DataPlanContract(
                dataPlan: dataPlan,
                contractDate: LocalDate.of(2018, 4, 1),
                cancelDate: null,
            )
        )

        and:
        def trafficStats = new TrafficStats(totalDataBytes: totalDataBytes)

        expect:
        tariff.getTotalRate(cutoffDate_, customerContract, trafficStats) == rate

        where:
        callPlan       | dataPlan | totalDataBytes | rate
        BASIC_THE_NEXT | FLAT_LL  | 0              | 4500 + 7000 + 300
        BASIC_HENSHIN  | FLAT_LL  | 0              | 3500 + 7000 + 300
        BASIC_X        | FLAT_LL  | 0              | 2500 + 7000 + 300
        // TODO 組み合わせ書く
    }

    private static LocalDate dateOf(String date) {
        if (!date) return null
        def token = date.split("-")*.toInteger()
        assert token.size() == 3
        LocalDate.of(token[0], token[1], token[2])
    }
}
