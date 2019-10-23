package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.RoleAddOrEditVm;
import org.xi.quick.sys.vm.detail.RoleDetailVm;
import org.xi.quick.sys.vm.order.RoleOrderVm;
import org.xi.quick.sys.vm.search.RoleSearchVm;

import java.util.List;
import java.util.Set;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface RoleService extends BaseService<RoleAddOrEditVm, RoleDetailVm, RoleOrderVm, RoleSearchVm> {

    /**
     * 根据条件禁用角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int disable(RoleSearchVm searchVm);

    /**
     * 根据条件启用角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int enable(RoleSearchVm searchVm);

    /**
     * 根据主键更新角色
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int update(RoleAddOrEditVm vm);

    /**
     * 根据主键获取角色详情
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    RoleDetailVm getDetail(Integer roleId);

    /**
     * 根据角色ID获取权限列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<String> getRolePermissions(Integer roleId);

    /**
     * 根据角色ID列表获取权限列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<String> getRolesPermissions(List<Integer> roleIds);

    /**
     * 根据角色ID获取权限ID列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<Integer> getRolePermissionIds(Integer roleId);

    /**
     * 根据角色ID列表获取权限ID列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<Integer> getRolesPermissionIds(List<Integer> roleIds);

    /**
     * 根据角色ID获取菜单权限ID列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<Integer> getMenuRolePermissionIds(Integer roleId);

    /**
     * 根据角色ID列表获取菜单权限ID列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<Integer> getMenuRolesPermissionIds(List<Integer> roleIds);
}
