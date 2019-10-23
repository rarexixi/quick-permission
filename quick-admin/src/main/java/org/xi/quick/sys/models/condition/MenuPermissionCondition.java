package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

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
public class MenuPermissionCondition extends BaseCondition {

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
}
