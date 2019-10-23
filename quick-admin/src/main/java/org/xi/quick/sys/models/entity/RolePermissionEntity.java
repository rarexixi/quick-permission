package org.xi.quick.sys.models.entity;

import lombok.*;
import org.xi.quick.common.model.BaseEntity;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RolePermissionEntity extends BaseEntity {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer permissionId;
}
