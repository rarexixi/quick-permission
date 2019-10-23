package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.ConfigOrderCondition;

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
public class ConfigOrderVm implements OrderVm {

    /**
     * 以配置键排序
     */
    public SortConstants keySort;

    public ConfigOrderCondition getOrderCondition() {

        ConfigOrderCondition condition = new ConfigOrderCondition();
        condition.setKeySort(keySort);

        return condition;
    }
}
