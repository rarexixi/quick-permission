<#list table.selectColumns as column>
            <#include "/include/column/properties.ftl">
            get${propertyName}Text: function (value) {
                let self = this;
                let entity = self.${fieldNameExceptKey}SelectList.find(item => item.value == value);
                return entity ? entity.text : '';
            },
</#list>