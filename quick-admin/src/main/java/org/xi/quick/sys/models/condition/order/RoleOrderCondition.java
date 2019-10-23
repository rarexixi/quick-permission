package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

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
public class RoleOrderCondition implements OrderCondition {

    /**
     * 以角色ID排序
     */
    public SortConstants roleIdSort;

    /**
     * 以角色编码排序
     */
    public SortConstants roleCodeSort;

    /**
     * 以角色名称排序
     */
    public SortConstants roleNameSort;

    /**
     * 以是否内置角色排序
     */
    public SortConstants builtInSort;

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
