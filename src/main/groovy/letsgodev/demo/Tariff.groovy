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
}
