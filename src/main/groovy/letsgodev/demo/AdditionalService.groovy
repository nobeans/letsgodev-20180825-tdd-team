package letsgodev.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum AdditionalService implements Rateable {

    SAFE_COMPENSATION_SUPPORT("あんしん補償サービス"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            330
        }
    },

    SAFE_REMOTE_SUPPORT("あんしん遠隔サポート"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            400
        }
    },

    SAFE_NET_SECURITY_SUPPORT("あんしんネットセキュリティ"){
        @Override
        Integer rate(CustomerContract customerContract, TrafficStats trafficStats) {
            500
        }
    }

    String name

    @Override
    String toString() {
        name
    }

    @Override
    abstract Integer rate(CustomerContract customerContract, TrafficStats trafficStats)
}
