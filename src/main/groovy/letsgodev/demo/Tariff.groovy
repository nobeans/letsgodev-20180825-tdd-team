package letsgodev.demo

import java.time.LocalDate

class Tariff {

    BigDecimal getTotalRate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        getSubtotalRateOfCallPlan(cutoffDate, customerContract, trafficStats) +
            getSubtotalRateOfDataPlan(cutoffDate, customerContract, trafficStats) +
            getSubtotalRateOfAdditionalServices(cutoffDate, customerContract, trafficStats)
    }

    private BigDecimal getSubtotalRateOfCallPlan(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        getRateOfCallPlan(cutoffDate, customerContract, trafficStats) +
            getRateOfInternetConnection(cutoffDate, customerContract, trafficStats)
    }

    private BigDecimal getRateOfCallPlan(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        RateUtils.prorateDaily(cutoffDate, customerContract.callPlanContract.contractDate, customerContract.callPlanContract.cancelDate, customerContract.callPlanContract.callPlan.rate(cutoffDate, customerContract, trafficStats))
    }

    private BigDecimal getRateOfInternetConnection(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) { // 統一感を出すためにあえてfeeではなくrateにした
        RateUtils.prorateDaily(cutoffDate, customerContract.callPlanContract.contractDate, customerContract.callPlanContract.cancelDate, new InternetConnection().rate(cutoffDate, customerContract, trafficStats))
    }

    private BigDecimal getSubtotalRateOfDataPlan(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        RateUtils.prorateDaily(cutoffDate, customerContract.dataPlanContract.contractDate, customerContract.dataPlanContract.cancelDate, customerContract.dataPlanContract.dataPlan.rate(cutoffDate, customerContract, trafficStats))
    }

    private BigDecimal getSubtotalRateOfAdditionalServices(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        // 当月に解約されたオプション契約も請求対象となる。
        (customerContract.availableAdditionalServiceContracts + customerContract.canceledAdditionalServiceContracts.findAll { DateUtils.isSameMonth(it.cancelDate, cutoffDate) }).sum { AdditionalServiceContract additionalServiceContract ->
            additionalServiceContract.additionalService.rate(cutoffDate, customerContract, trafficStats)
        } as BigDecimal ?: 0
    }
}
