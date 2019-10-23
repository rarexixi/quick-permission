package org.xi.quick.common.model;

public interface SearchVm<T extends BaseCondition> {

    default T getConditionExtension() {
        return null;
    }
}
