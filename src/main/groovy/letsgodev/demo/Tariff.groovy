package letsgodev.demo

class Tariff {

    BigDecimal getTotalRate(CustomerContract customerContract, TrafficStats trafficStats) {
        getSubtotalRateOfCallPlan(customerContract, trafficStats) +
            getSubtotalRateOfDataPlan(customerContract, trafficStats) +
            getRateOfAdditionalService(customerContract, trafficStats)
    }

    private BigDecimal getSubtotalRateOfCallPlan(CustomerContract customerContract, TrafficStats trafficStats) {
        getRateOfCallPlan(customerContract, trafficStats) + internetConnectionFee
    }

    private BigDecimal getRateOfCallPlan(CustomerContract customerContract, TrafficStats trafficStats) {
        customerContract.callPlan.getRate(customerContract, trafficStats)
    }

    private BigDecimal getInternetConnectionFee() {
        new InternetConnection().fee
    }

    private BigDecimal getSubtotalRateOfDataPlan(CustomerContract customerContract, TrafficStats trafficStats) {
        getRateOfDataPlan(customerContract, trafficStats)
    }

    private BigDecimal getRateOfDataPlan(CustomerContract customerContract, TrafficStats trafficStats) {
        customerContract.dataPlan.getRate(customerContract, trafficStats)
    }

    private BigDecimal getRateOfAdditionalService(CustomerContract customerContract, TrafficStats trafficStats) {
        customerContract.additionalServices?.sum { AdditionalService additionalService ->
            additionalService.getRate(customerContract, trafficStats)
        } as BigDecimal ?: 0
    }
}
