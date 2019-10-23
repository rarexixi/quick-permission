package org.xi.quick.sys.models.entity;

import org.xi.quick.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class PermissionEntity extends BaseEntity {

    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 模块ID，一级模块为0
     */
    private Integer parentId;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 类型 (0模块,1权限)
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;
}
