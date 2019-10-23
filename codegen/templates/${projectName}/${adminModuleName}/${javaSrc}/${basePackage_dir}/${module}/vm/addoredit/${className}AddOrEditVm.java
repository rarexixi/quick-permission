<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.vm.addoredit;

import ${baseCommonPackage}.validation.*;
import ${baseCommonPackage}.model.BaseEntity;
import ${basePackage}.${module}.models.entity.${className}Entity;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

<#include "/include/java_copyright.ftl">
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ${className}AddOrEditVm extends BaseEntity {
    <#list table.columnsExceptBase as column>
    <#include "/include/column/properties.ftl">

    /**
     * ${columnFullComment}
     */
    <#assign annotationName = ((fieldType == 'String') ? string('NotBlank', 'NotNull'))>
    <#if (column.pk)>
    @${annotationName}(groups = {<#if column.autoIncrement>DataEdit.class<#else>DataAdd.class</#if>}, message = "${fieldName} (${columnComment})不能为空")
    <#elseif (!column.notRequired && !column.nullable && !(column.columnDefault??))>
    @${annotationName}(groups = {DataAdd.class, DataEdit.class}, message = "${fieldName} (${columnComment})不能为空")
    </#if>
    private ${fieldType} ${fieldName};
    </#list>

    public ${className}Entity get${className}Entity() {

        ${className}Entity entity = new ${className}Entity();
        <#list table.columnsExceptBase as column>
        <#include "/include/column/properties.ftl">
        entity.set${propertyName}(this.${fieldName});
        </#list>
        super.setTargetEntity(entity);

        return entity;
    }

    public void set${className}Entity(${className}Entity entity) {

        if (entity == null) return;

        <#list table.columnsExceptBase as column>
        <#include "/include/column/properties.ftl">
        this.${fieldName} = entity.get${propertyName}();
        </#list>
        super.setCurrentEntity(entity);
    }
}
