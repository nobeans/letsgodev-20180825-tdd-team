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
                return 2900
            default:
                assert false
        }
    }
}
