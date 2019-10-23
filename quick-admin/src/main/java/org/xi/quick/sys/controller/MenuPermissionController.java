package org.xi.quick.sys.controller;

import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.sys.service.MenuPermissionService;
import org.xi.quick.sys.vm.addoredit.MenuPermissionAddOrEditVm;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.detail.MenuPermissionDetailVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.order.MenuPermissionOrderVm;
import org.xi.quick.sys.vm.search.MenuPermissionSearchVm;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/menu-permission")
@RestController
@Validated
public class MenuPermissionController {

    private final MenuPermissionService menuPermissionService;

    @Autowired
    public MenuPermissionController(MenuPermissionService menuPermissionService) {
        this.menuPermissionService = menuPermissionService;
    }

    /**
     * 批量保存菜单权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/batch-save")
    @RequiresPermissions("sys:menu:permission")
    public ResponseVo<MtmRelationBatchResultVm> batchSave(@RequestBody MtmRelationBatchSaveVm vm) {
        MtmRelationBatchResultVm batchResult = menuPermissionService.batchSave(vm);
        return new ResponseVo<>(batchResult);
    }

    /**
     * 获取菜单权限列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:menu:permission")
    public ResponseVo<List<MenuPermissionDetailVm>> getList(@RequestBody OrderSearch<MenuPermissionSearchVm, MenuPermissionOrderVm> search) {

        List<MenuPermissionDetailVm> list = menuPermissionService.getList(search);
        return new ResponseVo<>(list);
    }

}
