package letsgodev.demo

import groovy.transform.ToString
import jdk.nashorn.internal.ir.annotations.Immutable

import java.time.LocalDate

@ToString
@Immutable
class AdditionalServiceContract {

    AdditionalService additionalService

    LocalDate contractDate

    LocalDate cancelDate
}
