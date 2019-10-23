package org.xi.quick.sys.vm.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderVm;
import org.xi.quick.sys.models.condition.order.CaptchaOrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统验证码
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class CaptchaOrderVm implements OrderVm {

    /**
     * 以标识排序
     */
    public SortConstants uuidSort;

    public CaptchaOrderCondition getOrderCondition() {

        CaptchaOrderCondition condition = new CaptchaOrderCondition();
        condition.setUuidSort(uuidSort);

        return condition;
    }
}
