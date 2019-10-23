package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

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
public class CaptchaOrderCondition implements OrderCondition {

    /**
     * 以标识排序
     */
    public SortConstants uuidSort;

    /**
     * 以验证码排序
     */
    public SortConstants codeSort;

    /**
     * 以过期时间排序
     */
    public SortConstants expireAtSort;
}
