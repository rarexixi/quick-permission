package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.common.validation.DataEdit;
import org.xi.quick.sys.models.entity.RoleEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleAddOrEditVm extends BaseEntity {

    /**
     * 角色ID
     */
    @NotNull(groups = {DataEdit.class}, message = "roleId (角色ID)不能为空")
    private Integer roleId;

    /**
     * 角色编码
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "roleCode (角色编码)不能为空")
    private String roleCode;

    /**
     * 角色名称
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "roleName (角色名称)不能为空")
    private String roleName;

    /**
     * 是否内置角色 (0否, 1是)
     */
    private Integer builtIn;

    /**
     * 备注
     */
    private String remark;

    public RoleEntity getRoleEntity() {

        RoleEntity entity = new RoleEntity();
        entity.setRoleId(this.roleId);
        entity.setRoleCode(this.roleCode);
        entity.setRoleName(this.roleName);
        entity.setBuiltIn(this.builtIn);
        entity.setRemark(this.remark);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setRoleEntity(RoleEntity entity) {

        if (entity == null) return;

        this.roleId = entity.getRoleId();
        this.roleCode = entity.getRoleCode();
        this.roleName = entity.getRoleName();
        this.builtIn = entity.getBuiltIn();
        this.remark = entity.getRemark();
        super.setCurrentEntity(entity);
    }
}
