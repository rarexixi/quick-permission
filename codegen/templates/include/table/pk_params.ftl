<#list pks as column><#include "/include/column/properties.ftl"><#if (column_index > 0)>, </#if>${fieldType} ${fieldName}</#list>