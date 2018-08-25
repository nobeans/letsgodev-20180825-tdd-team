package letsgodev.demo

class Tariff {

    BigDecimal getPriceOfCallPlan(CallPlan plan) {
        switch (plan) {
            case CallPlan.BASIC_THE_NEXT:
                return 4500 + priceOfInternetFee
            case CallPlan.BASIC_HENSHIN:
                return 3500 + priceOfInternetFee
            case CallPlan.BASIC_X:
                return 2500 + priceOfInternetFee
            default:
                assert false
        }
    }

    BigDecimal getPriceOfInternetFee() {
        300
    }

    BigDecimal getPriceOfDataPlan(DataPlan plan, long dataTrafficBytes) {
        switch (plan) {
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

    BigDecimal getTotalPrice(CallPlan callPlan, DataPlan dataPlan, long dataTrafficBytes) {
        getPriceOfCallPlan(callPlan) + getPriceOfDataPlan(dataPlan, dataTrafficBytes)
    }
}
