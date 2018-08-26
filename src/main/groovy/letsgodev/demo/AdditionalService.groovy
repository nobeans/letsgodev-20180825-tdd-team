package letsgodev.demo

import groovy.transform.TupleConstructor

import java.time.LocalDate

@TupleConstructor
enum AdditionalService implements Rateable {

    SAFE_COMPENSATION_SUPPORT("あんしん補償サービス"){
        @Override
        BigDecimal rate(LocalDate cutoffDate, CustomerContract customerContract, TrafficStats trafficStats) {
            // ※14 初回加入時に限り最大2ヶ月無料(加入月とその翌月)
            // TODO キャンセル時の履歴をどうするか
            // TODO 「初回加入時」
            def contractDate = customerContract.additionalServiceContracts.find { it.additionalService == SAFE_COMPENSATION_SUPPORT }?.contractDate
            if (contractDate && cutoffDate <= contractDate.plusMonths(2)) {
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
