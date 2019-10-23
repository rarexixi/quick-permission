<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.service;

import ${baseCommonPackage}.service.BaseService;

import ${basePackage}.${module}.vm.addoredit.${className}AddOrEditVm;
import ${basePackage}.${module}.vm.detail.${className}DetailVm;
import ${basePackage}.${module}.vm.order.${className}OrderVm;
import ${basePackage}.${module}.vm.search.${className}SearchVm;

<#include "/include/java_copyright.ftl">
public interface ${className}Service extends BaseService<${className}AddOrEditVm, ${className}DetailVm, ${className}OrderVm, ${className}SearchVm> {
    <#if table.validStatusColumn??>

    /**
     * 根据条件禁用${tableComment}
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    int disable(${className}SearchVm searchVm);

    /**
     * 根据条件启用${tableComment}
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    int enable(${className}SearchVm searchVm);
    </#if>
    <#if (table.hasPk)>

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
    int update(${className}AddOrEditVm vm<#if !table.hasAutoIncUniPk>, <#include "/include/table/pk_params.ftl"></#if>);

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
    ${className}DetailVm getDetail(<#include "/include/table/pk_params.ftl">);
    </#if>
}
