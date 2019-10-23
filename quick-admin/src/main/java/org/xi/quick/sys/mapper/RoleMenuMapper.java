package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.RoleMenuCondition;
import org.xi.quick.sys.models.condition.extension.RoleMenuConditionExtension;
import org.xi.quick.sys.models.condition.order.RoleMenuOrderCondition;
import org.xi.quick.sys.models.entity.RoleMenuEntity;
import org.xi.quick.sys.models.entity.extension.RoleMenuEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity, RoleMenuCondition> {

    /**
     * 批量添加
     *
     * @param entityList
     * @return
     */
    int batchInsert(@Param("list") List<RoleMenuEntity> entityList);

    /**
     * 根据主键获取
     *
     * @param roleId
     * @param menuId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    RoleMenuEntityExtension getByPk(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<RoleMenuEntityExtension> getExList(@Param("condition") RoleMenuConditionExtension condition, @Param("order") RoleMenuOrderCondition order);
}
