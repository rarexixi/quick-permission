
<#if (table.hasUniPk)>
            getSelectedPks() {
                return {
                    ${table.uniPk.targetName?uncap_first}List: this.multipleSelection.map(item => item.${table.uniPk.targetName?uncap_first})
                };
            },
</#if>