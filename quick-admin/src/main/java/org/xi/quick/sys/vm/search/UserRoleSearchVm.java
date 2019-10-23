package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.UserRoleCondition;
import org.xi.quick.sys.models.condition.extension.UserRoleConditionExtension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 用户角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserRoleSearchVm implements SearchVm {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户ID 列表
     */
    private List<Integer> userIdList;

    /**
     * 最小 用户ID
     */
    private Integer userIdMin;

    /**
     * 最大 用户ID
     */
    private Integer userIdMax;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色ID 列表
     */
    private List<Integer> roleIdList;

    /**
     * 最小 角色ID
     */
    private Integer roleIdMin;

    /**
     * 最大 角色ID
     */
    private Integer roleIdMax;

    public UserRoleCondition getCondition() {

        return getConditionExtension();
    }

    public UserRoleConditionExtension getConditionExtension() {

        UserRoleConditionExtension condition = new UserRoleConditionExtension();
        condition.setUserId(this.userId);
        condition.setUserIdList(this.userIdList);
        condition.setUserIdMin(this.userIdMin);
        condition.setUserIdMax(this.userIdMax);
        condition.setRoleId(this.roleId);
        condition.setRoleIdList(this.roleIdList);
        condition.setRoleIdMin(this.roleIdMin);
        condition.setRoleIdMax(this.roleIdMax);
        return condition;
    }
}
