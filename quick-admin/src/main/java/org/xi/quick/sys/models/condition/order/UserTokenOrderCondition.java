package org.xi.quick.sys.models.condition.order;

import org.xi.quick.common.constant.SortConstants;
import org.xi.quick.common.model.OrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统用户Token
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserTokenOrderCondition implements OrderCondition {

    /**
     * 以UserId排序
     */
    public SortConstants userIdSort;

    /**
     * 以token排序
     */
    public SortConstants tokenSort;

    /**
     * 以过期时间排序
     */
    public SortConstants expireAtSort;
}
