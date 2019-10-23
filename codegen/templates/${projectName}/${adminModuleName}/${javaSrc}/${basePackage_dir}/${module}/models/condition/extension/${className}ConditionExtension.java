<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.models.condition.extension;

import ${basePackage}.${module}.models.condition.${className}Condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

<#include "/include/java_copyright.ftl">
@Getter
@Setter
@ToString
public class ${className}ConditionExtension extends ${className}Condition {
}
