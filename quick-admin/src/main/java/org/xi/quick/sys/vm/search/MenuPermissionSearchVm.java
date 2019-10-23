package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.MenuPermissionCondition;
import org.xi.quick.sys.models.condition.extension.MenuPermissionConditionExtension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class MenuPermissionSearchVm implements SearchVm {

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

    public MenuPermissionCondition getCondition() {

        return getConditionExtension();
    }

    public MenuPermissionConditionExtension getConditionExtension() {

        MenuPermissionConditionExtension condition = new MenuPermissionConditionExtension();
        condition.setMenuId(this.menuId);
        condition.setMenuIdList(this.menuIdList);
        condition.setMenuIdMin(this.menuIdMin);
        condition.setMenuIdMax(this.menuIdMax);
        condition.setPermissionId(this.permissionId);
        condition.setPermissionIdList(this.permissionIdList);
        condition.setPermissionIdMin(this.permissionIdMin);
        condition.setPermissionIdMax(this.permissionIdMax);
        return condition;
    }
}
