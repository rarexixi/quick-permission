package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.RoleEntity;
import org.xi.quick.sys.models.entity.extension.RoleEntityExtension;

import lombok.*;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleDetailVm extends BaseEntity {

    public RoleDetailVm(RoleEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        roleId = entity.getRoleId();
        roleCode = entity.getRoleCode();
        roleName = entity.getRoleName();
        builtIn = entity.getBuiltIn();
        builtInText = entity.getBuiltInText();
        remark = entity.getRemark();
    }

    public RoleDetailVm(RoleEntity entity) {
        super(entity);

        if (entity == null) return;
        roleId = entity.getRoleId();
        roleCode = entity.getRoleCode();
        roleName = entity.getRoleName();
        builtIn = entity.getBuiltIn();
        remark = entity.getRemark();
    }

    /**
     * 角色ID
     */
    @ExcelCol("角色ID")
    private Integer roleId;

    /**
     * 角色编码
     */
    @ExcelCol("角色编码")
    private String roleCode;

    /**
     * 角色名称
     */
    @ExcelCol("角色名称")
    private String roleName;

    /**
     * 是否内置角色 (0否, 1是)
     */
    @ExcelCol("是否内置角色")
    private Integer builtIn;

    private String builtInText;

    /**
     * 备注
     */
    @ExcelCol("备注")
    private String remark;
}
