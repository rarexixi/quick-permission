package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统配置
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class ConfigOrderCondition implements OrderCondition {

    /**
     * 以配置键排序
     */
    public SortConstants keySort;

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
