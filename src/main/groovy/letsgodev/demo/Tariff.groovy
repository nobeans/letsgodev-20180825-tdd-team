package letsgodev.demo

class Tariff {

    BigDecimal getTotalRate(CustomerContract customerContract, long dataTrafficBytes) {
        getSubtotalRateOfCallPlan(customerContract) + getSubtotalRateOfDataPlan(customerContract, dataTrafficBytes) + getRateOfAdditionalService(customerContract)
    }

    private BigDecimal getSubtotalRateOfCallPlan(CustomerContract customerContract) {
        getRateOfCallPlan(customerContract) + internetConnectionFee
    }

    private BigDecimal getRateOfCallPlan(CustomerContract customerContract) {
        customerContract.callPlan.rate
    }

    private BigDecimal getInternetConnectionFee() {
        new InternetConnection().fee
    }

    private BigDecimal getSubtotalRateOfDataPlan(CustomerContract customerContract, long dataTrafficBytes) {
        getRateOfDataPlan(customerContract, dataTrafficBytes)
    }

    private BigDecimal getRateOfDataPlan(CustomerContract customerContract, long dataTrafficBytes) {
        customerContract.dataPlan.getRate(customerContract, dataTrafficBytes)
    }

    private BigDecimal getRateOfAdditionalService(CustomerContract customerContract) {
        customerContract.additionalServices?.sum { AdditionalService additionalService ->
            additionalService.getRate(customerContract, -1) // TODO
        } as BigDecimal ?: 0
    }
}
