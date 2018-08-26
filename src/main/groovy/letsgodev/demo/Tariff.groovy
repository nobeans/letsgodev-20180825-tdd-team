package letsgodev.demo

import java.time.LocalDate

class Tariff {

    BigDecimal getTotalRate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        getSubtotalRateOfCallPlan(cutoffDate, customerContract, trafficStats) +
            getSubtotalRateOfDataPlan(cutoffDate, customerContract, trafficStats) +
            getRateOfAdditionalServices(cutoffDate, customerContract, trafficStats)
    }

    private BigDecimal getSubtotalRateOfCallPlan(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        getRateOfCallPlan(cutoffDate, customerContract, trafficStats) +
            getRateOfInternetConnection(cutoffDate, customerContract, trafficStats)
    }

    private BigDecimal getRateOfCallPlan(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        customerContract.callPlan.rate(cutoffDate, customerContract, trafficStats)
    }

    private BigDecimal getRateOfInternetConnection(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) { // 統一感を出すためにあえてfeeではなくrateにした
        new InternetConnection().rate(cutoffDate, customerContract, trafficStats)
    }

    private BigDecimal getSubtotalRateOfDataPlan(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        getRateOfDataPlan(cutoffDate, customerContract, trafficStats)
    }

    private BigDecimal getRateOfDataPlan(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        customerContract.dataPlan.rate(cutoffDate, customerContract, trafficStats)
    }

    private BigDecimal getRateOfAdditionalServices(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
        // 当月に解約されたオプション契約も請求対象となる。
        (customerContract.availableAdditionalServiceContracts + customerContract.canceledAdditionalServiceContracts.findAll { isSameMonth(it.cancelDate, cutoffDate) }).sum { AdditionalServiceContract additionalServiceContract ->
            additionalServiceContract.additionalService.rate(cutoffDate, customerContract, trafficStats)
        } as BigDecimal ?: 0
    }

    // TODO とでDateUtilsに移動
    static boolean isSameMonth(LocalDate date1, LocalDate date2) {
        date1.year == date2.year && date1.monthValue == date2.monthValue
    }
}
