package org.xi.quick.common.model;

public interface OrderVm<T extends OrderCondition> extends OrderCondition {

    default T getOrderCondition() {
        return null;
    }
}
