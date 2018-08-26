package letsgodev.demo

import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

import static letsgodev.demo.AdditionalService.*
import static letsgodev.demo.CallPlan.*
import static letsgodev.demo.DataPlan.*

class TariffSpec extends Specification {

    @Shared
    LocalDate cutoffDate = LocalDate.of(2018, 8, 31) // 月末締め(固定)とする

    Tariff tariff = new Tariff()

    @Unroll
    void "基本プランが#callPlanのとき、月額の基本料金は#rate円になる"() {
        given:
        def customerContract = new CustomerContract(
            callPlanContract: new CallPlanContract(
                callPlan: callPlan,
                contractDate: LocalDate.of(2018, 4, 1),
                cancelDate: null,
            )
        )

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
    @IgnoreRest
    void "基本プランの#callPlanを当月の#contractDateに新規契約したとき、月額の基本料金は日割りされて#rate円になる"() {
        given:
        def customerContract = new CustomerContract(
            callPlanContract: new CallPlanContract(
                callPlan: callPlan,
                contractDate: contractDate,
                cancelDate: null,
            )
        )

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfCallPlan(cutoffDate, customerContract, trafficStats) == rate


        where:
        callPlan       | contractDate                                          | rate
        BASIC_THE_NEXT | cutoffDate.withDayOfMonth(1)                          | 4500
        BASIC_THE_NEXT | cutoffDate.withDayOfMonth(2)                          | RateUtils.round(4500 / cutoffDate.lengthOfMonth() * (cutoffDate.lengthOfMonth() - 1))
        BASIC_THE_NEXT | cutoffDate.withDayOfMonth(cutoffDate.lengthOfMonth()) | RateUtils.round(4500 / cutoffDate.lengthOfMonth())
        //BASIC_HENSHIN  | cutoffDate.withDayOfMonth(cutoffDate.lengthOfMonth()) | 3500
        //BASIC_X        | cutoffDate.withDayOfMonth(1)                          | 2500
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
        tariff.getRateOfAdditionalServices(cutoffDate, customerContract, trafficStats) == rate

        where:
        additionalServices                                                  | rate
        []                                                                  | 0
        [SAFE_COMPENSATION_SERVICE]                                         | 330
        [SAFE_REMOTE_SUPPORT]                                               | 400
        [SAFE_NET_SECURITY]                                                 | 500
        [SAFE_COMPENSATION_SERVICE, SAFE_REMOTE_SUPPORT, SAFE_NET_SECURITY] | 330 + 400 + 500
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
            contractDate: contractDate,
            cancelDate: null,
        )

        def customerContract = new CustomerContract(additionalServiceContracts: additionalServiceContracts)

        and:
        def trafficStats = new TrafficStats()

        expect:
        tariff.getRateOfAdditionalServices(cutoffDate, customerContract, trafficStats) == rate

        where:
        additionalService         | canceledOnce | contractDate                | rate | description
        SAFE_COMPENSATION_SERVICE | false        | cutoffDate                  | 0    | "初回加入月は無料となる"
        SAFE_COMPENSATION_SERVICE | false        | cutoffDate.minusMonths(1)   | 0    | "初回加入月の翌月は無料となる"
        SAFE_COMPENSATION_SERVICE | false        | cutoffDate.minusMonths(2)   | 330  | "初回加入月の翌々月は有料となる"
        SAFE_COMPENSATION_SERVICE | false        | cutoffDate.minusMonths(999) | 330  | "初回加入月のかなり昔は有料となる"
        SAFE_COMPENSATION_SERVICE | true         | cutoffDate                  | 330  | "加入月ではあるが初回ではない場合は有料となる"
        SAFE_REMOTE_SUPPORT       | false        | cutoffDate                  | 0    | "初回加入月は無料となる"
        SAFE_REMOTE_SUPPORT       | false        | cutoffDate.minusMonths(1)   | 0    | "初回加入月の翌月は無料となる"
        SAFE_REMOTE_SUPPORT       | false        | cutoffDate.minusMonths(2)   | 400  | "初回加入月の翌々月は有料となる"
        SAFE_REMOTE_SUPPORT       | false        | cutoffDate.minusMonths(999) | 400  | "初回加入月のかなり昔は有料となる"
        SAFE_REMOTE_SUPPORT       | true         | cutoffDate                  | 400  | "加入月ではあるが初回ではない場合は有料となる"
        SAFE_NET_SECURITY         | false        | cutoffDate                  | 0    | "初回加入月は無料となる"
        SAFE_NET_SECURITY         | false        | cutoffDate.minusMonths(1)   | 0    | "初回加入月の翌月は無料となる"
        SAFE_NET_SECURITY         | false        | cutoffDate.minusMonths(2)   | 500  | "初回加入月の翌々月は有料となる"
        SAFE_NET_SECURITY         | false        | cutoffDate.minusMonths(999) | 500  | "初回加入月のかなり昔は有料となる"
        SAFE_NET_SECURITY         | true         | cutoffDate                  | 500  | "加入月ではあるが初回ではない場合は有料となる"
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
        tariff.getRateOfAdditionalServices(cutoffDate, customerContract, trafficStats) == rate

        where:
        additionalService         | contractDate                                          | rate
        SAFE_COMPENSATION_SERVICE | cutoffDate.withDayOfMonth(1)                          | 330
        SAFE_COMPENSATION_SERVICE | cutoffDate.withDayOfMonth(cutoffDate.lengthOfMonth()) | 330
        SAFE_REMOTE_SUPPORT       | cutoffDate.withDayOfMonth(1)                          | 400
        SAFE_REMOTE_SUPPORT       | cutoffDate.withDayOfMonth(cutoffDate.lengthOfMonth()) | 400
        SAFE_NET_SECURITY         | cutoffDate.withDayOfMonth(1)                          | 500
        SAFE_NET_SECURITY         | cutoffDate.withDayOfMonth(cutoffDate.lengthOfMonth()) | 500
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
        tariff.getRateOfAdditionalServices(cutoffDate, customerContract, trafficStats) == rate

        where:
        additionalService         | cancelDate                                            | rate
        SAFE_COMPENSATION_SERVICE | cutoffDate.withDayOfMonth(1)                          | 330
        SAFE_COMPENSATION_SERVICE | cutoffDate.withDayOfMonth(cutoffDate.lengthOfMonth()) | 330
        SAFE_REMOTE_SUPPORT       | cutoffDate.withDayOfMonth(1)                          | 400
        SAFE_REMOTE_SUPPORT       | cutoffDate.withDayOfMonth(cutoffDate.lengthOfMonth()) | 400
        SAFE_NET_SECURITY         | cutoffDate.withDayOfMonth(1)                          | 500
        SAFE_NET_SECURITY         | cutoffDate.withDayOfMonth(cutoffDate.lengthOfMonth()) | 500
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
