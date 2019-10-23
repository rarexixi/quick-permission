package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

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
public class RolePermissionOrderCondition implements OrderCondition {

    /**
     * 以角色ID排序
     */
    public SortConstants roleIdSort;

    /**
     * 以权限ID排序
     */
    public SortConstants permissionIdSort;
}
