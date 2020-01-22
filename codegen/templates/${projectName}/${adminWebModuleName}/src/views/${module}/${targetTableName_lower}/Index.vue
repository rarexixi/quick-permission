<#include "/include/table/properties.ftl">
<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item>${tableComment}管理</el-breadcrumb-item>
            <el-breadcrumb-item>${tableComment}列表</el-breadcrumb-item>
        </el-breadcrumb>
        <#include "/include/html/search.ftl">
        <div class="operate-btn-group">
            <el-button @click="add" v-permission="'${module}:${tablePath}:add'" type="primary" icon="el-icon-plus" size="small">添加</el-button>
            <template v-if="multipleSelection.length > 0">
                <#if table.validStatusColumn??>
                <el-button @click="enableSelected" v-permission="'${module}:${tablePath}:enable'" type="success" icon="el-icon-check" size="small">启用</el-button>
                <el-button @click="disableSelected" v-permission="'${module}:${tablePath}:disable'" v-if="multipleSelection.length > 0" type="warning" icon="el-icon-close" size="small">禁用</el-button>
                </#if>
                <#if table.hasUniPk>
                <el-button @click="delSelected" v-permission="'${module}:${tablePath}:del'" v-if="multipleSelection.length > 0" type="danger" icon="el-icon-delete" size="small">删除</el-button>
                </#if>
            </template>
        </div>
        <page-table :data-list="pageInfo.list || []" :properties="columns" @sort-change="handleSortChange"<#if (table.hasUniPk)> @selection-change="handleSelectionChange"</#if>
                    @page-size-change="changePageSize" @page-index-change="changePage" :selectable="<#if (table.hasUniPk)>true<#else>false</#if>"
                    :total="pageInfo.total || 0" :current-page="pageInfo.pageNum || 0" :page-size="pageInfo.pageSize || 0">
            <#if table.validStatusColumn??>
            <el-table-column v-permission="['${module}:${tablePath}:enable', '${module}:${tablePath}:disable']" fixed="right" label="是否有效" width="60">
                <el-switch slot-scope="scope" v-model="scope.row.${table.validStatusColumn.targetName?uncap_first}" :active-value="${table.validStatusColumn.validStatusOption.valid}" :inactive-value="${table.validStatusColumn.validStatusOption.invalid}" @change="scope.row.${table.validStatusColumn.targetName?uncap_first} == ${table.validStatusColumn.validStatusOption.invalid} ? disable(scope.row) : enable(scope.row)"></el-switch>
            </el-table-column>
            </#if>
            <el-table-column fixed="right" label="操作" width="140">
                <template slot-scope="scope">
                    <el-link @click="edit(scope.row, false)" v-permission="'${module}:${tablePath}:update'" type="primary" icon="el-icon-edit" size="mini">编辑</el-link>
                    <el-link @click="edit(scope.row, true)" v-permission="'${module}:${tablePath}:add'" type="primary" icon="el-icon-document-copy" size="mini">复制</el-link>
                    <el-link @click="get(scope.row)" type="info" icon="el-icon-more" size="mini" circle>详情</el-link>
                    <el-link @click="del(scope.row)" v-permission="'${module}:${tablePath}:del'" type="danger" icon="el-icon-delete" size="mini">删除</el-link>
                </template>
            </el-table-column>
        </page-table>
        <detail-dialog :detail="detail" title="${tableComment}详情" :properties="detailProperties" :visible.sync="detailDialogVisible"
                       :editable="hasPermission('meta:meta-model-theme-group:update')"
                       :deletable="hasPermission('meta:meta-model-theme-group:del')"
                       @close="closeDialog" @edit="edit(detail)" @del="del(detail)" />
        <${tablePath}-add-or-edit :detail="addOrEditParams" :title="addOrEditTitle" :visible.sync="addOrEditDialogVisible" @save="save" @close="closeDialog" />
    </div>
</template>

<script>
    import request_utils from "@/utils/request_utils";
    import datetime_utils from "@/utils/datetime_utils";
    import {mapGetters} from 'vuex';
    import MixinIndex from "@/minix/index";

    import ${className}AddOrEdit from "@/views/${module}/${targetTableName}/AddOrEdit";

    export default {
        name: "${className}",
        components: {${className}AddOrEdit},
        mixins: [MixinIndex],
        data() {
            let self = this;
            return {
                columns: [
                <#list table.columns as column>
                <#include "/include/column/properties.ftl">
                <#if (isContent)>
                <#else>
                    <#if column.validStatus>
                    {name: "${fieldName}", label: "${columnComment}"<#if (column.index)>, sortable: true</#if>, render: detail => self.deletedMap[detail.${fieldName}] || ""},
                    <#elseif (column.select || column.fkSelect)>
                    {name: "${fieldName}", label: "${columnComment}"<#if (column.index)>, sortable: true</#if>, render: detail => self.get${propertyExceptKey}Text(detail.${fieldName})},
                    <#elseif column.imgUrl>
                    {name: "${fieldName}", label: "${columnComment}"<#if (column.index)>, sortable: true</#if>, img: true},
                    <#elseif (isDate)>
                    {name: "${fieldName}", label: "${columnComment}"<#if (column.index)>, sortable: true</#if>, render: detail => datetime_utils.formatDate(detail.${fieldName})},
                    <#elseif (isTime)>
                    {name: "${fieldName}", label: "${columnComment}"<#if (column.index)>, sortable: true</#if>, render: detail => datetime_utils.formatTime(detail.${fieldName})},
                    <#elseif (isDateTime)>
                    {name: "${fieldName}", label: "${columnComment}"<#if (column.index)>, sortable: true</#if>, render: detail => datetime_utils.formatDatetime(detail.${fieldName})},
                    <#else>
                    {name: "${fieldName}", label: "${columnComment}"<#if (column.index)>, sortable: true</#if>},
                    </#if>
                </#if>
                </#list>
                ],
                detailProperties: [
                <#list table.columns as column>
                <#include "/include/column/properties.ftl">
                    <#if (column.validStatus)>
                    {name: "${fieldName}", label: "是否删除", render: detail => self.deletedMap[detail.isDelete] || ""},
                    <#elseif (column.select || column.fkSelect)>
                    {name: "${fieldName}", label: "${columnComment}", render: detail => self.get${propertyExceptKey}Text(detail.${fieldName})},
                    <#elseif column.imgUrl>
                    {name: "${fieldName}", label: "${columnComment}", img: true},
                    <#elseif column.content>
                    {name: "${fieldName}", label: "${columnComment}", pre: true},
                    <#elseif (isDate)>
                    {name: "${fieldName}", label: "${columnComment}", render: detail => datetime_utils.formatDate(detail.${fieldName})},
                    <#elseif (isTime)>
                    {name: "${fieldName}", label: "${columnComment}", render: detail => datetime_utils.formatTime(detail.${fieldName})},
                    <#elseif (isDateTime)>
                    {name: "${fieldName}", label: "${columnComment}", render: detail => datetime_utils.formatDatetime(detail.${fieldName})},
                    <#else>
                    {name: "${fieldName}", label: "${columnComment}"},
                    </#if>
                </#list>
                ],
                <#include "/include/js/data_select_list.ftl">
                <#list table.fkSelectColumns as column>
                <#include "/include/column/properties.ftl">
                ${fieldNameExceptKey}SelectList: [],
                </#list>
                pkParams: {
                    <#list pks as column>
                    <#include "/include/column/properties.ftl">
                    ${fieldName}: '',
                    </#list>
                },
                copy: false,
                <#include "/include/js/search_params_data.ftl">
                addOrEditParams: self.getDefaultAddOrEditParams(),
                detail: {},
                addOrEditTitle: '',
                detailDialogVisible: false,
                addOrEditDialogVisible: false,
                apis: {
                    pageListUrl: '/${tablePath}/page-list',
                    detailUrl: '/${tablePath}/detail',
                    addUrl: '/${tablePath}/add',
                    updateUrl: '/${tablePath}/update',
                    <#if table.validStatusColumn??>
                    enableUrl: '/${tablePath}/enable',
                    disableUrl: '/${tablePath}/disable',
                    </#if>
                    deleteUrl: '/${tablePath}/delete',
                    exportUrl: '/${tablePath}/export'
                }
            }
        },
        computed: {
            ...mapGetters(['hasPermission'])
        },
        mounted() {
            let self = this;
            self.search();
            <#list table.fkSelectColumns as column>
            <#include "/include/column/properties.ftl">
            self.init${propertyExceptKey}SelectList();
            </#list>
        },
        methods: {
            <#include "/include/js/search.ftl">
            add() {
                let self = this;
                self.resetSave();
                self.addOrEditDialogVisible = true;
                self.addOrEditTitle = '添加${tableComment}';
            },
            edit(item, copy) {
                let self = this;
                self.resetSave();
                self.addOrEditDialogVisible = true;
                self.addOrEditTitle = copy ? '添加${tableComment}' : '编辑${tableComment}';
                <#list pks as column>
                <#include "/include/column/properties.ftl">
                self.pkParams = self.getPkParams(item);
                </#list>
                self.copy = copy;

                request_utils.get(self.apis.detailUrl, {
                    params: self.pkParams
                }).then(response => {
                    <#list table.columns as column>
                    <#include "/include/column/properties.ftl">
                    <#if column.notRequired>
                    <#elseif column.autoIncrement>
                    if (!copy) {
                        self.addOrEditParams.${fieldName} = response.data.${fieldName};
                    }
                    <#else>
                    self.addOrEditParams.${fieldName} = response.data.${fieldName};
                    </#if>
                    </#list>
                });
            },
            save(addOrEditParams) {
                let self = this;
                let url;
                if (self.copy || (<#list pks as column><#include "/include/column/properties.ftl"><#if (column_index > 0)> && </#if>self.pkParams.${fieldName} == ''</#list>)) {
                    url = self.apis.addUrl;
                } else {
                    <#if table.hasAutoIncUniPk>
                    url = self.apis.updateUrl;
                    <#else>
                    url = self.apis.updateUrl + '?'<#list pks as column><#include "/include/column/properties.ftl"><#if (column_index > 0)> + '&'</#if> + '${fieldName}=' + self.pkParams.${fieldName}</#list>;
                    </#if>
                }

                request_utils.post(url, addOrEditParams).then(response => {
                    self.$notify({
                        message: '操作成功！',
                        type: 'success'
                    });
                    self.resetSave();
                    setTimeout(self.search, 1000);
                });
            },
            resetSave() {
                let self = this;
                <#list pks as column>
                <#include "/include/column/properties.ftl">
                self.pkParams.${fieldName} = '';
                </#list>
                self.addOrEditParams = self.getDefaultAddOrEditParams();
                self.closeDialog();
            },
            getDefaultAddOrEditParams() {
                let addOrEditParams = {};
                <#list table.requiredColumns as column>
                <#include "/include/column/properties.ftl">
                <#if (column.validStatus)>
                <#-- addOrEditParams.${fieldName} = ${table.validStatusColumn.validStatusOption.valid}; -->
                <#else>
                addOrEditParams.${fieldName} = '';
                </#if>
                </#list>
                return addOrEditParams;
            },
            get(item) {
                let self = this;
                self.detailDialogVisible = true;
                request_utils.get(self.apis.detailUrl, {
                    params: self.getPkParams(item)
                }).then(response => {
                    self.detail = response.data;
                });
            },
            closeDialog() {
                let self = this;
                self.detailDialogVisible = false;
                self.addOrEditDialogVisible = false;
            },
            getPkParams(item) {
                return {
                    <#list pks as column>
                    <#include "/include/column/properties.ftl">
                    ${fieldName}: item.${fieldName}<#if (column?has_next)>,</#if>
                    </#list>
                };
            },
            <#if (table.hasUniPk)>
            getSelectedPks() {
                return {
                    ${table.uniPk.targetName?uncap_first}List: this.multipleSelection.map(item => item.${table.uniPk.targetName?uncap_first})
                };
            },
            </#if>
            <#include "/include/js/select_get_text.ftl">
            <#include "/include/js/init_fk_list.ftl">
        }
    }
</script>

<style scoped>

</style>