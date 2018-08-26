package letsgodev.demo

class Tariff {

    BigDecimal getTotalRate(CustomerContract customerContract, TrafficStats trafficStats) {
        getSubtotalRateOfCallPlan(customerContract, trafficStats) +
            getSubtotalRateOfDataPlan(customerContract, trafficStats) +
            getRateOfAdditionalServices(customerContract, trafficStats)
    }

    private BigDecimal getSubtotalRateOfCallPlan(CustomerContract customerContract, TrafficStats trafficStats) {
        getRateOfCallPlan(customerContract, trafficStats) + getRateOfInternetConnection(customerContract, trafficStats)
    }

    private BigDecimal getRateOfCallPlan(CustomerContract customerContract, TrafficStats trafficStats) {
        customerContract.callPlan.rate(customerContract, trafficStats)
    }

    private BigDecimal getRateOfInternetConnection(CustomerContract customerContract, TrafficStats trafficStats) { // 統一感を出すためにあえてfeeではなくrateにした
        new InternetConnection().rate(customerContract, trafficStats)
    }

    private BigDecimal getSubtotalRateOfDataPlan(CustomerContract customerContract, TrafficStats trafficStats) {
        getRateOfDataPlan(customerContract, trafficStats)
    }

    private BigDecimal getRateOfDataPlan(CustomerContract customerContract, TrafficStats trafficStats) {
        customerContract.dataPlan.rate(customerContract, trafficStats)
    }

    private BigDecimal getRateOfAdditionalServices(CustomerContract customerContract, TrafficStats trafficStats) {
        customerContract.additionalServices?.sum { AdditionalService additionalService ->
            additionalService.rate(customerContract, trafficStats)
        } as BigDecimal ?: 0
    }
}
