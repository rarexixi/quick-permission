<#include "/include/table/properties.ftl">
package ${basePackage}.${module}.service.impl;

import ${baseCommonPackage}.model.*;
import ${baseCommonPackage}.utils.VoUtils;
import ${basePackage}.${module}.mapper.${className}Mapper;
import ${basePackage}.${module}.models.condition.${className}Condition;
import ${basePackage}.${module}.models.condition.order.${className}OrderCondition;
import ${basePackage}.${module}.models.entity.${className}Entity;
import ${basePackage}.${module}.models.condition.extension.${className}ConditionExtension;
import ${basePackage}.${module}.models.entity.extension.${className}EntityExtension;
import ${basePackage}.${module}.service.${className}Service;
import ${basePackage}.${module}.vm.addoredit.${className}AddOrEditVm;
import ${basePackage}.${module}.vm.detail.${className}DetailVm;
import ${basePackage}.${module}.vm.order.${className}OrderVm;
import ${basePackage}.${module}.vm.search.${className}SearchVm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

<#include "/include/java_copyright.ftl">
@Service("${classNameFirstLower}Service")
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

    @Autowired
    public ${className}ServiceImpl(${className}Mapper ${classNameFirstLower}Mapper) {
        this.${classNameFirstLower}Mapper = ${classNameFirstLower}Mapper;
    }

    private ${className}Mapper ${classNameFirstLower}Mapper;

    /**
     * 添加${tableComment}
     *
     * @param vm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public int add(${className}AddOrEditVm vm) {
        ${className}Entity entity = vm.get${className}Entity();
        int count = ${classNameFirstLower}Mapper.insert(entity);
        vm.set${className}Entity(entity);
        return count;
    }

    /**
     * 根据条件删除${tableComment}
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public int delete(${className}SearchVm searchVm) {
        ${className}Condition condition = searchVm.getCondition();
        return ${classNameFirstLower}Mapper.deleteByCondition(condition);
    }
    <#if table.validStatusColumn??>

    /**
     * 根据条件禁用${tableComment}
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public int disable(${className}SearchVm searchVm) {
        ${className}Condition condition = searchVm.getCondition();
        ${className}Entity entity = new ${className}Entity();
        entity.set${table.validStatusColumn.targetName}(${table.validStatusColumn.validStatusOption.invalid});
        return ${classNameFirstLower}Mapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件启用${tableComment}
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public int enable(${className}SearchVm searchVm) {
        ${className}Condition condition = searchVm.getCondition();
        ${className}Entity entity = new ${className}Entity();
        entity.set${table.validStatusColumn.targetName}(${table.validStatusColumn.validStatusOption.valid});
        return ${classNameFirstLower}Mapper.updateByCondition(entity, condition);
    }
    </#if>

    /**
     * 根据条件获取${tableComment}实体
     *
     * @param searchVm
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    @Transactional(readOnly = true)
    public ${className}DetailVm get(${className}SearchVm searchVm) {
        ${className}Condition condition = searchVm.getCondition();
        ${className}Entity entity = ${classNameFirstLower}Mapper.getByCondition(condition);
        if (entity == null) return null;
        ${className}DetailVm detailVm = new ${className}DetailVm(entity);
        return detailVm;
    }
    <#if (table.hasPk)>

    /**
     * 根据主键更新${tableComment}
     *
     * @param vm
     <#if !table.hasAutoIncUniPk>
     <#list pks as column>
     <#include "/include/column/properties.ftl">
     * @param ${fieldName}
     </#list>
     </#if>
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    public int update(${className}AddOrEditVm vm<#if !table.hasAutoIncUniPk>, <#include "/include/table/pk_params.ftl"></#if>) {
        ${className}Condition condition = new ${className}Condition();
        <#list pks as column>
        <#include "/include/column/properties.ftl">
        condition.set${propertyName}(<#if table.hasAutoIncUniPk>vm.get${propertyName}()<#else>${fieldName}</#if>);
        </#list>
        return ${classNameFirstLower}Mapper.updateByCondition(vm.get${className}Entity(), condition);
    }

    /**
     * 根据主键获取${tableComment}详情
     *
     <#list pks as column>
     <#include "/include/column/properties.ftl">
     * @param ${fieldName}
     </#list>
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    @Transactional(readOnly = true)
    public ${className}DetailVm getDetail(<#include "/include/table/pk_params.ftl">) {
        ${className}EntityExtension entity = ${classNameFirstLower}Mapper.getByPk(<#include "/include/table/pk_values.ftl">);
        if (entity == null) return null;
        ${className}DetailVm vm = new ${className}DetailVm(entity);
        return vm;
    }
    </#if>

    /**
     * 获取${tableComment}列表
     *
     * @param search
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    @Transactional(readOnly = true)
    public List<${className}DetailVm> getList(${className}SearchVm search) {
        if (search == null) return null;
        List<${className}EntityExtension> list = ${classNameFirstLower}Mapper.getExList(search.getConditionExtension(), null);
        List<${className}DetailVm> vmList = list.stream().map(${className}DetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取${tableComment}列表
     *
     * @param search
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    @Transactional(readOnly = true)
    public List<${className}DetailVm> getList(OrderSearch<${className}SearchVm, ${className}OrderVm> search) {
        if (search == null) return null;
        OrderSearch<${className}ConditionExtension, ${className}OrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<${className}EntityExtension> list = ${classNameFirstLower}Mapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<${className}DetailVm> vmList = list.stream().map(${className}DetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询${tableComment}
     *
     * @param searchPage
     * @return
     <#include "/include/author_info1.ftl">
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<${className}DetailVm> getPageInfo(OrderSearchPage<${className}SearchVm, ${className}OrderVm> searchPage) {

        OrderSearch<${className}ConditionExtension, ${className}OrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<${className}EntityExtension> list = ${classNameFirstLower}Mapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<${className}EntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<${className}DetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, ${className}DetailVm::new);
        return pageInfoVo;
    }
}
