package letsgodev.demo

import groovy.transform.TupleConstructor

import java.time.LocalDate

@TupleConstructor
enum AdditionalService implements Rateable {

    SAFE_COMPENSATION_SUPPORT("あんしん補償サービス"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            // ※14 初回加入時に限り最大2ヶ月無料(加入月とその翌月)
            def currentContract = customerContract.availableAdditionalServiceContracts.find { it.additionalService == SAFE_COMPENSATION_SUPPORT }
            def canceledContract = customerContract.canceledAdditionalServiceContracts.find { it.additionalService == SAFE_COMPENSATION_SUPPORT }
            if (currentContract && !canceledContract && cutoffDate <= currentContract.contractDate.plusMonths(2)) {
                return 0
            }
            330
        }
    },

    SAFE_REMOTE_SUPPORT("あんしん遠隔サポート"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            400
        }
    },

    SAFE_NET_SECURITY_SUPPORT("あんしんネットセキュリティ"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
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
}
