package org.xi.quick.sys.controller;

import org.xi.quick.common.annotation.UpdateUser;
import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.sys.service.UserRoleService;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.UserRoleAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.detail.UserRoleDetailVm;
import org.xi.quick.sys.vm.order.UserRoleOrderVm;
import org.xi.quick.sys.vm.search.UserRoleSearchVm;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 用户角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/user-role")
@RestController
@Validated
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 批量保存用户角色
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/batch-save")
    @RequiresPermissions("sys:user:role")
    public ResponseVo<MtmRelationBatchResultVm> batchSave(@RequestBody MtmRelationBatchSaveVm vm) {
        MtmRelationBatchResultVm batchResult = userRoleService.batchSave(vm);
        return new ResponseVo<>(batchResult);
    }

    /**
     * 添加用户角色
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:user:role")
    public ResponseVo<UserRoleAddOrEditVm> add(@UpdateUser(create = true) @Validated({DataAdd.class}) @RequestBody UserRoleAddOrEditVm vm, Errors errors) {

        userRoleService.add(vm);
        return new ResponseVo<>(vm);
    }

    /**
     * 根据条件删除用户角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:role")
    public ResponseVo<Integer> delete(@RequestBody UserRoleSearchVm searchVm) {

        Integer count = userRoleService.delete(searchVm);
        return new ResponseVo<>(count);
    }

    /**
     * 获取用户角色列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:user:role")
    public ResponseVo<List<UserRoleDetailVm>> getList(@RequestBody OrderSearch<UserRoleSearchVm, UserRoleOrderVm> search) {

        List<UserRoleDetailVm> list = userRoleService.getList(search);
        return new ResponseVo<>(list);
    }

}
