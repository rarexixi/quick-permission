package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.sys.models.entity.RoleMenuEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 角色菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleMenuAddOrEditVm extends BaseEntity {

    /**
     * 角色ID
     */
    @NotNull(groups = {DataAdd.class}, message = "roleId (角色ID)不能为空")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @NotNull(groups = {DataAdd.class}, message = "menuId (菜单ID)不能为空")
    private Integer menuId;

    public RoleMenuEntity getRoleMenuEntity() {

        RoleMenuEntity entity = new RoleMenuEntity();
        entity.setRoleId(this.roleId);
        entity.setMenuId(this.menuId);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setRoleMenuEntity(RoleMenuEntity entity) {

        if (entity == null) return;

        this.roleId = entity.getRoleId();
        this.menuId = entity.getMenuId();
        super.setCurrentEntity(entity);
    }
}
