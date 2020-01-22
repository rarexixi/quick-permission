        <el-form ref="addOrEditForm" :model="detail" label-position="left" label-width="120px" size="small">
<#list table.columns as column>
            <#include "/include/column/properties.ftl">
            <#if column.notRequired>
            <#elseif column.autoIncrement>
            <#elseif (column.select)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-select v-model="detail.${fieldName}" filterable placeholder="请选择">
                    <el-option v-for="item in ${fieldNameExceptKey}SelectList" :value="item.value" :label="item.text"></el-option>
                </el-select>
            </el-form-item>
            <#elseif (column.fkSelect)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-select v-model="detail.${fieldName}" filterable placeholder="请选择">
                    <el-option v-for="item in ${fieldNameExceptKey}SelectList" :value="item.${column.fkSelectColumn.valueName?uncap_first}" :label="item.${column.fkSelectColumn.textName?uncap_first}"></el-option>
                </el-select>
            </el-form-item>
            <#elseif (column.validStatus)>
            <#-- <el-form-item label="${columnComment}">
                <el-select v-model="detail.${fieldName}" placeholder="请选择">
                    <el-option v-for="item in ${fieldName}SelectList" :value="item.value" :label="item.text"></el-option>
                </el-select>
            </el-form-item> -->
            <#elseif (isInteger)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-input v-model="detail.${fieldName}"></el-input>
            </el-form-item>
            <#elseif (isDecimal)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-input v-model="detail.${fieldName}"></el-input>
            </el-form-item>
            <#elseif (isDate)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-date-picker v-model="detail.${fieldName}" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
            <#elseif (isTime)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-time-select v-model="detail.${fieldName}" placeholder="选择时间"></el-time-select>
            </el-form-item>
            <#elseif (isDateTime)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-date-picker v-model="detail.${fieldName}" type="datetime" placeholder="选择日期时间"></el-date-picker>
            </el-form-item>
            <#elseif (isContent)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-input v-model="detail.${fieldName}" type="textarea" :autosize="{ minRows: 5, maxRows: 100}"></el-input>
            </el-form-item>
            <#elseif (isString)>
            <el-form-item label="${columnComment}" prop="${fieldName}">
                <el-input v-model="detail.${fieldName}" type="text"></el-input>
            </el-form-item>
            <#else>
            </#if>
</#list>
        </el-form>
