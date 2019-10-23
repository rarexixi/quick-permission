package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.common.validation.DataEdit;
import org.xi.quick.sys.models.entity.MenuEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuAddOrEditVm extends BaseEntity {

    /**
     * 按钮ID
     */
    @NotNull(groups = {DataEdit.class}, message = "menuId (按钮ID)不能为空")
    private Integer menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "menuName (菜单名称)不能为空")
    private String menuName;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 类型 (0目录,1菜单)
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    public MenuEntity getMenuEntity() {

        MenuEntity entity = new MenuEntity();
        entity.setMenuId(this.menuId);
        entity.setParentId(this.parentId);
        entity.setMenuCode(this.menuCode);
        entity.setMenuName(this.menuName);
        entity.setUrl(this.url);
        entity.setType(this.type);
        entity.setIcon(this.icon);
        entity.setSort(this.sort);
        entity.setRemark(this.remark);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setMenuEntity(MenuEntity entity) {

        if (entity == null) return;

        this.menuId = entity.getMenuId();
        this.parentId = entity.getParentId();
        this.menuCode = entity.getMenuCode();
        this.menuName = entity.getMenuName();
        this.url = entity.getUrl();
        this.type = entity.getType();
        this.icon = entity.getIcon();
        this.sort = entity.getSort();
        this.remark = entity.getRemark();
        super.setCurrentEntity(entity);
    }
}
