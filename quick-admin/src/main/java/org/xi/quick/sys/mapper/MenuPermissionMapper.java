package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.MenuPermissionCondition;
import org.xi.quick.sys.models.condition.extension.MenuPermissionConditionExtension;
import org.xi.quick.sys.models.condition.order.MenuPermissionOrderCondition;
import org.xi.quick.sys.models.entity.MenuPermissionEntity;
import org.xi.quick.sys.models.entity.extension.MenuPermissionEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface MenuPermissionMapper extends BaseMapper<MenuPermissionEntity, MenuPermissionCondition> {

    /**
     * 批量添加
     *
     * @param entityList
     * @return
     */
    int batchInsert(@Param("list") List<MenuPermissionEntity> entityList);

    /**
     * 根据主键获取
     *
     * @param menuId
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    MenuPermissionEntityExtension getByPk(@Param("menuId") Integer menuId, @Param("permissionId") Integer permissionId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<MenuPermissionEntityExtension> getExList(@Param("condition") MenuPermissionConditionExtension condition, @Param("order") MenuPermissionOrderCondition order);
}
