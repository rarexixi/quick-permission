package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

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
public class UserOrderCondition implements OrderCondition {

    /**
     * 以用户ID排序
     */
    public SortConstants userIdSort;

    /**
     * 以用户名排序
     */
    public SortConstants usernameSort;

    /**
     * 以密码排序
     */
    public SortConstants passwordSort;

    /**
     * 以盐排序
     */
    public SortConstants saltSort;

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

    /**
     * 以头像排序
     */
    public SortConstants avatarSort;

    /**
     * 以是否删除排序
     */
    public SortConstants deletedSort;

    /**
     * 以创建人排序
     */
    public SortConstants createUserSort;

    /**
     * 以修改人排序
     */
    public SortConstants updateUserSort;

    /**
     * 以创建时间排序
     */
    public SortConstants createTimeSort;

    /**
     * 以更新时间排序
     */
    public SortConstants updateTimeSort;
}
