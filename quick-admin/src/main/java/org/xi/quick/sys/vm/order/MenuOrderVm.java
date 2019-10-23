package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.MenuOrderCondition;

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
public class MenuOrderVm implements OrderVm {

    /**
     * 以按钮ID排序
     */
    public SortConstants menuIdSort;

    /**
     * 以排序排序
     */
    public SortConstants sortSort;

    /**
     * 以创建时间排序
     */
    public SortConstants createTimeSort;

    /**
     * 以更新时间排序
     */
    public SortConstants updateTimeSort;

    public MenuOrderCondition getOrderCondition() {

        MenuOrderCondition condition = new MenuOrderCondition();
        condition.setMenuIdSort(menuIdSort);
        condition.setSortSort(sortSort);
        condition.setCreateTimeSort(createTimeSort);
        condition.setUpdateTimeSort(updateTimeSort);

        return condition;
    }
}
