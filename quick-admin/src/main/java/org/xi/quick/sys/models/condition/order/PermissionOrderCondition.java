package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class PermissionOrderCondition implements OrderCondition {

    /**
     * 以权限ID排序
     */
    public SortConstants permissionIdSort;

    /**
     * 以模块ID排序
     */
    public SortConstants parentIdSort;

    /**
     * 以权限编码排序
     */
    public SortConstants permissionCodeSort;

    /**
     * 以权限名称排序
     */
    public SortConstants permissionNameSort;

    /**
     * 以类型排序
     */
    public SortConstants typeSort;

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
