<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.vm.detail;

import ${baseCommonPackage}.annotation.ExcelCol;
import ${baseCommonPackage}.model.BaseEntity;

import ${basePackage}.${module}.models.entity.${className}Entity;
import ${basePackage}.${module}.models.entity.extension.${className}EntityExtension;

import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

<#include "/include/java_copyright.ftl">
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ${className}DetailVm extends BaseEntity {

    public ${className}DetailVm(${className}EntityExtension entity) {
        super(entity);

        if (entity == null) return;
        <#list table.columnsExceptBase as column>
        <#include "/include/column/properties.ftl">
        ${fieldName} = entity.get${propertyName}();
        <#if (column.fkSelect)>
        ${fieldNameExceptKey}Text = entity.get${propertyExceptKey}Text();
        </#if>
        </#list>
    }

    public ${className}DetailVm(${className}Entity entity) {
        super(entity);

        if (entity == null) return;
        <#list table.columnsExceptBase as column>
        <#include "/include/column/properties.ftl">
        ${fieldName} = entity.get${propertyName}();
        </#list>
    }
    <#list table.columnsExceptBase as column>
    <#include "/include/column/properties.ftl">

    /**
     * ${columnFullComment}
     */
    <#if (isDecimal)>
    @ExcelCol(value = "${columnComment}", formatter = "%.2f%%")
    <#elseif (isDate)>
    @ExcelCol(value = "${columnComment}", formatter = "yyyy-MM-dd")
    <#elseif (isTime)>
    @ExcelCol(value = "${columnComment}", formatter = "HH:mm")
    <#elseif (isDateTime)>
    @ExcelCol(value = "${columnComment}", formatter = "yyyy-MM-dd HH:mm")
    <#else>
    @ExcelCol("${columnComment}")
    </#if>
    private ${fieldType} ${fieldName};
    <#if (column.fkSelect)>

    private String ${fieldNameExceptKey}Text;
    </#if>
    </#list>
}
