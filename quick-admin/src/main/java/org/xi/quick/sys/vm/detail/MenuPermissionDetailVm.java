package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.MenuPermissionEntity;
import org.xi.quick.sys.models.entity.extension.MenuPermissionEntityExtension;

import lombok.*;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuPermissionDetailVm extends BaseEntity {

    public MenuPermissionDetailVm(MenuPermissionEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        menuId = entity.getMenuId();
        permissionId = entity.getPermissionId();
    }

    public MenuPermissionDetailVm(MenuPermissionEntity entity) {
        super(entity);

        if (entity == null) return;
        menuId = entity.getMenuId();
        permissionId = entity.getPermissionId();
    }

    /**
     * 菜单ID
     */
    @ExcelCol("菜单ID")
    private Integer menuId;

    /**
     * 权限ID
     */
    @ExcelCol("权限ID")
    private Integer permissionId;
}
