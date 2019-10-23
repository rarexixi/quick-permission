package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.RolePermissionEntity;
import org.xi.quick.sys.models.entity.extension.RolePermissionEntityExtension;

import lombok.*;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RolePermissionDetailVm extends BaseEntity {

    public RolePermissionDetailVm(RolePermissionEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        roleId = entity.getRoleId();
        permissionId = entity.getPermissionId();
    }

    public RolePermissionDetailVm(RolePermissionEntity entity) {
        super(entity);

        if (entity == null) return;
        roleId = entity.getRoleId();
        permissionId = entity.getPermissionId();
    }

    /**
     * 角色ID
     */
    @ExcelCol("角色ID")
    private Integer roleId;

    /**
     * 权限ID
     */
    @ExcelCol("权限ID")
    private Integer permissionId;
}
