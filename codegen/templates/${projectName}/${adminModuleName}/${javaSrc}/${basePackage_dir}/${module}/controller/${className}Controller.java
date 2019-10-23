<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.controller;

import ${baseCommonPackage}.annotation.UpdateUser;
import ${baseCommonPackage}.model.PageInfoVo;
import ${baseCommonPackage}.model.ResponseVo;
import ${baseCommonPackage}.model.OrderSearch;
import ${baseCommonPackage}.model.OrderSearchPage;
import ${baseCommonPackage}.utils.VoUtils;
import ${baseCommonPackage}.utils.poi.ExcelUtils;
import ${baseCommonPackage}.validation.*;
import ${basePackage}.${module}.service.${className}Service;
import ${basePackage}.${module}.vm.addoredit.${className}AddOrEditVm;
import ${basePackage}.${module}.vm.detail.${className}DetailVm;
import ${basePackage}.${module}.vm.order.${className}OrderVm;
import ${basePackage}.${module}.vm.search.${className}SearchVm;

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

<#include "/include/java_copyright.ftl">
@CrossOrigin
@RequestMapping("/${tablePath}")
@RestController
@Validated
public class ${className}Controller {

    private final ${className}Service ${classNameFirstLower}Service;

    @Autowired
    public ${className}Controller(${className}Service ${classNameFirstLower}Service) {
        this.${classNameFirstLower}Service = ${classNameFirstLower}Service;
    }

    /**
     * 添加${tableComment}
     *
     * @param vm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @PostMapping("/add")
    @RequiresPermissions("${module}:${tablePath}:add")
    public ResponseVo<${className}AddOrEditVm> add(@UpdateUser(create = true) @Validated({DataAdd.class}) @RequestBody ${className}AddOrEditVm vm, Errors errors) {

        ${classNameFirstLower}Service.add(vm);
        return new ResponseVo(vm);
    }

    /**
     * 根据条件删除${tableComment}
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @PostMapping("/delete")
    @RequiresPermissions("${module}:${tablePath}:del")
    public ResponseVo<Integer> delete(@RequestBody ${className}SearchVm searchVm) {

        Integer count = ${classNameFirstLower}Service.delete(searchVm);
        return new ResponseVo(count);
    }
    <#if table.validStatusColumn??>

    /**
     * 根据条件禁用${tableComment}
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @PostMapping("/disable")
    @RequiresPermissions("${module}:${tablePath}:disable")
    public ResponseVo<Integer> disable(@UpdateUser @RequestBody ${className}SearchVm searchVm) {

        Integer count = ${classNameFirstLower}Service.disable(searchVm);
        return new ResponseVo(count);
    }

    /**
     * 根据条件启用${tableComment}
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @PostMapping("/enable")
    @RequiresPermissions("${module}:${tablePath}:enable")
    public ResponseVo<Integer> enable(@UpdateUser @RequestBody ${className}SearchVm searchVm) {

        Integer count = ${classNameFirstLower}Service.enable(searchVm);
        return new ResponseVo(count);
    }
    </#if>

    /**
     * 根据条件获取${tableComment}实体
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @PostMapping("/get")
    @RequiresPermissions("${module}:${tablePath}:view")
    public ResponseVo<${className}DetailVm> get(@RequestBody ${className}SearchVm searchVm) {

        ${className}DetailVm result = ${classNameFirstLower}Service.get(searchVm);
        return new ResponseVo(result);
    }
    <#if table.hasPk>

    /**
     * 根据主键更新${tableComment}
     *
     * @param vm
     <#if !table.hasAutoIncUniPk>
     <#list pks as column>
     <#include "/include/column/properties.ftl">
     * @param ${fieldName}
     </#list>
     </#if>
     * @return
     <#include "/include/author_info1.ftl">
     */
    @PostMapping("/update")
    @RequiresPermissions("${module}:${tablePath}:update")
    public ResponseVo<Integer> update(@UpdateUser @Validated({DataEdit.class}) @RequestBody ${className}AddOrEditVm vm, Errors errors<#if !table.hasAutoIncUniPk><#list pks as column><#include "/include/column/properties.ftl"><#assign annotationName = (isString ? string('NotBlank', 'NotNull'))>, @${annotationName}(message = "${fieldName} (${columnComment})不能为空") @RequestParam(value = "${fieldName}") ${fieldType} ${fieldName}</#list></#if>) {

        int count = ${classNameFirstLower}Service.update(vm<#if !table.hasAutoIncUniPk>, <#include "/include/table/pk_values.ftl"></#if>);
        return new ResponseVo(count);
    }

    /**
     * 根据主键获取${tableComment}详情
     *
     <#list pks as column>
     <#include "/include/column/properties.ftl">
     * @param ${fieldName}
     </#list>
     * @return
     <#include "/include/author_info1.ftl">
     */
    @GetMapping("/detail")
    @RequiresPermissions("${module}:${tablePath}:view")
    public ResponseVo<${className}DetailVm> getDetail(<#list pks as column><#include "/include/column/properties.ftl"><#assign annotationName = (isString ? string('NotBlank', 'NotNull'))>@${annotationName}(message = "${fieldName} (${columnComment})不能为空") @RequestParam(value = "${fieldName}") ${fieldType} ${fieldName}<#if column?has_next>,</#if></#list>) {

        ${className}DetailVm detail = ${classNameFirstLower}Service.getDetail(<#include "/include/table/pk_values.ftl">);
        return new ResponseVo(detail);
    }
    </#if>

    /**
     * 获取${tableComment}列表
     *
     * @param search
     * @return
     <#include "/include/author_info1.ftl">
     */
    @PostMapping("/list")
    @RequiresPermissions("${module}:${tablePath}:view")
    public ResponseVo<List<${className}DetailVm>> getList(@RequestBody OrderSearch<${className}SearchVm, ${className}OrderVm> search) {

        List<${className}DetailVm> list = ${classNameFirstLower}Service.getList(search);
        return new ResponseVo(list);
    }

    /**
     * 分页查询${tableComment}
     *
     * @param searchPage
     * @return
     <#include "/include/author_info1.ftl">
     */
    @PostMapping("/page-list")
    @RequiresPermissions("${module}:${tablePath}:view")
    public ResponseVo<PageInfoVo<${className}DetailVm>> getPageInfo(@RequestBody OrderSearchPage<${className}SearchVm, ${className}OrderVm> searchPage) {

        PageInfoVo<${className}DetailVm> pageInfo = ${classNameFirstLower}Service.getPageInfo(searchPage);
        return new ResponseVo(pageInfo);
    }

    /**
     * 导出Excel
     *
     * @param params
     * @return
     <#include "/include/author_info1.ftl">
     */
    @RequestMapping(value = {"/export"})
    @RequiresPermissions("${module}:${tablePath}:export")
    public void export(HttpServletResponse response, String params, @RequestParam(defaultValue = "", required = false) String exportName) throws IOException, IllegalAccessException {

        OrderSearch<${className}SearchVm, ${className}OrderVm> search = VoUtils.getOrderSearch(params, ${className}SearchVm.class, ${className}OrderVm.class);
        List<${className}DetailVm> list = ${classNameFirstLower}Service.getList(search);

        String fileName = StringUtils.isBlank(exportName) ? "${tableComment}" : exportName;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
        ExcelUtils.exportExcel(fileName, ${className}DetailVm.class, list, response.getOutputStream());
    }

}
