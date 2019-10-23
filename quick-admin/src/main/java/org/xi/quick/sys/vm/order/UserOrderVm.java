package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.UserOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserOrderVm implements OrderVm {

    /**
     * 以用户ID排序
     */
    public SortConstants userIdSort;

    /**
     * 以用户名排序
     */
    public SortConstants usernameSort;

    /**
     * 以邮箱排序
     */
    public SortConstants emailSort;

    /**
     * 以手机号排序
     */
    public SortConstants mobileSort;

    /**
     * 以姓名排序
     */
    public SortConstants realNameSort;

    public UserOrderCondition getOrderCondition() {

        UserOrderCondition condition = new UserOrderCondition();
        condition.setUserIdSort(userIdSort);
        condition.setUsernameSort(usernameSort);
        condition.setEmailSort(emailSort);
        condition.setMobileSort(mobileSort);
        condition.setRealNameSort(realNameSort);

        return condition;
    }
}
