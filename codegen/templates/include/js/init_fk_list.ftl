<#list table.fkSelectColumns as column>
            <#include "/include/column/properties.ftl">
            get${propertyExceptKey}(${column.fkSelectColumn.valueName?uncap_first}) {
                return this.${fieldNameExceptKey}SelectList.find(item => item.${column.fkSelectColumn.valueName?uncap_first} == ${column.fkSelectColumn.valueName?uncap_first});
            },
            get${propertyExceptKey}Text(${column.fkSelectColumn.valueName?uncap_first}) {
                let entity = this.get${propertyExceptKey}(${column.fkSelectColumn.valueName?uncap_first});
                return entity ? entity.${column.fkSelectColumn.textName?uncap_first} : '';
            },
</#list>
<#list table.fkSelectColumns as column>
            <#include "/include/column/properties.ftl">
            init${propertyExceptKey}SelectList() {
                let self = this;
                let url = '/${column.fkSelectColumn.foreignTargetTableName?replace("_", "-")}/list';
                let params = {
                    condition: {
                        <#list column.fkSelectColumn.conditions as condition>
                        ${condition.fieldTargetName?uncap_first}: '${condition.value}'<#if condition?has_next>,</#if>
                        </#list>
                    },
                    order: {}
                };
                request_utils.post(url, params).then(response => {
                    self.${fieldNameExceptKey}SelectList = response.data;
                });
            },
</#list>
