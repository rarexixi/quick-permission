package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.MenuEntity;
import org.xi.quick.sys.models.entity.extension.MenuEntityExtension;

import lombok.*;

/**
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuDetailVm extends BaseEntity {

    public MenuDetailVm(MenuEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        menuId = entity.getMenuId();
        parentId = entity.getParentId();
        menuCode = entity.getMenuCode();
        menuName = entity.getMenuName();
        url = entity.getUrl();
        type = entity.getType();
        typeText = entity.getTypeText();
        icon = entity.getIcon();
        sort = entity.getSort();
        remark = entity.getRemark();
    }

    public MenuDetailVm(MenuEntity entity) {
        super(entity);

        if (entity == null) return;
        menuId = entity.getMenuId();
        parentId = entity.getParentId();
        menuCode = entity.getMenuCode();
        menuName = entity.getMenuName();
        url = entity.getUrl();
        type = entity.getType();
        icon = entity.getIcon();
        sort = entity.getSort();
        remark = entity.getRemark();
    }

    /**
     * 按钮ID
     */
    @ExcelCol("按钮ID")
    private Integer menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    @ExcelCol("父菜单ID")
    private Integer parentId;

    /**
     * 菜单编码
     */
    @ExcelCol("菜单编码")
    private String menuCode;

    /**
     * 菜单名称
     */
    @ExcelCol("菜单名称")
    private String menuName;

    /**
     * 菜单URL
     */
    @ExcelCol("菜单URL")
    private String url;

    /**
     * 类型 (0目录,1菜单)
     */
    @ExcelCol("类型")
    private Integer type;

    private String typeText;

    /**
     * 菜单图标
     */
    @ExcelCol("菜单图标")
    private String icon;

    /**
     * 排序
     */
    @ExcelCol("排序")
    private Integer sort;

    /**
     * 备注
     */
    @ExcelCol("备注")
    private String remark;
}
