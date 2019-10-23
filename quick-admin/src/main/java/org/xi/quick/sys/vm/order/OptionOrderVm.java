package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.OptionOrderCondition;

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
public class OptionOrderVm implements OrderVm {

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
     * 以父ID排序
     */
    public SortConstants parentIdSort;

    public OptionOrderCondition getOrderCondition() {

        OptionOrderCondition condition = new OptionOrderCondition();
        condition.setIdSort(idSort);
        condition.setOptionCodeSort(optionCodeSort);
        condition.setValueSort(valueSort);
        condition.setParentIdSort(parentIdSort);

        return condition;
    }
}
