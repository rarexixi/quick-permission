package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.validation.*;
import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.sys.models.entity.MenuPermissionEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuPermissionAddOrEditVm extends BaseEntity {

    /**
     * 菜单ID
     */
    @NotNull(groups = {DataAdd.class}, message = "menuId (菜单ID)不能为空")
    private Integer menuId;

    /**
     * 权限ID
     */
    @NotNull(groups = {DataAdd.class}, message = "permissionId (权限ID)不能为空")
    private Integer permissionId;

    public MenuPermissionEntity getMenuPermissionEntity() {

        MenuPermissionEntity entity = new MenuPermissionEntity();
        entity.setMenuId(this.menuId);
        entity.setPermissionId(this.permissionId);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setMenuPermissionEntity(MenuPermissionEntity entity) {

        if (entity == null) return;

        this.menuId = entity.getMenuId();
        this.permissionId = entity.getPermissionId();
        super.setCurrentEntity(entity);
    }
}
