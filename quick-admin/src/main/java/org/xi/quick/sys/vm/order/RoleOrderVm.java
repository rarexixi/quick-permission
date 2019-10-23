package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.RoleOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RoleOrderVm implements OrderVm {

    /**
     * 以角色ID排序
     */
    public SortConstants roleIdSort;

    public RoleOrderCondition getOrderCondition() {

        RoleOrderCondition condition = new RoleOrderCondition();
        condition.setRoleIdSort(roleIdSort);

        return condition;
    }
}
