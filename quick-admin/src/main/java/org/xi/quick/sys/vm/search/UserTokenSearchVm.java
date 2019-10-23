package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.UserTokenCondition;
import org.xi.quick.sys.models.condition.extension.UserTokenConditionExtension;

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
public class UserTokenSearchVm implements SearchVm {

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

    public UserTokenCondition getCondition() {

        return getConditionExtension();
    }

    public UserTokenConditionExtension getConditionExtension() {

        UserTokenConditionExtension condition = new UserTokenConditionExtension();
        condition.setUserId(this.userId);
        condition.setUserIdList(this.userIdList);
        condition.setUserIdMin(this.userIdMin);
        condition.setUserIdMax(this.userIdMax);
        condition.setToken(this.token);
        condition.setTokenList(this.tokenList);
        condition.setTokenIsEmpty(this.tokenIsEmpty);
        condition.setTokenStartWith(this.tokenStartWith);
        condition.setTokenContains(this.tokenContains);
        return condition;
    }
}
