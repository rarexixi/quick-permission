package ${baseCommonPackage}.model;

import ${baseCommonPackage}.annotation.ExcelCol;
import ${baseCommonPackage}.validation.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

<#include "/include/java_copyright.ftl">
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

    public BaseEntity() {
    }

    public BaseEntity(BaseEntity entity) {
        if (entity == null) return;
        <#list baseColumns as column>
        <#include "/include/column/properties.ftl">
        ${fieldName} = entity.get${propertyName}();
        </#list>
    }

    <#list baseColumns as column>
    <#include "/include/column/properties.ftl">
    <#assign annotationName = ((fieldType == 'String') ? string('NotBlank', 'NotNull'))>

    /**
     * ${columnFullComment}
     */
    <#if (column.pk)>
    @${annotationName}(groups = {<#if column.autoIncrement>DataEdit.class<#else>DataAdd.class</#if>}, message = "${fieldName} (${columnComment})不能为空")
    <#elseif (!column.notRequired && (!column.nullable && !(column.columnDefault??)))>
    @${annotationName}(groups = {DataAdd.class, DataEdit.class}, message = "${fieldName} (${columnComment})不能为空")
    </#if>
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
    protected ${fieldType} ${fieldName};
    </#list>

    protected void setTargetEntity(BaseEntity entity) {
        if (entity == null) return;
        <#list baseColumns as column>
        <#include "/include/column/properties.ftl">
        entity.set${propertyName}(${fieldName});
        </#list>
    }

    protected void setCurrentEntity(BaseEntity entity) {
        if (entity == null) return;
        <#list baseColumns as column>
        <#include "/include/column/properties.ftl">
        ${fieldName} = entity.get${propertyName}();
        </#list>
    }
}
