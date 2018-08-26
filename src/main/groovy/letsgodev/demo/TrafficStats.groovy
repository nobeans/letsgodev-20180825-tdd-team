package letsgodev.demo

import groovy.transform.ToString
import jdk.nashorn.internal.ir.annotations.Immutable

@ToString
@Immutable
class TrafficStats {

    long totalCallSeconds
    long totalDataBytes
}
