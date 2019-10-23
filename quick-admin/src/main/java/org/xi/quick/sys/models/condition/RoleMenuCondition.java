package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

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
public class RoleMenuCondition extends BaseCondition {

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
}
