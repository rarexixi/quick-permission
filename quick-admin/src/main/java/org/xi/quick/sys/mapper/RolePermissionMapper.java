package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.RolePermissionCondition;
import org.xi.quick.sys.models.condition.extension.RolePermissionConditionExtension;
import org.xi.quick.sys.models.condition.order.RolePermissionOrderCondition;
import org.xi.quick.sys.models.entity.RolePermissionEntity;
import org.xi.quick.sys.models.entity.extension.RolePermissionEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity, RolePermissionCondition> {

    /**
     * 批量添加
     *
     * @param entityList
     * @return
     */
    int batchInsert(@Param("list") List<RolePermissionEntity> entityList);

    /**
     * 根据主键获取
     *
     * @param roleId
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    RolePermissionEntityExtension getByPk(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<RolePermissionEntityExtension> getExList(@Param("condition") RolePermissionConditionExtension condition, @Param("order") RolePermissionOrderCondition order);
}
