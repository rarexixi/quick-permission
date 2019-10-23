package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

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
public class UserRoleOrderCondition implements OrderCondition {

    /**
     * 以用户ID排序
     */
    public SortConstants userIdSort;

    /**
     * 以角色ID排序
     */
    public SortConstants roleIdSort;
}
