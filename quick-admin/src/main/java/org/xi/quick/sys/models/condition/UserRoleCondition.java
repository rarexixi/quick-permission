package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

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
public class UserRoleCondition extends BaseCondition {

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
}
