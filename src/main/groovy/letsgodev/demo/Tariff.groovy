package letsgodev.demo

class Tariff {

    BigDecimal getSubtotalRateOfCall(CustomerContract customerContract) {
        getRateOfCallPlan(customerContract) + internetConnectionFee
    }

    private BigDecimal getRateOfCallPlan(CustomerContract customerContract) {
        customerContract.callPlan.rate
    }

    private BigDecimal getInternetConnectionFee() {
        new InternetConnection().fee
    }

    BigDecimal getRateOfDataPlan(CustomerContract customerContract, long dataTrafficBytes) {
        customerContract.dataPlan.getRate(customerContract, dataTrafficBytes)
    }

    BigDecimal getRateOfAdditionalService(CustomerContract customerContract) {
        customerContract.additionalServices.sum { AdditionalService additionalService ->
            additionalService.getRate(customerContract, -1) // TODO
        } as BigDecimal ?: 0
    }

    BigDecimal getTotalRate(CustomerContract customerContract, long dataTrafficBytes) {
        getSubtotalRateOfCall(customerContract) + getRateOfDataPlan(customerContract, dataTrafficBytes)
    }
}
