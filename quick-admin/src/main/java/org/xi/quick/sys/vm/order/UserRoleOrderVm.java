package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.UserRoleOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserRoleOrderVm implements OrderVm {

    /**
     * 以用户ID排序
     */
    public SortConstants userIdSort;

    /**
     * 以角色ID排序
     */
    public SortConstants roleIdSort;

    public UserRoleOrderCondition getOrderCondition() {

        UserRoleOrderCondition condition = new UserRoleOrderCondition();
        condition.setUserIdSort(userIdSort);
        condition.setRoleIdSort(roleIdSort);

        return condition;
    }
}
