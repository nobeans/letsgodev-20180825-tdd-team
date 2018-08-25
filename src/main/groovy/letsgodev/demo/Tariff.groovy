package letsgodev.demo

class Tariff {

    BigDecimal getSubtotalPriceOfCall(CustomerContract customerContract) {
        getPriceOfCallPlan(customerContract) + priceOfInternetFee
    }

    private BigDecimal getPriceOfCallPlan(CustomerContract customerContract) {
        switch (customerContract.callPlan) {
            case CallPlan.BASIC_THE_NEXT:
                return 4500
            case CallPlan.BASIC_HENSHIN:
                return 3500
            case CallPlan.BASIC_X:
                return 2500
            default:
                assert false
        }
    }

    private BigDecimal getPriceOfInternetFee() {
        300
    }

    BigDecimal getSubtotalPriceOfDataPlan(CustomerContract customerContract, long dataTrafficBytes) {
        getPriceOfDataPlan(customerContract, dataTrafficBytes)
    }

    private BigDecimal getPriceOfDataPlan(CustomerContract customerContract, long dataTrafficBytes) {
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

    BigDecimal getTotalPrice(CustomerContract customerContract, long dataTrafficBytes) {
        getSubtotalPriceOfCall(customerContract) + getSubtotalPriceOfDataPlan(customerContract, dataTrafficBytes)
    }
}
