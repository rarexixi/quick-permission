package org.xi.quick.sys.mapper;

import org.xi.quick.common.mapper.BaseMapper;
import org.xi.quick.sys.models.condition.RoleCondition;
import org.xi.quick.sys.models.condition.extension.RoleConditionExtension;
import org.xi.quick.sys.models.condition.order.RoleOrderCondition;
import org.xi.quick.sys.models.entity.RoleEntity;
import org.xi.quick.sys.models.entity.extension.RoleEntityExtension;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity, RoleCondition> {

    /**
     * 根据主键获取
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    RoleEntityExtension getByPk(@Param("roleId") Integer roleId);

    /**
     * 查询
     *
     * @param condition
     * @param order
     * @return
     */
    List<RoleEntityExtension> getExList(@Param("condition") RoleConditionExtension condition, @Param("order") RoleOrderCondition order);

    /**
     * 根据角色ID获取权限列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<String> getPermissionsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色ID列表获取权限列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<String> getPermissionsByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据角色ID获取权限ID列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<Integer> getPermissionIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色ID列表获取权限ID列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<Integer> getPermissionIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 根据角色ID获取菜单关联权限ID列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<Integer> getMenuPermissionIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色ID列表获取菜单关联权限ID列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<Integer> getMenuPermissionIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
