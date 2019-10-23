package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.common.validation.DataEdit;
import org.xi.quick.sys.models.entity.PermissionEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PermissionAddOrEditVm extends BaseEntity {

    /**
     * 权限ID
     */
    @NotNull(groups = {DataEdit.class}, message = "permissionId (权限ID)不能为空")
    private Integer permissionId;

    /**
     * 模块ID，一级模块为0
     */
    private Integer parentId;

    /**
     * 权限编码
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "permissionCode (权限编码)不能为空")
    private String permissionCode;

    /**
     * 权限名称
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "permissionName (权限名称)不能为空")
    private String permissionName;

    /**
     * 类型 (0模块,1权限)
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    public PermissionEntity getPermissionEntity() {

        PermissionEntity entity = new PermissionEntity();
        entity.setPermissionId(this.permissionId);
        entity.setParentId(this.parentId);
        entity.setPermissionCode(this.permissionCode);
        entity.setPermissionName(this.permissionName);
        entity.setType(this.type);
        entity.setRemark(this.remark);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setPermissionEntity(PermissionEntity entity) {

        if (entity == null) return;

        this.permissionId = entity.getPermissionId();
        this.parentId = entity.getParentId();
        this.permissionCode = entity.getPermissionCode();
        this.permissionName = entity.getPermissionName();
        this.type = entity.getType();
        this.remark = entity.getRemark();
        super.setCurrentEntity(entity);
    }
}
