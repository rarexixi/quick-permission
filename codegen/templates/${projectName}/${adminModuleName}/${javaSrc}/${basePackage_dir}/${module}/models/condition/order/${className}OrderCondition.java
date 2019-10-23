<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.models.condition.order;

import ${baseCommonPackage}.constant.SortConstants;
import ${baseCommonPackage}.model.OrderCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

<#include "/include/java_copyright.ftl">
@Getter
@Setter
@ToString
public class ${className}OrderCondition implements OrderCondition {
    <#list table.columns as column>
    <#include "/include/column/properties.ftl">
    <#if (column.ignoreSearch || isContent)>
    <#else>

    /**
     * 以${columnComment}排序
     */
    public SortConstants ${fieldName}Sort;
    </#if>
    </#list>
}
