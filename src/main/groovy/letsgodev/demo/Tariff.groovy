package letsgodev.demo

class Tariff {

    BigDecimal getPriceOfBasicPlan(BasicPlan plan) {
        switch (plan) {
            case BasicPlan.THE_NEXT:
                return 4500
            case BasicPlan.HENSHIN:
                return 3500
            case BasicPlan.X:
                return 2500
            default:
                assert false
        }
    }

    BigDecimal getPriceOfInternetFee() {
        300
    }

    BigDecimal getPriceOfFlatRatePlan(FlatRatePlan plan, long dataTrafficBytes) {
        switch (plan) {
            case FlatRatePlan.FLAT_LL:
                return 7000
            case FlatRatePlan.FLAT_L:
                return 6000
            case FlatRatePlan.FLAT_M:
                return 4500
            case FlatRatePlan.STEPWISE_S:
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
}
