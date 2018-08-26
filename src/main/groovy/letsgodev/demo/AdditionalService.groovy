package letsgodev.demo


import groovy.transform.TupleConstructor

@TupleConstructor
enum AdditionalService {

    SAFE_COMPENSATION_SUPPORT("あんしん補償サービス"),
    SAFE_REMOTE_SUPPORT("あんしん遠隔サポート"),
    SAFE_NET_SECURITY_SUPPORT("あんしんネットセキュリティ")

    String name

    @Override
    String toString() {
        name
    }
}
