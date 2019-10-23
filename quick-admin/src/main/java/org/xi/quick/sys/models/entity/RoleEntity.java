package org.xi.quick.sys.models.entity;

import org.xi.quick.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RoleEntity extends BaseEntity {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否内置角色 (0否, 1是)
     */
    private Integer builtIn;

    /**
     * 备注
     */
    private String remark;
}
