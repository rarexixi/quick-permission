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
import org.xi.quick.sys.service.PermissionService;
import org.xi.quick.sys.vm.addoredit.PermissionAddOrEditVm;
import org.xi.quick.sys.vm.detail.PermissionDetailVm;
import org.xi.quick.sys.vm.order.PermissionOrderVm;
import org.xi.quick.sys.vm.search.PermissionSearchVm;

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
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/permission")
@RestController
@Validated
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 添加权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:permission:add")
    public ResponseVo<PermissionAddOrEditVm> add(@UpdateUser(create = true) @Validated({DataAdd.class}) @RequestBody PermissionAddOrEditVm vm, Errors errors) {

        permissionService.add(vm);
        return new ResponseVo(vm);
    }

    /**
     * 根据条件删除权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:permission:del")
    public ResponseVo<Integer> delete(@RequestBody PermissionSearchVm searchVm) {

        Integer count = permissionService.delete(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件禁用权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/disable")
    @RequiresPermissions("sys:permission:disable")
    public ResponseVo<Integer> disable(@UpdateUser @RequestBody PermissionSearchVm searchVm) {

        Integer count = permissionService.disable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件启用权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/enable")
    @RequiresPermissions("sys:permission:enable")
    public ResponseVo<Integer> enable(@UpdateUser @RequestBody PermissionSearchVm searchVm) {

        Integer count = permissionService.enable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件获取权限实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/get")
    @RequiresPermissions("sys:permission:view")
    public ResponseVo<PermissionDetailVm> get(@RequestBody PermissionSearchVm searchVm) {

        PermissionDetailVm result = permissionService.get(searchVm);
        return new ResponseVo(result);
    }

    /**
     * 根据主键更新权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:permission:update")
    public ResponseVo<Integer> update(@UpdateUser @Validated({DataEdit.class}) @RequestBody PermissionAddOrEditVm vm, Errors errors) {

        int count = permissionService.update(vm);
        return new ResponseVo(count);
    }

    /**
     * 根据主键获取权限详情
     *
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @GetMapping("/detail")
    @RequiresPermissions("sys:permission:view")
    public ResponseVo<PermissionDetailVm> getDetail(@NotNull(message = "permissionId (权限ID)不能为空") @RequestParam(value = "permissionId") Integer permissionId) {

        PermissionDetailVm detail = permissionService.getDetail(permissionId);
        return new ResponseVo(detail);
    }

    /**
     * 获取权限列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:permission:view")
    public ResponseVo<List<PermissionDetailVm>> getList(@RequestBody OrderSearch<PermissionSearchVm, PermissionOrderVm> search) {

        List<PermissionDetailVm> list = permissionService.getList(search);
        return new ResponseVo(list);
    }

    /**
     * 分页查询权限
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/page-list")
    @RequiresPermissions("sys:permission:view")
    public ResponseVo<PageInfoVo<PermissionDetailVm>> getPageInfo(@RequestBody OrderSearchPage<PermissionSearchVm, PermissionOrderVm> searchPage) {

        PageInfoVo<PermissionDetailVm> pageInfo = permissionService.getPageInfo(searchPage);
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
    @RequiresPermissions("sys:permission:export")
    public void export(HttpServletResponse response, String params, @RequestParam(defaultValue = "", required = false) String exportName) throws IOException, IllegalAccessException {

        OrderSearch<PermissionSearchVm, PermissionOrderVm> search = VoUtils.getOrderSearch(params, PermissionSearchVm.class, PermissionOrderVm.class);
        List<PermissionDetailVm> list = permissionService.getList(search);

        String fileName = StringUtils.isBlank(exportName) ? "权限" : exportName;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
        ExcelUtils.exportExcel(fileName, PermissionDetailVm.class, list, response.getOutputStream());
    }

}
