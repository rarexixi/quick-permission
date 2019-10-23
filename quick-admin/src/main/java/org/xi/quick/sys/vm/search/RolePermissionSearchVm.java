package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.RolePermissionCondition;
import org.xi.quick.sys.models.condition.extension.RolePermissionConditionExtension;

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
public class RolePermissionSearchVm implements SearchVm {

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

    public RolePermissionCondition getCondition() {

        return getConditionExtension();
    }

    public RolePermissionConditionExtension getConditionExtension() {

        RolePermissionConditionExtension condition = new RolePermissionConditionExtension();
        condition.setRoleId(this.roleId);
        condition.setRoleIdList(this.roleIdList);
        condition.setRoleIdMin(this.roleIdMin);
        condition.setRoleIdMax(this.roleIdMax);
        condition.setPermissionId(this.permissionId);
        condition.setPermissionIdList(this.permissionIdList);
        condition.setPermissionIdMin(this.permissionIdMin);
        condition.setPermissionIdMax(this.permissionIdMax);
        return condition;
    }
}
