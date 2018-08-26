package letsgodev.demo

import groovy.transform.ToString
import jdk.nashorn.internal.ir.annotations.Immutable

@ToString
@Immutable
class TrafficStats {

    /** 合計電話通話時間(sec) */
    long totalCallSeconds

    /** 国際電話通話時間(sec) */
    long internationalCallSeconds

    /**
     * サービス利用通話時間(sec)。
     * <p>
     * 以下の有料電話サービスの利用時間の合計です。
     * <ul>
     *   <li>テレドーム(0180から始まる番号)、
     *   <li>ナビダイヤル等(0570から始まる番号)
     *   <li>番号案内(104)(0570から始まる番号)
     *   <li>番号案内(104)
     *   <li>等
     * </ul>
     */
    long serviceCallSeconds

    /** データ通信量(byte) */
    long totalDataBytes
}
