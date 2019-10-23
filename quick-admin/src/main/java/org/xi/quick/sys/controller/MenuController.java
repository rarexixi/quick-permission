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
import org.xi.quick.sys.service.MenuService;
import org.xi.quick.sys.vm.addoredit.MenuAddOrEditVm;
import org.xi.quick.sys.vm.detail.MenuDetailVm;
import org.xi.quick.sys.vm.order.MenuOrderVm;
import org.xi.quick.sys.vm.search.MenuSearchVm;

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
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/menu")
@RestController
@Validated
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 添加菜单
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:menu:add")
    public ResponseVo<MenuAddOrEditVm> add(@UpdateUser(create = true) @Validated({DataAdd.class}) @RequestBody MenuAddOrEditVm vm, Errors errors) {

        menuService.add(vm);
        return new ResponseVo(vm);
    }

    /**
     * 根据条件删除菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:menu:del")
    public ResponseVo<Integer> delete(@RequestBody MenuSearchVm searchVm) {

        Integer count = menuService.delete(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件禁用菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/disable")
    @RequiresPermissions("sys:menu:disable")
    public ResponseVo<Integer> disable(@UpdateUser @RequestBody MenuSearchVm searchVm) {

        Integer count = menuService.disable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件启用菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/enable")
    @RequiresPermissions("sys:menu:enable")
    public ResponseVo<Integer> enable(@UpdateUser @RequestBody MenuSearchVm searchVm) {

        Integer count = menuService.enable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件获取菜单实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/get")
    @RequiresPermissions("sys:menu:view")
    public ResponseVo<MenuDetailVm> get(@RequestBody MenuSearchVm searchVm) {

        MenuDetailVm result = menuService.get(searchVm);
        return new ResponseVo(result);
    }

    /**
     * 根据主键更新菜单
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public ResponseVo<Integer> update(@UpdateUser @Validated({DataEdit.class}) @RequestBody MenuAddOrEditVm vm, Errors errors) {

        int count = menuService.update(vm);
        return new ResponseVo(count);
    }

    /**
     * 根据主键获取菜单详情
     *
     * @param menuId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @GetMapping("/detail")
    @RequiresPermissions("sys:menu:view")
    public ResponseVo<MenuDetailVm> getDetail(@NotNull(message = "menuId (按钮ID)不能为空") @RequestParam(value = "menuId") Integer menuId) {

        MenuDetailVm detail = menuService.getDetail(menuId);
        return new ResponseVo(detail);
    }

    /**
     * 获取菜单列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:menu:view")
    public ResponseVo<List<MenuDetailVm>> getList(@RequestBody OrderSearch<MenuSearchVm, MenuOrderVm> search) {

        List<MenuDetailVm> list = menuService.getList(search);
        return new ResponseVo(list);
    }

    /**
     * 分页查询菜单
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/page-list")
    @RequiresPermissions("sys:menu:view")
    public ResponseVo<PageInfoVo<MenuDetailVm>> getPageInfo(@RequestBody OrderSearchPage<MenuSearchVm, MenuOrderVm> searchPage) {

        PageInfoVo<MenuDetailVm> pageInfo = menuService.getPageInfo(searchPage);
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
    @RequiresPermissions("sys:menu:export")
    public void export(HttpServletResponse response, String params, @RequestParam(defaultValue = "", required = false) String exportName) throws IOException, IllegalAccessException {

        OrderSearch<MenuSearchVm, MenuOrderVm> search = VoUtils.getOrderSearch(params, MenuSearchVm.class, MenuOrderVm.class);
        List<MenuDetailVm> list = menuService.getList(search);

        String fileName = StringUtils.isBlank(exportName) ? "菜单" : exportName;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
        ExcelUtils.exportExcel(fileName, MenuDetailVm.class, list, response.getOutputStream());
    }

}
