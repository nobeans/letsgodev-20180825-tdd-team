package letsgodev.demo

import groovy.transform.TupleConstructor

import java.time.LocalDate

@TupleConstructor
enum AdditionalService implements Rateable {

    SAFE_COMPENSATION_SERVICE("あんしん補償サービス"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            if (canBeFreeForFirstContract(cutoffDate, customerContract)) {
                return 0
            }
            330
        }
    },

    SAFE_REMOTE_SUPPORT("あんしん遠隔サポート"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            if (canBeFreeForFirstContract(cutoffDate, customerContract)) {
                return 0
            }
            400
        }
    },

    SAFE_NET_SECURITY("あんしんネットセキュリティ"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            if (canBeFreeForFirstContract(cutoffDate, customerContract)) {
                return 0
            }
            500
        }
    }

    String name

    @Override
    String toString() {
        name
    }

    @Override
    abstract BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats)

    /** ※14 初回加入時に限り最大2ヶ月無料(加入月とその翌月)かどうか */
    boolean canBeFreeForFirstContract(LocalDate cutoffDate, CustomerContract customerContract) {
        def currentContract = customerContract.availableAdditionalServiceContracts.find { it.additionalService == this }
        def canceledContract = customerContract.canceledAdditionalServiceContracts.find { it.additionalService == this }
        currentContract && !canceledContract && cutoffDate <= currentContract.contractDate.plusMonths(2)
    }
}
