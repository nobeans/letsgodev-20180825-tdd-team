package letsgodev.demo

import groovy.transform.ToString
import jdk.nashorn.internal.ir.annotations.Immutable

@ToString
@Immutable
class CustomerContract {

    CallPlanContract callPlanContract
    DataPlan dataPlan

    Set<AdditionalServiceContract> additionalServiceContracts

    Set<AdditionalServiceContract> getAvailableAdditionalServiceContracts() {
        additionalServiceContracts.findAll { it.cancelDate == null } ?: []
    }

    Set<AdditionalServiceContract> getCanceledAdditionalServiceContracts() {
        additionalServiceContracts.findAll { it.cancelDate != null } ?: []
    }
}
