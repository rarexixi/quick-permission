package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class MenuOrderCondition implements OrderCondition {

    /**
     * 以按钮ID排序
     */
    public SortConstants menuIdSort;

    /**
     * 以父菜单ID排序
     */
    public SortConstants parentIdSort;

    /**
     * 以菜单编码排序
     */
    public SortConstants menuCodeSort;

    /**
     * 以菜单名称排序
     */
    public SortConstants menuNameSort;

    /**
     * 以菜单URL排序
     */
    public SortConstants urlSort;

    /**
     * 以类型排序
     */
    public SortConstants typeSort;

    /**
     * 以菜单图标排序
     */
    public SortConstants iconSort;

    /**
     * 以排序排序
     */
    public SortConstants sortSort;

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
