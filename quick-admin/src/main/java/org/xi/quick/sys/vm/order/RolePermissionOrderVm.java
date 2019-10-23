package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.RolePermissionOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RolePermissionOrderVm implements OrderVm {

    /**
     * 以角色ID排序
     */
    public SortConstants roleIdSort;

    /**
     * 以权限ID排序
     */
    public SortConstants permissionIdSort;

    public RolePermissionOrderCondition getOrderCondition() {

        RolePermissionOrderCondition condition = new RolePermissionOrderCondition();
        condition.setRoleIdSort(roleIdSort);
        condition.setPermissionIdSort(permissionIdSort);

        return condition;
    }
}
