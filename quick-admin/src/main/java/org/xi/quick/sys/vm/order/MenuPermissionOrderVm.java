package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.MenuPermissionOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class MenuPermissionOrderVm implements OrderVm {

    /**
     * 以菜单ID排序
     */
    public SortConstants menuIdSort;

    /**
     * 以权限ID排序
     */
    public SortConstants permissionIdSort;

    public MenuPermissionOrderCondition getOrderCondition() {

        MenuPermissionOrderCondition condition = new MenuPermissionOrderCondition();
        condition.setMenuIdSort(menuIdSort);
        condition.setPermissionIdSort(permissionIdSort);

        return condition;
    }
}
