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
import org.xi.quick.sys.service.ConfigService;
import org.xi.quick.sys.vm.addoredit.ConfigAddOrEditVm;
import org.xi.quick.sys.vm.detail.ConfigDetailVm;
import org.xi.quick.sys.vm.order.ConfigOrderVm;
import org.xi.quick.sys.vm.search.ConfigSearchVm;

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
 * 系统配置
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@CrossOrigin
@RequestMapping("/config")
@RestController
@Validated
public class ConfigController {

    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * 添加系统配置
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:config:add")
    public ResponseVo<ConfigAddOrEditVm> add(@UpdateUser(create = true) @Validated({DataAdd.class}) @RequestBody ConfigAddOrEditVm vm, Errors errors) {

        configService.add(vm);
        return new ResponseVo(vm);
    }

    /**
     * 根据条件删除系统配置
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:del")
    public ResponseVo<Integer> delete(@RequestBody ConfigSearchVm searchVm) {

        Integer count = configService.delete(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件禁用系统配置
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/disable")
    @RequiresPermissions("sys:config:disable")
    public ResponseVo<Integer> disable(@UpdateUser @RequestBody ConfigSearchVm searchVm) {

        Integer count = configService.disable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件启用系统配置
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/enable")
    @RequiresPermissions("sys:config:enable")
    public ResponseVo<Integer> enable(@UpdateUser @RequestBody ConfigSearchVm searchVm) {

        Integer count = configService.enable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件获取系统配置实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/get")
    @RequiresPermissions("sys:config:view")
    public ResponseVo<ConfigDetailVm> get(@RequestBody ConfigSearchVm searchVm) {

        ConfigDetailVm result = configService.get(searchVm);
        return new ResponseVo(result);
    }

    /**
     * 根据主键更新系统配置
     *
     * @param vm
     * @param key
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public ResponseVo<Integer> update(@UpdateUser @Validated({DataEdit.class}) @RequestBody ConfigAddOrEditVm vm, Errors errors, @NotBlank(message = "key (配置键)不能为空") @RequestParam(value = "key") String key) {

        int count = configService.update(vm, key);
        return new ResponseVo(count);
    }

    /**
     * 根据主键获取系统配置详情
     *
     * @param key
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @GetMapping("/detail")
    @RequiresPermissions("sys:config:view")
    public ResponseVo<ConfigDetailVm> getDetail(@NotBlank(message = "key (配置键)不能为空") @RequestParam(value = "key") String key) {

        ConfigDetailVm detail = configService.getDetail(key);
        return new ResponseVo(detail);
    }

    /**
     * 获取系统配置列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/list")
    @RequiresPermissions("sys:config:view")
    public ResponseVo<List<ConfigDetailVm>> getList(@RequestBody OrderSearch<ConfigSearchVm, ConfigOrderVm> search) {

        List<ConfigDetailVm> list = configService.getList(search);
        return new ResponseVo(list);
    }

    /**
     * 分页查询系统配置
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @PostMapping("/page-list")
    @RequiresPermissions("sys:config:view")
    public ResponseVo<PageInfoVo<ConfigDetailVm>> getPageInfo(@RequestBody OrderSearchPage<ConfigSearchVm, ConfigOrderVm> searchPage) {

        PageInfoVo<ConfigDetailVm> pageInfo = configService.getPageInfo(searchPage);
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
    @RequiresPermissions("sys:config:export")
    public void export(HttpServletResponse response, String params, @RequestParam(defaultValue = "", required = false) String exportName) throws IOException, IllegalAccessException {

        OrderSearch<ConfigSearchVm, ConfigOrderVm> search = VoUtils.getOrderSearch(params, ConfigSearchVm.class, ConfigOrderVm.class);
        List<ConfigDetailVm> list = configService.getList(search);

        String fileName = StringUtils.isBlank(exportName) ? "系统配置" : exportName;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
        ExcelUtils.exportExcel(fileName, ConfigDetailVm.class, list, response.getOutputStream());
    }

}
