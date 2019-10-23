package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.UserTokenOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统用户Token
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserTokenOrderVm implements OrderVm {

    /**
     * 以UserId排序
     */
    public SortConstants userIdSort;

    /**
     * 以token排序
     */
    public SortConstants tokenSort;

    public UserTokenOrderCondition getOrderCondition() {

        UserTokenOrderCondition condition = new UserTokenOrderCondition();
        condition.setUserIdSort(userIdSort);
        condition.setTokenSort(tokenSort);

        return condition;
    }
}
