package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class OptionOrderCondition implements OrderCondition {

    /**
     * 以枚举ID排序
     */
    public SortConstants idSort;

    /**
     * 以编码排序
     */
    public SortConstants optionCodeSort;

    /**
     * 以值排序
     */
    public SortConstants valueSort;

    /**
     * 以展示文字排序
     */
    public SortConstants textSort;

    /**
     * 以排序排序
     */
    public SortConstants sortSort;

    /**
     * 以父ID排序
     */
    public SortConstants parentIdSort;

    /**
     * 以类型排序
     */
    public SortConstants typeSort;

    /**
     * 以是否系统内置排序
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
