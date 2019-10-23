package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class PermissionCondition extends BaseCondition {

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

    /**
     * 模块ID，一级模块为0
     */
    private Integer parentId;

    /**
     * 模块ID 列表
     */
    private List<Integer> parentIdList;

    /**
     * 最小 模块ID
     */
    private Integer parentIdMin;

    /**
     * 最大 模块ID
     */
    private Integer parentIdMax;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限编码 列表
     */
    private List<String> permissionCodeList;

    /**
     * 权限编码为空
     */
    private Boolean permissionCodeIsEmpty;

    /**
     * 权限编码
     */
    private String permissionCodeStartWith;

    /**
     * 权限编码
     */
    private String permissionCodeContains;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限名称 列表
     */
    private List<String> permissionNameList;

    /**
     * 权限名称为空
     */
    private Boolean permissionNameIsEmpty;

    /**
     * 权限名称
     */
    private String permissionNameStartWith;

    /**
     * 权限名称
     */
    private String permissionNameContains;

    /**
     * 类型 (0模块,1权限)
     */
    private Integer type;

    /**
     * 类型 列表
     */
    private List<Integer> typeList;

    /**
     * 最小 类型
     */
    private Integer typeMin;

    /**
     * 最大 类型
     */
    private Integer typeMax;
}
