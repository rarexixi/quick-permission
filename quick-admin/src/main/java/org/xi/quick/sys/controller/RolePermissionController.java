package org.xi.quick.sys.controller;

import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.sys.service.RolePermissionService;
import org.xi.quick.sys.service.RoleService;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.RolePermissionAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.search.RolePermissionSearchVm;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/role-permission")
@RestController
@Validated
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;
    private final RoleService roleService;

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService, RoleService roleService) {
        this.rolePermissionService = rolePermissionService;
        this.roleService = roleService;
    }

    /**
     * 批量保存角色权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/batch-save")
    @RequiresPermissions("sys:role:permission")
    public ResponseVo<MtmRelationBatchResultVm> batchSave(@RequestBody MtmRelationBatchSaveVm vm) {
        MtmRelationBatchResultVm batchResult = rolePermissionService.batchSave(vm);
        return new ResponseVo<>(batchResult);
    }

    /**
     * 获取角色权限列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @GetMapping("/get-permission-by-role")
    @RequiresPermissions("sys:role:permission")
    public ResponseVo<Set<Integer>> getPermissionList(@NotNull(message = "roleId (角色ID)不能为空") @RequestParam(value = "roleId") Integer roleId) {

        Set<Integer> list = roleService.getRolePermissionIds(roleId);
        return new ResponseVo<>(list);
    }

    /**
     * 获取角色权限列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @GetMapping("/get-menu-permission-by-role")
    @RequiresPermissions("sys:role:permission")
    public ResponseVo<Set<Integer>> getMenuPermissionList(@NotNull(message = "roleId (角色ID)不能为空") @RequestParam(value = "roleId") Integer roleId) {

        Set<Integer> list = roleService.getMenuRolePermissionIds(roleId);
        return new ResponseVo<>(list);
    }
}
