package org.xi.quick.sys.controller;

import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.sys.service.RoleMenuService;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.RoleMenuAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.detail.RoleMenuDetailVm;
import org.xi.quick.sys.vm.order.RoleMenuOrderVm;
import org.xi.quick.sys.vm.search.RoleMenuSearchVm;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 角色菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/role-menu")
@RestController
@Validated
public class RoleMenuController {

    private final RoleMenuService roleMenuService;

    @Autowired
    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    /**
     * 批量保存角色菜单
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/batch-save")
    @RequiresPermissions("sys:role:menu")
    public ResponseVo<MtmRelationBatchResultVm> batchSave(@RequestBody MtmRelationBatchSaveVm vm) {
        MtmRelationBatchResultVm batchResult = roleMenuService.batchSave(vm);
        return new ResponseVo<>(batchResult);
    }

    /**
     * 获取角色菜单列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:role:menu")
    public ResponseVo<List<RoleMenuDetailVm>> getList(@RequestBody OrderSearch<RoleMenuSearchVm, RoleMenuOrderVm> search) {

        List<RoleMenuDetailVm> list = roleMenuService.getList(search);
        return new ResponseVo<>(list);
    }
}
