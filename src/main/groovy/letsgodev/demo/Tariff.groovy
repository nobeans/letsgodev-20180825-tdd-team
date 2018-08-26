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
            switch (additionalService) {
                case AdditionalService.SAFE_COMPENSATION_SUPPORT:
                    return 330
                case AdditionalService.SAFE_REMOTE_SUPPORT:
                    return 400
                case AdditionalService.SAFE_NET_SECURITY_SUPPORT:
                    return 500
                default:
                    return 0
            }
        } as BigDecimal ?: 0
    }

    BigDecimal getTotalRate(CustomerContract customerContract, long dataTrafficBytes) {
        getSubtotalRateOfCall(customerContract) + getRateOfDataPlan(customerContract, dataTrafficBytes)
    }
}
