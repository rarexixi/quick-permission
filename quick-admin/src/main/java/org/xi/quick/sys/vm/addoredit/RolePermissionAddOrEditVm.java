package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.sys.models.entity.RolePermissionEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RolePermissionAddOrEditVm extends BaseEntity {

    /**
     * 角色ID
     */
    @NotNull(groups = {DataAdd.class}, message = "roleId (角色ID)不能为空")
    private Integer roleId;

    /**
     * 权限ID
     */
    @NotNull(groups = {DataAdd.class}, message = "permissionId (权限ID)不能为空")
    private Integer permissionId;

    public RolePermissionEntity getRolePermissionEntity() {

        RolePermissionEntity entity = new RolePermissionEntity();
        entity.setRoleId(this.roleId);
        entity.setPermissionId(this.permissionId);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setRolePermissionEntity(RolePermissionEntity entity) {

        if (entity == null) return;

        this.roleId = entity.getRoleId();
        this.permissionId = entity.getPermissionId();
        super.setCurrentEntity(entity);
    }
}
