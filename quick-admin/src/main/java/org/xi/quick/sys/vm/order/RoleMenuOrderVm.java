package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.RoleMenuOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RoleMenuOrderVm implements OrderVm {

    /**
     * 以角色ID排序
     */
    public SortConstants roleIdSort;

    /**
     * 以菜单ID排序
     */
    public SortConstants menuIdSort;

    public RoleMenuOrderCondition getOrderCondition() {

        RoleMenuOrderCondition condition = new RoleMenuOrderCondition();
        condition.setRoleIdSort(roleIdSort);
        condition.setMenuIdSort(menuIdSort);

        return condition;
    }
}
