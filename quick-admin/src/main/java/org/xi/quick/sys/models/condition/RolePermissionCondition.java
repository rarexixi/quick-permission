package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RolePermissionCondition extends BaseCondition {

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

    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 权限ID 列表
     */
    private List<Integer> permissionIdList;

    /**
     * 最小 权限ID
     */
    private Integer permissionIdMin;

    /**
     * 最大 权限ID
     */
    private Integer permissionIdMax;
}
