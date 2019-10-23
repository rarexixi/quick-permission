package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.PermissionOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class PermissionOrderVm implements OrderVm {

    /**
     * 以权限ID排序
     */
    public SortConstants permissionIdSort;

    public PermissionOrderCondition getOrderCondition() {

        PermissionOrderCondition condition = new PermissionOrderCondition();
        condition.setPermissionIdSort(permissionIdSort);

        return condition;
    }
}
