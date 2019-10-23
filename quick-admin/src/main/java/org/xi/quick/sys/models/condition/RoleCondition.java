package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RoleCondition extends BaseCondition {

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
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色编码 列表
     */
    private List<String> roleCodeList;

    /**
     * 角色编码为空
     */
    private Boolean roleCodeIsEmpty;

    /**
     * 角色编码
     */
    private String roleCodeStartWith;

    /**
     * 角色编码
     */
    private String roleCodeContains;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色名称 列表
     */
    private List<String> roleNameList;

    /**
     * 角色名称为空
     */
    private Boolean roleNameIsEmpty;

    /**
     * 角色名称
     */
    private String roleNameStartWith;

    /**
     * 角色名称
     */
    private String roleNameContains;

    /**
     * 是否内置角色 (0否, 1是)
     */
    private Integer builtIn;

    /**
     * 是否内置角色 列表
     */
    private List<Integer> builtInList;

    /**
     * 最小 是否内置角色
     */
    private Integer builtInMin;

    /**
     * 最大 是否内置角色
     */
    private Integer builtInMax;
}
