package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.RoleMenuCondition;
import org.xi.quick.sys.models.condition.extension.RoleMenuConditionExtension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 角色菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RoleMenuSearchVm implements SearchVm {

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
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 菜单ID 列表
     */
    private List<Integer> menuIdList;

    /**
     * 最小 菜单ID
     */
    private Integer menuIdMin;

    /**
     * 最大 菜单ID
     */
    private Integer menuIdMax;

    public RoleMenuCondition getCondition() {

        return getConditionExtension();
    }

    public RoleMenuConditionExtension getConditionExtension() {

        RoleMenuConditionExtension condition = new RoleMenuConditionExtension();
        condition.setRoleId(this.roleId);
        condition.setRoleIdList(this.roleIdList);
        condition.setRoleIdMin(this.roleIdMin);
        condition.setRoleIdMax(this.roleIdMax);
        condition.setMenuId(this.menuId);
        condition.setMenuIdList(this.menuIdList);
        condition.setMenuIdMin(this.menuIdMin);
        condition.setMenuIdMax(this.menuIdMax);
        return condition;
    }
}
