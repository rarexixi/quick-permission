package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

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
public class MenuPermissionOrderCondition implements OrderCondition {

    /**
     * 以菜单ID排序
     */
    public SortConstants menuIdSort;

    /**
     * 以权限ID排序
     */
    public SortConstants permissionIdSort;
}
