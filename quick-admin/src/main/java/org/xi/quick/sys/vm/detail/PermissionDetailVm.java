package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.PermissionEntity;
import org.xi.quick.sys.models.entity.extension.PermissionEntityExtension;

import lombok.*;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PermissionDetailVm extends BaseEntity {

    public PermissionDetailVm(PermissionEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        permissionId = entity.getPermissionId();
        parentId = entity.getParentId();
        permissionCode = entity.getPermissionCode();
        permissionName = entity.getPermissionName();
        type = entity.getType();
        typeText = entity.getTypeText();
        remark = entity.getRemark();
    }

    public PermissionDetailVm(PermissionEntity entity) {
        super(entity);

        if (entity == null) return;
        permissionId = entity.getPermissionId();
        parentId = entity.getParentId();
        permissionCode = entity.getPermissionCode();
        permissionName = entity.getPermissionName();
        type = entity.getType();
        remark = entity.getRemark();
    }

    /**
     * 权限ID
     */
    @ExcelCol("权限ID")
    private Integer permissionId;

    /**
     * 模块ID，一级模块为0
     */
    @ExcelCol("模块ID")
    private Integer parentId;

    /**
     * 权限编码
     */
    @ExcelCol("权限编码")
    private String permissionCode;

    /**
     * 权限名称
     */
    @ExcelCol("权限名称")
    private String permissionName;

    /**
     * 类型 (0模块,1权限)
     */
    @ExcelCol("类型")
    private Integer type;

    private String typeText;

    /**
     * 备注
     */
    @ExcelCol("备注")
    private String remark;
}
