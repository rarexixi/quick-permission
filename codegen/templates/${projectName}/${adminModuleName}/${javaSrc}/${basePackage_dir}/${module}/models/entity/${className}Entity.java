<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.models.entity;

import ${baseCommonPackage}.model.BaseEntity;
import ${baseCommonPackage}.validation.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

<#include "/include/java_copyright.ftl">
@Getter
@Setter
@ToString
public class ${className}Entity extends BaseEntity {
    <#list table.columnsExceptBase as column>
    <#include "/include/column/properties.ftl">

    /**
     * ${columnFullComment}
     */
    private ${fieldType} ${fieldName};
    </#list>
}
