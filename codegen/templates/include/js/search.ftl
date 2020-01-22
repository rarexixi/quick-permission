            getDefaultSortParams() {
                return {
<#if table.hasAutoIncUniPk>
                <#list pks as column>
                    <#include "/include/column/properties.ftl">
                    ${fieldName}Sort: this.SortEnum.DESC
                </#list>
</#if>
                };
            },
<#if table.validStatusColumn??>
            changeValidSearch(valid) {
                let self = this;
                if (self.searchParams.${table.validStatusColumn.targetName?uncap_first} === valid) {
                    return;
                }
                self.resetSearch();
                self.searchParams.${table.validStatusColumn.targetName?uncap_first} = valid;
                self.search();
            },
</#if>