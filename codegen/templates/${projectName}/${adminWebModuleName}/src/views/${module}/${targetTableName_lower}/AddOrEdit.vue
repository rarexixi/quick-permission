<#include "/include/table/properties.ftl">
<template>
    <el-dialog :title="title" :visible="visible" @close="close">
        <el-form ref="addOrEditForm" :model="detail" label-position="left" label-width="120px" size="small">
            <el-row :gutter="10">
            <#list table.columns as column>
                <#include "/include/column/properties.ftl">
                <#if column.notRequired>
                <#elseif column.autoIncrement>
                <#elseif (column.select)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-select v-model="detail.${fieldName}" filterable placeholder="请选择">
                            <el-option v-for="(item, index) in ${fieldNameExceptKey}SelectList" :key="index" :value="item.value" :label="item.text"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <#elseif (column.fkSelect)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-select v-model="detail.${fieldName}" filterable placeholder="请选择">
                            <el-option v-for="(item, index) in ${fieldNameExceptKey}SelectList" :key="index" :value="item.${column.fkSelectColumn.valueName?uncap_first}" :label="item.${column.fkSelectColumn.textName?uncap_first}"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <#elseif (column.validStatus)>
                <#elseif (isInteger)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-input v-model="detail.${fieldName}"></el-input>
                    </el-form-item>
                </el-col>
                <#elseif (isDecimal)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-input v-model="detail.${fieldName}"></el-input>
                    </el-form-item>
                </el-col>
                <#elseif (isDate)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-date-picker v-model="detail.${fieldName}" type="date" placeholder="选择日期"></el-date-picker>
                    </el-form-item>
                </el-col>
                <#elseif (isTime)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-time-select v-model="detail.${fieldName}" placeholder="选择时间"></el-time-select>
                    </el-form-item>
                </el-col>
                <#elseif (isDateTime)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-date-picker v-model="detail.${fieldName}" type="datetime" placeholder="选择日期时间"></el-date-picker>
                    </el-form-item>
                </el-col>
                <#elseif (isContent)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-input v-model="detail.${fieldName}" type="textarea" :autosize="{ minRows: 5, maxRows: 100}"></el-input>
                    </el-form-item>
                </el-col>
                <#elseif (isString)>
                <el-col :span="24">
                    <el-form-item label="${columnComment}" prop="${fieldName}">
                        <el-input v-model="detail.${fieldName}" type="text"></el-input>
                    </el-form-item>
                </el-col>
                <#else>
                </#if>
            </#list>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="save" type="primary" icon="el-icon-document" size="small">保存</el-button>
            <el-button @click="reset" type="warning" icon="el-icon-refresh" size="small">重置</el-button>
            <el-button @click="close" icon="el-icon-close" size="small">取消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import request_utils from "@/utils/request_utils";
    import datetime_utils from "@/utils/datetime_utils";

    export default {
        name: "${className}AddOrEdit",
        props: {
            title: {
                type: String,
                default: ""
            },
            detail: {
                type: [Object, Map],
                required: true
            },
            visible: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                <#list table.fkSelectColumns as column>
                <#include "/include/column/properties.ftl">
                ${fieldNameExceptKey}SelectList: [],
                </#list>
            };
        },
        mounted() {
            let self = this;
            <#list table.fkSelectColumns as column>
            <#include "/include/column/properties.ftl">
            self.init${propertyExceptKey}SelectList();
            </#list>
        },
        methods: {
            <#include "/include/js/init_fk_list.ftl">
            close() {
                this.$emit("close");
            },
            save() {
                this.$emit("save", this.detail);
            },
            reset() {
                this.$refs['addOrEditForm'].resetFields();
            }
        }
    }
</script>

<style scoped>

</style>