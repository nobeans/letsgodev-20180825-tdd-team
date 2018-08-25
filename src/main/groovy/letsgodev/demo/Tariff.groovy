package letsgodev.demo

class Tariff {
    BigDecimal calculateMonthlyPayment(BasicPlan plan) {
        switch (plan) {
            case BasicPlan.THE_NEXT:
                return 4500
            case BasicPlan.HENSHIN:
                return 3500
            default:
                assert false
        }
    }
}
