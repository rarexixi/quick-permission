
            getPkParams(item) {
                return {
<#list pks as column>
                <#include "/include/column/properties.ftl">
                    ${fieldName}: item.${fieldName}<#if (column?has_next)>,</#if>
</#list>
                };
            },
