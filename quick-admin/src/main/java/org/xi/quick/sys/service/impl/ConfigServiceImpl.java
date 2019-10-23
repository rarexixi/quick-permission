package org.xi.quick.sys.service.impl;

import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.ConfigMapper;
import org.xi.quick.sys.models.condition.ConfigCondition;
import org.xi.quick.sys.models.condition.order.ConfigOrderCondition;
import org.xi.quick.sys.models.entity.ConfigEntity;
import org.xi.quick.sys.models.condition.extension.ConfigConditionExtension;
import org.xi.quick.sys.models.entity.extension.ConfigEntityExtension;
import org.xi.quick.sys.service.ConfigService;
import org.xi.quick.sys.vm.addoredit.ConfigAddOrEditVm;
import org.xi.quick.sys.vm.detail.ConfigDetailVm;
import org.xi.quick.sys.vm.order.ConfigOrderVm;
import org.xi.quick.sys.vm.search.ConfigSearchVm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.model.PageInfoVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统配置
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("configService")
@Transactional
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    public ConfigServiceImpl(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    private ConfigMapper configMapper;

    /**
     * 添加系统配置
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(ConfigAddOrEditVm vm) {
        ConfigEntity entity = vm.getConfigEntity();
        int count = configMapper.insert(entity);
        vm.setConfigEntity(entity);
        return count;
    }

    /**
     * 根据条件删除系统配置
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(ConfigSearchVm searchVm) {
        ConfigCondition condition = searchVm.getCondition();
        return configMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件禁用系统配置
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int disable(ConfigSearchVm searchVm) {
        ConfigCondition condition = searchVm.getCondition();
        ConfigEntity entity = new ConfigEntity();
        entity.setDeleted(1);
        return configMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件启用系统配置
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int enable(ConfigSearchVm searchVm) {
        ConfigCondition condition = searchVm.getCondition();
        ConfigEntity entity = new ConfigEntity();
        entity.setDeleted(0);
        return configMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件获取系统配置实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public ConfigDetailVm get(ConfigSearchVm searchVm) {
        ConfigCondition condition = searchVm.getCondition();
        ConfigEntity entity = configMapper.getByCondition(condition);
        if (entity == null) return null;
        ConfigDetailVm detailVm = new ConfigDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键更新系统配置
     *
     * @param vm
     * @param key
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int update(ConfigAddOrEditVm vm, String key) {
        ConfigCondition condition = new ConfigCondition();
        condition.setKey(key);
        return configMapper.updateByCondition(vm.getConfigEntity(), condition);
    }

    /**
     * 根据主键获取系统配置详情
     *
     * @param key
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public ConfigDetailVm getDetail(String key) {
        ConfigEntityExtension entity = configMapper.getByPk(key);
        if (entity == null) return null;
        ConfigDetailVm vm = new ConfigDetailVm(entity);
        return vm;
    }

    /**
     * 获取系统配置列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConfigDetailVm> getList(ConfigSearchVm search) {
        if (search == null) return null;
        List<ConfigEntityExtension> list = configMapper.getExList(search.getConditionExtension(), null);
        List<ConfigDetailVm> vmList = list.stream().map(ConfigDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取系统配置列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConfigDetailVm> getList(OrderSearch<ConfigSearchVm, ConfigOrderVm> search) {
        if (search == null) return null;
        OrderSearch<ConfigConditionExtension, ConfigOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<ConfigEntityExtension> list = configMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<ConfigDetailVm> vmList = list.stream().map(ConfigDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询系统配置
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<ConfigDetailVm> getPageInfo(OrderSearchPage<ConfigSearchVm, ConfigOrderVm> searchPage) {

        OrderSearch<ConfigConditionExtension, ConfigOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<ConfigEntityExtension> list = configMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<ConfigEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<ConfigDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, ConfigDetailVm::new);
        return pageInfoVo;
    }
}
