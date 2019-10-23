package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 系统用户Token
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserTokenCondition extends BaseCondition {

    /**
     * UserId
     */
    private Integer userId;

    /**
     * UserId 列表
     */
    private List<Integer> userIdList;

    /**
     * 最小 UserId
     */
    private Integer userIdMin;

    /**
     * 最大 UserId
     */
    private Integer userIdMax;

    /**
     * token
     */
    private String token;

    /**
     * token 列表
     */
    private List<String> tokenList;

    /**
     * token为空
     */
    private Boolean tokenIsEmpty;

    /**
     * token
     */
    private String tokenStartWith;

    /**
     * token
     */
    private String tokenContains;

    /**
     * 过期时间 列表
     */
    private List<Date> expireAtList;

    /**
     * 最小 过期时间
     */
    private Date expireAtMin;

    /**
     * 最大 过期时间
     */
    private Date expireAtMax;
}
