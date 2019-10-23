package org.xi.quick.sys.controller;

import org.xi.quick.common.annotation.UpdateUser;
import org.xi.quick.common.model.PageInfoVo;
import org.xi.quick.common.model.ResponseVo;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.common.utils.poi.ExcelUtils;
import org.xi.quick.common.validation.*;
import org.xi.quick.sys.service.OptionService;
import org.xi.quick.sys.vm.addoredit.OptionAddOrEditVm;
import org.xi.quick.sys.vm.detail.OptionDetailVm;
import org.xi.quick.sys.vm.order.OptionOrderVm;
import org.xi.quick.sys.vm.search.OptionSearchVm;

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
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/option")
@RestController
@Validated
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    /**
     * 添加系统选项
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:option:add")
    public ResponseVo<OptionAddOrEditVm> add(@UpdateUser(create = true) @Validated({DataAdd.class}) @RequestBody OptionAddOrEditVm vm, Errors errors) {

        optionService.add(vm);
        return new ResponseVo(vm);
    }

    /**
     * 根据条件删除系统选项
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:option:del")
    public ResponseVo<Integer> delete(@RequestBody OptionSearchVm searchVm) {

        Integer count = optionService.delete(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件禁用系统选项
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/disable")
    @RequiresPermissions("sys:option:disable")
    public ResponseVo<Integer> disable(@UpdateUser @RequestBody OptionSearchVm searchVm) {

        Integer count = optionService.disable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件启用系统选项
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/enable")
    @RequiresPermissions("sys:option:enable")
    public ResponseVo<Integer> enable(@UpdateUser @RequestBody OptionSearchVm searchVm) {

        Integer count = optionService.enable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件获取系统选项实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/get")
    @RequiresPermissions("sys:option:view")
    public ResponseVo<OptionDetailVm> get(@RequestBody OptionSearchVm searchVm) {

        OptionDetailVm result = optionService.get(searchVm);
        return new ResponseVo(result);
    }

    /**
     * 根据主键更新系统选项
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:option:update")
    public ResponseVo<Integer> update(@UpdateUser @Validated({DataEdit.class}) @RequestBody OptionAddOrEditVm vm, Errors errors) {

        int count = optionService.update(vm);
        return new ResponseVo(count);
    }

    /**
     * 根据主键获取系统选项详情
     *
     * @param id
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @GetMapping("/detail")
    @RequiresPermissions("sys:option:view")
    public ResponseVo<OptionDetailVm> getDetail(@NotNull(message = "id (枚举ID)不能为空") @RequestParam(value = "id") Integer id) {

        OptionDetailVm detail = optionService.getDetail(id);
        return new ResponseVo(detail);
    }

    /**
     * 获取系统选项列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:option:view")
    public ResponseVo<List<OptionDetailVm>> getList(@RequestBody OrderSearch<OptionSearchVm, OptionOrderVm> search) {

        List<OptionDetailVm> list = optionService.getList(search);
        return new ResponseVo(list);
    }

    /**
     * 分页查询系统选项
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/page-list")
    @RequiresPermissions("sys:option:view")
    public ResponseVo<PageInfoVo<OptionDetailVm>> getPageInfo(@RequestBody OrderSearchPage<OptionSearchVm, OptionOrderVm> searchPage) {

        PageInfoVo<OptionDetailVm> pageInfo = optionService.getPageInfo(searchPage);
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
    @RequiresPermissions("sys:option:export")
    public void export(HttpServletResponse response, String params, @RequestParam(defaultValue = "", required = false) String exportName) throws IOException, IllegalAccessException {

        OrderSearch<OptionSearchVm, OptionOrderVm> search = VoUtils.getOrderSearch(params, OptionSearchVm.class, OptionOrderVm.class);
        List<OptionDetailVm> list = optionService.getList(search);

        String fileName = StringUtils.isBlank(exportName) ? "系统选项" : exportName;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
        ExcelUtils.exportExcel(fileName, OptionDetailVm.class, list, response.getOutputStream());
    }

}
