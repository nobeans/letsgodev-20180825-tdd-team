package letsgodev.demo

class Tariff {

    BigDecimal getSubtotalRateOfCall(CustomerContract customerContract) {
        getRateOfCallPlan(customerContract) + internetConnectionFee
    }

    private BigDecimal getRateOfCallPlan(CustomerContract customerContract) {
        customerContract.callPlan.rate
    }

    private BigDecimal getInternetConnectionFee() {
        300
    }

    BigDecimal getRatePriceOfDataPlan(CustomerContract customerContract, long dataTrafficBytes) {
        getRateOfDataPlan(customerContract, dataTrafficBytes)
    }

    private BigDecimal getRateOfDataPlan(CustomerContract customerContract, long dataTrafficBytes) {
        switch (customerContract.dataPlan) {
            case DataPlan.FLAT_LL:
                return 7000
            case DataPlan.FLAT_L:
                return 6000
            case DataPlan.FLAT_M:
                return 4500
            case DataPlan.STEPWISE_S:
                if (dataTrafficBytes <= 1_000_000_000) {
                    return 2900
                } else if (dataTrafficBytes <= 3_000_000_000) {
                    return 4000
                } else if (dataTrafficBytes <= 5_000_000_000) {
                    return 5000
                } else {
                    return 7000
                }
            default:
                assert false
        }
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
        getSubtotalRateOfCall(customerContract) + getRatePriceOfDataPlan(customerContract, dataTrafficBytes)
    }
}
