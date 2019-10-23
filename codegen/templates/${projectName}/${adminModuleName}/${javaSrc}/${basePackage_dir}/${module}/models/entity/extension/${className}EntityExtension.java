<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.models.entity.extension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ${basePackage}.${module}.models.entity.${className}Entity;

<#include "/include/java_copyright.ftl">
@Getter
@Setter
@ToString
public class ${className}EntityExtension extends ${className}Entity {
    <#list table.fkSelectColumns as column>
    <#include "/include/column/properties.ftl">

    /**
     * ${columnFullComment}
     */
    private String ${fieldNameExceptKey}Text;
    </#list>
}
