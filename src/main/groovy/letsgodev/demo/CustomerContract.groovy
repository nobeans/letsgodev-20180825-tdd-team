package letsgodev.demo

import groovy.transform.ToString
import jdk.nashorn.internal.ir.annotations.Immutable

@ToString
@Immutable
class CustomerContract {

    CallPlan callPlan
    DataPlan dataPlan

    Set<AdditionalServiceContract> additionalServiceContracts

    Set<AdditionalServiceContract> getAvailableAdditionalServiceContracts() {
        additionalServiceContracts.findAll { it.cancelDate == null } ?: []
    }

    Set<AdditionalServiceContract> getCanceledAdditionalServiceContracts() {
        additionalServiceContracts.findAll { it.cancelDate != null } ?: []
    }
}
