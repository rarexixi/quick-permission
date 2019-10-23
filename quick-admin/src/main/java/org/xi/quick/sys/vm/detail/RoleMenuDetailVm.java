package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.RoleMenuEntity;
import org.xi.quick.sys.models.entity.extension.RoleMenuEntityExtension;

import lombok.*;

/**
 * 角色菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleMenuDetailVm extends BaseEntity {

    public RoleMenuDetailVm(RoleMenuEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        roleId = entity.getRoleId();
        menuId = entity.getMenuId();
    }

    public RoleMenuDetailVm(RoleMenuEntity entity) {
        super(entity);

        if (entity == null) return;
        roleId = entity.getRoleId();
        menuId = entity.getMenuId();
    }

    /**
     * 角色ID
     */
    @ExcelCol("角色ID")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @ExcelCol("菜单ID")
    private Integer menuId;
}
