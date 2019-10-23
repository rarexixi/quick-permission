package org.xi.quick.sys.controller;

import org.xi.quick.common.annotation.UpdateUser;
import org.xi.quick.common.model.PageInfoVo;
import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.common.utils.poi.ExcelUtils;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.common.validation.DataEdit;
import org.xi.quick.sys.service.RoleService;
import org.xi.quick.sys.vm.addoredit.RoleAddOrEditVm;
import org.xi.quick.sys.vm.detail.RoleDetailVm;
import org.xi.quick.sys.vm.order.RoleOrderVm;
import org.xi.quick.sys.vm.search.RoleSearchVm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/role")
@RestController
@Validated
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 添加角色
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:role:add")
    public ResponseVo<RoleAddOrEditVm> add(@UpdateUser(create = true) @Validated({DataAdd.class}) @RequestBody RoleAddOrEditVm vm, Errors errors) {

        roleService.add(vm);
        return new ResponseVo(vm);
    }

    /**
     * 根据条件删除角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:del")
    public ResponseVo<Integer> delete(@RequestBody RoleSearchVm searchVm) {

        Integer count = roleService.delete(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件禁用角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/disable")
    @RequiresPermissions("sys:role:disable")
    public ResponseVo<Integer> disable(@UpdateUser @RequestBody RoleSearchVm searchVm) {

        Integer count = roleService.disable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件启用角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/enable")
    @RequiresPermissions("sys:role:enable")
    public ResponseVo<Integer> enable(@UpdateUser @RequestBody RoleSearchVm searchVm) {

        Integer count = roleService.enable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件获取角色实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/get")
    @RequiresPermissions("sys:role:view")
    public ResponseVo<RoleDetailVm> get(@RequestBody RoleSearchVm searchVm) {

        RoleDetailVm result = roleService.get(searchVm);
        return new ResponseVo(result);
    }

    /**
     * 根据主键更新角色
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public ResponseVo<Integer> update(@UpdateUser @Validated({DataEdit.class}) @RequestBody RoleAddOrEditVm vm, Errors errors) {

        int count = roleService.update(vm);
        return new ResponseVo(count);
    }

    /**
     * 根据主键获取角色详情
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @GetMapping("/detail")
    @RequiresPermissions("sys:role:view")
    public ResponseVo<RoleDetailVm> getDetail(@NotNull(message = "roleId (角色ID)不能为空") @RequestParam(value = "roleId") Integer roleId) {

        RoleDetailVm detail = roleService.getDetail(roleId);
        return new ResponseVo(detail);
    }

    /**
     * 获取角色列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:role:view")
    public ResponseVo<List<RoleDetailVm>> getList(@RequestBody OrderSearch<RoleSearchVm, RoleOrderVm> search) {

        List<RoleDetailVm> list = roleService.getList(search);
        return new ResponseVo(list);
    }

    /**
     * 分页查询角色
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/page-list")
    @RequiresPermissions("sys:role:view")
    public ResponseVo<PageInfoVo<RoleDetailVm>> getPageInfo(@RequestBody OrderSearchPage<RoleSearchVm, RoleOrderVm> searchPage) {

        PageInfoVo<RoleDetailVm> pageInfo = roleService.getPageInfo(searchPage);
        return new ResponseVo(pageInfo);
    }

    /**
     * 导出Excel
     *
     * @param params
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @RequestMapping(value = {"/export"})
    @RequiresPermissions("sys:role:export")
    public void export(HttpServletResponse response, String params, @RequestParam(defaultValue = "", required = false) String exportName) throws IOException, IllegalAccessException {

        OrderSearch<RoleSearchVm, RoleOrderVm> search = VoUtils.getOrderSearch(params, RoleSearchVm.class, RoleOrderVm.class);
        List<RoleDetailVm> list = roleService.getList(search);

        String fileName = StringUtils.isBlank(exportName) ? "角色" : exportName;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
        ExcelUtils.exportExcel(fileName, RoleDetailVm.class, list, response.getOutputStream());
    }

}
